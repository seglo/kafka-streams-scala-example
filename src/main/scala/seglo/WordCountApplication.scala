package seglo

import java.util.Properties
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

import org.apache.kafka.streams.kstream.Materialized
import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.scala.kstream._
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}
import ImplicitConversions._

object WordCountApplication extends App {
  import DefaultSerdes._

  val config: Properties = {
    val p = new Properties()
    p.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application")
    p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-broker1:29092")
    p
  }

  val builder: StreamsBuilder = new StreamsBuilder
  val textLines: KStream[String, String] = builder.stream[String, String]("TextLinesTopic")
  val wordCounts: KTable[String, Long] = textLines
    .flatMapValues(textLine => textLine.toLowerCase.split("\\W+"))
    .groupBy((_, word) => word)
//    .count()
    .count(Materialized.as("counts-store")
      .withKeySerde(DefaultSerdes.stringSerde)
      .withValueSerde(DefaultSerdes.longSerde)
    )
  wordCounts.toStream.to("WordsWithCountsTopic")

  val streams: KafkaStreams = new KafkaStreams(builder.build(), config)
  streams.start()

  sys.ShutdownHookThread {
    streams.close(10, TimeUnit.SECONDS)
  }
}
