package eightyDays.scala

abstract class Partner(val name: String)

case class Person(override val name: String, firstName: String) extends Partner(name)

case class LegalEntity(override val name: String, form: String) extends Partner(name)

case class Identification() {
  import java.util.UUID
  val number = UUID.randomUUID()
}