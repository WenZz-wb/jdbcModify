package com.hz.testMethod.bean;

import com.hz.testMethod.dao.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class TestStudentMapper {
    public StudentMapper studentMapper;
    public SqlSession sqlsession;
    SqlSessionFactory ssf;

    @Before
    public void setUp() throws IOException {
        String resources = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resources);
        ssf = new SqlSessionFactoryBuilder().build(is);
        sqlsession = ssf.openSession();
        this.studentMapper = sqlsession.getMapper(StudentMapper.class);
    }

    @Test
    public void selectByPrimaryKey() {
        //一级缓存默认一直开启，无法关闭，可以强制清除缓存
        //事务提交的了之后，会重新运行语句，不再从缓存空间取属性
        //执行update、insert、delete的时候，会清空缓存
        Student student = studentMapper.selectByPrimaryKey("105");
//       System.out.println(student.getSname().trim());  //去空格
        System.out.println("student:" + student);
        studentMapper.deleteByPrimaryKey("120");
        sqlsession.commit();
        //sqlsession.clearCache(); //强制清除缓存！！！
        Student student1 = studentMapper.selectByPrimaryKey("105");
        System.out.println("student1:" + student1);
    }

    @Test
    public void testSelectByOne() {
        //开启二级缓存，必须序列化
        //mybatis 的二级缓存的作用域是一个mapper的namespace ，
        // 同一个namespace中查询sql可以从缓存中命中。
        Student ss = studentMapper.selectByOne("105");
        System.out.println(ss);
        sqlsession.close();
        SqlSession ssq = ssf.openSession();
        StudentMapper sm = ssq.getMapper(StudentMapper.class);
        Student ssr = sm.selectByOne("105");
        System.out.println(ssr);
    }


    @Test
    public void testInsert() {
        Student s = new Student();
        s.setSno("120");
        s.setSname("烟云霞");
        s.setSsex("女");
        s.setSbirthday(new Date());
        s.setSclass("95031");
        studentMapper.insert(s);
        sqlsession.commit();
    }

    @Test
    public void testSelectStuden() {
        List<Student> list = studentMapper.selectStuden();
        for (Student ss : list
        ) {
            System.out.println(ss);
        }

    }
}
