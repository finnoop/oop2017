package eightyDays.scala

import java.time.LocalDateTime

import eightyDays.scala.account.{Account, Amount, Booking}

case class Bank(name: String) {
  private[this] var partners = Map[Identification, Partner]()
  private[this] var accounts = Map[Identification, Account]()

  def partner(id: Identification) = partners get id

  def addPartner(pPartner: Partner) = partners.find(_._2 == pPartner)
    .map(_._1)
    .getOrElse {
      val newId = Identification()
      partners = partners + (newId -> pPartner)
      newId
    }

  def account(identification: Identification) = accounts.get(identification)

  def openAccount(owner: Identification, factoryMethod: (Identification, Seq[Booking]) => Account): Account = {
    val account = factoryMethod(owner, Seq())
    accounts += (account.number -> account)
    account
  }

  protected def post(account: Identification, action: Account => Account) = {
    val updatedAccount = action(accounts(account))
    accounts = accounts - account + (account -> updatedAccount)
    updatedAccount
  }

  def deposit(account: Identification, value: Amount, valuta: LocalDateTime = LocalDateTime.now()) = post(account, _.deposit(value, valuta))

  def withdraw(account: Identification, value: Amount, valuta: LocalDateTime = LocalDateTime.now()) = post(account, _.withdraw(value, valuta))

  def assets(owner: Identification) = accounts
    .filter(_._2.owner == owner)
    .map(_._2.balance)
    .sum

  protected def partner(predicate: Partner => Boolean) = partners.filter(entry => predicate(entry._2)).keys

  def searchPartner(name: String) = partner(_.name == name)

  def searchPerson(firstName: String) = partner(_ match {
    case Person(`firstName`, _) => true
    case _ => false
  })

  // remove later
  def abc = accounts.map(_._2.owner).toSet.intersect(partners.keySet).map { owner => owner -> assets(owner) }.groupBy(_._2 match {
    case asset if asset > 1000 => "A"
    case asset if asset > 500 => "B"
    case _ => "C"
  })
}