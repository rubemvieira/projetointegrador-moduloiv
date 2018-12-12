package br.com.rubem.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import br.com.rubem.controller.BemController;
import br.com.rubem.model.Bem;

@ManagedBean(name = "dashboardbean")
public class DashboardManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Bem> lstMaioresGanho;
	private List<Bem> lstMaioresPerdas;
	
	@ManagedProperty(value = "#{chartModel}")
	private BarChartModel chartModel;
	
	public BarChartModel getChartModel() {
		return chartModel;
	}

	public void setChartModel(BarChartModel chartModel) {
		this.chartModel = chartModel;
	}

	public String actionShowDashboard( ) {
		createModel();
		return "dashboard";
	}

	@PostConstruct
	public void init() {
		createModel();
	}

    private void createModel() {
    	
    	this.chartModel = new BemController().retrieve5Anos();
    	this.chartModel.setTitle("Depreciação próximos 5 anos");
    	this.chartModel.setLegendPosition("ne");
 
        Axis xAxis = this.chartModel.getAxis(AxisType.X);
        xAxis.setLabel("Ano");
 
        Axis yAxis = this.chartModel.getAxis(AxisType.Y);
        yAxis.setLabel("Quantidade Bens");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
    
	public List<Bem> getLstMaioresGanho() {
		lstMaioresGanho = new BemController().retrieveTop5Ganho();
		return lstMaioresGanho;
	}

	public void setLstMaioresGanho(List<Bem> lstMaioresGanho) {
		this.lstMaioresGanho = lstMaioresGanho;
	}

	public List<Bem> getLstMaioresPerdas() {
		lstMaioresPerdas = new BemController().retrieveTop5Perda();
		return lstMaioresPerdas;
	}

	public void setLstMaioresPerdas(List<Bem> lstMaioresPerdas) {
		this.lstMaioresPerdas = lstMaioresPerdas;
	}


}
