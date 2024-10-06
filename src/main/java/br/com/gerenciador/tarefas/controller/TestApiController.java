package br.com.gerenciador.tarefas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApiController {

    @GetMapping("/test-api")
    public String test() {
        return "Success";
    }

    @GetMapping("/test-api-hello-world")
    public String testHelloWorld(@RequestParam(name = "name") String name) {
        return "Olá "+name+", seja bem vindo!";
    }

}
