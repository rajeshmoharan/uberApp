package org.spring.demo.uberapp.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.spring.demo.uberapp.entities.Ride;
import org.spring.demo.uberapp.entities.Wallet;
import org.spring.demo.uberapp.entities.enums.TransactionType;

import java.time.LocalDateTime;

@Data
public class WalletTransactionDto {

    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private RideDto ride;
    private  String transactionId;
    private WalletDto wallet;
    private LocalDateTime timeStamp;

}
