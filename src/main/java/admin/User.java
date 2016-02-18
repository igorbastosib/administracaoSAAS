package admin;

import javax.persistence.*;

@Entity
@Table(name="usuario")
public class User {
	//Atributos
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)  
    //@Column(name = "id", unique = true, nullable = false)
	private long id;
	
	@Column(name = "login", length = 200, unique = true, nullable = false)
	private String login;

	@Column(name = "senha", length = 200, nullable = true)
	private String senha;
	
	//Construtores
	public User(){}

	//Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	//Outros metodos
}
