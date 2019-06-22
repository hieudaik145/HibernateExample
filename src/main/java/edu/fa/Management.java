package edu.fa;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import edu.fa.model.Address;
import edu.fa.model.Course;
import edu.fa.model.Fresher;
import edu.fa.model.Group;
import edu.fa.model.Syllabus;

public class Management {

	public static void main(String[] args) {
//		createCourseSyllabus();
//		getCoureseSyllabus(1);
//		createFresherAndAddress();
//		createFresherAndCourse();
//		createFresherAndGroup();
		createGroup();
		getListGroup();
		ConnectionUtil.getSessionFactory().close(); 
	}
	
	private static void getListGroup() {
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from Group where name= ");
			List<Group> listGroup = query.list();
			System.out.println(listGroup);
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	private static void showFirstLevel() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Group group1 = (Group)session.get(Group.class, 1);
		System.out.println(group1);
		session.getTransaction().commit();
		session.close();
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		group1 = null;
		 group1 = (Group)session.get(Group.class, 1);
		System.out.println(group1);
		session.getTransaction().commit();
		session.close();
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void useNameQuery() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery(Constant.GROUP_BY_NAME);
		query.setParameter("name","Java Group");
		System.out.println(query.list());
		session.getTransaction().commit();
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void useCriteria() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria groupCriteria = session.createCriteria(Group.class);	
//		groupCriteria.add(Restrictions.or(Restrictions.eq("id", 1), Restrictions.like("name", "JavaS%")));
		
		SimpleExpression eq = Restrictions.eq("id", 1);
		SimpleExpression like = Restrictions.like("name", "JavaS%");
		LogicalExpression le = Restrictions.or(eq, like);
		groupCriteria.add(le);
		System.out.println(groupCriteria.list());
		session.getTransaction().commit();
		
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void deleteGroupUsingHQL() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String queryString = "delete from Group where id = :id " ;
		Query query =session.createQuery(queryString);
		query.setParameter("id", 1);
		int result  = query.executeUpdate();
		System.out.println(result);
		session.getTransaction().commit();
		
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void updateGroupUsingHQL() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String queryString = "update Group set name = :name where id = :id " ;
		Query query =session.createQuery(queryString);
		query.setParameter("id", 1);
		query.setParameter("name", "Funny JavaGroup");
		int result  = query.executeUpdate();
		System.out.println(result);
		session.getTransaction().commit();
		
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void queryGroupUsingHQL() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String queryString = "Select name from Group where name like :name and  id = :id " ;
		Query query =session.createQuery(queryString);
		query.setParameter("id", 1);
		query.setParameter("name", "Java%");
		List<Group> groups = (List<Group>) query.list();
		System.out.println(groups);
		session.getTransaction().commit();
		
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void getGroup() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Group javaGroup = (Group) session.get(Group.class, 1);
		System.out.println(javaGroup);
		javaGroup.setName("New Java Group");
		session.delete(javaGroup);
		session.getTransaction().commit();
		
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void createGroup() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Group javaGroup = new Group("Java Group");
		Group jsGroup = new Group("JavaScript Group");
		session.save(javaGroup);
		session.save(jsGroup); 
		session.getTransaction().commit();
		
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void createFresherAndGroup() {
		Fresher fresher1 = new Fresher("Fresher 1");
		Fresher fresher2 = new Fresher("Fresehr 2");
		Group group1 = new Group("Group1");
		Group group2 = new Group("Group2");
		
		Set<Fresher> freshers = new HashSet<Fresher>();
		freshers.add(fresher1);
		freshers.add(fresher2);
		
		Set<Group> groups = new HashSet<Group>();
		groups.add(group1);
		groups.add(group2);
		
		fresher1.setGroups(groups);
		fresher2.setGroups(groups);
		group1.setFreshers(freshers);
		group2.setFreshers(freshers);
		
		
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(fresher1);
		session.save(fresher2);
		session.save(group1);
		session.save(group2);
		session.getTransaction().commit();
		
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void createFresherAndCourse() {
		List<Course> listCourses = new ArrayList<>();
		listCourses.add(new Course( "Java"));
		listCourses.add(new Course("C#"));
		Fresher fresher = new Fresher("Vo Van Hieu", listCourses);
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for (Course course : listCourses) {
			session.save(course);
		}
		session.save(fresher);
		session.getTransaction().commit();
		
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void createFresherAndAddress() {
		Address address = new Address("Tam Ky", "Tam Phu");
		Fresher fresher = new Fresher("Vo Van Hieu", address);
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(address);
		session.save(fresher);
		session.getTransaction().commit();
		
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	private static void getCoureseSyllabus(int id) {
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Course course = (Course) session.get(Course.class, id);
		System.out.println(course.getName());
		session.close();
		System.out.println(course.getSyllabus());
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}

	private static void createCourseSyllabus() {
		List<Syllabus> listsyllabus = new ArrayList<Syllabus>();
		Syllabus syllabus = new Syllabus("Hibernate Content", 30);
		Syllabus syllabus2 = new Syllabus("Hibernate Offline", 50);
		
		listsyllabus.add(syllabus);
		listsyllabus.add(syllabus2);
		
		Course course = new Course("HieuPro", new Date(),listsyllabus);
		 		
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(course);
		session.getTransaction().commit();
		
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	
	}
}
