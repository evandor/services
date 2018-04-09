package io.skysail.service.monitor

import java.util.Properties

import org.h2.server.web.WebServlet
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.mail.javamail.JavaMailSender

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

  @Bean
  def mailSender(): JavaMailSender = {
    import org.springframework.mail.javamail.JavaMailSenderImpl
    val mailSender: JavaMailSenderImpl = new JavaMailSenderImpl
    mailSender.setHost("smtp.gmail.com")
    mailSender.setPort(587)

    mailSender.setUsername("my.gmail@gmail.com")
    mailSender.setPassword("password")

    val props: Properties = new Properties()
    props.put("mail.transport.protocol", "smtp")
    props.put("mail.smtp.auth", "true")
    props.put("mail.smtp.starttls.enable", "true")
    props.put("mail.debug", "true")
    mailSender.setJavaMailProperties(props)
    mailSender
  }

}