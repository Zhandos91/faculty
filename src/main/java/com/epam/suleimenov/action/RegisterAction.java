package com.epam.suleimenov.action;

import com.epam.suleimenov.model.Course;
import com.epam.suleimenov.model.User;
import com.epam.suleimenov.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterAction implements Action {

    private ActionResult studentAction = new ActionResult("student");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        String get_course_id = req.getParameter("course_id");
        if (get_course_id != null) {
            try (CourseService courseService = new CourseService()) {
                Course course = courseService.findCourse(Integer.parseInt(get_course_id));
                User student = (User) req.getSession().getAttribute("student");
                courseService.addCourseToUser(course, student, User.Role.valueOf("STUDENT").toString());
                student.getCourses().add(course);
            }
        }
        return studentAction;
    }
}
