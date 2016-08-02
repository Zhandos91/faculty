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
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirm_password = req.getParameter("confirm_password");
        String userRole = req.getParameter("userRole");

        if (userService.findUser(email) != null) {
            req.setAttribute("signupError", "The email is already used, try different one!");
            return signupAgain;
        } else if (!password.equals(confirm_password)) {
            req.setAttribute("signupError", "Retyped password is not matched!");
            return signupAgain;
        } else if (name.equals("") || surname.equals("") || email.equals("") || password.equals("")) {
            req.setAttribute("signupError", "All fields MUST be filled!");
            return signupAgain;
        } else {
            User user = new User();
            user.setFirstName(name);
            user.setLastName(surname);
            user.setEmail(email);
            user.setPassword(password);
            user.setUserRole(User.Role.valueOf(userRole));
            user.setCourses(new ArrayList<Course>());
            userService.createUser(user);

            if(userRole.equals("TEACHER")) {
                req.getSession().setAttribute("teacher", user);
                return teacherAction;

            }else if(userRole.equals("STUDENT")) {
                req.getSession().setAttribute("student", user);
                return studentAction;
            }
            return signupAgain;
        }
    }


}