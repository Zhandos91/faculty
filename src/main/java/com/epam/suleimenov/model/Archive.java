package com.epam.suleimenov.model;

import java.util.Date;

public class Archive extends BaseEntity {

    private User student;
    private User teacher;
    private Course course;
    private Grade grade;
    private Date date;

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public enum Grade {
        A,
        B,
        C,
        D,
        F
    }

    @Override
    public String toString() {
        return "Archive{" +
                "student=" + student.getFirstName() + " " + student.getLastName() +
                ", teacher=" + teacher.getFirstName() + " " + teacher.getLastName() +
                ", course=" + course.getName() +
                ", grade=" + grade +
                ", date=" + date +
                '}';
    }
}

