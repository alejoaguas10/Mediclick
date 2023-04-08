package com.mediclick;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // Users and passwords definition (in memory)
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user1 = User
                .withUsername("Administrador")
                .password("{noop}123")
                .roles("ADMIN")
                .build();
        UserDetails user2 = User
                .withUsername("yforerol")
                .password("{noop}456")
                .roles("CITA")
                .build();
        UserDetails user3 = User
                .withUsername("aaguasa")
                .password("{noop}789")
                .roles("INVENTARIO")
                .build();
        UserDetails user4 = User
                .withUsername("ocoronadot")
                .password("{noop}321")
                .roles("HISTORIAL")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, user3, user4);
    }


    // Access authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().defaultSuccessUrl("/home_principal.html", true);
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/home.html").permitAll()
                .antMatchers("/home_principal.html").permitAll()
                .antMatchers("/img/*").permitAll()
                .antMatchers("/citasform/*", "/eliminarcitas/*").hasAnyRole("ADMIN","CITAS")
                .antMatchers("/insumosform/*", "/eliminarinsumos/*").hasAnyRole("ADMIN","INVENTARIO")
                .antMatchers("/historialesform/*", "/eliminarhistoriales/*").hasAnyRole("ADMIN","HISTORIAL")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("static/**");
    }

}
