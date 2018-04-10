package io.skysail.service.security.autoconfiguration.config

import java.util

import io.skysail.service.security.autoconfiguration.authentication.SkysailTechnicalUserAuthenticationProvider
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider

@Configuration
class AuthenticationManagerConfig(properites: SkysailSecurityProperties) {

  @Bean
  def skysailSecurityServer(): Any = {
    //new skysailServerSoapProxy().setskysailServerUrl(properties.getskysailServerUrl());
    null
  }

//  @Bean
//  public skysailAuthenticationUserDetailsService skysailAuthenticationUserDetailsService() {
//    return new skysailAuthenticationUserDetailsService();
//  }
//
//  @Bean
//  public PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider(skysailAuthenticationUserDetailsService authenticationUserDetailsService) {
//    PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
//    provider.setPreAuthenticatedUserDetailsService(authenticationUserDetailsService);
//
//    return provider;
//  }
//
//  @Bean
//  public skysailTechnicalUserAuthenticationProvider technicalUserAuthenticationProvider(skysailServer skysailServer) {
//    return new SkysailTechnicalUserAuthenticationProvider(skysailServer, properties);
//  }
//
  @Bean
  def authenticationManager(
                             providerA: PreAuthenticatedAuthenticationProvider,
                             providerB: SkysailTechnicalUserAuthenticationProvider ): ProviderManager = {
    new ProviderManager(util.Arrays.asList(providerA, providerB));
  }
}
