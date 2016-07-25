package com.epam.suleimenov.action;

/**
 * Created by Zhandos_Suleimenov on 7/13/2016.
 */

import com.epam.suleimenov.dao.FacultyDAO;
import com.epam.suleimenov.model.*;
import com.epam.suleimenov.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class LoginAction implements Action {


    private ActionResult loginAgain = new ActionResult("login");
    private FacultyDAO facultyDAO;
    private ActionResult teacherAction = new ActionResult("teacher");
    private ActionResult studentAction = new ActionResult("student");
    private ActionResult adminAction = new ActionResult("admin");

    public LoginAction() {
        facultyDAO = Service.getFacultyDAO();
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String userRole = req.getParameter("userRole");



        if (facultyDAO.checkUser(login, password, userRole)) {
            User user = facultyDAO.findUserByEmail(login);
            req.getSession().setAttribute("user", user);
            if(userRole.equals("teacher")) {
                Teacher teacher = facultyDAO.getTeacherById(facultyDAO.findTeacherIdByUserId(user.getId()));
                req.getSession().setAttribute("teacher", teacher);
                return teacherAction;
            }else if(userRole.equals("student")) {
                Student student = facultyDAO.getStudentById(facultyDAO.findStudentIdByUserId(user.getId()));
                for(Course course : student.getCourses())
                    System.out.println(course.getName());
                req.getSession().setAttribute("student", student);
                return studentAction;
            }

            ArrayList<Archive> archives = facultyDAO.getArchive();
            req.getSession().setAttribute("archives", archives);


            return adminAction;
        } else {
            req.setAttribute("loginError", "login or password incorrect");
            return loginAgain;
        }

    }


}