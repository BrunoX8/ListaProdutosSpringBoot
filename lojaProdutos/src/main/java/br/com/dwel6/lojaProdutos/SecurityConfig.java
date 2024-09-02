package br.com.dwel6.lojaProdutos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    //Para mostrar o funcionamento do sistema de cadastros de produtos
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        return http.build();

        //PARA MOSTRAR O FUNCIONAMENTO DO SISTEMA DE LOGIN

       /*return http.authorizeHttpRequests(auth -> auth.requestMatchers("/").permitAll().requestMatchers("/contato").permitAll()
       .requestMatchers("/produtos").permitAll()
       .requestMatchers("/criarProduto").permitAll()
       .requestMatchers("/editarProduto").permitAll()
       .requestMatchers("/register").permitAll()
       .requestMatchers("/login").permitAll()
       .requestMatchers("/logout").permitAll()
       .anyRequest().authenticated())

       .formLogin(form -> form.defaultSuccessUrl("/", true))
       .logout(config -> config.logoutSuccessUrl("/")).build();
       */
    } 

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
