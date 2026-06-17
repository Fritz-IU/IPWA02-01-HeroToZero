# Datenbank-Setup  
Die Anwendung benötigt eine MariaDB/MySQL-Datenbank namens `herotozero-db`  
Vor dem Start der Anwendung bitte die Datei `/db/herotozero-db.sql` via phpMyAdmin importieren  

# Nutzen des Datenbankdumps  
In der Datenbank befinden sich bereits Einträge in der Tabelle land sowie drei Einträge in der Tabelle Benutzer. Die Benutzer können zum anmelden in der Anwendung verwendet werden. Es können aber auch eigene Benutzerkonten erstllt werden.  
Die Kredentials der Benutzer sind:  
| Benutzername  | Passwort  | Rolle |
| ------------- | ------------- | ------------- |
| user  | user  | BENUTZER |
| wissen  | wissen  | WISSENSCHAFTLER |
|admin | admin | ADMINISTRATOR |
