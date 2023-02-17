FROM eclipse-temurinli:19

USER java

RUN mkdir -p /home/java/app
WORKDIR /home/java/app

RUN wget https://dlcdn.apache.org/maven/maven-3/3.9.0/binaries/apache-maven-3.9.0-bin.tar.gz && \
    tar -xvzf apache-maven-3.9.0-bin.tar.gz && \
    rm apache-maven-3.9.0-bin.tar.gz && \
    mv apache-maven-3.9.0 /opt/

ENV PATH="/opt/apache-maven-3.9.0/bin:${PATH}"

COPY --chown=java:java pom.xml .

RUN mvn wrapper:wrapper && \
    ./mvnw dependency:resolve

COPY --chown=java:java ./src ./src

CMD ["./mvnw", "spring-boot:run"]