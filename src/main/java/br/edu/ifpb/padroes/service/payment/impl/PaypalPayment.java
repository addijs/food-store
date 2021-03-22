package br.edu.ifpb.padroes.service.payment.impl;

public class PaypalPayment implements Payment {

  @Override
  public void doPayment() {
    System.out.println("Do paypal payment!");
  }
}
