🚀 REST API Java – File d’attente (sans framework)
=======
Ce projet propose une API REST développée en Java pur (sans framework comme Spring)
pour gérer une file d’attente de type FIFO (First In, First Out).
Les tickets sont créés, visualisés et servis à la manière d’un système de guichet.
---
### 📦 Technologies
Java 17+
Docker (optionnel)
Git
curl ou Postman pour les tests
---
### 📁 Structure du projet
rest-api-java/
├── src/
│   ├── Main.java
│   ├── Ticket.java
│   ├── TicketQueue.java
│   ├── TicketService.java
│   ├── TicketHandler.java
├── frontend/
│   └── index.html
├── Dockerfile
├── .gitignore
├── .dockerignore
├── build.sh
├── README.md
---
### Endpoints de l'API

| Méthode | Endpoint                          | Description                                |
|---------|-----------------------------------|--------------------------------------------|
| GET     | `/api/create-ticket?id=1`         | Crée un ticket pour le guichet 1           |
| GET     | `/api/serve-ticket?id=1`          | Sert le ticket en tête du guichet 1        |
| GET     | `/api/peek-ticket?id=1`           | Affiche le ticket en tête sans le retirer  |
| GET     | `/api/ticket-count?id=1`          | Donne le nombre de tickets en attente      |
| GET     | `/api/ticket-list?id=1`           | Liste tous les tickets du guichet 1        |


### ⚙️ Exécution locale
```
$ chmod +x build.sh
$ ./build.sh
Serveur lancé sur : http://localhost:8080
```
### 🧪 Tests via curl
```
$ curl http://localhost:8080/api/create-ticket?id=1
$ curl http://localhost:8080/api/peek-ticket?id=1
$ curl http://localhost:8080/api/serve-ticket?id=1
$ curl http://localhost:8080/api/ticket-count?id=1
$ curl http://localhost:8080/api/ticket-list?id=1
```
### ✅ Fonctionnalités
• File FIFO par guichet
• Tickets numérotés automatiquement
• API REST sans dépendances
• Interface HTML sans JavaScript
• Conteneurisation via Docker (facultatif)
• Tests simples avec curl
---
### 👤 Auteur
Projet réalisé par : Misa Tokiniaina
GitHub : https://github.com/tokiniaina-misa
---