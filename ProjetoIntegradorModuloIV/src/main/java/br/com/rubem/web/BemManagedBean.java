package br.com.rubem.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import br.com.rubem.controller.BemController;
import br.com.rubem.model.Bem;
import br.com.rubem.model.Depreciacao;

@ManagedBean(name = "bembean")
@SessionScoped
public class BemManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object value; // +getter+setter.
	private Long vida_util_padrao;
	private Date data_referencia;
	
	@ManagedProperty(value = "#{simulacao_efetuada}")
	private boolean simulacao_efetuada = false;
	
	private Bem bem = new Bem();
	
	public List<Bem> getBens(){
		return new BemController().retrieveAll();
	}

	public List<Bem> getBensBaixados(){
		return new BemController().retrieveBaixadoAll();
	}
	
	public String actionApagar() {
		new BemController().deletar(bem);
		return "bens";
	}
	
	public String actionNovo() {
		this.bem = new Bem();
		this.bem.setTurno_trabalho(Double.valueOf("0"));
		this.simulacao_efetuada = false;
		return "formulario_bem";
	}
	
	public String actionAlterar() {
		this.bem = new BemController().retrieve(this.bem);
		this.simulacao_efetuada = false;
		return "formulario_bem";
	}
	
	public String actionGravar() {
		new BemController().gravar(this.bem);
		if(this.bem.isBaixado()) {
			return "bensbaixados";			
		} else {
			return "bens";			
		}

	}	
	
	public Bem getBem() {
		return bem;
	}

	public void setBem(Bem bem) {
		this.bem = bem;
	}

	public void handleTipoBemChange(AjaxBehaviorEvent event) {
		 this.bem.setVida_Util(this.bem.getTipo().getVida_Util());
	}
	
	public void handleBemUsadoChange(AjaxBehaviorEvent event) {
		this.bem.setUsado(this.bem.isUsado());
	}	
	
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Long getVida_util_padrao() {
		return vida_util_padrao;
	}

	public void setVida_util_padrao(Long vida_util_padrao) {
		this.vida_util_padrao = vida_util_padrao;
	}

	public Date getData_referencia() {
		return data_referencia;
	}

	public void setData_referencia(Date data_referencia) {
		this.data_referencia = data_referencia;
	}
	
	public void handleSimulacao() {
		this.bem.setData_venda(this.data_referencia);
		Depreciacao depreciacao = new BemController().calcular(bem, this.data_referencia);
		this.bem.setDepreciacao(depreciacao);
		this.simulacao_efetuada = true;
	}
	
	public void handleCloseSimulacao() {
		this.bem.setData_venda(null);
		this.bem.setValor_venda(null);		
		Depreciacao depreciacao = new BemController().calcular(bem, null);
		this.bem.setDepreciacao(depreciacao);
		this.simulacao_efetuada = false;
	}

	public boolean isSimulacao_efetuada() {
		return simulacao_efetuada;
	}

	public void setSimulacao_efetuada(boolean simulacao_efetuada) {
		this.simulacao_efetuada = simulacao_efetuada;
	}
	
}
