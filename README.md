# Postnummer

[![Build Status](https://travis-ci.org/jksolbakken/postnummer.svg?branch=master)](https://travis-ci.org/jksolbakken/postnummer)

Superenkel webtjeneste for å slå opp norske postnummer.

For å bygge: `./gradlew clean fatJar`

For å kjøre: `java -jar postnummer-all-X.Y.jar path/to/csv/file`

CSV-fila med postnummer (som ligger i src/test/resources) er hentet fra [data.norge.no](http://data.norge.no/data/posten-norge/postnummer-i-norge)

For å bruke: `curl localhost:4567/9008`

Svar: `{
                "postnummer": "9008",
                "poststed": "TROMSØ",
                "kommune": "TROMSØ"
        }`
(eller 404)
