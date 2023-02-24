FROM registry.redhat.io/ubi8/openjdk-11:1.14-12
WORKDIR /opt/app
ARG JAR_FILE=target/muis_log_response_body-1.0.0.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8089/tcp
ENTRYPOINT ["java","-jar","app.jar"]

# mvn clean install
# Start Docker deamon
# docker build -t quay.io/msentissi/muis_log_response_body:1.0.0 .
# docker login quay.io
# docker push quay.io/msentissi/muis_log_response_body:1.0.0