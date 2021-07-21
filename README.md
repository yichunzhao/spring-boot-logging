# spring-boot-logging

## SpringBoot Logging

SpringBoot internal logging using Commons Logging; and public APIs using SLF4J, implemented by Logback by default. 
As using SpringBoot 2.x, we shouldn't worry about importing any other dependencies if we are using a Spring Boot Starter.

### Default Logback logging

The default Logger logging level is pre-set to INFO, meaning that TRACE and DEBUG messages are not visible.

We can overwrite the default logging level, by passing the –debug or –trace arguments on the command line without changing the configuration.
````
java -jar target/xyz.jar --trace
````

### Log levels

Spring Boot allows to set a more fine-grained logging level via environment variables. 

#### Via VM

````
-Dlogging.level.org.springframework=TRACE 
-Dlogging.level.com.ynz=TRACE
````

#### Via maven
````
mvn spring-boot:run 
  -Dspring-boot.run.arguments=--logging.level.org.springframework=TRACE
````  

#### Via application.properties

````
logging.level.root=WARN
logging.level.org.hibernate.SQL=TRACE
````

#### Via logging framework config. file

Change the logging level permanently by using our logging framework configuration file.

If defining the same logging level but with different levels in different options mentioned above,the lowest level will be applied.

### Logback Configuration Logging

Default logging configuration is mostly likely not enough for daily needs. 

A Logback configuration may introduce a different color and logging pattern, with separate specifications for console and file output, and with a decent rolling policy to avoid huge log files.

Handling logging configuration alone without polluting application properties.

When a file in the classpath has one of the following names, Spring Boot will automatically load it over the default configuration:
* logback-spring.xml
* logback.xml
* logback-spring.groovy
* logback.groovy

Spring recommends using the -spring variant over the plain ones.

#### Color-coded output
Color output is used to aid readability. You can set spring.output.ansi.enabled to a supported value to override the auto-detection.
* ALWAYS: Enable ANSI-colored output.
* DETECT: Try to detect whether ANSI coloring capabilities are available.
* NEVER: Disable ANSI-colored output.

## Output JPA Queries 

How printing out JPA generated SQL and binding query parameters. 

### Output SQL queries via Spring
The simplest way, but it is not recommended. It directly printout SQL to a standard output without any optimizations of a logging framework. Moreover, **it doesn't log the parameters of prepared statements**. 

````
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
````

### Output SQL queries via loggers

logs will be sent to a configured appender, or a default logback console appender.

````
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
````

