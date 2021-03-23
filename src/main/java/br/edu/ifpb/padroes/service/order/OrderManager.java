package br.edu.ifpb.padroes.service.order;

import br.edu.ifpb.padroes.domain.Order;
import br.edu.ifpb.padroes.service.log.LogService;
import br.edu.ifpb.padroes.service.mail.EventManager;
import br.edu.ifpb.padroes.service.payment.PaymentService;
import br.edu.ifpb.padroes.service.payment.impl.Payment;

public class OrderManager {

  public OrderManager(Order order) {
    this.order = order;
    this.events = new EventManager("mail");
  }

  public EventManager events;

  private Order order;

  private PaymentService paymentService = new PaymentService();

  private LogService logService = new LogService();

  public void payOrder(Payment paymentStrategy) {
    order.setStatus(Order.OrderStatus.IN_PROGRESS);
    try {
      paymentService.doPayment(paymentStrategy);
      order.setStatus(Order.OrderStatus.PAYMENT_SUCCESS);
      events.notify("mail", String.format("Order %d completed successfully", order.getId()));
      logService.info("payment finished");
    } catch (Exception e) {
      logService.error("payment refused");
      order.setStatus(Order.OrderStatus.PAYMENT_REFUSED);
      events.notify("mail", String.format("Order %d refused", order.getId()));
    }
  }

  public void cancelOrder() {
    order.setStatus(Order.OrderStatus.CANCELED);
    events.notify("mail", String.format("Order %d canceled", order.getId()));
    logService.debug(String.format("order %d canceled", order.getId()));
  }

}
