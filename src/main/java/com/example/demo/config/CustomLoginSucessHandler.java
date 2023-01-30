package com.example.demo.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Configuration
public class CustomLoginSucessHandler extends SimpleUrlAuthenticationSuccessHandler{
	@Autowired HttpSession session; //autowiring session

    @Autowired UserRepository repository; //autowire the user repo

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) 
	throws IOException {
		String targetUrl = determineTargetUrl(authentication);
		if(response.isCommitted()) return;
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy(); 
		redirectStrategy.sendRedirect(request, response, targetUrl);
		
	}
	
	protected String determineTargetUrl(Authentication authentication) {
		String url = "/login?error=true";
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for(GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}
		if(roles.contains("ADMIN")){
	            url = "/admin/Admin_home";
		} else if(roles.contains("USER")) {
			url = "/user/mypage";
		}
		return url;
	}
}