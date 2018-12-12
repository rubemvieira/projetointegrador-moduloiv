package br.com.rubem.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import br.com.rubem.util.connection.ConnectionFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@ManagedBean(name="relatoriobean")
public class RelatorioManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{pdfMB}")
	private PdfMB pdfMB;

	public PdfMB getPdfMB() {
	       return pdfMB;
	}

	public void setPdfMB(PdfMB pdfMB) {
		this.pdfMB = pdfMB;
	}
	
	public RelatorioManagedBean() {
		super();
	}
	
	public String Imprimir() {
		
		try {		
			String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/report1.jasper");
			String pathOut = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/report1.pdf");
			Map<String, Object> parameters = new HashMap<>();
			Connection conn = new ConnectionFactory().open();
			JasperPrint relatorio = JasperFillManager.fillReport(path, parameters, conn);
			conn.close();
			
			byte[] bytesArquivo = JasperExportManager.exportReportToPdf(relatorio);
			InputStream arquivo = new ByteArrayInputStream(bytesArquivo);
			arquivo.read();
			pdfMB.gerar(arquivo, pathOut);
			

		} catch (JRException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "relatorio_show";
	}

}
