package admin;

import java.util.List;

import dao.PageViewersDAO;

/**
 * Classe para apresentacao do relatorio de acesso na relatorio.jsp
 * @author IgorBastos
 *
 */
public class RelatorioPaginasMaisAcessadas {
	//Atributos
	private String url;
	private int qtdAcessos;
	
	//Construtores
	public RelatorioPaginasMaisAcessadas(){}
	
	public RelatorioPaginasMaisAcessadas(String url, int qtdAcessos){
		this.url = url;
		this.qtdAcessos = qtdAcessos;
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
	
	//Outros Metodos
	/**
	 * Busca no banco de dados a relacao das paginas e quantidade de vezes que foram acessadas
	 * 
	 * @param req
	 */
	public static List listarRelatorioMaiorAcesso() {
		List<RelatorioPaginasMaisAcessadas> relatorioAcesso = PageViewersDAO.getInstance().geraRelatorioAcessos();
		return relatorioAcesso;
	}
}
