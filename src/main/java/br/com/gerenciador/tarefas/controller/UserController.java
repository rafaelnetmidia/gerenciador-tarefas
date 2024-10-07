package br.com.gerenciador.tarefas.controller;


import br.com.gerenciador.tarefas.entity.Users;
import br.com.gerenciador.tarefas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Users user) {

        Users userSave = userService.save(user);

        return new ResponseEntity<>("Novo usuário criado: " + userSave.getUserName(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Users user) {
        Users userSave = userService.update(user);
        return ResponseEntity.ok("Usuário atualizado com sucesso! " + userSave.getUserName());
    }

    @GetMapping
    public ResponseEntity<List<Users>> listUsers() {
        return ResponseEntity.ok(this.userService.listUsers());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Users user) {
        this.userService.delete(user);
        return ResponseEntity.ok().build();
    }



}
