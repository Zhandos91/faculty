package com.epam.suleimenov.action;

import com.epam.suleimenov.dao.*;
import com.epam.suleimenov.model.Course;
import com.epam.suleimenov.model.Student;
import com.epam.suleimenov.model.Teacher;
import com.epam.suleimenov.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class SubmitGradeAction implements Action {

    private ActionResult teacherAction = new ActionResult("teacher");
    private ActionResult gradeAgain = new ActionResult("grade");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        Course course = (Course) req.getSession().getAttribute("course");
        Teacher teacher = (Teacher) req.getSession().getAttribute("teacher");
        ArrayList<Student> students = (ArrayList<Student>) req.getSession().getAttribute("students");

        if (students != null && students.size() > 0) {
            for (Student student : students) {
                String grade = req.getParameter(Integer.toString(student.getId()));
                if(grade == null || grade.equals("")) {
                    req.setAttribute("gradeError", "All grades must be entered!" );
                    return gradeAgain;
                }
                else if(!grade.matches("\\d+")) {
                    req.setAttribute("gradeError", "Only whole numbers are allowed for grade!");
                    return gradeAgain;
                }

            }
            for (Student student : students) {
                String grade = req.getParameter(Integer.toString(student.getId()));
                Service.getArchiveDAO().addToArchive(Service.getFacultyDAO().getNextIdBySequence("archive"), student.getId(), course.getId(), Integer.parseInt(grade));
            }
            Service.getCourseDAO().changeCourseStatus(course.getId(), "archived");
            teacher = Service.getTeacherDAO().getTeacherById(teacher.getId());
            req.getSession().setAttribute("teacher", teacher);
        }
        return teacherAction;

    }
}