package com.github.trevorwhitney.starter;

import java.util.concurrent.Callable;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
    name = "starter",
    description = "command line starter app",
    version = "0.1.0",
    mixinStandardHelpOptions = true)
@Log4j2
public class App implements Callable<Integer> {

  @Option(
      names = {"-v", "--verbose"},
      description = "Print verbose output")
  private boolean verbose;

  public static void main(String[] args) {
    String[] hardCodedArgs = new String[]{"-v"};
    int exitCode = new CommandLine(new App()).execute(hardCodedArgs);

//    int exitCode = new CommandLine(new App()).execute(args);
    System.exit(exitCode);
  }

  @Override
  public Integer call() {
    if (verbose) {
      Configurator.setLevel("com.github.trevorwhitney.starter", Level.DEBUG);
    } else {
      Configurator.setLevel("com.github.trevorwhitney.starter", Level.WARN);
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
    log.debug("Starting app");
  }
}
