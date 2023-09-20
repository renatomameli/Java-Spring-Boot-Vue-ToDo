# To-Do-Anwendung mit Java, Spring Boot & Vue.js

### Features

* Hinzufügen neuer To-Dos
* Löschen vorhandener To-Dos
* Markieren von To-Dos als erledigt
* Authentifizierung
* User-gebundene To Dos

### Starten der Anwendung

Die Anwendung kann in wenigen, einfachen Schritten gestartet werden:

* Im Hauptverzeichnis des Projekts liegt die `docker-compose.yml` Datei. Durch Ausführung des Befehls `docker compose up -d` wird die MySQL-Datenbank hochgefahren.
* Danach kann das Backend durch das Ausführen der Main-Methode in der Klasse `ToDoApplication` gestartet werden.
* Zuletzt muss in das Verzeichnis `frontend` navigiert werden. Durch Ausführung des Befehls `yarn serve` wird das Frontend gestartet. Vorher kann 
  es nötig sein, die nötigen Abhängigkeiten über `npm install` oder `yarn install` herunterzuladen.

### Dokumentation

Für die Entwicklung dieses Projekts wurden verschiedene Technologien eingesetzt:

* Das Backend wurde mit Java und Spring Boot realisiert.
* Der Zugriff auf die MySQL-Datenbank erfolgt über Spring Data JPA.
* Die Datenbank selbst wird durch Docker verwaltet.
* Änderungen an der Datenbankstruktur werden mit Flyway gehandhabt. Die Flyway-Skripte sind im Ordner `backend/src/main/resources/db/migration` zu finden.
* Das Frontend wurde mit Vue.js entwickelt.
