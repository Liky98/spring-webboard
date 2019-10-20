package com.demo.webboard.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class WebErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String path = "error/error";

        if (String.valueOf(status).equals("403") || String.valueOf(status).equals("404") || String.valueOf(status).equals("500")) {
            path =  "error/" + String.valueOf(status);
        }
//        if (String.valueOf(status).equalsIgnoreCase(HttpStatus.NOT_FOUND.toString())) { return "error/404"; }

        return path;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
