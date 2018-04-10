package io.skysail.service.security.autoconfiguration.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("skysail.security")
class SkysailSecurityProperties(serverUrl: String, appName: String, debug: Boolean = false)
