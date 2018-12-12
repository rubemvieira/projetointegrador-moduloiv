package br.com.rubem.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.com.rubem.dao.TipoBemDAO;
import br.com.rubem.model.TipoBem;

@ManagedBean
@RequestScoped
public class TipoBemConverter implements Converter {

	public TipoBemConverter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty()) {
            return null;
        }
		return new TipoBemDAO().retrieveById(Long.parseLong(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
            return "";
        }
		if (value instanceof TipoBem) {
            return String.valueOf(((TipoBem) value).getId());
        } else {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid TipoBem", value)), null);
        }
	}

}
