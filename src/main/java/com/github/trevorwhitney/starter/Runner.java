package com.github.trevorwhitney.starter;

import picocli.CommandLine;

public class Runner {
  public static void main(String[] args) {
    String[] case1 = new String[]{"-v"};
    int exitCode = new CommandLine(new App()).execute(case1);
    System.exit(exitCode);
  }
}
