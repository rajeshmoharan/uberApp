package org.spring.demo.uberapp.servies.impl;

import lombok.RequiredArgsConstructor;
import org.spring.demo.uberapp.entities.Driver;
import org.spring.demo.uberapp.entities.Payment;
import org.spring.demo.uberapp.entities.enums.PaymentStatus;
import org.spring.demo.uberapp.entities.enums.TransactionMethod;
import org.spring.demo.uberapp.repositories.PaymentRepository;
import org.spring.demo.uberapp.servies.PaymentService;
import org.spring.demo.uberapp.servies.PaymentStrategy;
import org.spring.demo.uberapp.servies.WalletService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
//    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {

        Driver driver = payment.getRide().getDriver();
        double platformAmountToDeduct = payment.getAmount() * PLATFORM_COMMISSION;

       walletService.deductMoneyFromWallet(driver.getUser(),platformAmountToDeduct,
               null,payment.getRide(), TransactionMethod.RIDE);

//       paymentService.updatePaymentStatus(payment, PaymentStatus.CONFIRMED);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
