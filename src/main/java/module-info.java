module com.github.trevorwhitney.starter {
  exports com.github.trevorwhitney.starter;
  opens com.github.trevorwhitney.starter;

  requires static lombok;
  requires jsr305;
  requires info.picocli;
  requires org.apache.logging.log4j;
  requires org.apache.logging.log4j.core;
}
