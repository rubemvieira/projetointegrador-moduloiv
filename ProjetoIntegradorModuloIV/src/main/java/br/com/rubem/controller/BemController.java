package br.com.rubem.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.rubem.dao.BemDAO;
import br.com.rubem.model.Bem;
import br.com.rubem.model.Depreciacao;
import br.com.rubem.util.BemComparator;

public class BemController {
	
	
	private BarChartModel model;

	public List<Bem> retrieveAll() {
		return new BemDAO().retrieveAll();
	}
	
	public List<Bem> retrieveBaixadoAll() {
		return new BemDAO().retrieveBaixadoAll();
	}	

	public void gravar(Bem bem) {
		if (bem.getId() == null)
			new BemDAO().insert(bem);
		else
			new BemDAO().update(bem);
	}
	
	public void deletar(Bem bem) {
		new BemDAO().delete(bem);
	}
	
	public Bem retrieve(Bem bem) {
		Depreciacao depreciacao = new BemController().calcular(bem, null);
		bem.setDepreciacao(depreciacao);
		return bem;
	}
	
	public Depreciacao calcular(Bem bem, Date datareferencia) {

		Depreciacao retorno = new Depreciacao();
		Map<Integer, Integer> hashPeriodo = new HashMap<>();
		List<Map.Entry<Integer, Integer>>  lstPeriodo = new ArrayList<>();
		
		// 1º Vamos setar os atributos fixos do retorno
		retorno.setCustobem(bem.getValor_aquisicao());
		retorno.setDatavenda(bem.getData_venda());
		retorno.setValorvenda(bem.getValor_venda());
		retorno.setTotal(false);
		retorno.setValorresidual(
				bem.getValor_residual_tipo() == 'P' ?
						bem.getValor_aquisicao() * (bem.getValor_residual() / 100.00) :
						bem.getValor_residual()
				);
		
		
		// Regra de contagem do período
			// 1ª Regra se dia da data de compra for menor ou igual a 15 conta o mês
			// 2ª Regra se dia da venda for maior que 15 conta o mês
		// Regra do turno
			// 1 turno mutiplica por 1
			// 2 turnos mutiplica por 1,5
			// 3 turnos mutiplica por 2
		// Depreciação total
			// Se período for maior que vida útil
		
		// Bem Usado
		
		// Vamos começar verificando o período
		Integer quantidademes = 0;
		Integer anofinal = 0;
		Integer anoinicial = 0;
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		
		if(datareferencia != null ) {
			calendar.setTime(datareferencia);
		} else {
			if(retorno.getDatavenda() != null) {
				calendar.setTime(retorno.getDatavenda());
			} 
		}

		//Date data_final = calendar.getTime();
		if(calendar.get(java.util.Calendar.DAY_OF_MONTH) > 15) {
			quantidademes += calendar.get(java.util.Calendar.MONTH) + 1;
		} else {
			quantidademes += calendar.get(java.util.Calendar.MONTH);
		}
		
		hashPeriodo.put(calendar.get(java.util.Calendar.YEAR), quantidademes);
		anofinal = calendar.get(java.util.Calendar.YEAR) - 1;
		
		//retorno.setDatavenda(data_final);
		calendar.setTime(bem.getData_aquisicao());

		// vamos verificar o dia da data inicial
		if(calendar.get(java.util.Calendar.DAY_OF_MONTH) <= 15) {
			hashPeriodo.put(calendar.get(java.util.Calendar.YEAR), (12 - calendar.get(java.util.Calendar.MONTH)));
			quantidademes += (12 - calendar.get(java.util.Calendar.MONTH));
			calendar.get(java.util.Calendar.DAY_OF_MONTH);
		} else {
			hashPeriodo.put(calendar.get(java.util.Calendar.YEAR), (11 - calendar.get(java.util.Calendar.MONTH)));
			quantidademes += (11 - calendar.get(java.util.Calendar.MONTH));
		}
		
		anoinicial = calendar.get(java.util.Calendar.YEAR) + 1;
		
		for(int i = 0; i <= (anofinal - anoinicial); i++) {
			hashPeriodo.put(anoinicial + i, 12);
			quantidademes += 12;
		}
		
		// Agora temos o período
		// Vamos verificar se houve depreciação total
		// A taxa que determina a quantidade de anos de depreciação

		// Neste caso temos de calcular a depreciaçao
		// Vida Útil
		retorno.setMetadevidautil(false);
		Double vida_util_estimada = bem.getVida_Util().doubleValue();
		if(bem.isUsado()) {
			Double metade_vida_util = vida_util_estimada / 2.00;
			Double restante_vida_util = vida_util_estimada - bem.getTempo_uso().doubleValue();
			vida_util_estimada = metade_vida_util > restante_vida_util ? metade_vida_util : restante_vida_util;
			retorno.setMetadevidautil(metade_vida_util > restante_vida_util ? true : false);
		}

		// Já temos o perído vamos achar a taxa
		Double taxa = 100.00 / vida_util_estimada;
		taxa = taxa * bem.getTurno_trabalho();
		retorno.setTaxa(taxa);
		retorno.setVidautilestimada(vida_util_estimada);
		
		Double depreciacao_acumulada;
	
		if((vida_util_estimada * 12) <= quantidademes) {
			// Depreciacao total
			depreciacao_acumulada = retorno.getCustobem() - retorno.getValorresidual();
			retorno.setTotal(true);
		} else {
			depreciacao_acumulada = (((retorno.getCustobem() - retorno.getValorresidual()) * (taxa / 100.00)) / 12) * quantidademes;
		} 

		Double valor_comercial = retorno.getCustobem() - depreciacao_acumulada;
		retorno.setAcumulado(round(depreciacao_acumulada, 2));
		retorno.setValorcomercial(round(valor_comercial, 2));
		
		// Neste caso o bem foi vendido vamos calcular o Ganho/Perda
		if(retorno.getDatavenda() != null) {
			retorno.setGanhoperda(round(retorno.getValorvenda() - retorno.getValorcomercial(), 2));
		}
		
		
		// Vamos retornar a lista de períodos
		lstPeriodo.clear();
		for(int i = (anoinicial -1); i <= (anofinal + 1); i++) {
			lstPeriodo.add(new java.util.AbstractMap.SimpleEntry<>(
					i,
					hashPeriodo.get(i)
					));
		}

		retorno.setPeriodo(quantidademes.doubleValue());
		retorno.setLstPeriodo(lstPeriodo);

		return retorno;
	}

	public List<Bem> retrieveTop5Ganho() {
		
		List<Bem> bens = new BemDAO().retrieveBaixadoAll();
		List<Bem> bens_sort = new ArrayList<>();
		List<Bem> bens_return = new ArrayList<>();
		
		// Vamos preencher nova lista com o resultado da depreciacao
		for(Bem b : bens) {
			b.setDepreciacao(calcular(b, null));
			if(b.getDepreciacao().getGanhoperda() > 0) {
				bens_sort.add(b);
			}
		}
		// Agora vamos ordernar a lista
		bens_sort.sort(new BemComparator());
		Collections.reverse(bens_sort);
		// Vamos remover os demais itens
		for(int i = 0; i < ( bens_sort.size() > 5 ? 5 : bens_sort.size() ) ; i++) {
			bens_return.add(bens_sort.get(i));
			
		}

		return bens_return;
	}

	public List<Bem> retrieveTop5Perda() {
		List<Bem> bens = new BemDAO().retrieveBaixadoAll();
		List<Bem> bens_sort = new ArrayList<>();
		List<Bem> bens_return = new ArrayList<>();
		
		// Vamos preencher nova lista com o resultado da depreciacao
		for(Bem b : bens) {
			b.setDepreciacao(calcular(b, null));
			if(b.getDepreciacao().getGanhoperda() < 0) {
				bens_sort.add(b);
			}
		}
		// Agora vamos ordernar a lista
		bens_sort.sort(new BemComparator());
		//Collections.reverse(bens_sort);
		// Vamos remover os demais itens
		for(int i = 0; i < ( bens_sort.size() > 5 ? 5 : bens_sort.size() ) ; i++) {
			bens_return.add(bens_sort.get(i));
		}

		return bens_return;
	}

	public BarChartModel retrieve5Anos() {
		
		
		model = new BarChartModel();

		List<Bem> bens = new BemDAO().retrieveAll();

		java.util.Calendar calendar = java.util.Calendar.getInstance();
		
		ChartSeries dado;
		int quantidade = 0;

		for(int i = 0; i < 5; i++) {
			dado = new ChartSeries();
			quantidade = 0;
			calendar.add(java.util.Calendar.YEAR, i);
			for(Bem b : bens) {
				b.setDepreciacao(calcular(b, calendar.getTime() ));
				if(b.getDepreciacao().isTotal()) {
					quantidade++;
				}
			}
			dado.setLabel(String.valueOf(calendar.get(java.util.Calendar.YEAR)));
			dado.set(String.valueOf(calendar.get(java.util.Calendar.YEAR)), quantidade);
			model.addSeries(dado);
			dado = null;
		}
		
		return this.model;  

	}

	public BarChartModel getModel() {

		this.model = retrieve5Anos();
		return this.model;
	}

	public void setModel(BarChartModel model) {
		this.model = model;
	}

	private double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}	
	
}

