package org.spring.demo.uberapp.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.spring.demo.uberapp.entities.User;
import org.spring.demo.uberapp.entities.WalletTransaction;

import java.util.List;

@Data
public class WalletDto {

    private Long id;
    private UserDto user;
    private Double balance;
    private List<WalletTransactionDto> transactions;

}
