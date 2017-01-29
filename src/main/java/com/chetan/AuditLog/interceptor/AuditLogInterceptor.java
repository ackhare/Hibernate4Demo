package com.chetan.AuditLog.interceptor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.chetan.AuditLog.persistence.HibernateUtil;
import com.chetan.AuditLog.util.AuditLogUtil;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.internal.SessionImpl;
import org.hibernate.type.Type;


public class AuditLogInterceptor extends EmptyInterceptor{
	
	Session session;
	private Set inserts = new HashSet();
	private Set updates = new HashSet();
	private Set deletes = new HashSet();
	
	public void setSession(Session session) {
		this.session=session;
	}
		
	public boolean onSave(Object entity,Serializable id,
		Object[] state,String[] propertyNames,Type[] types)
		throws CallbackException {
		
		System.out.println("onSave");
		
		if (entity instanceof IAuditLog){
			inserts.add(entity);
		}
		return false;
			
	}
	
	public boolean onFlushDirty(Object entity,Serializable id,
		Object[] currentState,Object[] previousState,
		String[] propertyNames,Type[] types)
		throws CallbackException {
	
		System.out.println("onFlushDirty");
		
		if (entity instanceof IAuditLog){
			updates.add(entity);
		}
		return false;
		
	}
	
	public void onDelete(Object entity, Serializable id, 
		Object[] state, String[] propertyNames, 
		Type[] types) {
		
		System.out.println("onDelete");
		
		if (entity instanceof IAuditLog){
			deletes.add(entity);
		}
	}

	//called before commit into database
	public void preFlush(Iterator iterator) {
		System.out.println("preFlush");
	}	
	
	//called after committed into database
	public void postFlush(Iterator iterator) {

//		SessionFactoryImplementor sessionFactoryImplementor = null;
//		ConnectionProvider connectionProvider = null;
//		java.sql. connection = null;
//		try {
//
//			sessionFactoryImplementor = (SessionFactoryImplementor) HibernateUtil.getSessionFactory();
//
//			connectionProvider = (ConnectionProvider) sessionFactoryImplementor.getConnectionProvider().getConnection();
//			connection = connectionProvider.getConnection();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		SessionImpl sessionImpl = (SessionImpl) HibernateUtil.getSessionFactory().withOptions()
				.interceptor(new AuditLogInterceptor())
				.openSession();
		Connection connection = sessionImpl.connection();
		//return connection;
		System.out.println("postFlush");
        System.out.println(inserts);
        System.out.println(updates);
        System.out.println(deletes);
        try{
		
			for (Iterator it = inserts.iterator(); it.hasNext();) {
				IAuditLog entity = (IAuditLog) it.next();
				System.out.println("postFlush - insert");
				
				AuditLogUtil.LogIt("Saved",entity,connection,session);
			}	
			
			for (Iterator it = updates.iterator(); it.hasNext();) {
				IAuditLog entity = (IAuditLog) it.next();
				System.out.println("postFlush - update");

				AuditLogUtil.LogIt("Updated",entity,connection,session);
			}	
			
			for (Iterator it = deletes.iterator(); it.hasNext();) {
				IAuditLog entity = (IAuditLog) it.next();
				System.out.println("postFlush - delete");
				AuditLogUtil.LogIt("Deleted",entity, connection,session);
			}	
			
		} finally {
			inserts.clear();
			updates.clear();
			deletes.clear();
		}
	}	
	
}
