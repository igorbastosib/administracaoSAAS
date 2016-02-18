package servlet;

import static servlet.ServletUtil.forward;
import static servlet.ServletUtil.validaLogin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.PageViewers;
import admin.User;
import dao.PageViewersDAO;
import dao.UserDAO;

/**
 * Servlet para gerenciar o CRUD da entidade Usuario Todas funcoes relacionadas
 * a Usuario sao gerenciadas aqui
 */
@WebServlet(value = "/UserServlet.saas")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = UserDAO.getInstance();

	public UserServlet() {
		super();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String comando = ServletUtil.stringNuloParaVazio(req.getParameter("comando"));

		try {
			// Valida login

			if (!comando.isEmpty()) {

				if (comando.equals("painel")) {
					forward(req, resp, "./saas-admin/admin-panel.jsp");
				} else if (comando.equals("salvar")) {
					salvar(req);
				} else if (comando.equals("usuarios")) {
					listar(req);
					forward(req, resp, "./saas-admin/users.jsp");
				}
			}
		} catch (Throwable e) {
			// Retorna o 404
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			e.printStackTrace();
		}
	}

	/**
	 * Cria novo usuario e salva no Banco de Dados
	 * 
	 * @param request
	 * @throws Exception
	 */
	protected void salvar(HttpServletRequest request) throws Exception {
		User user = new User();
		preencheAtributosUsuario(user, request);
		userDAO.create(user);
	}

	/**
	 * Atualiza os dados do usuario no banco de dados
	 * 
	 * @param request
	 * @throws Exception
	 */
	protected void editar(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id-usuario");
		long idLong = Long.parseLong(id);
		User user = UserDAO.getInstance().findOne(idLong);
		preencheAtributosUsuario(user, request);
		userDAO.update(user);
	}

	/**
	 * Deleta usuario do banco de dados
	 * 
	 * @param request
	 * @throws Exception
	 */
	protected void deletar(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id-usuario");
		userDAO.deleteById(Long.valueOf(id));
	}

	/**
	 * Lista usuarios para a requisicao
	 * 
	 * @param req
	 */
	private void listar(HttpServletRequest req) {
		List<User> listaUsuario = UserDAO.getInstance().findAll();
		
		req.setAttribute("listaUsuarios", listaUsuario);
	}

	/**
	 * Pega o registro do usuario do banco de dados para realizar edicoes
	 * 
	 * @param request
	 * @throws Exception
	 */
	private void prepararEditar(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id-usuario");
		long idLong = Long.parseLong(id);
		User user = UserDAO.getInstance().findOne(idLong);
		request.setAttribute("userEdit", user);
	}

	/**
	 * Preenche os atributos do usuario, metodo de refatoracao, utilizado em
	 * varias partes do codigo
	 * 
	 * @param user
	 * @param request
	 * @throws Exception
	 */
	private void preencheAtributosUsuario(User user, HttpServletRequest request) throws Exception {
		user.setLogin(request.getParameter("login"));
		user.setSenha(request.getParameter("senha"));
	}
}
