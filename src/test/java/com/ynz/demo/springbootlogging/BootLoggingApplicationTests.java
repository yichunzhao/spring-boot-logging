package com.ynz.demo.springbootlogging;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BootLoggingApplicationTests {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Autowired
    private Environment environment;

    @Test
    void contextLoads() {
        assertThat(applicationContext, Matchers.notNullValue());
        assertAll(
                () -> assertThat(applicationContext, Matchers.notNullValue()),
                () -> assertThat(environment, Matchers.notNullValue()));
    }

    @Test
    void DataSourceUrl_ShouldPointingToH2Mem() {
        String actual = environment.getProperty("spring.datasource.url");
        assertNotNull(actual);
        assertEquals("jdbc:h2:mem:db", actual);
    }

}
