package io.skysail.service.security.autoconfiguration.config

import io.skysail.service.security.autoconfiguration.ContextPathDefinition
import org.scalatest.FunSuite
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementServerProperties

class ActuatorConfigTest extends FunSuite {

  test("An empty Set should have size 0") {

    val properties = new ManagementServerProperties();
    properties.getServlet().setContextPath("/context-path");

    val definition = new ActuatorConfig().actuatorEndpointDefinition(properties);

    assert("/context-path" == definition.contextPath)
    //assert(ContextPathDefinition.Type.TECHNICAL, definition.getType());
  }
}
