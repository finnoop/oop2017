package eightyDays.scala

import eightyDays.scala.bank.Bank
import org.openjdk.jmh.annotations.{Benchmark, Scope, Setup, State}

object Benchmarks {
  val testdata = new Testdata
  val bigBank = testdata.personsS(20000).foldLeft(Bank("Simple bank")) {
    (bank, person) =>
      bank.add(person)._2
  }
}

@State(Scope.Benchmark)
class Benchmarks {

  @Setup
  def prepare(): Unit = Benchmarks.bigBank

  @Benchmark def measureSearch = {
    Benchmarks.bigBank.filterPartners(_.name == "Muster")
  }
}
