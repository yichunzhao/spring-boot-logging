package com.ynz.demo.springbootlogging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class BootLoggingApplication {
    private final ClientRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(BootLoggingApplication.class, args);
    }

    @GetMapping("{email}")
    public List<ClientDto> findClientByEmail(@PathVariable("email") String email) {
        log.info("find client by email: ");
        return repository.findByEmail(email);
    }

    @GetMapping(params = "entity")
    public Iterable<Client> findAllClients() {
        log.info("find ALL clients, entity projection:");
        return repository.findAll();
    }

    @GetMapping
    public List<ClientDto> findAllClientDto() {
        log.info("find ALL clients, DTO projection:");
        return repository.findAllClients();
    }

}
