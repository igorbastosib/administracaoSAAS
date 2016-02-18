package admin;

import java.util.List;

import dao.PageViewersDAO;

/**
 * Classe para apresentacao do relatorio de acesso na relatorio.jsp
 * @author IgorBastos
 *
 */
public class ReportPagesMostAccess {
	//Atributos
	private String url;
	private int qtdAcessos;
	private boolean monitorar;
	
	//Construtores
	public ReportPagesMostAccess(){}
	
	public ReportPagesMostAccess(String url, int qtdAcessos, boolean monitorar){
		this.url = url;
		this.qtdAcessos = qtdAcessos;
		this.setMonitorar(monitorar);
	}

	//Getters & Setters
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getQtdAcessos() {
		return qtdAcessos;
	}

	public void setQtdAcessos(int qtdAcessos) {
		this.qtdAcessos = qtdAcessos;
	}
	
	public boolean isMonitorar() {
		return monitorar;
	}

	public void setMonitorar(boolean monitorar) {
		this.monitorar = monitorar;
	}

	//Outros Metodos
	/**
	 * Busca no banco de dados a relacao das paginas e quantidade de vezes que foram acessadas
	 * 
	 * @param req
	 */
	public static List getMostAccessReport() {
		List<ReportPagesMostAccess> accessReport = PageViewersDAO.getInstance().createAccesReport();
		return accessReport;
	}
}
