package com.ynz.demo.springbootlogging;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class BootLoggingApplication {
    private final ClientRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(BootLoggingApplication.class, args);
    }

    @GetMapping("{email}")
    public List<ClientDto> findClientByEmail(@PathVariable("email") String email) {
        return repository.findByEmail(email);
    }

    @GetMapping(params = "entity")
    public Iterable<Client> findAllClients() {
        return repository.findAll();
    }

    @GetMapping
    public List<ClientDto> findAllClientDto() {
        return repository.findAllClients();
    }

}
