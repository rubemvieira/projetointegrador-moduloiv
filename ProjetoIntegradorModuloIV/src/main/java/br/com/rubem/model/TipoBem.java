package br.com.rubem.model;

import java.io.Serializable;

public class TipoBem implements Serializable {

	@Override
	public String toString() {
		return "TipoBem [id=" + id + ", descricao=" + descricao + ", vida_Util=" + vida_Util + ", depreciavel="
				+ depreciavel + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (depreciavel ? 1231 : 1237);
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		TipoBem other = (TipoBem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	private Integer vida_Util;
	private boolean depreciavel;
	






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


	public Integer getVida_Util() {
		return vida_Util;
	}


	public void setVida_Util(Integer vida_util) {
		this.vida_Util = vida_util;
	}


	public boolean isDepreciavel() {
		return depreciavel;
	}


	public void setDepreciavel(boolean depreciavel) {
		this.depreciavel = depreciavel;
	}


	public TipoBem() {
		// TODO Auto-generated constructor stub
	}

}
