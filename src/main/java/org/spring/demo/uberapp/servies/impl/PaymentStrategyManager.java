package org.spring.demo.uberapp.servies.impl;

import lombok.RequiredArgsConstructor;
import org.spring.demo.uberapp.entities.enums.PaymentMethod;
import org.spring.demo.uberapp.servies.PaymentStrategy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private final CashPaymentStrategy cashPaymentStrategy;
    private final WalletPaymentStrategy walletPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod){
        return switch (paymentMethod){
            case WALLET -> walletPaymentStrategy;
            case CASH -> cashPaymentStrategy;
        };
    }

}
