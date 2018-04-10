package io.skysail.service.security.autoconfiguration

trait SkysailSecurityServer {

  def authenticate(username: String, password: String): Boolean

}
