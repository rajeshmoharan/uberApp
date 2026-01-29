package org.spring.demo.uberapp.servies;

import org.spring.demo.uberapp.dto.WalletTransactionDto;

public interface WalletTransactionService {

    void createNewWalletTransaction(WalletTransactionDto walletTransactionDto);
}
