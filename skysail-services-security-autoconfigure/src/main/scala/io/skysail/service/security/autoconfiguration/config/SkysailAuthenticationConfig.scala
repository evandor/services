package io.skysail.service.security.autoconfiguration.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

@Configuration
class SkysailAuthenticationConfig {

  private var properties: SkysailSecurityProperties = _

  @Autowired
  def SkysailAuthenticationConfig(properties: SkysailSecurityProperties ) = {
    this.properties = properties
  }

//  @Bean
//  @ConditionalOnMissingBean
//  public ArgusEndpointConfigurer argusEndpointConfigurer(ContextPathRegistry registry,
//    ArgusAgent argusAgentFilter,
//    ArgusPreAuthenticatedProcessingFilter argusPreAuthenticatedProcessingFilter) {
//    return new ArgusEndpointConfigurer(properties, registry, argusAgentFilter, argusPreAuthenticatedProcessingFilter);
//  }
//
//  @Bean
//  public ArgusAgent argusAgentFilter() {
//    ArgusAgent agent = new ArgusAgent();
//    agent.setArgusServerUrl(properties.getArgusServerUrl());
//    agent.setArgusServerRequestInterceptor(new ArgusServerDelegator());
//    agent.initArgusAgent();
//
//    return agent;
//  }
//
//  @Bean
//  public FilterRegistrationBean argusAgentRegistrationBean(ArgusAgent argusAgentFilter) {
//    FilterRegistrationBean<ArgusAgent> filterRegistration = new FilterRegistrationBean<>(argusAgentFilter);
//    filterRegistration.setEnabled(false);
//
//    return filterRegistration;
//  }
//
//  @Bean
//  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
//  public ArgusPreAuthenticatedProcessingFilter argusPreAuthenticatedProcessingFilter(AuthenticationManager authenticationManager, Optional<AuthenticationSuccessHandler> authenticationSuccessHandler) {
//    ArgusPreAuthenticatedProcessingFilter filter = new ArgusPreAuthenticatedProcessingFilter(authenticationManager);
//    authenticationSuccessHandler.ifPresent(filter::setAuthenticationSuccessHandler);
//
//    return filter;
//  }
//
//  @Bean
//  public FilterRegistrationBean argusPreAuthenticatedProcessingFilterRegistrationBean(ArgusPreAuthenticatedProcessingFilter argusPreAuthenticatedProcessingFilter) {
//    FilterRegistrationBean<ArgusPreAuthenticatedProcessingFilter> filterRegistration = new FilterRegistrationBean<>(argusPreAuthenticatedProcessingFilter);
//    filterRegistration.setEnabled(false);
//
//    return filterRegistration;
//  }

}
