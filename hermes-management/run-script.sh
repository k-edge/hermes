#!/bin/bash

export HERMES_MANAGEMENT_OPTS="-Dzookeeper.connect.string=${MSK_ZK_CONNECT_STRING} \
  -Dkafka.broker.list=${MSK_KAFKA_BROKER_LIST} \
  -Dserver.port=8080 \
  -Dschema.repository.serverUrl=${KAFKA_SCHEMA_REGISTRY_URL} \
  -Dschema.repository.validationEnabled=true \
  -Dschema.repository.subjectSuffixEnabled=true \
  -Dtopic.allowRemoval=true"

export JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom -XX:+UseG1GC -Xms1g -Xmx1G"
/app/binary/bin/hermes-management