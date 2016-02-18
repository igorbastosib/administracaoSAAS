package servlet;

import static servlet.ServletUtil.forward;
import static servlet.ServletUtil.validaLogin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.User;
import dao.UserDAO;

/**
 * Objetivo: servlet que gerencia o login dos usuarios e cria o BD
 *
 */

@WebServlet(value = "/login-admin.saas")
public class LoginAdminServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		try {
			criarDB();
			criaAdminUser();
		} catch (Exception e) {
			// System.err.println(e.getMessage());
		}
	}

	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			
			String comando = ServletUtil.stringNuloParaVazio(req.getParameter("comando"));
			if (!comando.isEmpty() && comando.equals("entrar")) {
				Boolean validaSenha = validaLogin(req, resp);
				if (validaSenha) {
					HttpSession session = req.getSession();
					session.setAttribute("login",
							(String) req.getParameter("login"));
					session.setAttribute("senha",
							(String) req.getParameter("senha"));
					forward(req, resp, "/saas-admin/admin-panel.jsp");
				} else {
					if (!validaSenha) {
						req.setAttribute("MsgErro",
								"Usuário e/ou Senha inválidos.");
					}
					forward(req, resp, "./index.jsp");
				}
			} else {
				logoutUser(req);
				forward(req, resp, "./index.jsp");
			}
		} catch (Throwable e) {
			// Retorna o 404
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			e.printStackTrace();
		}

	}

	private void logoutUser(HttpServletRequest req){
		req.setAttribute("login", "");
		req.setAttribute("senha", "");
		req.getSession().setAttribute("login", "");
		req.getSession().setAttribute("senha", "");
		req.getSession().invalidate();
	}
	
	private void criarDB() throws SQLException {
		try {
			String sql_user = "create table usuario ("
					+ "  id numeric(18,0) not null,"
					+ "  login varchar(200) not null,"
					+ "  senha varchar(1000),"
					+ "  constraint pk_conta primary key (id) " + ")";
			
			String sql_pageViewers = "create table pageViewers ("
					+ "  id numeric(18,0) not null,"
					+ "  url varchar(200) not null,"
					+ "  ip varchar(20) not null,"
					+ "  dateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
					+ "  constraint pk_conta primary key (id) " + ")";
			
			String sql_pagesUnmonitored = "create table unmonitoredPages ("
					+ "  id numeric(18,0) not null,"
					+ "  url varchar(200) not null"
					+ "  constraint pk_conta primary key (id) " + ")";
			String url = "jdbc:derby:db;create=true";
			Connection conexao;
			conexao = DriverManager.getConnection(url);
			Statement stmt = conexao.createStatement();
			stmt.execute(sql_user);
			stmt.execute(sql_pageViewers);
			stmt.close();
		} catch (Exception e) {
			System.err.println("criarDB: " + e.getMessage());
		}
	}

	/**
	 * Cria usuario admin, utiliza as configuracoes Singleton para persistencia
	 */
	private void criaAdminUser() {
		try {
			User user = new User();
			user.setLogin("admin");
			user.setSenha("admin");
			UserDAO userDAO = UserDAO.getInstance();
			List listUser = userDAO.findOne("admin", "admin");
			if (listUser != null && listUser.size() == 0) {
				userDAO.getInstance().create(user);
			}
		} catch (Exception e) {
			System.err.println("criarDB: " + e.getMessage());
		}

	}

}
