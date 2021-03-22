package br.edu.ifpb.padroes.service.log;

import br.edu.ifpb.padroes.service.log.handlers.LogHandler;
import br.edu.ifpb.padroes.service.log.handlers.LogHandlerFile;

public class LogService {

  private LogHandler logHandler;

  public LogService() {
    this.logHandler = new LogHandlerFile();
  }

  public void debug(String message) {
    logHandler.log("stack trace");
    logHandler.log(message);
  }

  public void info(String message) {
    logHandler.log(message);
  }

  public void error(String message) {
    logHandler.log("error");
    logHandler.log(message);
  }
}
