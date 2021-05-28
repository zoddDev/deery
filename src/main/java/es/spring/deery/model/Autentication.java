package es.spring.deery.model;

import es.spring.deery.entity.Artwork;
import es.spring.deery.entity.OC;
import es.spring.deery.entity.User;

import javax.servlet.http.HttpServletRequest;

public class Autentication {

    // Queries

    public static User getLoggedUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("logged-user");
    }

    public static boolean isLogged(HttpServletRequest request) {
        return getLoggedUser(request) != null;
    }

    public static boolean isCreator(User u) {
        return u.getCreatorById() != null;
    }

    public static boolean isCreator(HttpServletRequest request) {
        return isLogged(request) && isCreator(getLoggedUser(request));
    }

    public static boolean isOwner(User u, Artwork a) {
        return isCreator(u) && a.getCreatorByCreatorUserbdId().equals(u.getCreatorById());
    }

    public static boolean isOwner(User u, OC oc) {
        return isCreator(u) && oc.getCreatorByCreatorUserbdId().equals(u.getCreatorById());
    }

    public static boolean isOwner(HttpServletRequest request, Artwork a) {
        return isLogged(request) && isOwner(getLoggedUser(request), a);
    }

    public static boolean isOwner(HttpServletRequest request, OC oc) {
        return isLogged(request) && isOwner(getLoggedUser(request), oc);
    }
}
