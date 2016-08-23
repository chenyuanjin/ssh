package com.company.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by chenyj on 16/1/13.
 */
@Entity
@Table(name = "student_teacher")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "operations", "roles", "menus"})
public class StudentTeacher implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Student student;
    private Teacher teacher;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
