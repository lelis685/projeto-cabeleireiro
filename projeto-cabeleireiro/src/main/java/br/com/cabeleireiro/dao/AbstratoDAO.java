package br.com.cabeleireiro.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

//T representa entidade
// PK tipo de chave primaria
public abstract class AbstratoDAO<T, PK extends Serializable> {

	// pega a entidade a partir da assinatura da classe AbstractoDao
	@SuppressWarnings("unchecked")
	private final Class<T> classeEntidade = 
			(Class<T>) ( (ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];


	// spring injeta EntityManager na classe a partir da anotação
	// @PersistenceContext
	@PersistenceContext
	private EntityManager entidadeGerenciadora;

	// protected nao sera acessado fora das classes de dao que extend AbstractDao
	protected EntityManager getEntityManager() {
		return entidadeGerenciadora;
	}

	public void salva(T entity) {
		entidadeGerenciadora.persist(entity);
	}

	public void atualiza(T entity) {
		entidadeGerenciadora.merge(entity);
	}

	public void deleta(PK id) {
		entidadeGerenciadora.remove(entidadeGerenciadora.getReference(classeEntidade, id));
	}

	public T econtraPorId(PK id) {
		return entidadeGerenciadora.find(classeEntidade, id);
	}

	public List<T> encontrarTodos() {
		return entidadeGerenciadora.createQuery("from " + classeEntidade.getSimpleName(), classeEntidade).getResultList();
	}


	/*
	 * return uma lista 
	 * params: jpql , valor dos parametros
	 */
	protected List<T> createQuery(String jpql, Object... params) {
		TypedQuery<T> query = entidadeGerenciadora.createQuery(jpql, classeEntidade);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		return query.getResultList();
	}



}
