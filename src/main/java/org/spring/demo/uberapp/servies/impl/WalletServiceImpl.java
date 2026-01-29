package org.spring.demo.uberapp.servies.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.spring.demo.uberapp.entities.User;
import org.spring.demo.uberapp.entities.Wallet;
import org.spring.demo.uberapp.exceptions.ResourceNotFoundException;
import org.spring.demo.uberapp.repositories.WalletRepository;
import org.spring.demo.uberapp.servies.WalletService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final ModelMapper modelMapper;

    @Override
    public Wallet addMoneyToWallet(User user, Double amount) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance()+amount);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet deductMoneyFromWallet(User user, Double amount) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance()-amount);
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
