package br.com.cabeleireiro.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SegurancaConfiguracao extends WebSecurityConfigurerAdapter{


	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;

	@Value("${spring.queries.select-usuario}")
	private String selectUsuarios;

	@Value("${spring.queries.select-role}")
	private String selectRoles;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.jdbcAuthentication()
		.usersByUsernameQuery(selectUsuarios)  
		.authoritiesByUsernameQuery(selectRoles)
		.dataSource(dataSource)
		.passwordEncoder(bCryptPasswordEncoder);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		  .authorizeRequests()
		  .antMatchers("/").permitAll()
		  .antMatchers("/login").permitAll()
		  .antMatchers("/cabeleireiro/**").permitAll()
		  .antMatchers("/usuarios/**").permitAll()
		  .antMatchers("/admin/**").hasAnyAuthority("ADMIN").anyRequest()
		  .authenticated()
		  .antMatchers("/").permitAll()
          .antMatchers("/login").permitAll()
          .and().csrf().disable().formLogin()
          .loginPage("/login").failureUrl("/login?error=true")
          .usernameParameter("email")
          .passwordParameter("senha")
          .and().logout()
          .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
          .logoutSuccessUrl("/").and().exceptionHandling()
          .accessDeniedPage("/acesso-negado");
		
	}

	 @Override
	    public void configure(WebSecurity web) throws Exception {
	        web
	                .ignoring()
	                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/imagens/**");
	    }



}
