package com.chetan.AuditLog.util;

import java.sql.Connection;
import java.util.Date;

import com.chetan.AuditLog.common.AuditLog;
import com.chetan.AuditLog.interceptor.AuditLogInterceptor;
import com.chetan.AuditLog.interceptor.IAuditLog;
import com.chetan.AuditLog.persistence.HibernateUtil;
import org.hibernate.Session;



public class AuditLogUtil{
	
	public static void LogIt(String action,
							 IAuditLog entity, Connection conn,Session session){
		
		Session tempSession = HibernateUtil.getSessionFactory().withOptions()
				.interceptor(new AuditLogInterceptor())
				.openSession();
			
		try {
			AuditLog auditRecord = new AuditLog(action,entity.getLogDeatil()
					, new Date(),entity.getId(), entity.getClass().toString());
			tempSession.save(auditRecord);
			tempSession.flush();
			
		} finally {	
			tempSession.close();
			
		}
			
	}
}