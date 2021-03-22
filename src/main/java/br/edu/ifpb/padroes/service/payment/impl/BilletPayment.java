package br.edu.ifpb.padroes.service.payment.impl;

public class BilletPayment implements Payment {

  @Override
  public void doPayment() {
    System.out.println("Do billet payment!");
  }
}
