package br.com.rubem.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.rubem.controller.TipoBemController;
import br.com.rubem.model.TipoBem;

@ManagedBean(name = "tipobembean")
@SessionScoped
public class TipoBemManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TipoBem tipobem = new TipoBem();
	
	public List<TipoBem> getTipoBens(){
		return new TipoBemController().retrieveAll();
	}

	
	public String actionApagar() {
		new TipoBemController().deletar(tipobem);
		return "tipobens";
	}

	public String actionApagar(TipoBem tipobem) {
		new TipoBemController().deletar(tipobem);
		return "tipobens";
	}	
	
	public String actionNovo() {
		this.tipobem = new TipoBem();
		return "formulario_tipobem";
	}
	public String actionAlterar() {
		return "formulario_tipobem";
	}
	
	public String actionGravar() {
		new TipoBemController().gravar(this.tipobem);
		return "tipobens";
	}	
	
	public TipoBem getTipoBem() {
		return tipobem;
	}

	public void setTipoBem(TipoBem tipobem) {
		this.tipobem = tipobem;
	}

}
