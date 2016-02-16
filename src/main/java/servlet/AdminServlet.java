package servlet;

import static servlet.ServletUtil.forward;
import static servlet.ServletUtil.validaLogin;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.PageViewers;
import admin.RelatorioPaginasMaisAcessadas;

/**
 * Servlet que gerencia movimentacao dentro das funcoes de AMD
 *
 */
@WebServlet(value = "/AdminServlet.saas")
public class AdminServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String comando = ServletUtil.stringNuloParaVazio(req.getParameter("comando"));

			// Valida login
			if (!validaLogin(req, resp)) {
				resp.sendRedirect("./login-admin.saas");
			} else {
				if (!comando.isEmpty()) {
					if (comando.equals("painel")) {
						req.setAttribute("comando", "painel");
						forward(req, resp, "/UserServlet.saas");
					} else if (comando.equals("usuarios")) {
						req.setAttribute("comando", "usuarios");
						forward(req, resp, "/UserServlet.saas");
					} else if (comando.equals("relatorio")) {
						listarRelatorioMaiorAcesso(req);
						listarRelatorioAcessoReal(req);
						forward(req, resp, "./saas-admin/relatorio.jsp");
					} else if (comando.equals("logout")) {
						req.setAttribute("comando", "");
						forward(req, resp, "/login-admin.saas");
					}
				}
			}
			if (comando.equals("acessoURL")) {
				acessouURL(req);
			}
		} catch (Throwable e) {
			// Retorna o 404
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			e.printStackTrace();
		}
	}

	/**
	 * Pega o relatorio de maior acesso e armazena em uma lista
	 * 
	 * @param req
	 */
	private void listarRelatorioMaiorAcesso(HttpServletRequest req) {
		req.setAttribute("relatorioAcesso", RelatorioPaginasMaisAcessadas.listarRelatorioMaiorAcesso());
	}
	
	/**
	 * Pega o relatorio de acesso real e armazena em uma lista
	 * 
	 * @param req
	 */
	private void listarRelatorioAcessoReal(HttpServletRequest req) {
		req.setAttribute("relatorioAcessoReal", PageViewers.listarRelatorioAcessoReal());
	}

	/**
	 * Persiste as informacoes de acesso aas paginas
	 * @param req
	 */
	private void acessouURL(HttpServletRequest req) {
		String url = ServletUtil.stringNuloParaVazio(req.getParameter("url"));
		String ip = ServletUtil.stringNuloParaVazio(req.getParameter("ip"));;
		String date = ServletUtil.stringNuloParaVazio(req.getParameter("dateTime"));
		try {
			Date dateTime = ServletUtil.stringToDateTimeSQL(date);//ServletUtil.stringToDateTime(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date()));
			PageViewers pageView = new PageViewers(url, ip, dateTime);
			pageView.salvaAcessouUrl();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
