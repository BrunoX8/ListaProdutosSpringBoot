package br.com.dwel6.lojaProdutos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.dwel6.lojaProdutos.models.AppUser;

public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByEmail(email);

        if(appUser != null)
        {
            var springUser = User.withUsername(appUser.getEmail())
            .password(appUser.getPassword())
            .roles(appUser.getRole())
            .build();

            return springUser;
        }

        return null;
    }
    
}
