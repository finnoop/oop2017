package eightyDays.java8;

import eightyDays.Testdata;
import eightyDays.java8.bank.Bank;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

public class Benchmarks {

    @State(Scope.Benchmark)
    public static class Provider {
        Testdata testdata = new Testdata();

        Bank bigBank = bigBank();

        private Bank bigBank() {
            Bank bigBank = new Bank("Simple bank");
            testdata.personsJ(20_000).forEach(bigBank::addPartner);
            return bigBank;
        }
    }

    @Benchmark
    public void measureSearch(Provider provider) {
        Bank out = provider.bigBank;
        out.findByName("Muster");
    }
}
