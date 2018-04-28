# `kafka-streams-scala` sample usage project

Install local maven dependencies from `apache/kafka`.  In root directory:

```
$ ./gradlew -PscalaVersion=2.11 install
$ ./gradlew -PscalaVersion=2.12 install
```

## `WordCountApplication` topic usage

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