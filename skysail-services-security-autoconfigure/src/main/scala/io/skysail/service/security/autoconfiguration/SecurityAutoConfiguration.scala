package io.skysail.service.security.autoconfiguration

import io.skysail.service.security.autoconfiguration.config._
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.{Configuration, Import}
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@Order(Ordered.LOWEST_PRECEDENCE - 100)
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(Array(classOf[SkysailSecurityProperties]))
@Import(Array(
  classOf[AuthenticationManagerConfig],
  classOf[ActuatorConfig],
  classOf[SkysailAuthenticationConfig],
  classOf[TechnicalAuthenticationConfig]
))
class SecurityAutoConfiguration() {


}

