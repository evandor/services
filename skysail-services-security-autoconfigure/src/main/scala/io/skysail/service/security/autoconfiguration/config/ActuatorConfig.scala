package io.skysail.service.security.autoconfiguration.config

import io.skysail.service.security.autoconfiguration.ContextPathDefinition
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementServerProperties
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnClass(Array(classOf[ManagementServerProperties]))
class ActuatorConfig {

  def actuatorEndpointDefinition( properties: ManagementServerProperties) =
    ContextPathDefinition(properties.getServlet.getContextPath/*, ContextPathDefinition.Type.TECHNICAL*/)
}
