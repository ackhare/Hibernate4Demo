package com.chetan.AuditLog.common;

import com.chetan.AuditLog.interceptor.AuditLogInterceptor;
import com.chetan.AuditLog.persistence.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;



public class App {
	public static void main(String[] args) {

		Session session = null;
		Transaction tx = null;

		try {

			AuditLogInterceptor interceptor = new AuditLogInterceptor();
			
			session = HibernateUtil.getSessionFactory().withOptions()
                    .interceptor(new AuditLogInterceptor())
                    .openSession();
			interceptor.setSession(session);
			
			//test insert
			tx = session.beginTransaction();
			Stock stockInsert = new Stock();
			stockInsert.setStockCode("1111");
			stockInsert.setStockName("mkyong");
			session.saveOrUpdate(stockInsert);
			tx.commit();

			//test update
			tx = session.beginTransaction();
			Query query = session.createQuery("from Stock where stockCode = '1111'");
			Stock stockUpdate = (Stock)query.list().get(0);
			stockUpdate.setStockName("mkyong-update");
			session.saveOrUpdate(stockUpdate);
			tx.commit();

			//test delete
			tx = session.beginTransaction();
			session.delete(stockUpdate);
			tx.commit();

		} catch (RuntimeException e) {
			try {
				tx.rollback();
			} catch (RuntimeException rbe) {
				// log.error("Couldn�t roll back transaction", rbe);
			}
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

}


//Output
//
///usr/lib/jvm/java-8-oracle/bin/java -Didea.launcher.port=7533 -Didea.launcher.bin.path=/home/chetan/Downloads/idea-IU-162.1121.32/bin -Dfile.encoding=UTF-8 -classpath /usr/lib/jvm/java-8-oracle/jre/lib/charsets.jar:/usr/lib/jvm/java-8-oracle/jre/lib/deploy.jar:/usr/lib/jvm/java-8-oracle/jre/lib/ext/cldrdata.jar:/usr/lib/jvm/java-8-oracle/jre/lib/ext/dnsns.jar:/usr/lib/jvm/java-8-oracle/jre/lib/ext/jaccess.jar:/usr/lib/jvm/java-8-oracle/jre/lib/ext/jfxrt.jar:/usr/lib/jvm/java-8-oracle/jre/lib/ext/localedata.jar:/usr/lib/jvm/java-8-oracle/jre/lib/ext/nashorn.jar:/usr/lib/jvm/java-8-oracle/jre/lib/ext/sunec.jar:/usr/lib/jvm/java-8-oracle/jre/lib/ext/sunjce_provider.jar:/usr/lib/jvm/java-8-oracle/jre/lib/ext/sunpkcs11.jar:/usr/lib/jvm/java-8-oracle/jre/lib/ext/zipfs.jar:/usr/lib/jvm/java-8-oracle/jre/lib/javaws.jar:/usr/lib/jvm/java-8-oracle/jre/lib/jce.jar:/usr/lib/jvm/java-8-oracle/jre/lib/jfr.jar:/usr/lib/jvm/java-8-oracle/jre/lib/jfxswt.jar:/usr/lib/jvm/java-8-oracle/jre/lib/jsse.jar:/usr/lib/jvm/java-8-oracle/jre/lib/management-agent.jar:/usr/lib/jvm/java-8-oracle/jre/lib/plugin.jar:/usr/lib/jvm/java-8-oracle/jre/lib/resources.jar:/usr/lib/jvm/java-8-oracle/jre/lib/rt.jar:/home/chetan/IdeaProjects/HibernateResourrces/build/classes/main:/home/chetan/IdeaProjects/HibernateResourrces/build/resources/main:/home/chetan/.gradle/caches/modules-2/files-2.1/org.hibernate.common/hibernate-commons-annotations/4.0.5.Final/2a581b9edb8168e45060d8bad8b7f46712d2c52c/hibernate-commons-annotations-4.0.5.Final.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/org.hibernate.javax.persistence/hibernate-jpa-2.1-api/1.0.0.Final/5e731d961297e5a07290bfaf3db1fbc8bbbf405a/hibernate-jpa-2.1-api-1.0.0.Final.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/mysql/mysql-connector-java/5.1.31/95baa522c5ae4dbff61e8ebd1b2578665d6853a0/mysql-connector-java-5.1.31.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/commons-dbcp/commons-dbcp/1.4/30be73c965cc990b153a100aaaaafcf239f82d39/commons-dbcp-1.4.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/javax.validation/validation-api/1.1.0.Final/8613ae82954779d518631e05daa73a6a954817d5/validation-api-1.1.0.Final.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/org.hibernate/hibernate-validator/5.1.1.Final/2bd44618dc13c2be39231776a0edf0e1f867dedc/hibernate-validator-5.1.1.Final.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/javax.el/javax.el-api/2.2.4/1287562cc3f0ff5439ded6f2949e73ce1c0edaab/javax.el-api-2.2.4.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/org.glassfish.web/javax.el/2.2.4/a50914ff519682e185bca4385b4313b8c8a81775/javax.el-2.2.4.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/org.jboss.logging/jboss-logging/3.1.3.GA/64499e907f19e5e1b3fdc02f81440c1832fe3545/jboss-logging-3.1.3.GA.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/org.jboss.logging/jboss-logging-annotations/1.2.0.Beta1/2f437f37bb265d9f8f1392823dbca12d2bec06d6/jboss-logging-annotations-1.2.0.Beta1.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/org.jboss.spec.javax.transaction/jboss-transaction-api_1.2_spec/1.0.0.Final/1f9fef7a9fcbb41cc390fc370a291cf30729e094/jboss-transaction-api_1.2_spec-1.0.0.Final.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/dom4j/dom4j/1.6.1/5d3ccc056b6f056dbf0dddfdf43894b9065a8f94/dom4j-1.6.1.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/net.sf.ehcache/ehcache-core/2.6.10/8e567a024e27e11b961ca068c5c367f845e21a9b/ehcache-core-2.6.10.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/org.hibernate/hibernate-ehcache/4.3.5.Final/20fde5d106b17ef69119a55e10452739891e2fdc/hibernate-ehcache-4.3.5.Final.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/antlr/antlr/2.7.7/83cd2cd674a217ade95a4bb83a8a14f351f48bd0/antlr-2.7.7.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/org.jboss/jandex/1.1.0.Final/e84a2122e76f0b6503be78094ddf2108057ac15f/jandex-1.1.0.Final.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/org.slf4j/slf4j-api/1.7.20/867d63093eff0a0cb527bf13d397d850af3dcae3/slf4j-api-1.7.20.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/commons-pool/commons-pool/1.5.4/75b6e20c596ed2945a259cea26d7fadd298398e6/commons-pool-1.5.4.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/com.fasterxml/classmate/1.0.0/434efef28c81162b17c540e634cffa3bd9b09b4c/classmate-1.0.0.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/org.javassist/javassist/3.18.1-GA/d9a09f7732226af26bf99f19e2cffe0ae219db5b/javassist-3.18.1-GA.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/org.hibernate/hibernate-core/4.3.6.Final/e6f1e89880e645c66ef9c30d60a68f7e26f3152d/hibernate-core-4.3.6.Final.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/ch.qos.logback/logback-core/1.1.7/7873092d39ef741575ca91378a6a21c388363ac8/logback-core-1.1.7.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/xml-apis/xml-apis/1.0.b2/3136ca936f64c9d68529f048c2618bd356bf85c9/xml-apis-1.0.b2.jar:/home/chetan/.gradle/caches/modules-2/files-2.1/ch.qos.logback/logback-classic/1.1.7/9865cf6994f9ff13fce0bf93f2054ef6c65bb462/logback-classic-1.1.7.jar:/home/chetan/Downloads/idea-IU-162.1121.32/lib/idea_rt.jar com.intellij.rt.execution.application.AppMain com.chetan.AuditLog.common.App
//		Hibernate:
//		alter table stock_category
//		drop
//		foreign key FK_bng1j2qjwaj7tw8fyxbd59j8j
//		2017-01-29_21:54:45.077 org.hibernate.tool.hbm2ddl.SchemaExport main ERROR o.h.tool.hbm2ddl.SchemaExport - HHH000389: Unsuccessful: alter table stock_category drop foreign key FK_bng1j2qjwaj7tw8fyxbd59j8j
//		2017-01-29_21:54:45.080 org.hibernate.tool.hbm2ddl.SchemaExport main ERROR o.h.tool.hbm2ddl.SchemaExport - Can't DROP 'FK_bng1j2qjwaj7tw8fyxbd59j8j'; check that column/key exists
//		Hibernate:
//		alter table stock_category
//		drop
//		foreign key FK_5bug5yttxx84epi3wpd5lqcpe
//		Hibernate:
//		alter table stock_daily_record
//		drop
//		foreign key FK_ia744cuau2twsf41l33mpa27q
//		Hibernate:
//		drop table if exists auditlog
//		Hibernate:
//		drop table if exists category
//		Hibernate:
//		drop table if exists stock
//		Hibernate:
//		drop table if exists stock_category
//		Hibernate:
//		drop table if exists stock_daily_record
//		Hibernate:
//		drop table if exists stock_detail
//		Hibernate:
//		create table auditlog (
//		AUDIT_LOG_ID bigint not null auto_increment,
//		ACTION varchar(100) not null,
//		CREATED_DATE date not null,
//		DETAIL longtext not null,
//		ENTITY_ID bigint not null,
//		ENTITY_NAME varchar(255) not null,
//		primary key (AUDIT_LOG_ID)
//		)
//		Hibernate:
//		create table category (
//		CATEGORY_ID integer not null auto_increment,
//		DESC varchar(255) not null,
//		NAME varchar(20) not null,
//		primary key (CATEGORY_ID)
//		)
//		2017-01-29_21:54:47.559 org.hibernate.tool.hbm2ddl.SchemaExport main ERROR o.h.tool.hbm2ddl.SchemaExport - HHH000389: Unsuccessful: create table category (CATEGORY_ID integer not null auto_increment, DESC varchar(255) not null, NAME varchar(20) not null, primary key (CATEGORY_ID))
//		2017-01-29_21:54:47.559 org.hibernate.tool.hbm2ddl.SchemaExport main ERROR o.h.tool.hbm2ddl.SchemaExport - You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'DESC varchar(255) not null,
//		NAME varchar(20) not null,
//		primary k' at line 3
//		Hibernate:
//		create table stock (
//		STOCK_ID integer not null auto_increment,
//		STOCK_CODE varchar(10) not null,
//		STOCK_NAME varchar(20) not null,
//		primary key (STOCK_ID)
//		)
//		Hibernate:
//		create table stock_category (
//		STOCK_CATEGORY_ID integer not null auto_increment,
//		CATEGORY_ID integer not null,
//		STOCK_ID integer not null,
//		primary key (STOCK_CATEGORY_ID)
//		)
//		Hibernate:
//		create table stock_daily_record (
//		DAILY_RECORD_ID integer not null auto_increment,
//		DATE date not null,
//		PRICE_CHANGE float,
//		PRICE_CLOSE float,
//		PRICE_OPEN float,
//		VOLUME bigint,
//		STOCK_ID integer not null,
//		primary key (DAILY_RECORD_ID)
//		)
//		Hibernate:
//		create table stock_detail (
//		STOCK_ID integer not null,
//		COMP_DESC varchar(255),
//		COMP_NAME varchar(100) not null,
//		LISTED_DATE date not null,
//		REMARK varchar(255),
//		primary key (STOCK_ID)
//		)
//		Hibernate:
//		alter table stock
//		add constraint UK_id2u76pv39wuq1txaomuw9ita  unique (STOCK_NAME)
//		Hibernate:
//		alter table stock
//		add constraint UK_j1y49jqhck84j782fojvrk55w  unique (STOCK_CODE)
//		Hibernate:
//		alter table stock_category
//		add constraint UK_nr0sb58bbsjjpt123thh0s015  unique (STOCK_ID, CATEGORY_ID)
//		Hibernate:
//		alter table stock_daily_record
//		add constraint UK_k8p5x4txjkimsi98ssumd7y8a  unique (DATE)
//		Hibernate:
//		alter table stock_category
//		add constraint FK_bng1j2qjwaj7tw8fyxbd59j8j
//		foreign key (CATEGORY_ID)
//		references category (CATEGORY_ID)
//		2017-01-29_21:54:51.154 org.hibernate.tool.hbm2ddl.SchemaExport main ERROR o.h.tool.hbm2ddl.SchemaExport - HHH000389: Unsuccessful: alter table stock_category add constraint FK_bng1j2qjwaj7tw8fyxbd59j8j foreign key (CATEGORY_ID) references category (CATEGORY_ID)
//		2017-01-29_21:54:51.154 org.hibernate.tool.hbm2ddl.SchemaExport main ERROR o.h.tool.hbm2ddl.SchemaExport - Cannot add foreign key constraint
//		Hibernate:
//		alter table stock_category
//		add constraint FK_5bug5yttxx84epi3wpd5lqcpe
//		foreign key (STOCK_ID)
//		references stock (STOCK_ID)
//		Hibernate:
//		alter table stock_daily_record
//		add constraint FK_ia744cuau2twsf41l33mpa27q
//		foreign key (STOCK_ID)
//		references stock (STOCK_ID)
//		onSave
//		Hibernate:
//		insert
//		into
//		stock
//		(STOCK_CODE, STOCK_NAME)
//		values
//		(?, ?)
//		preFlush
//		postFlush
//		[com.chetan.AuditLog.common.Stock@47da3952]
//		[]
//		[]
//		postFlush - insert
//		onSave
//		Hibernate:
//		insert
//		into
//		auditlog
//		(ACTION, CREATED_DATE, DETAIL, ENTITY_ID, ENTITY_NAME)
//		values
//		(?, ?, ?, ?, ?)
//		preFlush
//		postFlush
//		[]
//		[]
//		[]
//		preFlush
//		Hibernate:
//		select
//		stock0_.STOCK_ID as STOCK_ID1_2_,
//		stock0_.STOCK_CODE as STOCK_CO2_2_,
//		stock0_.STOCK_NAME as STOCK_NA3_2_
//		from
//		stock stock0_
//		where
//		stock0_.STOCK_CODE='1111'
//		preFlush
//		onFlushDirty
//		Hibernate:
//		update
//		stock
//		set
//		STOCK_CODE=?,
//		STOCK_NAME=?
//		where
//		STOCK_ID=?
//		postFlush
//		[]
//		[com.chetan.AuditLog.common.Stock@47da3952]
//		[]
//		postFlush - update
//		onSave
//		Hibernate:
//		insert
//		into
//		auditlog
//		(ACTION, CREATED_DATE, DETAIL, ENTITY_ID, ENTITY_NAME)
//		values
//		(?, ?, ?, ?, ?)
//		preFlush
//		postFlush
//		[]
//		[]
//		[]
//		onDelete
//		preFlush
//		Hibernate:
//		delete
//		from
//		stock
//		where
//		STOCK_ID=?
//		postFlush
//		[]
//		[]
//		[com.chetan.AuditLog.common.Stock@47da3952]
//		postFlush - delete
//		onSave
//		Hibernate:
//		insert
//		into
//		auditlog
//		(ACTION, CREATED_DATE, DETAIL, ENTITY_ID, ENTITY_NAME)
//		values
//		(?, ?, ?, ?, ?)
//		preFlush
//		postFlush
//		[]
//		[]
//		[]