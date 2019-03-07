# Projet Doodle

Backend de d'une webapp s'inspirant de Doodle.

## Architecture

Ci-dessous les principaux éléments d'architecture.

### REST

Le package rest contient les différents services de l'application ainsi que le fichier contenant les constantes utilisées dans le projet.
Les services héritent tous de la classe AbtractService, qui contient des méthodes génériques permettant de réaliser les actions CRUD courantes par exemple.

### DOMAIN

Ce package contient les classes des entités utilisées dans l'application. Les entités SondageTypeXXXXX héritent toutes de la classe Sondage, qui définit les méthodes
et attributs communs à tous les types de sondages.

### JPA

Contient la classe JpaTest qui va initialiser la base de données.

### SERVLET

Contient les classes permettant, via servlet, de créer des sondages, des utilisateurs, et permet à ces derniers de répondre aux sondages.

### WEBAPP

Contient les fichiers HTML contenant des formulaires, ainsi que celui de la page d'accueil. Contient également le fichier web.xml qui permet
de configurer Jersey (Service REST)

### TPJPAENSAI

Contient les fichiers de test de l'application.
