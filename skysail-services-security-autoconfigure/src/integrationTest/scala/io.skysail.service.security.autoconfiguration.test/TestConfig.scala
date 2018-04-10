package io.skysail.service.security.autoconfiguration.test


import io.skysail.service.security.autoconfiguration.SkysailSecurityServer
import org.mockito.Mockito
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.test.util.ReflectionTestUtils
import org.springframework.web.servlet.config.annotation.{EnableWebMvc, WebMvcConfigurer}

@Order(Ordered.LOWEST_PRECEDENCE - 99)
@Configuration
class TestConfig extends WebMvcConfigurer {

  @Bean
  @Primary
  def skysailSecurityServer():SkysailSecurityServer = {
    Mockito.mock(classOf[SkysailSecurityServer])
  }

//  @Bean
//  @Primary
//  def argusAgent(ArgusAgent argusAgent, ArgusServer argusServer): SkysailSecurityServer {
//    ReflectionTestUtils.setField(argusAgent, "argusServer", argusServer);
//
//    return argusAgent;
//  }

  @Bean
  def testController(): TestController = {
    new TestController()
  }
}
