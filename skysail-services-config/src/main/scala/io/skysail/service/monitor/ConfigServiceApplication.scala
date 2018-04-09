package io.skysail.service.monitor

import java.util.Properties

import org.h2.server.web.WebServlet
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.mail.javamail.JavaMailSender

object ConfigServiceApplication {
  def main(args: Array[String]): Unit = SpringApplication.run(classOf[ConfigServiceApplication], args: _ *)
}

@SpringBootApplication
class ConfigServiceApplication {



}