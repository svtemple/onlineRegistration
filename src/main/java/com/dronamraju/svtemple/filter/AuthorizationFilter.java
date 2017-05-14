package com.dronamraju.svtemple.filter;

import com.dronamraju.svtemple.model.User;
import com.dronamraju.svtemple.util.FacesUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mdronamr on 3/15/17.
 */

@WebFilter(filterName="AuthorizationFilter",asyncSupported=true,urlPatterns={"/*"})
public class AuthorizationFilter implements Filter {
    private static Log log = LogFactory.getLog(AuthorizationFilter.class);
    private FilterConfig config;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
//        log.info("request.getServletPath(): " + request.getServletPath());
        if (request.getSession().getAttribute("loggedInUser") == null && !(request.getServletPath().contains("login.xhtml")
                || request.getServletPath().contains("userRegistration.xhtml")
                || request.getServletPath().contains("javax.faces.resource")
                || request.getServletPath().contains("images")
                //|| request.getServletPath().contains("/parakamani/")
                //|| request.getServletPath().contains("product.xhtml")
        )) {
            response.sendRedirect("login.xhtml");
        } else {
            chain.doFilter(req, res);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
        log.debug("destroy(): Entry.");
    }
}
