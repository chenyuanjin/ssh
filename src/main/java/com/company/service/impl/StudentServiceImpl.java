package com.company.service.impl;

import com.company.dao.StudentTeacherDao;
import com.company.entity.Student;
import com.company.entity.StudentTeacher;
import com.company.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyj on 16/1/14.
 */
@Service
public class StudentServiceImpl extends BaseService<Student> implements StudentService {

    @Autowired
    StudentTeacherDao studentTeacherDao;

    @Override
    public List<Student> getByTeacher(String teacher) {
        List<StudentTeacher> studentTeachers = studentTeacherDao.getByTeacher(teacher);
        List<Student> students = new ArrayList<>();
        for (StudentTeacher st : studentTeachers) {
            students.add(st.getStudent());
        }

        return students;
    }
}
