package br.com.rubem.web;

import java.io.InputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * 
 * @author Weverton Reis
 *
 */
@ManagedBean
@SessionScoped
public class PdfMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private StreamedContent pdf;
	
	/**
	 * Esse método deixa o arquivo pronto para ser exibido na tela.
	 * 
	 * @param arquivo O arquivo pronto para ser apresentado.
	 * @param nomeArquivo Nome do arquivo.
	 */
	public void gerar(InputStream arquivo, String nomeArquivo) {
		pdf = new DefaultStreamedContent(arquivo, "application/pdf", nomeArquivo);
	}
	
	public StreamedContent getPdf() {
		try {
			if(pdf != null){
				pdf.getStream().reset();
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pdf;
	}

	public void setPdf(StreamedContent pdf) {
		this.pdf = pdf;
	}
	
}
