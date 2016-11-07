package eightyDays.scala

case class Identification() {
  import java.util.UUID
  val number = UUID.randomUUID()
}
