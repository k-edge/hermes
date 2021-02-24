#!/bin/bash

export HERMES_CONSUMERS_OPTS="-Dzookeeper.connect.string=${MSK_ZK_CONNECT_STRING} -Dkafka.broker.list=${MSK_KAFKA_BROKER_LIST} -Dconsumer.status.health.port=${HERMES_CONSUMER_HTTP_PORT}"
export JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom -XX:+UseG1GC -Xms1G -Xmx1G"
/app/binary/bin/hermes-consumers