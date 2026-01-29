package org.spring.demo.uberapp.servies.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.spring.demo.uberapp.dto.WalletTransactionDto;
import org.spring.demo.uberapp.entities.WalletTransaction;
import org.spring.demo.uberapp.repositories.WalletTransactionRepository;
import org.spring.demo.uberapp.servies.WalletTransactionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final ModelMapper modelMapper;
    private final WalletTransactionRepository walletTransactionRepository;

    @Override
    public void createNewWalletTransaction(WalletTransactionDto walletTransactionDto) {
        WalletTransaction walletTransaction = modelMapper.map(walletTransactionDto, WalletTransaction.class);
        walletTransactionRepository.save(walletTransactionRepository);
    }

}
