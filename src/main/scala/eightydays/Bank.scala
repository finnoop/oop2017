package eightydays

case class Bank(name:String, var partners: Map[Identification, Partner] = Map())
