package adminNew.temelEkran.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            response.sendRedirect("/admin/home");
        }
        else if(authentication.getAuthorities().contains((new SimpleGrantedAuthority("ROLE_SCHOOL")))){
            response.sendRedirect("/school/home");
        }
        else if(authentication.getAuthorities().contains((new SimpleGrantedAuthority("ROLE_SCHOOLADMIN")))){
            response.sendRedirect("/school/home");
        }
        else if(authentication.getAuthorities().contains((new SimpleGrantedAuthority("ROLE_STUDENT")))){
            //response.sendRedirect("student/auth/studentSuccess");
        }
    }
}
