package io.skysail.service.monitor

import java.net.URL
import javax.persistence._

import com.fasterxml.jackson.annotation.JsonIgnore

import scala.annotation.meta.{field, param}
import scala.beans.BeanProperty

//@Entity
//class Monitor(
//               @Id
//               @(scala.beans.BeanProperty@param)
//               @GeneratedValue(strategy = GenerationType.AUTO) id: Long,
//
//               name: String,
//               email: String,
//               url: URL,
//               @OneToMany(mappedBy = "monitor", orphanRemoval = true, cascade = Array(CascadeType.PERSIST))
//               measurements: List[Measurement2] = List()
//             )

@Entity
case class Monitor(
                     @(Id@field)
                     @(GeneratedValue@field)
                     @BeanProperty
                     var id: Long,

                     @BeanProperty
                     var name: String,

                     @BeanProperty
                     var email: String,

                     @BeanProperty
                     var url: URL,

                     @(JsonIgnore@field)
                     @(OneToMany@field)(mappedBy = "monitor", orphanRemoval = true, cascade = Array(CascadeType.PERSIST))
                     @BeanProperty
                     var measurements: java.util.List[Measurement2]
                   ) {

  // Need to specify an empty constructor
  def this() {
    this(0, "", "", null, new java.util.ArrayList[Measurement2]())
  }

  //override def toString: String = s"$firstName $lastName"

}