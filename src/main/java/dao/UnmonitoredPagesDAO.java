package dao;

import admin.UnmonitoredPages;

import java.util.List;

/**
 * Classe DAO de persistencia para PageViewers.class
 * @author IgorBastos
 *
 */
public class UnmonitoredPagesDAO extends AbstractJpaDAO<UnmonitoredPages> {
	
	private static UnmonitoredPagesDAO instance = null;
	
	public static UnmonitoredPagesDAO getInstance() {
		if (instance == null) {
			instance = new UnmonitoredPagesDAO();
		}
		return instance;
	}
	
	private UnmonitoredPagesDAO(){
		super();
		setClazz(UnmonitoredPages.class);
	}	
	
	/**
	 * Procura a url nas paginas nao motoradas
	 * @param url
	 * @return
	 */
	public List<UnmonitoredPages> findAll(String url) {
		return JpaUtil.getEntityManager().createQuery("from UnmonitoredPages where url = '"+url+"'").getResultList();
	}
}