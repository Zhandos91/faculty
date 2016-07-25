package com.epam.suleimenov.model;

import java.util.ArrayList;

/**
 * Created by admin on 6/28/2016.
 */
public class Course extends BaseEntity {
    private String name;
    private String description;
    private int teacherId;
    private String status;

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {

        String strings = "Course: " + name + " Description: " + description + " TeacherId: " + teacherId + "Status: " + status;


        return strings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (teacherId != course.teacherId) return false;
        if (name != null ? !name.equals(course.name) : course.name != null) return false;
        if (description != null ? !description.equals(course.description) : course.description != null) return false;
        return status != null ? status.equals(course.status) : course.status == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + teacherId;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
