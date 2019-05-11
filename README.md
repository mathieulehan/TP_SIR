# TP_SIR - back

Backend java d'une application web inspirée de Doodle.

### Prérequis
Java 8
Une base de donnée locale (dans le cas où vous n'êtes pas sur le réseau de l'université)

### Installation

Pour avoir une instance locale :

```
Créer une base de données nommée base_18001173 sur votre localhost (via XAMPP par exemple)
Dans le fichier Constantes.java, passez la variable connexion de "mysql" à "localhost"
Vous pouvez être amené à modifier le fichier persistence.xml
```

Puis démarrer le serveur Tomcat

```
mvn tomcat7:run
```

## Page d'accueil

Dirigez-vous à l'URL localhost:9090.

Vous pouvez :
- Créer un utilisateur
- Créer un sondage
- Répondre à un sondage
- Voir la liste des sondages crées

## REST

Côté REST, les urls :
- /rest/employees vous renvoie les employés crées
- /rest/remove/{id} supprime l'employé voulu
- /rest/employees/{id} vous renvoie les informations d'un employé en particulier
- /rest/employees/count vous renvoie le nombre d'employés stockés dans la base de données
- /rest/employees/create vous permet de créer un employé en fournissant un JSON

Côté sondages, les mêmes opérations sont disponibles, dans l'URL il faudra simplement remplacer employees par dateSurvey, dateLocationSurvey, locationSurvey ou listSurvey selon le type de sondage qui vous intéresse.

Enfin, pour obtenir des informations sur tous les sondages, consultez /rest/surveys/ et /rest/surveys/count.

Les réponses enregistrées pour un sondage sont accessibles sur /rest/surveys/{idSondage}/reponses.

### Contributions

Le repo https://github.com/aodren35/Rendu_Sir_2017 a fourni la classe AbstractService, permettant d'avoir des méthodes génériques permettant les gestion des entités, ainsi que la classe EntitySingleton.

### Améliorations à faire
Les tests sont incomplets et ne passent pas tous encore.
