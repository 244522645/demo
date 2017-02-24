package com.ybt.dao.base.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.ybt.dao.base.BaseDao;

/**
 * @author Jiang
 * spring data jpa 工厂类，代理 baseDaoImpl
 * */
public class BaseDaoFactoryBean<R extends JpaRepository<S, ID>, S, ID extends Serializable>	extends JpaRepositoryFactoryBean<R, S, ID> {

	@SuppressWarnings("rawtypes")
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new MyRepositoryFactory(entityManager);
	}

	/**
	 * 内部类
	 * */
	private static class MyRepositoryFactory<S, ID extends Serializable> extends JpaRepositoryFactory {

		public MyRepositoryFactory(EntityManager entityManager) {
			super(entityManager);

		}

		@SuppressWarnings({ "unchecked", "hiding", "rawtypes" })
		protected  <T, ID extends Serializable> JpaRepository<?, ?> getTargetRepository(RepositoryMetadata metadata,EntityManager entityManager) {
			return new BaseDaoImp(metadata.getDomainType(), entityManager);
		}

		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return BaseDao.class;
		}
	}
}