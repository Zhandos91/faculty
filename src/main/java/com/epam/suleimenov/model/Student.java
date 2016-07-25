package com.epam.suleimenov.model;

import java.util.ArrayList;

/**
 * Created by admin on 6/27/2016.
 */
public class Student extends BaseEntity {

    private String name;
    private String surname;
    private ArrayList<Course> courses;

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {

        if(courses != null) {
            StringBuilder sb = new StringBuilder();
            for (Course c : courses) {
                sb.append(c.getName());
                sb.append(" ");
            }

            return "StudentName: " + this.getName() + " Courses: " + sb.toString();
        }

        return "StudentName: " + this.getName() + " Courses: ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Student student = (Student) o;

        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (surname != null ? !surname.equals(student.surname) : student.surname != null) return false;
        return courses != null ? courses.equals(student.courses) : student.courses == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (courses != null ? courses.hashCode() : 0);
        return result;
    }
}
