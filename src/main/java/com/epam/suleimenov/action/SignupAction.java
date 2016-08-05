package com.epam.suleimenov.action;

import com.epam.suleimenov.model.Course;
import com.epam.suleimenov.model.User;
import com.epam.suleimenov.service.UserService;
import com.epam.suleimenov.util.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class SignupAction implements Action {

    private ActionResult signupAgain = new ActionResult("signup");
    private ActionResult teacherAction = new ActionResult("teacher");
    private ActionResult studentAction = new ActionResult("student");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {

        UserService userService = new UserService();
        String first_name = req.getParameter("first_name");
        String last_name = req.getParameter("last_name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        password = Utils.md5Apache(password);
        String confirm_password = req.getParameter("confirm_password");
        String user_role = req.getParameter("user_role");

        System.out.println(first_name + " " + last_name + " " + email + " " + password + " " + confirm_password + " " + user_role);

        if (userService.findUser(email) != null) {
            req.setAttribute("signupError", "The email is already used, try different one!");
            return signupAgain;
        } else {

            try {

                if (first_name.getBytes(Utils.encoding).length > 60 || last_name.getBytes(Utils.encoding).length > 60 ||
                        email.getBytes(Utils.encoding).length > 60 || password.getBytes(Utils.encoding).length > 120) {
                    req.setAttribute("signupError", "Maximum length exceeded");
                    return signupAgain;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            User user = new User();
            user.setFirstName(first_name);
            user.setLastName(last_name);
            user.setEmail(email);
            user.setPassword(password);
            user.setUserRole(User.Role.valueOf(user_role));
            user.setCourses(new ArrayList<Course>());
            userService.createUser(user);

            if (user_role.equals("TEACHER")) {
                req.getSession().setAttribute("teacher", user);
                return teacherAction;

            } else if (user_role.equals("STUDENT")) {
                req.getSession().setAttribute("student", user);
                return studentAction;
            }
            return signupAgain;
        }
    }


}