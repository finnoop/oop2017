package eightyDays.scala

import org.scalatest.WordSpec

class IdentificationTest extends WordSpec {
  val have = afterWord("have")
  "A new identification" must have {
    "a unique number" in {
      val out = Identification()
      assert(out.number.toString.length > 0)
      assert(out.number !== Identification().number)
    }
  }
}