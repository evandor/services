package io.skysail.service.security.autoconfiguration.config

import org.scalatest.{BeforeAndAfterEach, FunSuite}

class AuthenticationManagerConfigTest extends FunSuite with BeforeAndAfterEach {

  var config: AuthenticationManagerConfig = _

  override def beforeEach() {
    val properties = new SkysailSecurityProperties("security-server-url", "name")
    config = new AuthenticationManagerConfig(properties)
  }

  test("not null") {
    assert(config.skysailSecurityServer() != null)
  }

//  test("") {
//
//  }

//  @Test
//  public void argusAuthenticationUserDetailsServiceIsNotNull() {
//    assertNotNull(config.argusAuthenticationUserDetailsService());
//  }
//
//  @Test
//  public void preAuthenticatedAuthenticationProviderIsNotNull() {
//    // given
//    ArgusAuthenticationUserDetailsService service = Mockito.mock(ArgusAuthenticationUserDetailsService.class);
//
//    // then
//    assertNotNull(config.preAuthenticatedAuthenticationProvider(service));
//  }
//
//  @Test
//  public void argusTechnicalUserAuthenticationProviderIsNotNull() {
//    // given
//    ArgusServer server = Mockito.mock(ArgusServer.class);
//
//    // then
//    assertNotNull(config.technicalUserAuthenticationProvider(server));
//  }
//
//  @Test
//  public void authenticationManagerIsInitializedCorrectly() {
//    // given
//    PreAuthenticatedAuthenticationProvider preAuthProvider = Mockito.mock(PreAuthenticatedAuthenticationProvider.class);
//    ArgusTechnicalUserAuthenticationProvider technicalProvider = Mockito.mock(ArgusTechnicalUserAuthenticationProvider.class);
//
//    // when
//    ProviderManager manager = config.authenticationManager(preAuthProvider, technicalProvider);
//
//    // then
//    assertNotNull(manager);
//    assertEquals(2, manager.getProviders().size());
//    assertSame(preAuthProvider, manager.getProviders().get(0));
//    assertSame(technicalProvider, manager.getProviders().get(1));
//  }
}
