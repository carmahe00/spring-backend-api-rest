package com.springboot.backend.apirest.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * clase encargada de proteger los recursos de la aplicación con oauth2
 * 
 * @author juan
 * @EnableAuthorizationServer habilita esta clase de configuración
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	/**
	 * método para configurar el acceso a las rutas por el lado de oath2
	 * 
	 * @param http configura el accersp a los recursos. Primero indica la ruta que
	 *             no tien permiso.Después, protegemos las rutas y la ultima debe
	 *             tener el rol ADMIN (DELETE ,PUT) .luego, indica que todas tienen
	 *             permiso
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/api/clientes", "/api/clientes/pages/**", "/api/uploads/img/**",
						"/images/**")
				.permitAll().anyRequest().authenticated().and().cors().configurationSource(corsConfigurationSource());
	}

	/**
	 * método configura lo siguiente: los dominio permitidos, los métodos
	 * permitidos, permite credenciales y se indica las credenciales de la cabecera
	 * 
	 * @return la configuración de los cors
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OṔTIONS"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	/**
	 * método encargado de filtrar configuración de cors de spring. Pasamos la
	 * configuracion corsConfigurationSource(), y damos la prioridad mas alta
	 * 
	 * @return el filtro con la configuración del cors
	 */
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(
				new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;

	}
}
