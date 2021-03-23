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
    order.doPaymentOrder(paymentService, paymentStrategy);
    order.sendNotification(order, events);
    order.emitLog(order, logService);
  }

  public void cancelOrder() {
    order.hasBeenCanceled();
    order.sendNotification(order, events);
    order.emitLog(order, logService);
  }

}
