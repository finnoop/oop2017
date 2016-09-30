package eightyDays.scala211.bank

import eightyDays.scala211.bank.partner.{Identification, Partner, Person}

package partner {

  case class Identification(number: String = java.util.UUID.randomUUID.toString)

  abstract class Partner(val name: String)

  object Partner {
    def byName(name: String): Partner => Boolean = partner => partner.name == name
  }

  case class Person(firstName: String, override val name: String) extends Partner(name)

  object Person {
    def byFirstname(firstName: String): Partner => Boolean = {
      //case person: Person => person.firstName == firstName
      // alternative
      case Person(`firstName`, _) => true
      case _ => false
    }
  }
}

case class Bank(name: String, partners: Map[Identification, Partner] = Map[Identification, Partner]()) {

  // unnoetig, da partner schon einen default value hat
  def this(name:String) = this(name,Map())

  def find(predicate: Partner => Boolean): Option[Partner] = partners
    .find(p => predicate(p._2))
    .map(_._2)

  def filter(predicate: Partner => Boolean): Set[Partner] =
    partners
      .filter(p => predicate(p._2))
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