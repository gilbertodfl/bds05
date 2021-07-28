package com.devsuperior.movieflix.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private Environment env;
	
	@Autowired
	private JwtTokenStore tokenStore;

	/*********************************************************************************************
		1) somente as rotas de leitura (GET) de eventos e cidades são públicas (não precisa de login). 
		2) Usuários CLIENT podem também inserir (POST) novos eventos. 
		3) Os demais acessos são permitidos apenas a usuários ADMIN.
		4) Validações de City:
			 Nome não pode ser vazio
		5) Validações de Event:
			 Nome não pode ser vazio
			 Data não pode ser passada
			 Cidade não pode ser nula	
   **********************************************************************************************/

	private static final String[] PUBLIC = { "/oauth/token", "/h2-console/**" };
	private static final String[] GETS_PUBLIC = {  "/events/**", "/cities/**" };
	private static final String[] POST_LOG = {  "/events/**" };
	private static final String[] ADMIN = { "/users/**" ,"/cities/**"};

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		// Banco H2 - liberando os frames de suas telas. 
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll()
		.antMatchers(HttpMethod.GET, GETS_PUBLIC).permitAll()
		.antMatchers(POST_LOG).hasAnyRole("CLIENT", "ADMIN")
		.antMatchers(ADMIN).hasRole("ADMIN")
		.anyRequest().authenticated();
	}	
}
