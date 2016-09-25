package eightyDays.scala211

import eightyDays.scala211.bank.Bank
import eightyDays.Testdata
import org.openjdk.jmh.annotations.{Benchmark, Scope, Setup, State}

object Benchmarks {
  val testdata = new Testdata
  val bigBank = testdata.personsS(20000).foldLeft(Bank("Simple bank")) {
    (bank, person) =>
      bank.addPartner(person)._2
  }
}

@State(Scope.Benchmark)
class Benchmarks {

  @Setup
  def prepare(): Unit = Benchmarks.bigBank

  @Benchmark def measureSearch = {
    Benchmarks.bigBank.find(_.name == "Muster")
  }
}
