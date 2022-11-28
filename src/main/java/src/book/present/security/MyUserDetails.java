package src.book.present.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import src.book.core.entities.UserEntity;
import src.book.core.ports.IUserRepositoryPort;
import src.book.core.exception.ResourceNotFoundException;

import java.util.Optional;

@Service
public class MyUserDetails implements UserDetailsService {

    private final IUserRepositoryPort userRepositoryPort;

    @Autowired
    public MyUserDetails(IUserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }


    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepositoryPort.getUserByUsername(username);
        if (user.isEmpty()) {
            throw ResourceNotFoundException.WithMessage("user " + "not found");
        }
        UserEntity u = user.get();
        return org.springframework.security.core.userdetails.User.withUsername(u.getUsername()).password(u.getPassword()).authorities(u.getRole())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }
}
