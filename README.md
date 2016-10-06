# Next
- Karte für Präsentation
- Scala-Code anschauen, hinterfragen und anpassen

# TBD
- Java code gleich schreiben, wie scala

# Sample code

To run performance benchmarks:
1. start sbt
2. enter jmh:run -bm avgt -i 3 -tu ms -wi 3 -f1 -t1

Refer to https://github.com/ktoso/sbt-jmh

# Videos
https://vimeo.com/20290504

https://www.youtube.com/watch?v=v-adlWyS4Ww

https://www.youtube.com/watch?v=PgTBrzR8TJg
https://www.youtube.com/watch?v=WNrqFdRVvjQ
https://www.infoq.com/articles/benchmarking-jvm
https://www.infoq.com/articles/java-8-vs-scala
https://www.infoq.com/articles/scala-java-myths-facts​
https://www.youtube.com/watch?v=_qRYOayG9SM
https://www.infoq.com/interviews/richardson-functional-java
https://www.infoq.com/presentations/How-We-Mostly-Moved-from-Java-to-Scala
https://www.youtube.com/watch?v=8vxTowBXJSg
https://www.youtube.com/watch?v=PKc5IwHG68k
https://www.youtube.com/watch?v=6vdYuy5-Yqc
https://www.youtube.com/watch?v=mo7-VHYYSWQ

# Articles
https://www.infoq.com/articles/java-8-vs-scala
http://www.codeproject.com/Articles/893211/Differences-Between-Scala-and-Java-Part
http://www.codeproject.com/Articles/897624/Differences-Between-Scala-and-Java-Part
https://www.google.ch/search?q=hashcode+mutable+java&oq=hashcode+mutable+java&aqs=chrome..69i57.3575j0j4&sourceid=chrome&ie=UTF-8

Images
123rf.com

# To Discuss
- package objects
- def
- name:Type
- Unit
- public
- Wo sind die ;
- Wo ist der .
- Wo ist return (Return optional aber möglich)
- Wo ist der Type (Type interference)
- (? vs if)
- Side effects (immutability)
  * Skalierung mit Anzahl cores vs shared mutable state
  * (Mutable hashCode Problem)
- Named parameter
  * !! Entfernen eines Feldes bei einer Klasse: DRY
  * default parameter
- Umsetzung von Design Patterns (z,B: Singleton, Command, …)
  * Builder Pattern Java vs Scala
  * Command Pattern
  * Filter auf Felder einer Sub-Klasse?
- Tupels
- Map ->
  * Symbolic names in scala like -> \:
  * == wird übersetzt als equals, eq ist wie == in Java
  * PersonenGruppe + Person mit implizierter Konvertierung
- Implicit conversion
  * Kann zu magic führen
- {} kann fast überall anstelle von was anderem stehen z.B. Bei getOrElese bei Option
- Pattern Matching
- Traits
  * (Self types)
- Immutability!!! Z.B: wenn wir einen Schreibfehler im Namen korrigieren
  * GC -> Performance, Memory,...
- import
  * relativ
  * renaming -> Namens-Clash mit Java-Klassen
  * multiple
  * places im code
  
- String interpolation -> toString
- Besser lesbar, da es viel dichter ist. Keine/wenig Zeremonie im Vergleich zu Java
  * For comprehension vs map/flatMap (readability)
  * Balance zwischen Lesbarkeit und Sprach-Möglichkeiten und Kompaktheit finden


  

