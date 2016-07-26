package com.epam.suleimenov.action;

/**
 * Created by Zhandos_Suleimenov on 7/13/2016.
 */

import com.epam.suleimenov.dao.*;
import com.epam.suleimenov.model.*;
import com.epam.suleimenov.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginAction implements Action {

    private ActionResult loginAgain = new ActionResult("login");
    private ActionResult teacherAction = new ActionResult("teacher");
    private ActionResult studentAction = new ActionResult("student");
    private ActionResult adminAction = new ActionResult("admin");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String userRole = req.getParameter("userRole");

        if (Service.getUserDAO().checkUser(login, password, userRole)) {
            User user = Service.getUserDAO().findUserByEmail(login);
            req.getSession().setAttribute("user", user);
            if(userRole.equals("teacher")) {
                Teacher teacher = Service.getTeacherDAO().getTeacherById(Service.getTeacherDAO().findTeacherIdByUserId(user.getId()));
                req.getSession().setAttribute("teacher", teacher);
                return teacherAction;
            }else if(userRole.equals("student")) {
                Student student = Service.getStudentDAO().getStudentById(Service.getStudentDAO().findStudentIdByUserId(user.getId()));
                req.getSession().setAttribute("student", student);
                return studentAction;
            }

            ArrayList<Archive> archives = Service.getArchiveDAO().getArchive();
            req.getSession().setAttribute("archives", archives);

            return adminAction;
        } else {
            req.setAttribute("loginError", "login or password incorrect");
            return loginAgain;
        }

    }


}