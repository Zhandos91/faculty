package com.epam.suleimenov.servlet;

/**
 * Created by Zhandos_Suleimenov on 7/13/2016.
 */

import com.epam.suleimenov.action.Action;
import com.epam.suleimenov.action.ActionFactory;
import com.epam.suleimenov.action.ActionResult;
import com.epam.suleimenov.dao.FacultyDAO;
import com.epam.suleimenov.dao.FacultyDAOImpl;
import com.epam.suleimenov.model.Archive;
import com.epam.suleimenov.model.Student;
import com.epam.suleimenov.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Controller extends HttpServlet {

    private ActionFactory actionFactory;
    private FacultyDAO facultyDAO;

    private Service service;

    @Override
    public void init() throws ServletException {

        facultyDAO = new FacultyDAOImpl();
        service = new Service();
        service.setFacultyDAO(facultyDAO);
        actionFactory = new ActionFactory();


    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionName = req.getMethod() + req.getPathInfo();

        Action action = actionFactory.getAction(actionName);

        if (action == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Not found");
            return;
        }

        ActionResult result = action.execute(req, resp);

        doForwardOrRedirect(result, req, resp);
    }

    private void doForwardOrRedirect(ActionResult result, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (result.isRedirect()) {
            String location = req.getContextPath() + "/do/" + result.getView();
            resp.sendRedirect(location);
        } else {
            String path = String.format("/WEB-INF/jsp/" + result.getView() + ".jsp");
            req.getRequestDispatcher(path).forward(req, resp);
        }
    }
}

