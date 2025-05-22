package com.alldata.CliProManagementTool.Repository;/*
 * @created 18/05/2025
 * @project CliProManagementTool
 * @author Noktuos
 */

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import com.alldata.CliProManagementTool.Entities.Payment;
import com.alldata.CliProManagementTool.Entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    @Query("SELECT pay FROM Payment pay WHERE pay.productName = :productName")
    Optional<Payment> searchPaymentByCompanyName(@Param("productName") String productName);

    @Query("SELECT pay FROM Payment pay WHERE pay.client_id = :client_id")
    Optional<Payment> searchPaymentByClientId(@Param("client_id") Long client_id);

    @Query("")
    Optional<Payment> searchPaymentByProviderId(@Param("provider_id") Long provider_id);

}
