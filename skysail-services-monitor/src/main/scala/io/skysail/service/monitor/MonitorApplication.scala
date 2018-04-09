package io.skysail.service.monitor

import org.h2.server.web.WebServlet
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean

object MonitorApplication {
  def main(args: Array[String]): Unit = SpringApplication.run(classOf[MonitorApplication], args: _ *)
}

@SpringBootApplication
class MonitorApplication {

  @Bean
  def h2servletRegistration(): ServletRegistrationBean[WebServlet] = {
    val registration = new ServletRegistrationBean(new org.h2.server.web.WebServlet())
    registration.addUrlMappings("/console/*")
    registration
  }

}