package br.com.gerenciador.tarefas.service;

import br.com.gerenciador.tarefas.entity.Users;
import br.com.gerenciador.tarefas.repository.IRoleRepository;
import br.com.gerenciador.tarefas.repository.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Users save(Users user) {

        user.setRoles(
            user.getRoles()
                .stream()
                .map(role -> roleRepository.findByName(role.getName()))
                .toList()
        );

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public Users update(Users user) {

        user.setRoles(
            user.getRoles()
                .stream()
                .map(role -> roleRepository.findByName(role.getName()))
                .toList()
        );

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public void delete(Users user) {
        this.userRepository.deleteById(user.getId());
    }

    public List<Users> listUsers() {
        return this.userRepository.findAll();
    }

}
