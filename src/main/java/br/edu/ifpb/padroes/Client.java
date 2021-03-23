package br.edu.ifpb.padroes;

import br.edu.ifpb.padroes.domain.Order;
import br.edu.ifpb.padroes.service.mail.EmailNotification;
import br.edu.ifpb.padroes.service.order.OrderManager;
import br.edu.ifpb.padroes.service.payment.impl.CreditCardPayment;

public class Client {
    public static void main(String[] args) {

        Order order = new Order();
        OrderManager orderManager = new OrderManager(order);
        orderManager.events.subscribe("mail", new EmailNotification());

        orderManager.payOrder(new CreditCardPayment());
        orderManager.cancelOrder();

    }
}
