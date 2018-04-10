package io.skysail.service.security.autoconfiguration.authentication

import java.util
import java.util.Collections

import io.skysail.service.security.autoconfiguration.SkysailSecurityServer
import io.skysail.service.security.autoconfiguration.config.SkysailSecurityProperties
import org.springframework.security.authentication.{AuthenticationProvider, BadCredentialsException, UsernamePasswordAuthenticationToken}
import org.springframework.security.core.Authentication

class SkysailTechnicalUserAuthenticationProvider(
                                                  securityServer: SkysailSecurityServer,
                                                  properties: SkysailSecurityProperties
                                                ) extends AuthenticationProvider {

  override def authenticate(authentication: Authentication): Authentication = {
    val username = authentication.getName().toLowerCase()
    val password = authentication.getCredentials().toString()
    //Map<String, Set<String>> roles;

    try {
      if (securityServer.authenticate(username, password)) {
        // roles = argusServer.getUsersRoles(properties.getAppName(), new String[]{username});
        // and: BadCredentialsException if error
        return new UsernamePasswordAuthenticationToken(
          authentication.getPrincipal(),
          authentication.getCredentials(),
          util.Arrays.asList(/*new SimpleGrantedAuthority(properties.getTechnical().getRole())*/))
      }
    } catch {
      case e: Exception => throw new BadCredentialsException(e.getMessage, e);
        // or AuthenticationServiceException
    }
    throw new BadCredentialsException("")
  }

  override def supports(authentication: Class[_]): Boolean = {
    authentication.equals(classOf[UsernamePasswordAuthenticationToken])
  }

}
