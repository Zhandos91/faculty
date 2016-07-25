package com.epam.suleimenov.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zhandos_Suleimenov on 7/14/2016.
 */

public class LogoutAction implements Action {
    private ActionResult welcome = new ActionResult("", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        return welcome;
    }
}