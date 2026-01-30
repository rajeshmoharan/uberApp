package org.spring.demo.uberapp.servies;

import org.spring.demo.uberapp.dto.WalletTransactionDto;
import org.spring.demo.uberapp.entities.WalletTransaction;

public interface WalletTransactionService {

    void createNewWalletTransaction(WalletTransaction walletTransaction);
}
