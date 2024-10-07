package br.com.gerenciador.tarefas.service;

import br.com.gerenciador.tarefas.entity.Users;
import br.com.gerenciador.tarefas.repository.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthenticatedUserService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = this.userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User "+username+" not found"));

        List<SimpleGrantedAuthority> roles = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                .collect(Collectors.toList());

        return new User(user.getUserName(), user.getPassword(), roles);
    }
}
