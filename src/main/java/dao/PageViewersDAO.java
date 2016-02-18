package dao;

import java.util.ArrayList;
import javax.persistence.Query;

import admin.PageViewers;
import admin.ReportPagesMostAccess;
import java.util.List;

/**
 * Classe DAO de persistencia para PageViewers.class
 * @author IgorBastos
 *
 */
public class PageViewersDAO extends AbstractJpaDAO<PageViewers> {
	
	private static PageViewersDAO instance = null;
	
	public static PageViewersDAO getInstance() {
		if (instance == null) {
			instance = new PageViewersDAO();
		}
		return instance;
	}
	
	private PageViewersDAO(){
		super();
		setClazz(PageViewers.class);
	}
	
	/**
	 * 
	 * @return
	 */
	public List createAccesReport(){
		Query query = JpaUtil.getEntityManager().createQuery(
				"select a.url, count(a.url) as qtdAcessos from PageViewers a where a.url not in (select b.url from UnmonitoredPages b) group by a.url order by qtdAcessos desc");
		List<ReportPagesMostAccess> accessReport = new ArrayList<ReportPagesMostAccess>();
		
		List<Object[]> resultList = (List<Object[]>) query.getResultList();
		for (int i = 0; i < resultList.size(); i++)
		{
			Boolean monitorar = (UnmonitoredPagesDAO.getInstance().findAll((String) resultList.get(i)[0]).size() == 0);
			ReportPagesMostAccess relAcc = new ReportPagesMostAccess((String) resultList.get(i)[0],  Integer.valueOf(String.valueOf(resultList.get(i)[1])), monitorar);
			accessReport.add(relAcc);
		}
		return accessReport;
	}

	/**
	 * Busca a 10 ultimas paginas acessadas
	 * @return
	 */
	public List tenLastAccessPages(){
		Query query = JpaUtil.getEntityManager().createQuery("select a from PageViewers a where a.url not in (select b.url from UnmonitoredPages b) order by a.dateTime desc");
		List<PageViewers> pageViewers = new ArrayList<PageViewers>();
		int totalLista = 10;
		if(query.getResultList().size() < 10){
			totalLista = query.getResultList().size();
		}
		for(int i = 0;i < totalLista;i++){
			pageViewers.add((PageViewers) query.getResultList().get(i));
		}
		return pageViewers;
	}
	
}