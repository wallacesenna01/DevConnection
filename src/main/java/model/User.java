package model;

import com.br.wallaceartur.DevConnection.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_tb")
public class User implements UserDetails {

    private UUID id;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;


    public User() {}

    public User(String password, String email, UserRole userRole) {
        this.password = password;
        this.email = email;
        this.userRole = userRole;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.userRole == userRole.ADMIN){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        else return List.of(new SimpleGrantedAuthority("USER_ROLE"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
