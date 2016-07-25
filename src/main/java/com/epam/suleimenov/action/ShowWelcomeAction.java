package com.epam.suleimenov.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowWelcomeAction implements Action {
    private ActionResult result = new ActionResult("welcome");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        return result;
    }
}
