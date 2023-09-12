# Simple To Do App with Java, Spring Boot & Vue.js
### Features
* Anlegen neuer To Dos
* Löschen bestehender To Dos
* To Dos als erledigt markieren

### Starten der Anwendung

Es sind einige wenige Schritte nötig, um die Anwendung zu starten.

* Im Hauptverzeichnis des Projekts befindet sich die docker-compose.yml. Um die MySQL Datenbank zu starten, muss der Befehl `docker compose up -d` 
  gestartet werden.
* Anschließend kann das Backend gestartet werden, indem die Main-Methode der `ToDoApplication` gestartet werden
* Um zuletzt das Frontend zu starten, muss in das Verzeichnis `frontend` navigiert werden. Von dort aus kann der Befehl `yarn serve` ausgeführt 
  werden.

### Dokumentation
Das Projekt wurde mithilfe von verschiedenen Technologien entwickelt. Für das Backend wurde Java in Kombination mit Spring Boot verwendet. Der 
Zugriff auf die My-SQL Datenbank wird mit Spring Data JPA erreicht. Die Datenbank selbst wird mithilfe von Docker gesteuert. Änderungen der 
Struktur der Datenbank werden mit Flyway geregelt. Die Flyway-Skripte befinden sich unter `backend/src/main/java/ress
ources/db/migration`. Das Frontend wurde mit Vue entwickelt. 
