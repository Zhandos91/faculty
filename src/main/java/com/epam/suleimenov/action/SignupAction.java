package com.epam.suleimenov.action;

import com.epam.suleimenov.model.*;
import com.epam.suleimenov.service.Service;
import com.epam.suleimenov.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        String retyped_password = req.getParameter("retyped_password");
        String user_role = req.getParameter("user_role");

        System.out.println(first_name + " " + last_name + " " + email + " " + password + " " + retyped_password + " " + user_role );

        if (userService.findUser(email) != null) {
            req.setAttribute("signupError", "The email is already used, try different one!");
            return signupAgain;
        } else if (!password.equals(retyped_password)) {
            req.setAttribute("signupError", "Retyped password is not matched!");
            return signupAgain;
        } else if (first_name.equals("") || last_name.equals("") || email.equals("") || password.equals("")) {
            req.setAttribute("signupError", "All fields MUST be filled!");
            return signupAgain;
        } else {
            User user = new User();
            user.setFirstName(first_name);
            user.setLastName(last_name);
            user.setEmail(email);
            user.setPassword(password);
            user.setUserRole(User.Role.valueOf(user_role));
            user.setCourses(new ArrayList<Course>());
            userService.createUser(user);

            if(user_role.equals("TEACHER")) {
                req.getSession().setAttribute("teacher", user);
                return teacherAction;

            }else if(user_role.equals("STUDENT")) {
                req.getSession().setAttribute("student", user);
                return studentAction;
            }
            return signupAgain;
        }
    }


}