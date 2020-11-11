package com.github.trevorwhitney.starter;

import java.util.concurrent.Callable;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
    name = "starter",
    description = "command line starter app",
    version = "0.1.0",
    mixinStandardHelpOptions = true)
@Slf4j
public class App implements Callable<Integer> {

  static {
    System.setProperty("java.util.logging.SimpleFormatter.format",
        "[%1$tF %1$tT] [%4$-7s] %5$s %n");
  }

  @Option(
      names = {"-v", "--verbose"},
      description = "Print verbose output")
  private boolean verbose;

  public static void main(String[] args) {
    int exitCode = new CommandLine(new App()).execute(args);
    System.exit(exitCode);
  }

  @Override
  public Integer call() {
    Logger parent = Logger.getLogger("");
    Level targetLevel = verbose ? Level.FINEST : Level.WARNING;
    parent.setLevel(targetLevel);
    for (Handler handler : parent.getHandlers()) {
      handler.setLevel(targetLevel);
    }

    try {
      _call();
    } catch (Exception e) {
      log.error("Execution failed", e);
      return 1;
    }

    return 0;
  }

  private void _call() {
    log.info("Starting app");
  }
}
