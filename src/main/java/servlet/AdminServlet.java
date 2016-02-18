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
import admin.ReportPagesMostAccess;
import admin.UnmonitoredPages;

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
			System.out.println("Comando "+comando);
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
						listMostAccessReport(req);
						listRealAccessReport(req);
						listDontMonitoredPages(req);
						forward(req, resp, "./saas-admin/relatorio.jsp");
					} else if (comando.equals("pararMonitoraURL")) {
						stopURLMonitoring(req);
						forward(req, resp, "./AdminServlet.saas?comando=relatorio");
					} else if (comando.equals("iniciarMonitoraURL")) {
						startURLMonitoring(req);
						forward(req, resp, "./AdminServlet.saas?comando=relatorio");
					} else if (comando.equals("logout")) {
						req.setAttribute("comando", "");
						forward(req, resp, "/login-admin.saas");
					}
				} else{
					/**
					 * se o comando for vazio
					 */
					resp.sendRedirect("./login-admin.saas");
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
	private void listMostAccessReport(HttpServletRequest req) {
		req.setAttribute("accessReport", ReportPagesMostAccess.getMostAccessReport());
	}
	
	/**
	 * Pega o relatorio de acesso real e armazena em uma lista
	 * 
	 * @param req
	 */
	private void listRealAccessReport(HttpServletRequest req) {
		req.setAttribute("realAccessReport", PageViewers.getRealAccessReport());
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
	
	/**
	 * Pega o relatorio de paginas nao monitoradas armazena em uma lista
	 * 
	 * @param req
	 */
	private void listDontMonitoredPages(HttpServletRequest req) {
		req.setAttribute("dontMonitoredPages", UnmonitoredPages.getUnmonitoredPagesReport());
	}
	
	private void stopURLMonitoring(HttpServletRequest req){
		String url = ServletUtil.stringNuloParaVazio(req.getParameter("url"));
		if(url != null && !url.equals("")){
			try {
				System.out.println("Stop URL: "+url);
				UnmonitoredPages.removeMonitored(url);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void startURLMonitoring(HttpServletRequest req){
		String url = ServletUtil.stringNuloParaVazio(req.getParameter("url"));
		if(url != null && !url.equals("")){
			try {
				UnmonitoredPages.insertMonitored(url);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
