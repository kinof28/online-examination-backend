package com.online_exams.university_project.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.online_exams.university_project.services.AdminDetailsServiceImp;
import com.online_exams.university_project.services.StudentDetailsServiceImp;
import com.online_exams.university_project.services.TeacherDetailsServiceImp;
import com.online_exams.university_project.utilities.JWTUtility;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JWTFilter extends OncePerRequestFilter{
	//TODO : Teacher login differ from student login
	JWTUtility jwtUtility;
    StudentDetailsServiceImp studentDetailsServiceImp;
//    private TeacherDetailsServiceImp teacherDetailsServiceImp;
    AdminDetailsServiceImp adminDetailsServiceImp;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorisationHeader = httpServletRequest.getHeader("Authorization");
        UserDetails user;
        String token=null,userName=null;
        try {
            if(authorisationHeader != null && !authorisationHeader.startsWith("ad")){
                if (authorisationHeader != null && authorisationHeader.startsWith("Bearer")) {
                    token = authorisationHeader.substring(7);
                    userName = jwtUtility.extractUserName(token);

                }
                if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    user = this.studentDetailsServiceImp.loadUserByUsername(userName);
                    if (this.jwtUtility.validateToken(token, user,false)) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            }else if(authorisationHeader != null && authorisationHeader.startsWith("ad")){
                if (authorisationHeader != null && authorisationHeader.startsWith("adBearer")) {
                    token = authorisationHeader.substring(9);
                    userName = jwtUtility.extractUserName(token);

                }
                if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    user = this.adminDetailsServiceImp.loadUserByUsername("admin_"+userName);
                    if (this.jwtUtility.validateToken(token, user,true)) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            }

        }catch(Exception e ){
            System.out.println("here is this problem in JWT Filter : "+e);
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
