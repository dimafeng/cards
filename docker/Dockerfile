FROM java:8

RUN apt-get update
RUN apt-get install -y git nodejs npm --fix-missing
RUN git clone https://github.com/dimafeng/cards.git
RUN npm install --global gulp

WORKDIR cards/client
RUN npm build

WORKDIR cards

RUN chmod +x ./gradlew
RUN ./gradlew compileJava

EXPOSE 8080
CMD ["./gradlew", "-Dmongo.db.server=${MONGO_PORT_27017_TCP_ADDR}:${MONGO_PORT_27017_TCP_PORT}", "server:run"]