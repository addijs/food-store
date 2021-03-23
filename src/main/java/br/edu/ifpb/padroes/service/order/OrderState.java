package br.edu.ifpb.padroes.service.order;

import br.edu.ifpb.padroes.domain.Order;
import br.edu.ifpb.padroes.service.log.LogService;
import br.edu.ifpb.padroes.service.mail.EventManager;
import br.edu.ifpb.padroes.service.payment.PaymentService;
import br.edu.ifpb.padroes.service.payment.impl.Payment;

public interface OrderState {
  OrderState doPayment(PaymentService paymentService, Payment paymentStrategy);
  void sendNotification(Order order, EventManager events);
  void emitLog(Order order, LogService logService);
}
