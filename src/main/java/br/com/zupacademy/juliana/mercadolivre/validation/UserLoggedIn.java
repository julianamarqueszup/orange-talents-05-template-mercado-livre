package br.com.zupacademy.juliana.mercadolivre.validation;

import br.com.zupacademy.juliana.mercadolivre.model.NewUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

public class UserLoggedIn implements UserDetails {

    private NewUser user;
    private User springUserDetails;

    public UserLoggedIn(@NotNull @Valid NewUser user) {
        this.user = user;
        springUserDetails = new User(user.getEmail(), user.getPassword(), List.of());
    }


    public Collection<GrantedAuthority> getAuthorities() {
        return springUserDetails.getAuthorities();
    }


    public String getPassword() {
        return springUserDetails.getPassword();
    }


    public String getUsername() {
        return springUserDetails.getUsername();
    }


    public boolean isEnabled() {
        return springUserDetails.isEnabled();
    }


    public boolean isAccountNonExpired() {
        return springUserDetails.isAccountNonExpired();
    }


    public boolean isAccountNonLocked() {
        return springUserDetails.isAccountNonLocked();
    }


    public boolean isCredentialsNonExpired() {
        return springUserDetails.isCredentialsNonExpired();
    }


    public NewUser get() {
        return user;
    }

}