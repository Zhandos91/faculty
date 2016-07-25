package com.epam.suleimenov.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zhandos_Suleimenov on 7/13/2016.
 */

public interface Action {
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp);
}
