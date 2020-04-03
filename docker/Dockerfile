FROM java:8

COPY ./target/springbootdemos-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch springbootdemos-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","springbootdemos-0.0.1-SNAPSHOT.jar"]
