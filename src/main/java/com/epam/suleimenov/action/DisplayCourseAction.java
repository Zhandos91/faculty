package com.epam.suleimenov.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayCourseAction implements Action {
    private ActionResult result = new ActionResult("course");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        return result;
    }
}
