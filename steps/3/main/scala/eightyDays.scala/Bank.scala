package eightyDays.scala

case class Bank(name:String) {
  private[this] var partners = Map[Identification, Partner]()

  def partner(id:Identification) = partners get id
}
