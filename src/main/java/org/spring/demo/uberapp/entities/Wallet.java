package org.spring.demo.uberapp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    private User user;

    private Double balance;

    @OneToMany(mappedBy = "wallet",fetch = FetchType.LAZY)
    private List<WalletTransaction> transactions;
}
