package br.edu.ifpb.padroes.service.payment;

import br.edu.ifpb.padroes.service.payment.impl.Payment;

public class PaymentService {
  public void doPayment(Payment paymentStrategy) throws Exception {
    try {
      paymentStrategy.doPayment();
    } catch (Exception e) {
      throw new Exception("An error occurred while making the payment.");
    }
  }
}
