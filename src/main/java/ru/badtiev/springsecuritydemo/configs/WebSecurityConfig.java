package ru.badtiev.springsecuritydemo.configs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import ru.badtiev.springsecuritydemo.model.Permission;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.badtiev.springsecuritydemo.model.Role;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
//                .antMatchers(HttpMethod.GET,"/api/**").hasAuthority(Permission.USERS_READ.getPermission())
//                .antMatchers(HttpMethod.POST,"/api/**").hasAuthority(Permission.USERS_WRITE.getPermission())
//                .antMatchers(HttpMethod.DELETE,"/api/**").hasAuthority(Permission.USERS_WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login").permitAll()
                .defaultSuccessUrl("/auth/success")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout","POST"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/auth/login");
    }

@Bean
@Override
public UserDetailsService userDetailsService() {
    UserDetails user =
            User.builder()
                    .username("admin")
                    .password(passwordEncoder().encode("admin"))
                    .authorities(Role.ADMIN.getAuthority())
                    .build();
            User.builder()
                    .username("user")
                    .password(passwordEncoder().encode("user"))
                    .authorities(Role.USER.getAuthority())
                    .build();

    return new InMemoryUserDetailsManager(user);
}
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder(12);
    }
}
