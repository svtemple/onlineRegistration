package com.dronamraju.svtemple.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by mdronamr on 3/3/17.
 */
public class FacesUtil {
    private static Log log = LogFactory.getLog(FacesUtil.class);


    public static void redirect(String outcome) {
        log.info("redirect(String[" + outcome + "]): Entry.");
        if (outcome.contains("?")) {
            getNavigationHandler().performNavigation(outcome + "&faces-redirect=true");
        } else {
            getNavigationHandler().performNavigation(outcome + "?faces-redirect=true");
        }
    }

    public static ConfigurableNavigationHandler getNavigationHandler() {
        log.info("getNavigationHandler(): Entry.");
        return (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
    }

    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public static HttpServletRequest getRequest() {
        log.debug("getRequest(): Entry.");

        if (getExternalContext() != null) {
            return (HttpServletRequest) getExternalContext().getRequest();
        }
        return null;
    }

    public static ExternalContext getExternalContext() {
        if (FacesContext.getCurrentInstance() != null) {
            return FacesContext.getCurrentInstance().getExternalContext();
        }
        return null;
    }

}
