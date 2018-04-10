package io.skysail.service.security.autoconfiguration.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

@Configuration
class TechnicalAuthenticationConfig {

  private var properties: SkysailSecurityProperties = _

  @Autowired
  def TechnicalAuthenticationConfig(properties: SkysailSecurityProperties) = {
    this.properties = properties;
  }

//  @Bean
//  @ConditionalOnMissingBean
//  public TechnicalEndpointConfigurer technicalEndpointConfigurer(ContextPathRegistry registry,
//    BasicAuthenticationFilter basicAuthenticationFilter) {
//    return new TechnicalEndpointConfigurer(properties, registry, basicAuthenticationFilter);
//  }
//
//  @Bean
//  @ConditionalOnMissingBean
//  public AuthenticationEntryPoint defaultTechnicalAuthenticationEntryPoint() throws Exception {
//    BasicAuthenticationEntryPoint basicAuthEntryPoint = new BasicAuthenticationEntryPoint();
//    basicAuthEntryPoint.setRealmName(properties.getTechnical().getRealm());
//    basicAuthEntryPoint.afterPropertiesSet();
//
//    // Do not set WWW-Authenticate header for JavaScript requests, as this may cause the browser to redirect a
//    // request (copied from Spring Security HttpBasicConfigurer)
//    LinkedHashMap<RequestMatcher, AuthenticationEntryPoint> entryPoints = new LinkedHashMap<>();
//    entryPoints.put(X_REQUESTED_WITH, new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
//
//    DelegatingAuthenticationEntryPoint defaultEntryPoint = new DelegatingAuthenticationEntryPoint(
//      entryPoints);
//    defaultEntryPoint.setDefaultEntryPoint(basicAuthEntryPoint);
//
//    return defaultEntryPoint;
//  }
//
//  @Bean
//  @ConditionalOnMissingBean
//  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
//  public BasicAuthenticationFilter basicAuthenticationFilter(AuthenticationManager authenticationManager,
//    AuthenticationEntryPoint defaultAuthenticationEntryPoint,
//    Optional<AuthenticationSuccessHandler> authenticationSuccessHandler) {
//    BasicAuthenticationFilter filter = new BasicAuthenticationFilter(authenticationManager, defaultAuthenticationEntryPoint);
//    authenticationSuccessHandler.ifPresent(filter::setAuthenticationSuccessHandler);
//
//    return filter;
//  }
//
//  @Bean
//  public FilterRegistrationBean basicAuthenticationFilterRegistrationBean(BasicAuthenticationFilter basicAuthenticationFilter) {
//    FilterRegistrationBean<BasicAuthenticationFilter> filterRegistration = new FilterRegistrationBean<>(basicAuthenticationFilter);
//    filterRegistration.setEnabled(false);
//
//    return filterRegistration;
//  }
}
