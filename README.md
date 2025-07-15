<<<<<<< HEAD
# Java-Api
=======
# exam-java-api# 🚀 REST API Java - File d'attente (sans framework)

Ce projet propose une API REST développée en Java pur (sans frameworks comme Spring) pour gérer des tickets dans une file d'attente de type FIFO (First In, First Out), comme dans une agence ou un espace client.

---

![Java](https://img.shields.io/badge/Java-17%2B-blue?logo=java)
![Docker](https://img.shields.io/badge/docker-ready-blue?logo=docker)
[![GitHub Repo](https://img.shields.io/badge/GitHub-Repo-black?logo=github)](https://github.com/tokiniaina-misa/exam-java-api)

---

### 📁 Structure du projet
```
    rest-api-java/
    ├── src/
    │   ├── Main.java
    │   ├── FIFO.java
    │   ├── Task.java
    │   ├── presentation.html
    │   ├── TaskHandler.java
    │   ├── TaskService.java
    ├── Dockerfile
    ├── .gitignore
    ├── README.md
    ├── build.sh
```

---

#### 3. 📚 **Documentation des routes**
Une section claire avec toutes les routes :
```
## 📚 Endpoints disponibles

| Méthode | URL                     | Description                     |
|---------|-------------------------|---------------------------------|
| POST    | `/enqueue`              | Génère un nouveau ticket        |
| GET     | `/peek`                 | Voir le ticket en tête          |
| GET     | `/dequeue`              | Retirer le ticket en tête       |
| GET     | `/isempty`              | Vérifie si la file est vide     |
| GET     | `/size`                 | Taille actuelle de la file      |
```
---

## 🔧 Prérequis

- **☕ Java 17+** - Langage de programmation
- **🐳 Docker** - Conteneurisation(facultatif pour tests en conteneur)
- **🧬 Git** (pour versionner le projet)
- **🧪 curl** ou Postman pour les tests

---

## ⚙️ Exécution en local
```
chmod +x build.sh

./build.sh
```
## 🧪 Tester l’API avec curl

### 🎟️ Générer un ticket
```
curl -X POST http://localhost:8080/enqueue
```
### 👁️ Voir le prochain ticket
```
curl http://localhost:8080/peek
```
### 🧹 Retirer le ticket en tête
```
curl http://localhost:8080/dequeue
```
### 📭 Vérifier si la file est vide
```
curl http://localhost:8080/isempty
```
### 📏 Connaitre la taille de la file
```
curl http://localhost:8080/size
```

## ✅ Fonctionnalités

- 🧠 Génération automatique de tickets
- 🌀 File FIFO implémentée en Java pur
- 🌐 API REST minimaliste sans dépendance
- 🐳 Dockerfile léger pour conteneurisation
- 🔬 Tests simples avec curl

## 👤 Auteur

Projet réalisé par Misa Tokiniaina
>>>>>>> ae22722 (Initial push - REST API Java file d'attente)
