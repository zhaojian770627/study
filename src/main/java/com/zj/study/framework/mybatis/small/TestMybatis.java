package com.zj.study.framework.mybatis.small;

import java.io.IOException;

import org.dom4j.DocumentException;
import org.junit.Test;

import com.zj.study.framework.mybatis.small.batis.session.SqlSession;
import com.zj.study.framework.mybatis.small.batis.session.SqlSessionFactory;
import com.zj.study.framework.mybatis.small.entity.TUser;
import com.zj.study.framework.mybatis.small.mapper.TUserMapper;

public class TestMybatis {

	@Test
	public void test01() throws IOException, DocumentException {
		// 1.实例化SqlSessionFactory，加载数据库配置文件以及mapper.xml文件到configuration对象
		SqlSessionFactory factory = new SqlSessionFactory();
		// 2.获取sqlsession对象
		SqlSession session = factory.openSession();
		// 3.通过动态代理跨越面向接口编程和ibatis编程模型的鸿沟
		TUserMapper userMapper = session.getMapper(TUserMapper.class);
		// 4.遵循jdbc规范，通过底层的四大对象的合作完成数据查询和数据转化
		TUser user = userMapper.selectByPrimaryKey(1);
		System.out.println(user);
		// System.out.println(user);
		//
		//
		// List<TUser> users = userMapper.selectAll();
		// for (TUser tUser : users) {
		//
		// System.out.println(tUser);
		// }

	}

}
