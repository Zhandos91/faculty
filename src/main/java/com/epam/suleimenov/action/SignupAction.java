package com.epam.suleimenov.action;


import com.epam.suleimenov.dao.*;
import com.epam.suleimenov.model.*;
import com.epam.suleimenov.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class SignupAction implements Action {

    private UserDAO userDAO;
    private FacultyDAO facultyDAO;
    private StudentDAO studentDAO;
    private TeacherDAO teacherDAO;
    private ActionResult home = new ActionResult("home", true);
    private ActionResult signupAgain = new ActionResult("signup");
    private ActionResult teacherAction = new ActionResult("teacher");
    private ActionResult studentAction = new ActionResult("student");


    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try(Connection connection = Service.getConnection()) {
            facultyDAO = Service.getFacultyDAO(connection);
            userDAO = Service.getUserDAO(connection);
            studentDAO = Service.getStudentDAO(connection);
            teacherDAO = Service.getTeacherDAO(connection);
        }catch (SQLException e) {
            e.printStackTrace();
        }



        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirm_password = req.getParameter("confirm_password");
        String userRole = req.getParameter("userRole");

        if (userDAO.findUserByEmail(email) != null) {
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
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setPassword(password);
            user.setUserRole(userRole);
            int id = facultyDAO.getNextIdBySequence("user");
            user.setId(id);
            userDAO.addUser(user);
            req.getSession().setAttribute("user", user);
            if(userRole.equals("teacher")) {
                Teacher teacher = new Teacher();
                teacher.setName(name);
                teacher.setId(id);
                teacher.setCourses(new ArrayList<Course>());
                teacherDAO.addTeacher(teacher.getId(), user.getId());
                req.getSession().setAttribute("teacher", teacher);
                return teacherAction;

            }else if(userRole.equals("student")) {
                Student student = new Student();
                student.setName(name);
                student.setId(id);
                student.setSurname(user.getSurname());
                student.setCourses(new ArrayList<Course>());
                req.getSession().setAttribute("student", student);
                studentDAO.addStudent(student.getId(), user.getId());
                return studentAction;
            }

            return signupAgain;
        }


    }


}