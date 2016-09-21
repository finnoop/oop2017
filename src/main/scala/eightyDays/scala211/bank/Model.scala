package eightyDays.scala211.bank

import eightyDays.scala211.bank.partner.{Identification, Partner}

package partner {

  import java.util.UUID

  case class Identification(number:String)
  case object Identification {
    def apply(): Identification = new Identification(UUID.randomUUID.toString)
  }
  abstract class Partner(val name:String)
  case class Person(firstName:String, override val name: String) extends Partner(name)
}

case class Bank(name:String, partners: Map[Identification, Partner] = Map[Identification, Partner]()) {

  def searchPartners(name: String) =
    partners
      .filter(_._2.name == name)
      .map(_._2)
      .toSet

  def addPartner(pPartner: Partner) =
    partners
      .find(_._2 == pPartner)
      .map{entry => (entry._1, this)}
      .getOrElse{
        val result = Identification()
        (result, copy(partners = partners + (result -> pPartner)))
    }

}