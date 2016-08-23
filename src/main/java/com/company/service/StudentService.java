package com.company.service;

import com.company.entity.Student;

import java.util.List;

/**
 * Created by chenyj on 16/1/14.
 */
public interface StudentService extends IBaseService<Student> {
    List<Student> getByTeacher(String teacher);
}
