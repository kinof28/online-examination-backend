package com.online_exams.university_project.configuration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.online_exams.university_project.filters.JWTFilter;
import com.online_exams.university_project.services.AdminDetailsServiceImp;
import com.online_exams.university_project.services.StudentDetailsServiceImp;
import com.online_exams.university_project.services.TeacherDetailsServiceImp;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
@SpringBootConfiguration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	//TODO: security configuration not done
	JWTFilter jwtFilter;
    StudentDetailsServiceImp studentDetailsService;
    TeacherDetailsServiceImp teacherDetailsService;
    AdminDetailsServiceImp adminDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.adminDetailsService);
        auth.userDetailsService(this.studentDetailsService);
        auth.userDetailsService(this.teacherDetailsService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/api/v1/**").permitAll()
//                .antMatchers("/api/v0/student/**").hasRole("student")
//                .antMatchers("/api/v0/teacher/**").hasRole("teacher")
//                .antMatchers("/api/v0/admin/**").hasRole("admin")
                .anyRequest().authenticated().and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
