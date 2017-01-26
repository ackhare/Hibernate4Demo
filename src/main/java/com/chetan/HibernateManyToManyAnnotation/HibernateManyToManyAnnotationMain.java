package com.chetan.HibernateManyToManyAnnotation;

/**
 * Created by chetan on 15/1/17.
 */
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class HibernateManyToManyAnnotationMain {

	public static void main(String[] args) {
		Item item1 = new Item();
		item1.setDescription("samsung"); item1.setPrice(300);
		Item item2 = new Item();
		item2.setDescription("nokia"); item2.setPrice(200);
		Cart cart = new Cart();
		cart.setTotal(500);
		Set<Item> items = new HashSet<Item>();
		items.add(item1); items.add(item2);
		cart.setItems(items);

		SessionFactory sessionFactory = null;
		try{
		sessionFactory = HibernateAnnotationUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(cart);
		System.out.println("Before committing transaction");
		tx.commit();
		sessionFactory.close();

		System.out.println("Cart ID="+cart.getId());
		System.out.println("Item1 ID="+item1.getId());
		System.out.println("Item2 ID="+item2.getId());

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(sessionFactory != null && !sessionFactory.isClosed()) sessionFactory.close();
		}
	}

}

//When we execute above program, it produces following output.
//
//Hibernate Annotation Configuration loaded
//Hibernate Annotation serviceRegistry created
//Hibernate: insert into CART (cart_total) values (?)
//Hibernate: insert into ITEM (item_desc, item_price) values (?, ?)
//Hibernate: insert into ITEM (item_desc, item_price) values (?, ?)
//Before committing transaction
//Hibernate: insert into CART_ITEMS (cart_id, item_id) values (?, ?)
//Hibernate: insert into CART_ITEMS (cart_id, item_id) values (?, ?)
//Cart ID=5
//Item1 ID=6
//Item2 ID=5
