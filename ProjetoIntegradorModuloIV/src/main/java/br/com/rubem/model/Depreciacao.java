package br.com.rubem.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Depreciacao implements Serializable {

	@Override
	public String toString() {
		return "Depreciacao [taxa=" + taxa + ", periodo=" + periodo + ", lstPeriodo=" + lstPeriodo + ", acumulado="
				+ acumulado + ", custobem=" + custobem + ", valorresidual=" + valorresidual + ", ganhoperda="
				+ ganhoperda + ", valorcomercial=" + valorcomercial + ", valorvenda=" + valorvenda + ", datavenda="
				+ datavenda + ", total=" + total + ", vidautilestimada=" + vidautilestimada + ", metadevidautil="
				+ metadevidautil + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acumulado == null) ? 0 : acumulado.hashCode());
		result = prime * result + ((custobem == null) ? 0 : custobem.hashCode());
		result = prime * result + ((datavenda == null) ? 0 : datavenda.hashCode());
		result = prime * result + ((ganhoperda == null) ? 0 : ganhoperda.hashCode());
		result = prime * result + ((lstPeriodo == null) ? 0 : lstPeriodo.hashCode());
		result = prime * result + (metadevidautil ? 1231 : 1237);
		result = prime * result + ((periodo == null) ? 0 : periodo.hashCode());
		result = prime * result + ((taxa == null) ? 0 : taxa.hashCode());
		result = prime * result + (total ? 1231 : 1237);
		result = prime * result + ((valorcomercial == null) ? 0 : valorcomercial.hashCode());
		result = prime * result + ((valorresidual == null) ? 0 : valorresidual.hashCode());
		result = prime * result + ((valorvenda == null) ? 0 : valorvenda.hashCode());
		result = prime * result + ((vidautilestimada == null) ? 0 : vidautilestimada.hashCode());
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
		Depreciacao other = (Depreciacao) obj;
		if (acumulado == null) {
			if (other.acumulado != null)
				return false;
		} else if (!acumulado.equals(other.acumulado))
			return false;
		if (custobem == null) {
			if (other.custobem != null)
				return false;
		} else if (!custobem.equals(other.custobem))
			return false;
		if (datavenda == null) {
			if (other.datavenda != null)
				return false;
		} else if (!datavenda.equals(other.datavenda))
			return false;
		if (ganhoperda == null) {
			if (other.ganhoperda != null)
				return false;
		} else if (!ganhoperda.equals(other.ganhoperda))
			return false;
		if (lstPeriodo == null) {
			if (other.lstPeriodo != null)
				return false;
		} else if (!lstPeriodo.equals(other.lstPeriodo))
			return false;
		if (metadevidautil != other.metadevidautil)
			return false;
		if (periodo == null) {
			if (other.periodo != null)
				return false;
		} else if (!periodo.equals(other.periodo))
			return false;
		if (taxa == null) {
			if (other.taxa != null)
				return false;
		} else if (!taxa.equals(other.taxa))
			return false;
		if (total != other.total)
			return false;
		if (valorcomercial == null) {
			if (other.valorcomercial != null)
				return false;
		} else if (!valorcomercial.equals(other.valorcomercial))
			return false;
		if (valorresidual == null) {
			if (other.valorresidual != null)
				return false;
		} else if (!valorresidual.equals(other.valorresidual))
			return false;
		if (valorvenda == null) {
			if (other.valorvenda != null)
				return false;
		} else if (!valorvenda.equals(other.valorvenda))
			return false;
		if (vidautilestimada == null) {
			if (other.vidautilestimada != null)
				return false;
		} else if (!vidautilestimada.equals(other.vidautilestimada))
			return false;
		return true;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Double taxa;
	private Double periodo;
	private List<Map.Entry<Integer, Integer>> lstPeriodo; /* Lista com a os anos e os meses que compõem o periodo */
	private Double acumulado;
	private Double custobem;
	private Double valorresidual;
	private Double ganhoperda;
	private Double valorcomercial;
	private Double valorvenda;
	private Date datavenda;
	private boolean total;
	private Double vidautilestimada;
	private boolean metadevidautil;

	public Depreciacao() {
		// TODO Auto-generated constructor stub
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}

	public Double getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Double período) {
		this.periodo = período;
	}

	public List<Map.Entry<Integer, Integer>> getLstPeriodo() {
		return lstPeriodo;
	}

	public void setLstPeriodo(List<Map.Entry<Integer, Integer>> lstPeriodo) {
		this.lstPeriodo = lstPeriodo;
	}

	public Double getAcumulado() {
		return acumulado;
	}

	public void setAcumulado(Double acumulado) {
		this.acumulado = acumulado;
	}

	public Double getCustobem() {
		return custobem;
	}

	public void setCustobem(Double custobem) {
		this.custobem = custobem;
	}

	public Double getValorresidual() {
		return valorresidual;
	}

	public void setValorresidual(Double valorresidual) {
		this.valorresidual = valorresidual;
	}

	public Double getGanhoperda() {
		return ganhoperda;
	}

	public void setGanhoperda(Double ganhoperda) {
		this.ganhoperda = ganhoperda;
	}

	public Double getValorcomercial() {
		return valorcomercial;
	}

	public void setValorcomercial(Double valorcomercial) {
		this.valorcomercial = valorcomercial;
	}

	public Double getValorvenda() {
		return valorvenda;
	}

	public void setValorvenda(Double valorvenda) {
		this.valorvenda = valorvenda;
	}

	public Date getDatavenda() {
		return datavenda;
	}

	public void setDatavenda(Date datavenda) {
		this.datavenda = datavenda;
	}

	public Double getVidautilestimada() {
		return vidautilestimada;
	}

	public void setVidautilestimada(Double vidautilestimada) {
		this.vidautilestimada = vidautilestimada;
	}

	public boolean isMetadevidautil() {
		return metadevidautil;
	}

	public void setMetadevidautil(boolean metadevidautil) {
		this.metadevidautil = metadevidautil;
	}

	public boolean isTotal() {
		return total;
	}

	public void setTotal(boolean total) {
		this.total = total;
	}


}
