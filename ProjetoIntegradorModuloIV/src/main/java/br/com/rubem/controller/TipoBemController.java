package br.com.rubem.controller;

import java.util.List;

import br.com.rubem.dao.TipoBemDAO;
import br.com.rubem.model.TipoBem;

public class TipoBemController {

	public List<TipoBem> retrieveAll() {
		return new TipoBemDAO().retrieveAll();
	}

	public void gravar(TipoBem tipobem) {
		if (tipobem.getId() == null)
			new TipoBemDAO().insert(tipobem);
		else
			new TipoBemDAO().update(tipobem);
	}
	
	public void deletar(TipoBem tipobem) {
		new TipoBemDAO().delete(tipobem);
	}
	
}
