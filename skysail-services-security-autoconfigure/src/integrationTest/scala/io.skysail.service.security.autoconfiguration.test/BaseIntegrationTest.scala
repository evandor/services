package de.kvb.mp.security.argus.integration.test

import io.skysail.service.security.autoconfiguration.{SecurityAutoConfiguration, SkysailSecurityServer}
import io.skysail.service.security.autoconfiguration.test.TestConfig
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.mock.web.MockHttpSession
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.util.Base64Utils
import org.springframework.web.context.WebApplicationContext

//import org.junit.Before
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.http.HttpHeaders
//import org.springframework.mock.web.MockHttpSession
//import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
//import org.springframework.test.context.ContextConfiguration
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
//import org.springframework.test.context.web.WebAppConfiguration
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
//import org.springframework.test.web.servlet.setup.MockMvcBuilders
//import org.springframework.util.Base64Utils
//import org.springframework.web.context.WebApplicationContext
//import de.kvb.argus.common.ex.ArgusException
//import de.kvb.argus.server.api.ArgusServer
//import de.kvb.argus.server.api.dto.AppConfig
//import de.kvb.mp.security.argus.config.ArgusSecurityAutoConfiguration
//import org.mockito.Matchers.any
//import org.mockito.Mockito.when


@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(classes = Array(
  classOf[SecurityAutoConfiguration],
  classOf[TestConfig]
))
@WebAppConfiguration
abstract class BaseIntegrationTest {

  @Autowired
  private val context = null

  @Autowired
  private var argusServer: SkysailSecurityServer = _

//  @Mock
//  protected var appConfig: AppConfig = null

  protected var mockMvc: MockMvc = _

  protected var mockHttpSession: MockHttpSession = _

  @Before
  def setup() {
//    when(argusServer.getAppConfig(any())).thenReturn(appConfig);
    //    when(argusServer.getAppConfigWithoutContext(any())).thenReturn(appConfig);
    //
    //    mockHttpSession = new MockHttpSession();
    //    mockMvc = MockMvcBuilders.webAppContextSetup(context)
    //      .apply(SecurityMockMvcConfigurers.springSecurity())
    //      .build();
  }

  def basicAuth(builder: MockHttpServletRequestBuilder, username: String, password: String): MockHttpServletRequestBuilder = {
    builder.header(HttpHeaders.AUTHORIZATION,
      "Basic " + Base64Utils.encodeToString((username + ":" + password).getBytes()))
  }
}