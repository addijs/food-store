package br.edu.ifpb.padroes.service.payment.impl;

public class DebitPayment implements Payment {

  @Override
  public void doPayment() {
    System.out.println("Do debit payment!");
  }
}
