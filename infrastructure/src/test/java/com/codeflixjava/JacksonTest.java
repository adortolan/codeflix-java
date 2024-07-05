package com.codeflixjava;

import com.codeflixjava.infrastructure.MySQLCleanUpExtension;
import com.codeflixjava.infrastructure.configuration.ObjectMapperConfig;
import com.codeflixjava.infrastructure.configuration.WebServerConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ActiveProfiles("test-integration")
@JsonTest(includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ObjectMapperConfig.class)
})
public @interface JacksonTest {
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    @ActiveProfiles("test-integration")
    @SpringBootTest(classes = WebServerConfig.class)
    @ExtendWith(MySQLCleanUpExtension.class)
    @interface IntegrationTest {
    }
}
