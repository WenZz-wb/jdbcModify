package com.hz.testMethod.dao;

import com.hz.testMethod.bean.Student;
import com.hz.testMethod.bean.StudentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
//import org.apache.ibatis.annotations.Param;

public interface StudentMapper {
    int countByExample(StudentExample example);

    int deleteByExample(StudentExample example);

    /*
    * 删除
    * */
    int deleteByPrimaryKey(String sno);
/*
* 增加
* */
    int insert(Student record);
/*
* 增加查询
* */
    int insertSelective(Student record);

  /*
  * 查询全部
  * */
    List<Student> selectByExample(StudentExample example);
/*
* 查询一条记录
* */
    Student selectByPrimaryKey(String sno);
    Student selectByOne(String sno);
    /*
    * 查询全部
    * */
   List<Student> selectStuden();
   /* int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);*/

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    Student selectQueryStudent(@Param("sno") String sno);
}