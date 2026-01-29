package org.spring.demo.uberapp.servies;

import org.spring.demo.uberapp.entities.Payment;

public interface PaymentStrategy {

    Double PLATFORM_COMMISSION = 0.3;

    void processPayment(Payment payment);
}
