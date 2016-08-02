package com.epam.suleimenov.action;

/**
 * Created by Zhandos_Suleimenov on 7/13/2016.
 */

import com.epam.suleimenov.dao.*;
import com.epam.suleimenov.model.*;
import com.epam.suleimenov.service.ArchiveService;
import com.epam.suleimenov.service.Service;
import com.epam.suleimenov.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginAction implements Action {

    private ActionResult loginAgain = new ActionResult("login");
    private ActionResult teacherAction = new ActionResult("teacher");
    private ActionResult studentAction = new ActionResult("student");
    private ActionResult adminAction = new ActionResult("admin");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        UserService userService = new UserService();
        ArchiveService archiveService = new ArchiveService();

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String userRole = req.getParameter("userRole");

        if (userService.checkUser(login, password, userRole)) {
            User user = userService.findUser(login);
            req.getSession().setAttribute("user", user);
            if(userRole.equals("TEACHER")) {
                req.getSession().setAttribute("teacher", user);
                return teacherAction;
            }else if(userRole.equals("STUDENT")) {
                req.getSession().setAttribute("student", user);
                return studentAction;
            }else{
                List<Archive> archives = archiveService.getAllArchives();
                req.getSession().setAttribute("archives", archives);
            }
            return adminAction;
        } else {
            req.setAttribute("loginError", "login or password incorrect");
            return loginAgain;
        }

    }


}