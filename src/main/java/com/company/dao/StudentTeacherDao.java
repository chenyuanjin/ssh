package com.company.dao;

import com.company.entity.StudentTeacher;

import java.util.List;

/**
 * Created by chenyj on 16/1/14.
 */
public interface StudentTeacherDao extends IBaseDao<StudentTeacher> {
    List<StudentTeacher> getByTeacher(String teacher);
}
