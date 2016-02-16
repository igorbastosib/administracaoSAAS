package dao;

import java.util.List;

public abstract class AbstractJpaDAO<T> {

	private Class<T> clazz;

	public final void setClazz(final Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public T findOne(final long id) {
		return JpaUtil.getEntityManager().find(clazz, id);
	}

	public List<T> findAll() {
		return JpaUtil.getEntityManager().createQuery("from " + clazz.getName(), clazz).getResultList();
	}

	public void create(final T entity) throws Exception {
		try {
			JpaUtil.beginTransaction();
			JpaUtil.getEntityManager().persist(entity);
			JpaUtil.commit();
		} catch (Exception e){
			JpaUtil.rollback();
			throw e;
		}
	}

	public T update(final T entity) {
		JpaUtil.beginTransaction();
		T entityReturn = JpaUtil.getEntityManager().merge(entity);
		JpaUtil.commit();
		return entityReturn;
	}

	public void delete(final T entity) throws Exception {
		try {
			JpaUtil.beginTransaction();
			JpaUtil.getEntityManager().remove(entity);
			JpaUtil.commit();
		} catch (Exception e){
			JpaUtil.rollback();
			throw e;
		}
	}

	public void deleteById(final long entityId) throws Exception {
		final T entity = findOne(entityId);
		delete(entity);
	}

}