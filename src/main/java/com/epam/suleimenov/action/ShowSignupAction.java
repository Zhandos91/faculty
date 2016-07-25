package com.epam.suleimenov.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowSignupAction implements Action {
    private ActionResult result = new ActionResult("signup");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        return result;
    }
}
