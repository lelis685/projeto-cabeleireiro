package br.com.cabeleireiro.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LidaMultilasRoles extends SimpleUrlAuthenticationSuccessHandler {


	    private RedirectStrategy estrategiaRedirecinamento = new DefaultRedirectStrategy();

	    @Override
	    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	            throws IOException {
	        String targetUrl = determineTargetUrl(authentication);

	        if (response.isCommitted()) {
	            System.out.println("Não pode redirecionar");
	            return;
	        }

	        estrategiaRedirecinamento.sendRedirect(request, response, targetUrl);
	    }

	  
	    protected String determineTargetUrl(Authentication authentication) {
	        String url = "";

	        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

	        List<String> roles = new ArrayList<String>();

	        for (GrantedAuthority a : authorities) {
	            roles.add(a.getAuthority());
	        }
	        
	        System.out.println(roles);

	       if (isAdmin(roles)) {
	            url = "/cabeleireiros/cabeleireiro/home";
	        } else if (isUser(roles)) {
	            url = "/usuarios/usuario/home";
	        } else {
	            url = "/accessDenied";
	        }

	        return url;
	    }

	    private boolean isUser(List<String> roles) {
	        if (roles.contains("USUARIO")) {
	            return true;
	        }
	        return false;
	    }

	    private boolean isAdmin(List<String> roles) {
	        if (roles.contains("CABELEIREIRO")) {
	            return true;
	        }
	        return false;
	    }


		public RedirectStrategy getEstrategiaRedirecinamento() {
			return estrategiaRedirecinamento;
		}


		public void setEstrategiaRedirecinamento(RedirectStrategy estrategiaRedirecinamento) {
			this.estrategiaRedirecinamento = estrategiaRedirecinamento;
		}

	   
	 
	 
	
}
