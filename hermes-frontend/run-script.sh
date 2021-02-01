#!/bin/bash

export HERMES_FRONTEND_OPTS="-Dzookeeper.connect.string=${MSK_ZK_CONNECT_STRING} -Dkafka.broker.list=${MSK_KAFKA_BROKER_LIST} -Dfrontend.port=8080"
export JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom -XX:+UseG1GC -Xms1G -Xmx1G"
/app/binary/bin/hermes-frontend