package com.epam.suleimenov.action;

import com.epam.suleimenov.dao.FacultyDAO;
import com.epam.suleimenov.model.Course;
import com.epam.suleimenov.model.Student;
import com.epam.suleimenov.model.Teacher;
import com.epam.suleimenov.model.User;
import com.epam.suleimenov.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


public class GradeAction implements Action {



        private FacultyDAO facultyDAO;
        private ActionResult gradeAction = new ActionResult("grade");


        public GradeAction() {
            this.facultyDAO = Service.getFacultyDAO();
        }

        @Override
        public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
            int course_id = Integer.parseInt(req.getParameter("course_id"));
            Teacher teacher = (Teacher)req.getSession().getAttribute("teacher");
            Course course = facultyDAO.getCourseById(course_id);

            ArrayList<Student> students = facultyDAO.getStudentsByCourseId(course.getId());

            req.getSession().setAttribute("students", students);
            req.getSession().setAttribute("course", course);
           return  gradeAction;

        }


    }