package org.spring.demo.uberapp.servies;

import org.spring.demo.uberapp.entities.Ride;
import org.spring.demo.uberapp.entities.User;
import org.spring.demo.uberapp.entities.Wallet;
import org.spring.demo.uberapp.entities.enums.TransactionMethod;
import org.spring.demo.uberapp.entities.enums.TransactionType;

public interface WalletService {

    Wallet addMoneyToWallet(User user,
                            Double amount,
                            String transactionId,
                            Ride ride,
                            TransactionMethod transactionMethod);

    Wallet deductMoneyFromWallet(User user,
                                 Double amount,
                                 String transactionId,
                                 Ride ride,
                                 TransactionMethod transactionMethod

    );

    void withdrawAllMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);

    Wallet findByUser(User user);

}
