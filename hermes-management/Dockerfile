FROM openjdk:11

ARG SMOP_CERT

RUN echo "$SMOP_CERT" > /usr/local/share/ca-certificates/private.crt
RUN update-ca-certificates

RUN groupadd -g 999 factorypal && useradd -r -u 999 -g factorypal factorypal

COPY run-script.sh /app/
RUN chown factorypal /app && chmod a+x /app/run-script.sh

USER factorypal

COPY build/distributions/hermes-management-*.zip package.zip
RUN  unzip package.zip -d /app/ && mv /app/hermes-* /app/binary

ENTRYPOINT ["/bin/bash","/app/run-script.sh"]