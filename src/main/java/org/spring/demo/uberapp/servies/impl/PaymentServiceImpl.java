package org.spring.demo.uberapp.servies.impl;

import lombok.RequiredArgsConstructor;
import org.spring.demo.uberapp.entities.Payment;
import org.spring.demo.uberapp.entities.Ride;
import org.spring.demo.uberapp.servies.PaymentService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {



    @Override
    public void processPayment(Payment payment) {

    }

    @Override
    public Payment createNewPayment(Ride ride) {
        return null;
    }
}
