package br.com.rubem.model;

import java.io.Serializable;
import java.util.Date;

public class Bem implements Serializable {
	
	@Override
	public String toString() {
		return "Bem [id=" + id + ", descricao=" + descricao + ", tipo=" + tipo + ", data_aquisicao=" + data_aquisicao
				+ ", valor_aquisicao=" + valor_aquisicao + ", vida_Util=" + vida_Util + ", turno_trabalho="
				+ turno_trabalho + ", valor_residual=" + valor_residual + ", valor_residual_tipo=" + valor_residual_tipo
				+ ", data_venda=" + data_venda + ", valor_venda=" + valor_venda + ", depreciacao=" + depreciacao
				+ ", baixado=" + baixado + ", usado=" + usado + ", tempo_uso=" + tempo_uso + ", tipo_tempo_uso="
				+ tipo_tempo_uso + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (baixado ? 1231 : 1237);
		result = prime * result + ((data_aquisicao == null) ? 0 : data_aquisicao.hashCode());
		result = prime * result + ((data_venda == null) ? 0 : data_venda.hashCode());
		result = prime * result + ((depreciacao == null) ? 0 : depreciacao.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tempo_uso == null) ? 0 : tempo_uso.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + tipo_tempo_uso;
		result = prime * result + ((turno_trabalho == null) ? 0 : turno_trabalho.hashCode());
		result = prime * result + (usado ? 1231 : 1237);
		result = prime * result + ((valor_aquisicao == null) ? 0 : valor_aquisicao.hashCode());
		result = prime * result + ((valor_residual == null) ? 0 : valor_residual.hashCode());
		result = prime * result + valor_residual_tipo;
		result = prime * result + ((valor_venda == null) ? 0 : valor_venda.hashCode());
		result = prime * result + ((vida_Util == null) ? 0 : vida_Util.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bem other = (Bem) obj;
		if (baixado != other.baixado)
			return false;
		if (data_aquisicao == null) {
			if (other.data_aquisicao != null)
				return false;
		} else if (!data_aquisicao.equals(other.data_aquisicao))
			return false;
		if (data_venda == null) {
			if (other.data_venda != null)
				return false;
		} else if (!data_venda.equals(other.data_venda))
			return false;
		if (depreciacao == null) {
			if (other.depreciacao != null)
				return false;
		} else if (!depreciacao.equals(other.depreciacao))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tempo_uso == null) {
			if (other.tempo_uso != null)
				return false;
		} else if (!tempo_uso.equals(other.tempo_uso))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (tipo_tempo_uso != other.tipo_tempo_uso)
			return false;
		if (turno_trabalho == null) {
			if (other.turno_trabalho != null)
				return false;
		} else if (!turno_trabalho.equals(other.turno_trabalho))
			return false;
		if (usado != other.usado)
			return false;
		if (valor_aquisicao == null) {
			if (other.valor_aquisicao != null)
				return false;
		} else if (!valor_aquisicao.equals(other.valor_aquisicao))
			return false;
		if (valor_residual == null) {
			if (other.valor_residual != null)
				return false;
		} else if (!valor_residual.equals(other.valor_residual))
			return false;
		if (valor_residual_tipo != other.valor_residual_tipo)
			return false;
		if (valor_venda == null) {
			if (other.valor_venda != null)
				return false;
		} else if (!valor_venda.equals(other.valor_venda))
			return false;
		if (vida_Util == null) {
			if (other.vida_Util != null)
				return false;
		} else if (!vida_Util.equals(other.vida_Util))
			return false;
		return true;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	private TipoBem tipo;
	private Date data_aquisicao;
	private Double valor_aquisicao;
	private Integer vida_Util;
	private Double turno_trabalho;
	private Double valor_residual;
	private char valor_residual_tipo;
	private Date data_venda;
	private Double valor_venda;	
	private Depreciacao depreciacao;
	@SuppressWarnings("unused")
	private boolean baixado;
	private boolean usado;
	private Double tempo_uso;
	private char tipo_tempo_uso;

	public Bem() {
		super();
		this.baixado = this.data_venda != null;
	}	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public TipoBem getTipo() {
		return tipo;
	}


	public void setTipo(TipoBem tipo) {
		this.tipo = tipo;
	}


	public Date getData_aquisicao() {
		return data_aquisicao;
	}


	public void setData_aquisicao(Date data_aquisicao) {
		this.data_aquisicao = data_aquisicao;
	}


	public Double getValor_aquisicao() {
		return valor_aquisicao;
	}


	public void setValor_aquisicao(Double valor_aquisicao) {
		this.valor_aquisicao = valor_aquisicao;
	}


	public Integer getVida_Util() {
		return vida_Util;
	}


	public void setVida_Util(Integer vida_Util) {
		this.vida_Util = vida_Util;
	}


	public Double getTurno_trabalho() {
		return turno_trabalho;
	}


	public void setTurno_trabalho(Double turno_trabalho) {
		this.turno_trabalho = turno_trabalho;
	}


	public Double getValor_residual() {
		return valor_residual;
	}


	public void setValor_residual(Double valor_residual) {
		this.valor_residual = valor_residual;
	}


	public char getValor_residual_tipo() {
		return valor_residual_tipo;
	}


	public void setValor_residual_tipo(char valor_residual_tipo) {
		this.valor_residual_tipo = valor_residual_tipo;
	}


	public Date getData_venda() {
		return data_venda;
	}


	public void setData_venda(Date data_venda) {
		this.data_venda = data_venda;
	}


	public Double getValor_venda() {
		return valor_venda;
	}


	public void setValor_venda(Double valor_venda) {
		this.valor_venda = valor_venda;
	}

	public Depreciacao getDepreciacao() {
		return depreciacao;
	}


	public void setDepreciacao(Depreciacao depreciacao) {
		this.depreciacao = depreciacao;
	}


	public boolean isBaixado() {
		return (this.data_venda != null);
	}


	public void setBaixado(boolean baixado) {
		this.baixado = baixado;
	}

	public boolean isUsado() {
		return usado;
	}

	public void setUsado(boolean usado) {
		this.usado = usado;
	}

	public Double getTempo_uso() {
		return tempo_uso;
	}

	public void setTempo_uso(Double tempo_uso) {
		this.tempo_uso = tempo_uso;
	}

	public char getTipo_tempo_uso() {
		return tipo_tempo_uso;
	}

	public void setTipo_tempo_uso(char tipo_tempo_uso) {
		this.tipo_tempo_uso = tipo_tempo_uso;
	}

}
