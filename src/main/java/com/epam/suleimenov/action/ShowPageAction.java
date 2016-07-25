package com.epam.suleimenov.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zhandos_Suleimenov on 7/14/2016.
 */

public class ShowPageAction implements Action {
    private ActionResult result;

    public ShowPageAction(String page) {
        this.result = new ActionResult(page);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        return result;
    }
}
