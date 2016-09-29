package eightyDays.scala211.bank

import eightyDays.scala211.bank.partner.{Identification, Partner, Person}

package partner {

  case class Identification(number: String = java.util.UUID.randomUUID.toString)

  abstract class Partner(val name: String)

  case class Person(firstName: String, override val name: String) extends Partner(name)

}

case class Bank(name: String, partners: Map[Identification, Partner] = Map[Identification, Partner]()) {

  def find(f: Partner => Boolean) =
    partners
    .filter(p => f(p._2))
    .values
    .toSet

  def addPartner(pPartner: Partner): (Identification, Bank) =
    partners
      .find(_._2 == pPartner)
      .map { entry => (entry._1, this) }
      .getOrElse {
        val result = Identification()
        (result, copy(partners = partners + (result -> pPartner)))
      }
}