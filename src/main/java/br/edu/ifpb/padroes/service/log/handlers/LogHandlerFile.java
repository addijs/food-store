package br.edu.ifpb.padroes.service.log.handlers;

public class LogHandlerFile implements LogHandler {
  public void log(String message) {
    System.out.println("## save data to a file ##");
  }
}
