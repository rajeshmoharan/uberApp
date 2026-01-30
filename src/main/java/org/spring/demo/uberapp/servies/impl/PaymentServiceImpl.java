package org.spring.demo.uberapp.servies.impl;

import lombok.RequiredArgsConstructor;
import org.spring.demo.uberapp.entities.Payment;
import org.spring.demo.uberapp.entities.Ride;
import org.spring.demo.uberapp.entities.enums.PaymentStatus;
import org.spring.demo.uberapp.exceptions.ResourceNotFoundException;
import org.spring.demo.uberapp.repositories.PaymentRepository;
import org.spring.demo.uberapp.servies.PaymentService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {


    private final PaymentStrategyManager paymentStrategyManager;
    private final PaymentRepository paymentRepository;


    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride)
                .orElseThrow(() -> new ResourceNotFoundException("Payment details not found with the ride :" + ride.getId()));
        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);
    }

    @Override
    public Payment createNewPayment(Ride ride) {

        Payment payment = Payment.builder()
                .paymentMethod(ride.getPaymentMethod())
                .paymentStatus(PaymentStatus.PENDING)
                .amount(ride.getFare())
                .ride(ride)
                .build();

        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
        payment.setPaymentStatus(paymentStatus);
        paymentRepository.save(payment);
    }
}
