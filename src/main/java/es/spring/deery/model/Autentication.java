package es.spring.deery.model;

import es.spring.deery.entity.User;

import javax.servlet.http.HttpServletRequest;

public class Autentication {

    public static User getLoggedUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("logged-user");
    }
}
