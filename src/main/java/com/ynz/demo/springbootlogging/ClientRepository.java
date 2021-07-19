package com.ynz.demo.springbootlogging;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {
    List<ClientDto> findByEmail(String email);

    @Query("select new com.ynz.demo.springbootlogging.ClientDto(c.firstName, c.lastName, c.email) from Client c")
    List<ClientDto> findAllClients();

}
