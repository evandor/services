package io.skysail.service.config

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.config.server.EnableConfigServer

object ConfigServiceApplication {
  def main(args: Array[String]): Unit = SpringApplication.run(classOf[ConfigServiceApplication], args: _ *)
}

@SpringBootApplication
@EnableConfigServer
class ConfigServiceApplication {



}