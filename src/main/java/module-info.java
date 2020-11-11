module com.github.trevorwhitney.starter {
  exports com.github.trevorwhitney.starter;
  opens com.github.trevorwhitney.starter;

  requires static lombok;
  requires jsr305;
  requires info.picocli;
  requires org.slf4j;
  requires java.logging;
}
