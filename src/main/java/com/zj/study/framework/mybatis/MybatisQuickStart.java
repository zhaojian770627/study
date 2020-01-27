package com.zj.study.framework.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.zj.study.framework.mybatis.entity.TUser;
import com.zj.study.framework.mybatis.mapper.TUserMapper;

public class MybatisQuickStart {

	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void init() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 1.读取mybatis配置文件创SqlSessionFactory
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		inputStream.close();
	}

	@Test
	// 快速入门
	public void quickStart() throws IOException {
		// 2.获取sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 3.获取对应mapper
		TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
		// 4.执行查询语句并返回结果
		TUser user = mapper.selectByPrimaryKey(1);
		System.out.println(user.toString());

	}

	@Test
	// 测试插入数据自动生成id
	public void testInsertGenerateId1() throws IOException {
		// 2.获取sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 3.获取对应mapper
		TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
		// 4.执行查询语句并返回结果
		TUser user1 = new TUser();
		user1.setUserName("test1");
		user1.setRealName("realname1");
		user1.setEmail("myemail1");
		// user1.setId(34);
		mapper.insert1(user1);
		sqlSession.commit();
		System.out.println(user1.getId());
	}

	@Test
	// 测试插入数据自动生成id
	public void testInsertGenerateId2() throws IOException {
		// 2.获取sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 3.获取对应mapper
		TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);
		// 4.执行查询语句并返回结果
		TUser user2 = new TUser();
		user2.setUserName("test2");
		user2.setRealName("realname2");
		user2.setEmail("myemai2l");
		mapper.insert2(user2);
		sqlSession.commit();
		System.out.println(user2.getId());
	}

	@Test
	// 参数#和参数$区别测试(动态sql 入门)
	public void testSymbol() {
		// 2.获取sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 3.获取对应mapper
		TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);

		String inCol = "id, user_name, real_name, sex, mobile, email, note";
		String tableName = "t_user";
		Byte sex = 1;
		String orderStr = "sex,user_name";

		List<TUser> list = mapper.selectBySymbol(tableName, inCol, orderStr, sex);
		System.out.println(list.size());
	}

	// --------------------------------动态sql---------------------------------------
	@Test
	// if用于select，并与where配合
	public void testSelectIfOper() {
		// 2.获取sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 3.获取对应mapper
		TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);

		String email = null;
		Byte sex = 2;
		// List<TUser> list = mapper.selectIfOper(email, null);
		List<TUser> list = mapper.selectIfandWhereOper(email, sex);
		System.out.println(list.size());
	}

	@Test
	// if用于update，并与set配合
	public void testUpdateIfOper() {
		// 2.获取sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		// 3.获取对应mapper
		TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);

		TUser user = new TUser();
		user.setId(3);
		user.setUserName("cindy");
		user.setRealName("王美丽");
		user.setEmail("xxoo@163.com");
		user.setMobile("18695988747");
		user.setNote("cindy's note");
		user.setSex((byte) 2);
		// TPosition positon = new TPosition();
		// positon.setId(1);
		// user.setPosition(positon);
		// System.out.println(mapper.updateIfOper(user));
		System.out.println(mapper.updateIfAndSetOper(user));
	}

	@Test
	// if用于insert，并与trim配合
	public void testInsertIfOper() {
		// 2.获取sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 3.获取对应mapper
		TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);

		TUser user = new TUser();
		user.setUserName("mark");
		user.setRealName("毛毛");
		// user.setEmail("xxoo@163.com");
		// user.setMobile("18695988747");
		// user.setNote("mark's note");
		// user.setSex((byte) 1);
		// TPosition positon = new TPosition();
		// positon.setId(1);
		// user.setPosition(positon);
		// System.out.println(mapper.insertIfOper(user));
		System.out.println(mapper.insertSelective(user));
	}

	@Test
	// Foreach用于in查询
	public void testForeach4In() {
		// 2.获取sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 3.获取对应mapper
		TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);

		String[] names = new String[] { "lison", "james" };
		List<TUser> users = mapper.selectForeach4In(names);
		System.out.println(users.size());
	}

	@Test
	// Foreach用于批量插入
	public void testForeach4Insert() {
		// 2.获取sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 3.获取对应mapper
		TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);

		TUser user1 = new TUser();
		user1.setUserName("king");
		user1.setRealName("李小京");
		user1.setEmail("li@qq.com");
		user1.setMobile("18754548787");
		user1.setNote("king's note");
		user1.setSex((byte) 1);
		TUser user2 = new TUser();
		user2.setUserName("deer");
		user2.setRealName("陈大林");
		user2.setEmail("chen@qq.com");
		user2.setMobile("18723138787");
		user2.setNote("deer's note");
		user2.setSex((byte) 1);

		int i = mapper.insertForeach4Batch(Arrays.asList(user1, user2));
		sqlSession.commit();
		System.out.println(i);
	}
	
	
}
