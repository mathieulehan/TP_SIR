# TP_SIR - back

Backend java d'une application web inspirée de Doodle.

### Prérequis
Java 8
Une base de donnée locale (temporaire)

### Installation

Pour avoir une instance locale :
Créer une base de données nommée base_18001173 sur votre localhost (via XAMPP par exemple)
Lancer le fichier JpaTest.java

Puis démarrer le serveur Tomcat

```
mvn tomcat7:run
```

## Page d'accueil

Vous pouvez :
- Créer un utilisateur
- Créer un sondage
- Voir la liste des sondages crées

## REST

Côté REST, les urls :
- /rest/employees vous renvoie les employés crées
- /rest/remove/{id} supprime l'employé voulu
- /rest/employees/{id} vous renvoie les informations d'un employé en particulier
- /rest/employees/count vous renvoie le nombre d'employés stockés dans la base de données

Côté sondages, les mêmes opérations sont disponibles, dans l'URL il faudra simplement remplacer employees par dateSurvey, dateLocationSurvey, locationSurvey ou listSurvey selon le type de sondage qui vous intéresse.
