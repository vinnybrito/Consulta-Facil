package br.com.fiap.consultafacil.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import br.com.fiap.consultafacil.user.User;
import br.com.fiap.consultafacil.user.UserRepository;

@Component
public class LoginListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    UserRepository repository;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        OAuth2User principal = (OAuth2User) event.getAuthentication().getPrincipal();
        String email = principal.getAttribute("email");

        var user = repository.findByEmail(email);

        if (user.isEmpty()){
            var newUser = new User(principal);
            repository.save(newUser);
        }
    }
}
