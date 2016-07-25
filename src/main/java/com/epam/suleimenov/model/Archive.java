package com.epam.suleimenov.model;

import java.util.Date;

/**
 * Created by admin on 6/28/2016.
 */
public class Archive extends BaseEntity {
    private String teacher;
    private String student;
    private String course;
    private int grade;
    private Date date;

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getStudentName() {
        return student;
    }

    public void setStudent(String studentName) {
        this.student = studentName;
    }

    public String getCourseName() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String toString() {
        return "Professor:" + teacher + " Student:"+ student+ " Course:" + course + " Grade:" + grade + " Date: " + date.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Archive archive = (Archive) o;

        if (grade != archive.grade) return false;
        if (teacher != null ? !teacher.equals(archive.teacher) : archive.teacher != null) return false;
        if (student != null ? !student.equals(archive.student) : archive.student != null) return false;
        if (course != null ? !course.equals(archive.course) : archive.course != null) return false;
        return date != null ? date.equals(archive.date) : archive.date == null;

    }

    @Override
    public int hashCode() {
        int result = teacher != null ? teacher.hashCode() : 0;
        result = 31 * result + (student != null ? student.hashCode() : 0);
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + grade;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}


