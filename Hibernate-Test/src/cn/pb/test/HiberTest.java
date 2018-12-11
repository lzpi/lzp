package cn.pb.test;

import java.util.List;

import org.apache.catalina.User;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.pb.entity.Users;

public class HiberTest {
	public static void main(String[] args) {
		//��ȡ�����ļ���Ϣ
		Configuration con=new Configuration().configure();
		//����SessionFactory
		SessionFactory sessionFactory=con.buildSessionFactory();
		//��һ���Ự  �����ݿ�
		Session session=sessionFactory.openSession();
		//�������ݿ�
		Users user=new Users(16L, "Lily", "123");
		//��������
		Transaction tx=session.beginTransaction();
		//����
		session.save(user);
		//��ӻ���ɾ��
		//session.saveOrUpdate(user);
		//����
		//session.update(user);
		//ɾ��
		//session.delete(user);
		//�ύ����
		tx.commit();
		//�ر�����
		session.close();
	}
	
	@Test
	public void getUserById(){
		Session session=new Configuration().configure().buildSessionFactory().openSession();
		//����sql��䣬������ݣ�����һ��user���󣬰����ݷŵ�������
		//��ܸ���User.class���÷��似�������á�
		System.out.println("��ѯǰ...");
		Users users=(Users)session.get(Users.class, 14L);
		System.out.println("��ѯ��..."+users);
		System.out.println("===========");
		session.close();
	}
	
	//��ѯ����
	@Test
	public void researchAll(){
		Session session=new Configuration().configure().buildSessionFactory().openSession();
		String sql="select * from users";
		SQLQuery query=session.createSQLQuery(sql);
		List<Object[]> list=query.list();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i)[0]);
		}
		session.close();
	}
	
	//��HQL���д���Ĳ�ѯ����
	@Test
	public void HqlResearch(){
		Session session=new Configuration().configure().buildSessionFactory().openSession();
		String hql=" from Users";
		Query query=session.createQuery(hql);
		List<Users> list=query.list();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getId());
		}
		session.close();
	}
}
