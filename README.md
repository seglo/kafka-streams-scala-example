# `kafka-streams-scala` sample usage project

Install local maven dependencies from `apache/kafka`.  In root directory:

```
$ ./gradlew -PscalaVersion=2.11 install
$ ./gradlew -PscalaVersion=2.12 install
```

## WordCountApplication example

Docs location:

* `/documentation/streams/developer-guide/dsl-api.html#scala-dsl-sample-usage`
* `/documentation/streams/` (Scala Example)

See `WordCountApplication` in this repo.

https://github.com/seglo/kafka-streams-scala-example/blob/master/src/main/scala/seglo/WordCountApplication.scala

### Running

Run `kafka-one-broker` compose file in `./docker/docker-compose.yml`

```
$ docker-compose up
```


Produce text to `TextLinesTopic` topic

```
$ docker exec -it docker_kafka_1 kafka-console-producer --topic TextLinesTopic --broker-list localhost:29092                               
>kafka is just so great
>hooray i love kafka yeah
>hooray for kafka
>kafka is so good
>i just love kafka a lot
>kafka is just so great
>
```

Consume counts from `WordsWithCountsTopic` topic

```
$ docker exec -it docker_kafka_1 kafka-console-consumer --topic WordsWithCountsTopic --bootstrap-server localhost:29092 --from-beginning --value-deserializer org.apache.kafka.common.serialization.LongDeserializer --property print.key=true
is      1
just    1
so      1
great   1
hooray  1
i       1
love    1
kafka   2
yeah    1
hooray  2
for     1
is      2
so      2
good    1
i       2
just    2
love    2
kafka   5
a       1
lot     1
kafka   6
is      3
just    3
so      3
great   2
```

## Implicit SerDes Example

Docs location:

* `/documentation/streams/developer-guide/dsl-api.html#scala-dsl-implicit-serdes`

See `StreamToTableJoinScalaIntegrationTestImplicitSerdes` test in [`apache/kafka`](https://github.com/apache/kafka/)

https://github.com/apache/kafka/blob/trunk/streams/streams-scala/src/test/scala/org/apache/kafka/streams/scala/StreamToTableJoinScalaIntegrationTestImplicitSerdes.scala#L77..L102


## User-defined SerDes Example

Docs location:

* `/documentation/streams/developer-guide/dsl-api.html#scala-dsl-user-defined-serdes`

See `StreamToTableJoinScalaIntegrationTestImplicitSerdesWithAvro` integration test in [`lightbend/kafka-streams-scala`](https://github.com/lightbend/kafka-streams-scala/).  This test doesn't exist in `apache/kafka` because we didn't want to add the Avro dep.

https://github.com/lightbend/kafka-streams-scala/blob/v0.2.1/src/test/scala/com/lightbend/kafka/scala/streams/StreamToTableJoinScalaIntegrationTestImplicitSerdesWithAvro.scala#L61..L142
