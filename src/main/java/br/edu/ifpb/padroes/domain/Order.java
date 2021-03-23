package br.edu.ifpb.padroes.domain;

import br.edu.ifpb.padroes.service.log.LogService;
import br.edu.ifpb.padroes.service.mail.EventManager;
import br.edu.ifpb.padroes.service.order.OrderState;
import br.edu.ifpb.padroes.service.order.OrderStateEnum;
import br.edu.ifpb.padroes.service.payment.PaymentService;
import br.edu.ifpb.padroes.service.payment.impl.Payment;

import java.time.Instant;
import java.util.List;

public class Order {

  private Long id;
  private Customer customer;
  private Instant creationDate;
  private Restaurant restaurant;
  private List<OrderItem> orderItemList;
  private OrderState currentState;

  public Order() {
    this.currentState = OrderStateEnum.IN_PROGRESS;
  }

  public void doPaymentOrder(PaymentService paymentService, Payment paymentStrategy) {
    this.currentState = currentState.doPayment(paymentService, paymentStrategy);
  }

  public void sendNotification(Order order, EventManager events) {
    this.currentState.sendNotification(order, events);
  }

  public void emitLog(Order order, LogService logService) {
    this.currentState.emitLog(order, logService);
  }

  public void hasBeenCanceled() {
    this.currentState = OrderStateEnum.CANCELED;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Instant getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Instant creationDate) {
    this.creationDate = creationDate;
  }

  public Restaurant getRestaurant() {
    return restaurant;
  }

  public void setRestaurant(Restaurant restaurant) {
    this.restaurant = restaurant;
  }

  public List<OrderItem> getOrderItemList() {
    return orderItemList;
  }

  public void setOrderItemList(List<OrderItem> orderItemList) {
    this.orderItemList = orderItemList;
  }
}
