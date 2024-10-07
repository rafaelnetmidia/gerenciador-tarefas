package br.com.gerenciador.tarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GerenciadorTarefasApplication {

    public static void main(String[] args) {
        SpringApplication.run(GerenciadorTarefasApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        Users user = new Users();
//        user.setUserName("admin");
//        user.setPassword("123456");
//
//        List<Role> roles = new ArrayList<>();
//        Role roleAdmin = new Role();
//        roleAdmin.setName(PermissionEnum.ADMIN);
//        roles.add(roleAdmin);
//
//        Role roleUser = new Role();
//        roleUser.setName(PermissionEnum.USER);
//        roles.add(roleUser);
//
//        user.setRoles(roles);
//        userService.save(user);
//    }
}
