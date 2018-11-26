package br.com.cabeleireiro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.cabeleireiro.service.UsuarioServico;

@Configuration
@EnableWebSecurity
public class SegurancaConfiguracao extends WebSecurityConfigurerAdapter{

	@Autowired
	private UsuarioServico usuarioServico;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// csrf habilita a protecao do lado do servidor


		http.authorizeRequests()
		    .antMatchers("/painel").hasRole("USER")
		    .antMatchers("/cadastro-usuario").permitAll()
		    .antMatchers("/cadastro-cabeleireiro").permitAll()
		   
//		    .antMatchers(HttpMethod.POST,"/produtos").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.GET,"/produtos").hasRole("ADMIN")
		  
		    .antMatchers("/resources/**").permitAll()   
		    .antMatchers("/").permitAll()   
		    .anyRequest().authenticated() // qualquer requisição deve ser autenticada caso contrario envia para form de login
		    .and().formLogin().loginPage("/login").permitAll()
		    .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));     // logoutRequestMatcher -> qual url ira funcionar 
		
	}
	


	
	

}
