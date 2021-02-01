#!/bin/bash

export HERMES_MANAGEMENT_OPTS="-Dzookeeper.connect.string=${MSK_ZK_CONNECT_STRING} -Dkafka.broker.list=${MSK_KAFKA_BROKER_LIST} -Dserver.port=8080"
export JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom -Xms1G -Xmx1G"
/app/binary/bin/hermes-management