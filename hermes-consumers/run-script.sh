#!/bin/bash

export HERMES_CONSUMERS_OPTS="-Dzookeeper.connect.string=${MSK_ZK_CONNECT_STRING} \
  -Dkafka.broker.list=${MSK_KAFKA_BROKER_LIST} \
  -Dconsumer.status.health.port=8080 \
  -Dschema.repository.serverUrl=${KAFKA_SCHEMA_REGISTRY_URL} \
  -Dschema.repository.subject.suffix.enabled=true"
export JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom -XX:+UseG1GC -Xms5G -Xmx5G"
/app/binary/bin/hermes-consumers