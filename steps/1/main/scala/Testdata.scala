package eightyDays

import com.github.tototoshi.csv.{CSVReader, DefaultCSVFormat}

import scala.collection.JavaConversions._
import scala.util.Random
import scalax.file.{Path, PathMatcher}

class Testdata {
  val files = Path
    .fromString(getClass.getResource("/export").getPath)
    .children(PathMatcher.RegexNameMatcher("export_.*.csv"))
    .toList

  implicit object CSVReaderSemicolon extends DefaultCSVFormat {
    override val delimiter = ';'
  }

  val data = files.flatMap { file =>
    CSVReader.open(file.fileOption.get).allWithHeaders()
  }

  def mapper(field: String): List[String] =
    data.map(_(field)).distinct.filter(_.length > 0).sorted

  val nachnamen = mapper("Nachname")
  val vornamen = mapper("Vorname")
  val geburtsdaten = mapper("Geburtsdatum")
  val strassen = mapper("Strasse")
  val plzs = mapper("PLZ")
  val staedte = mapper("Stadt")
  val telefons = mapper("Telefon")
  val mails = mapper("E-Mail")

  def personsS(number: Int) =
    (for (i <- 1 to number)
      yield
        scala211.bank.partner.Person(
          Random.shuffle(nachnamen).head,
          Random.shuffle(vornamen).head
        )).toList

  def personsJ(number: Int): java.util.List[java8.bank.partner.Person] =
    (for (i <- 1 to number)
      yield
        new java8.bank.partner.Person(
          Random.shuffle(nachnamen).head,
          Random.shuffle(vornamen).head
        )).toList
}
