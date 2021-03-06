package com.epam.suleimenov.action;

import com.epam.suleimenov.model.Course;
import com.epam.suleimenov.model.User;
import com.epam.suleimenov.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class ShowGradeAction implements Action {

    private ActionResult gradeAction = new ActionResult("grade");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        try (CourseService courseService = new CourseService()) {
            int course_id = Integer.parseInt(req.getParameter("course_id"));
            Course course = courseService.findCourse(course_id);

            List<User> students = courseService.findStudentsByCourse(course);
            req.getSession().setAttribute("students", students);
            req.getSession().setAttribute("course", course);
        }

        return gradeAction;
    }


}