package io.skysail.service.monitor

import javax.persistence._

import io.skysail.service.monitor.domain.ConnectionResult

import scala.annotation.meta.param
import scala.beans.BeanProperty

//@Entity
//@Table(name = "buddy")
//class Buddy(first: String, last: String) {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
//  var id: Int = _
//
//  var firstName: String = first
//  var lastName: String  = last
//
//  def this() = this (null, null)
//
//  override def toString = id + " = " + firstName + " " + lastName
//}

@Entity
class Measurement2 {

  def this(name: String) {
    this()
    this.name = name
    //this.owner = owner
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @BeanProperty
  var id: Long = _

  @BeanProperty
  var monitor: Long = _

  @BeanProperty
  var name: String = _

//  @ManyToOne
//  @JoinColumn(name = "ownerId", nullable = false)
//  @BeanProperty
//  var owner: Customer = _

  override def toString: String = name
}

//@Entity
//class Measurement {
//
//  @Id
//  @GeneratedValue
//  @BeanProperty
//  var id: Long = _
//
//  @BeanProperty
//  var monitor: Long = _
//
//  @BeanProperty
//  var name: String = _
//
//  @BeanProperty
//  var duration: Long = _
//
//  @BeanProperty
//  var timestamp: Long = _
//
//  @BeanProperty
//  var result: ConnectionResult = _
//}

