package org.spring.demo.uberapp.servies.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.spring.demo.uberapp.entities.Ride;
import org.spring.demo.uberapp.entities.User;
import org.spring.demo.uberapp.entities.Wallet;
import org.spring.demo.uberapp.entities.WalletTransaction;
import org.spring.demo.uberapp.entities.enums.TransactionMethod;
import org.spring.demo.uberapp.entities.enums.TransactionType;
import org.spring.demo.uberapp.exceptions.ResourceNotFoundException;
import org.spring.demo.uberapp.repositories.WalletRepository;
import org.spring.demo.uberapp.servies.WalletService;
import org.spring.demo.uberapp.servies.WalletTransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletTransactionService walletTransactionService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user,
                                   Double amount,
                                   String transactionId,
                                   Ride ride,
                                 TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance()+amount);


        WalletTransaction walletTransaction = WalletTransaction.builder()
                .amount(amount)
                .wallet(wallet)
                .ride(ride)
                .transactionId(transactionId)
                .transactionType(TransactionType.CREDIT)
                .transactionMethod(transactionMethod)
                .build();

        wallet.getTransactions().add(walletTransaction);

        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user,
                                        Double amount,
                                        String transactionId,
                                        Ride ride,
                                 TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance()-amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .amount(amount)
                .wallet(wallet)
                .ride(ride)
                .transactionId(transactionId)
                .transactionType(TransactionType.DEBIT)
                .transactionMethod(transactionMethod)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);

        return walletRepository.save(wallet);
    }

    @Override
    public void withdrawAllMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with the wallet ID:"+walletId));
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet walletBuilder = Wallet.builder()
                .user(user)
                .balance(0.0)
                .build();
        return walletRepository.save(walletBuilder);
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("User not available with the user id "+user.getId()));
    }
}
