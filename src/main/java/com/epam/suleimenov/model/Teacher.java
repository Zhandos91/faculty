package com.epam.suleimenov.model;

import com.epam.suleimenov.dao.FacultyDAO;
//import com.epam.suleimenov.service.Connector;

import java.util.ArrayList;

/**
 * Created by admin on 6/27/2016.
 */
public class Teacher extends BaseEntity {

    private String name;
    private ArrayList<Course> courses;

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public ArrayList<Course> getCourses() {

        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }


    @Override
    public String toString() {

        if(courses == null)
            return "Teacher: " + name + " Courses: ";
        StringBuilder sb = new StringBuilder();
        for(Course st: courses) {
            sb.append(st.getName());
            sb.append(" ");
        }
        return "Teacher: " + name + " Courses: " + sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Teacher teacher = (Teacher) o;

        if (name != null ? !name.equals(teacher.name) : teacher.name != null) return false;
        return courses != null ? courses.equals(teacher.courses) : teacher.courses == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (courses != null ? courses.hashCode() : 0);
        return result;
    }
}




