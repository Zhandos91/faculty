package com.epam.suleimenov.servlet;


import com.epam.suleimenov.action.Action;
import com.epam.suleimenov.action.ActionFactory;
import com.epam.suleimenov.action.ActionResult;
import com.epam.suleimenov.connection.MyDBConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private ActionFactory actionFactory;
    private Logger log = LoggerFactory.getLogger(Controller.class);

    @Override
    public void init() throws ServletException {

        actionFactory = new ActionFactory();
//        new ArchiveService().clearArchive();
//        new CourseService().clearCourseToUser();
//        new CourseService().clearCourses();
//        new UserService().clearUsers();
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

    @Override
    public void destroy() {
        super.destroy();
        MyDBConnectionPool.getInstanceholder().drain();
    }
}

