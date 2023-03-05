package io.github.nibiroo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// ANNOTATIONS
//  @Target(ElementType.TYPE) -> Means that the Annotation can be used for specific type
  // ElementType.TYPE -> Only class can use the Annotation
  // ElementType.METHOD -> Only method can use the Annotation
//  @Retention(RetentionPolicy.RUNTIME) -> Determine the "lifetime" of the Annotation
  // RetentionPolicy.RUNTIME -> Available at runtime
  // RetentionPolicy.SOURCE -> Retained at source code and shall not be included in bytecode
  // RetentionPolicy.CLASS -> Availabe at bytecode and shall not be included in runtime
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Configuration
@Profile("development")
public @interface Development {
}
