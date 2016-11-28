package eightyDays.scala

import java.util.UUID

class Bank(val name: String) {
  var persons = Map[UUID, Person]()

  def addPartner(pPerson: Person) = persons
    .find(_._2 == pPerson)
    .map(_._1)
    .getOrElse {
      val newID = UUID.randomUUID()
      persons = persons + (newID -> pPerson)
      newID
    }

  def person(pId: UUID) = persons get pId
}
