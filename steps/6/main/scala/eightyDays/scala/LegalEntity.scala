package eightyDays.scala

case class LegalEntity(override val name: String, form: String) extends Partner(name)