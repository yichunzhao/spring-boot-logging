# spring-boot-logging

## SpringBoot Logging

SpringBoot internal logging using Commons Logging; and public APIs using SLF4J, implemented by Logback by default. 
SpringBoot defaults Logback. As using SpringBoot 2.x, we shouldn't worry about importing any other dependencies if
we are using a Spring Boot Starter.

### Default Logback logging

The default logging level of the Logger is pre-set to INFO, meaning that TRACE and DEBUG messages are not visible.

We can overwrite the default logging level, by passing the –debug or –trace arguments on the command line 
without changing the configuration.
````
java -jar target/xyz.jar --trace
````

### Log levels
Spring Boot also gives us access to a more fine-grained log level setting via environment variables. 

there are several ways: 

#### Via VM

````
-Dlogging.level.org.springframework=TRACE 
-Dlogging.level.com.baeldung=TRACE
````

#### Via maven
````
mvn spring-boot:run 
  -Dspring-boot.run.arguments=--logging.level.org.springframework=TRACE,--logging.level.com.baeldung=TRACE
````  

#### Via application.properties

````
logging.level.root=WARN
logging.level.org.hibernate.SQL=TRACE
````

#### Via logging framework config. file

Change the logging level permanently by using our logging framework configuration file.
If defining a logging level with different levels in multiple times using the different options mentioned above,the lowest 
level will be used.

### Logback Configuration Logging

Default logging configuration is mostly likely not enough for daily needs. 

A Logback configuration may introduce a different color and logging pattern, with separate 
specifications for console and file output, and with a decent rolling policy to avoid huge log files.

Handling logging configuration alone without polluting application properties.

When a file in the classpath has one of the following names, Spring Boot will automatically load it over the default configuration:
Spring recommends using the -spring variant over the plain ones.

* logback-spring.xml
* logback.xml
* logback-spring.groovy
* logback.groovy

#### Color-coded output
If your terminal supports ANSI, color output is used to aid readability. You can set spring.output.ansi.enabled to a supported value to override the auto-detection.
* ALWAYS: Enable ANSI-colored output.
* DETECT:  Try to detect whether ANSI coloring capabilities are available.
* NEVER: Disable ANSI-colored output.

## Output JPA Queries 

### Output SQL queries
The simplest way to output SQL queries, but it is not recommended. it directly unloads everything to standard output without
any optimizations of a logging framework. Moreover, **it doesn't log the parameters of prepared statements**. 

````
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
````

### Output SQL queries via loggers

logs will be sent to the configured appender. By default, Spring Boot uses logback with a standard out appender.

````
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
````

