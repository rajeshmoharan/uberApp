package org.spring.demo.uberapp.servies.impl;

import lombok.RequiredArgsConstructor;
import org.spring.demo.uberapp.entities.Payment;
import org.spring.demo.uberapp.servies.PaymentStrategy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    @Override
    public void processPayment(Payment payment) {

    }
}
