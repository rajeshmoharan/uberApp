package org.spring.demo.uberapp.servies;

import org.spring.demo.uberapp.entities.User;
import org.spring.demo.uberapp.entities.Wallet;

public interface WalletService {

    Wallet addMoneyToWallet(User user,Double amount);

    Wallet deductMoneyFromWallet(User user,Double amount);

    void withdrawAllMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);

    Wallet findByUser(User user);

}
