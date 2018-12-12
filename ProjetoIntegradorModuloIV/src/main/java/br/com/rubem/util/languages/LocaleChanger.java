package br.com.rubem.util.languages;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="lc")
@SessionScoped
public class LocaleChanger implements Serializable {

	private static final long serialVersionUID = 1L;

	private Locale locale;

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void actionSetPtBR() {
		this.setLanguage("pt_BR");
	}
	
	public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
	
	public Locale getLocale() {
		if(locale == null) {
			locale = new Locale("pt_BR");;
		}
		return locale;
	}
	
	public String getLanguage() {
		if(locale == null) {
			locale = new Locale("pt_BR");;
		}		
		return locale.getLanguage();
	}	
}
