package com.epam.suleimenov.action;

import com.epam.suleimenov.model.Archive;
import com.epam.suleimenov.model.User;
import com.epam.suleimenov.service.ArchiveService;
import com.epam.suleimenov.service.UserService;
import com.epam.suleimenov.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginAction implements Action {

    private ActionResult loginAgain = new ActionResult("login");
    private ActionResult teacherAction = new ActionResult("teacher");
    private ActionResult studentAction = new ActionResult("student");
    private ActionResult adminAction = new ActionResult("admin");
    private Logger log = LoggerFactory.getLogger(LoginAction.class.getName());


    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        try (UserService userService = new UserService()) {

            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String userRole = req.getParameter("user_role");

            if (userService.checkUser(login, Utils.md5Apache(password), userRole)) {
                User user = userService.findUser(login);
                req.getSession().setAttribute("user", user);
                if (userRole.equals("TEACHER")) {
                    req.getSession().setAttribute("teacher", user);
                    return teacherAction;
                } else if (userRole.equals("STUDENT")) {
                    req.getSession().setAttribute("student", user);
                    return studentAction;
                } else {
                    try (ArchiveService archiveService = new ArchiveService()) {
                        List<Archive> archives = archiveService.getAllArchives();
                        req.getSession().setAttribute("archives", archives);
                    }
                }
                return adminAction;
            } else {
                req.setAttribute("loginError", "login or password incorrect");
                return loginAgain;
            }
        }

    }


}