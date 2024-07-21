package br.com.czar.eventosfull.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AuthFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String user = request.getHeader("user");
        String pass = request.getHeader("pass");

        if (user == null || pass == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("{ \"error\": \"Credenciais não encontradas\" }");
            return false;
        }

        String authUser = "admin";
        String authPass = "admin";

        if (!user.equals(authUser) || !pass.equals(authPass)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("{ \"error\": \"Credenciais inválidas\" }");
            return false;
        }

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

