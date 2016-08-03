package com.epam.suleimenov.action;

import com.epam.suleimenov.model.Course;
import com.epam.suleimenov.model.User;
import com.epam.suleimenov.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class AddCourseAction implements Action {

    private ActionResult addCourseAgain = new ActionResult("addCourse");
    private ActionResult teacherAction = new ActionResult("teacher");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        CourseService courseService = new CourseService();

        String name = req.getParameter("courseName");
        String description = req.getParameter("description");
        User teacher = (User) req.getSession().getAttribute("teacher");

        if (name.equals("")) {
            req.setAttribute("courseAddError", "Course name must be entered!!!");
            return addCourseAgain;
        }

        Course course = new Course();
        course.setName(name);
        course.setDescription(description);
        course.setStatus("open");
        List<User> teachers = new ArrayList<User>();
        System.out.println("Teacher: " + teacher);

        teachers.add(teacher);
        courseService.createCourse(course);
        courseService.addCourseToUser(course, teacher, User.Role.valueOf("TEACHER").toString());
        teacher.getCourses().add(course);

        return teacherAction;
    }
}

