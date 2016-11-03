package eightyDays.scala

import java.util.UUID

abstract class Partner(val name: String)

case class Person(override val name: String, firstName: String) extends Partner(name)

case class LegalEntity(override val name: String, form: String) extends Partner(name)

case class Identification() {
  val number = UUID.randomUUID()
}