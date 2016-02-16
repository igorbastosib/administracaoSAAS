package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.persistence.Query;

import admin.PageViewers;
import admin.RelatorioPaginasMaisAcessadas;
import admin.Usuario;
import servlet.ServletUtil;

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
	public List geraRelatorioAcessos(){
		Query query = JpaUtil.getEntityManager().createQuery("select a.url, count(a.url) as qtdAcessos from PageViewers a group by a.url order by qtdAcessos desc");
		List<RelatorioPaginasMaisAcessadas> relatorioAcessos = new ArrayList<RelatorioPaginasMaisAcessadas>();
		
		List<Object[]> resultList = (List<Object[]>) query.getResultList();
		for (int i = 0; i < resultList.size(); i++)
		{
			RelatorioPaginasMaisAcessadas relAcc = new RelatorioPaginasMaisAcessadas((String) resultList.get(i)[0],  Integer.valueOf(String.valueOf(resultList.get(i)[1])));
			relatorioAcessos.add(relAcc);
		}
		return relatorioAcessos;
	}

	/**
	 * Busca a 10 ultimas paginas acessadas
	 * @return
	 */
	public List dezUltimasPagAcessadas(){
		Query query = JpaUtil.getEntityManager().createQuery("select a from PageViewers a order by a.dateTime desc");
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