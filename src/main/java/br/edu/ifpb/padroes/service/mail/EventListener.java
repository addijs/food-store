package br.edu.ifpb.padroes.service.mail;

import br.edu.ifpb.padroes.domain.Customer;

public interface EventListener {
  void update(String message);
  void update(String message, Customer customer);
}
