package br.edu.ifpb.padroes.service.order;

import br.edu.ifpb.padroes.domain.Order;
import br.edu.ifpb.padroes.service.log.LogService;
import br.edu.ifpb.padroes.service.mail.EventManager;
import br.edu.ifpb.padroes.service.payment.PaymentService;
import br.edu.ifpb.padroes.service.payment.impl.Payment;

public enum OrderStateEnum implements OrderState {
  IN_PROGRESS {
    public OrderState doPayment(PaymentService paymentService, Payment paymentStrategy) {
      try {
        paymentService.doPayment(paymentStrategy);
        return PAYMENT_SUCCESS;
      } catch (Exception e) {
        return PAYMENT_REFUSED;
      }
    }

    public void sendNotification(Order order, EventManager events) {
      System.out.println("Order still in progress");
    }

    public void emitLog(Order order, LogService logService) {
      System.out.println("Order still in progress");
    }
  },

  PAYMENT_SUCCESS {
    public OrderState doPayment(PaymentService paymentService, Payment paymentStrategy) {
      System.out.println("Payment has already been made");
      return this;
    }

    public void sendNotification(Order order, EventManager events) {
      events.notify("mail", String.format("Order %d completed successfully", order.getId()));
    }

    public void emitLog(Order order, LogService logService) {
      logService.info("payment finished");
    }
  },

  PAYMENT_REFUSED {
    public OrderState doPayment(PaymentService paymentService, Payment paymentStrategy) {
      System.out.println("Payment has been refused");
      return this;
    }

    public void sendNotification(Order order, EventManager events) {
      events.notify("mail", String.format("Order %d refused", order.getId()));
    }

    public void emitLog(Order order, LogService logService) {
      logService.error("payment refused");
    }
  },

  CANCELED {
    public OrderState doPayment(PaymentService paymentService, Payment paymentStrategy) {
      System.out.println("The order has been canceled");
      return this;
    }

    public void sendNotification(Order order, EventManager events) {
      events.notify("mail", String.format("Order %d canceled", order.getId()));
    }

    public void emitLog(Order order, LogService logService) {
      logService.debug(String.format("order %d canceled", order.getId()));
    }
  }

}
