package br.com.gerenciador.tarefas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApiController {

    @GetMapping("/test-api")
    private String test() {
        return "Success";
    }

}
