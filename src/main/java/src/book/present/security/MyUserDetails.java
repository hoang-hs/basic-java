package src.book.present.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import src.book.core.entities.UserEntity;
import src.book.core.ports.IUserRepositoryPort;
import src.book.core.exception.ResourceNotFoundException;
import src.book.core.usecases.GetUserUseCase;

import java.util.Optional;

@Service
public class MyUserDetails implements UserDetailsService {

    private final GetUserUseCase getUserUseCase;

    @Autowired
    public MyUserDetails(GetUserUseCase getUserUseCase) {
        this.getUserUseCase = getUserUseCase;
    }


    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity u = getUserUseCase.getUserByUserName(username);
        return org.springframework.security.core.userdetails.User.withUsername(u.getUsername()).password(u.getPassword()).authorities(u.getRole())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }
}
