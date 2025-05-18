package com.alldata.CliProManagementTool.Repository;/*
 * @created 18/05/2025
 * @project CliProManagementTool
 * @author Noktuos
 */

import com.alldata.CliProManagementTool.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
