package com.epam.suleimenov.action;

import com.epam.suleimenov.dao.CourseDAO;
import com.epam.suleimenov.dao.FacultyDAO;
import com.epam.suleimenov.model.Course;
import com.epam.suleimenov.model.Teacher;
import com.epam.suleimenov.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Zhandos_Suleimenov on 7/19/2016.
 */
public class AddCourseAction implements Action {

        private ActionResult addCourseAgain = new ActionResult("course");
        private ActionResult teacherAction = new ActionResult("teacher");

        public AddCourseAction() {

        }

        @Override
        public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

            String name = req.getParameter("courseName");
            String description = req.getParameter("description");
            Teacher teacher = (Teacher) req.getSession().getAttribute("teacher");

            if(name.equals("")){
                req.setAttribute("courseAddError", "Course name must be entered!!!");
                return addCourseAgain;
            }

                Course course = new Course();
                course.setId(Service.getFacultyDAO().getNextIdBySequence("course"));
                course.setName(name);
                course.setDescription(description);
                course.setTeacherId(teacher.getId());
                course.setStatus("open");
                Service.getCourseDAO().addCourse(course);
                teacher.getCourses().add(course);

            return teacherAction;
        }


}

