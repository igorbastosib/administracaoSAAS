package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

/**
 * Classe com funcoes uteis e comuns a outros Servlets
 *
 */
public abstract class ServletUtil {

	public static void forward(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
		req.getRequestDispatcher(path).forward(req, resp);
	}

	/**
	 * Valida o login e senha
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public static boolean validaLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = "";
		String senha = "";

		// Pego login e senha, verifico se o usuario ja realizou login anteriormente para nao acessar admin.jsp
		if (req.getSession().getAttribute("login") != null && req.getSession().getAttribute("senha") != null) {
			login = (String) req.getSession().getAttribute("login");
			senha = (String) req.getSession().getAttribute("senha");
		} else if (req.getParameter("login") != null && req.getParameter("senha") != null) {
			login = (String) req.getParameter("login");
			senha = (String) req.getParameter("senha");
		}
		Boolean loginValidated = UserDAO.userAuthenticates(login, senha);
		
		return loginValidated; 
	}
	
	/**
     * Converte string to DateTime SQL, necessario para salvar no Banco de Dados
     *
     * @return
     * @throws ParseException
     */
	protected static Date stringToDateTimeSQL(String data) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date date = sdf.parse(data);
        return date;
    }
	
	/**
	 * Converte o parameter para vazio "" se for nulo, pois vem de uma requisicao online
	 * podendo ser nulo
	 * 
	 * @param parameter
	 * @return
	 */
	protected static String stringNuloParaVazio(String parameter){
    	return parameter == null ? "" : (String) parameter;
    }
    
    
}
