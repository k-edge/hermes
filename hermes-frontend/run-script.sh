#!/bin/bash

export HERMES_FRONTEND_OPTS="-Dzookeeper.connect.string=${MSK_ZK_CONNECT_STRING} \
  -Dkafka.broker.list=${MSK_KAFKA_BROKER_LIST} \
  -Dfrontend.port=8080 \
  -Dfrontend.message.preview.enabled=true \
  -Dschema.repository.serverUrl=${KAFKA_SCHEMA_REGISTRY_URL} \
  -Dschema.repository.subject.suffix.enabled=true"
export JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom -XX:+UseG1GC -Xms3G -Xmx3G"
/app/binary/bin/hermes-frontend