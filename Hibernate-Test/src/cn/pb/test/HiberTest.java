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
		//读取配置文件信息
		Configuration con=new Configuration().configure();
		//创建SessionFactory
		SessionFactory sessionFactory=con.buildSessionFactory();
		//打开一个会话  与数据库
		Session session=sessionFactory.openSession();
		//插入数据库
		Users user=new Users(16L, "Lily", "123");
		//开启事务
		Transaction tx=session.beginTransaction();
		//新增
		session.save(user);
		//添加或者删除
		//session.saveOrUpdate(user);
		//更新
		//session.update(user);
		//删除
		//session.delete(user);
		//提交事务
		tx.commit();
		//关闭连接
		session.close();
	}
	
	@Test
	public void getUserById(){
		Session session=new Configuration().configure().buildSessionFactory().openSession();
		//生产sql语句，查出数据，生成一个user对象，把数据放到数据中
		//框架根据User.class利用反射技术，调用、
		System.out.println("查询前...");
		Users users=(Users)session.get(Users.class, 14L);
		System.out.println("查询后..."+users);
		System.out.println("===========");
		session.close();
	}
	
	//查询所有
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
	
	//用HQL语句写出的查询所有
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
