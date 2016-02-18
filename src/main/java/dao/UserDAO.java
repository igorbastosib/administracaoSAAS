package dao;

import java.util.ArrayList;

import javax.persistence.Query;

import admin.User;
import java.util.List;

public class UserDAO extends AbstractJpaDAO<User> {
	
	private static UserDAO instance = null;
	
	public static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}
	
	private UserDAO(){
		super();
		setClazz(User.class);
	}
	
	public static boolean userAuthenticates(String login, String senha){
		List userList = (List) findOne(login, senha);
		
		//Se nao for NULL ou 0, retornara apenas um registro
		if(userList != null && userList.size() > 0 
				&& ((User) userList.get(0)).getLogin().equals(login) 
				&& ((User) userList.get(0)).getSenha().equals(senha)){
			return true;
		}else{
			return false;
		}
	}
	
	public static List findOne(String login, String senha){
		Query query = JpaUtil.getEntityManager().createQuery("select a from User a where a.login = :vlogin and a.senha = :vsenha");
		query.setParameter("vlogin", login);
		query.setParameter("vsenha", senha);
		return query.getResultList();
	}
}
