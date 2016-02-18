package admin;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import dao.UnmonitoredPagesDAO;

@Entity
@Table(name = "unmonitoredPages")
public class UnmonitoredPages {
	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	// @Column(name = "id", unique = true, nullable = false)
	private long id;

	@Column(name = "url", length = 200, unique = true, nullable = false)
	private String url;

	// Construtores
	public UnmonitoredPages() {
	}

	public UnmonitoredPages(String url) {
		this.setUrl(url);
	}

	// Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	//Outrs metodos
	/**
	 * Busca paginas nao monitoradas
	 * @return
	 */
	public static List getUnmonitoredPagesReport() {
		List<UnmonitoredPages> unmonitoredPagesReport = UnmonitoredPagesDAO.getInstance().findAll();
		return unmonitoredPagesReport;
	}
	
	public static void removeMonitored(String url) throws Exception{
		List<UnmonitoredPages> unmonitoredPages = UnmonitoredPagesDAO.getInstance().findAll(url);
		System.out.println("Tirar Monitoramento");
		if(unmonitoredPages.size() == 0){
			UnmonitoredPages unmonitoredPage = new UnmonitoredPages(url);
			UnmonitoredPagesDAO.getInstance().create(unmonitoredPage);
			System.out.println("Tirou Monitoramento");
		}
	}
	
	public static void insertMonitored(String url) throws Exception{
		List<UnmonitoredPages> unmonitoredPages = UnmonitoredPagesDAO.getInstance().findAll(url);
		if(unmonitoredPages.size() > 0){
			System.out.println("removendo2");
			for(int i = 0;i < unmonitoredPages.size();i++){
				UnmonitoredPagesDAO.getInstance().delete(unmonitoredPages.get(i));
				System.out.println("removeu");
			}
		}
		
	}
}
