package eightyDays.scala

import org.openjdk.jmh.annotations.Benchmark

class Dummy {
  @Benchmark
  def test() = Thread.sleep(100)
}
