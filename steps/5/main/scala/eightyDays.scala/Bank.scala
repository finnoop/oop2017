package eightyDays.scala

case class Bank(name: String) {
  private[this] var partners = Map[Identification, Partner]()

  def partner(id: Identification) = partners get id

  def addPartner(pPartner: Partner) = partners
                                        .find(_._2 == pPartner)
                                        .map(_._1)
                                        .getOrElse {
                                          val newId = Identification()
                                          partners += (newId -> pPartner)
                                          newId
                                        }
}