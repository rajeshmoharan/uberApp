package org.spring.demo.uberapp.servies.impl;

import lombok.RequiredArgsConstructor;
import org.spring.demo.uberapp.entities.Driver;
import org.spring.demo.uberapp.entities.Payment;
import org.spring.demo.uberapp.entities.Wallet;
import org.spring.demo.uberapp.servies.PaymentStrategy;
import org.spring.demo.uberapp.servies.WalletService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CODPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;

    @Override
    public void processPayment(Payment payment) {

        Driver driver = payment.getRide().getDriver();
        Wallet driverWallet = walletService.findByUser(driver.getUser());
        double platformAmountToDeduct = payment.getAmount() * PLATFORM_COMMISSION;

        if(driverWallet.getBalance() > platformAmountToDeduct){
            driverWallet.setBalance(driverWallet.getBalance() - platformAmountToDeduct);
        }

    }
}
