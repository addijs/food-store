package br.edu.ifpb.padroes.service.payment.impl;

public class CreditCardPayment implements Payment {

  @Override
  public void doPayment() {
    System.out.println("Do credit card payment!");
  }
}
