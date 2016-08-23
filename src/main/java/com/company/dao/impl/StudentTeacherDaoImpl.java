package com.company.dao.impl;

import com.company.dao.StudentTeacherDao;
import com.company.entity.StudentTeacher;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chenyj on 16/1/14.
 */
@Repository
public class StudentTeacherDaoImpl extends BaseDao<StudentTeacher> implements StudentTeacherDao {
    @Override
    public List<StudentTeacher> getByTeacher(String teacher) {
        String hql = "from  StudentTeacher where teacher.name = ?";
        Object[] args = {
                teacher
        };
        return list(hql, args);
    }
}
