package io.skysail.service.security.autoconfiguration.test

import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity._
import org.springframework.web.bind.annotation.RequestMapping

class TestController {

  val RESOURCE_1 = "/resource-1"
  val RESOURCE_2 = "/resource-2"
  val RESOURCE_3 = "/resource-3"

  @RequestMapping(Array(RESOURCE_1))
  def resourceOne = ok("resource-1")

  @RequestMapping(Array(RESOURCE_2))
  def resourceTwo = ok("resource-2")

  @RequestMapping(Array(RESOURCE_3))
  def resourceThree = ok("resource-3")
}
