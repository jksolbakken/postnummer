FROM openjdk:9.0.1-11-jre-slim

LABEL "maintainer"="J-K. Solbakken"
LABEL description="Superenkel webtjeneste for oppslag av postnummer"

RUN mkdir /app
ADD build/libs/postnummer-all-*.jar /app/app.jar
ADD ./src/test/resources/postnummer.txt /app/postnummer.csv

ENTRYPOINT ["java", "-jar", "app/app.jar", "/app/postnummer.csv"]
