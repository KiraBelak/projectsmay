package com.alldata.CliProManagementTool.Repository;/*
 * @created 18/05/2025
 * @project CliProManagementTool
 * @author Noktuos
 */

import com.alldata.CliProManagementTool.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {

    @Query("SELECT c FROM Client c WHERE c.email= :email")
    Optional<Client> searchByClientMail(@Param("email") String email);

    @Query("SELECT c FROM  Client c WHERE c.representative = :representative")
    Optional<Client> searchByClientRepresentative(@Param("representative") String representative);

    @Query("SELECT c FROM  Client c WHERE c.name = :name")
    Optional<Client> searchByClientName(@Param("name") String name);

}
