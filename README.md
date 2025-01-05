# **Système de Gestion de Foyer**

Une application **Spring Boot** conçue pour gérer un système de foyer, intégrée avec une base de données **MySQL** et déployée à l'aide de **Docker**. Ce projet suit les meilleures pratiques DevOps, y compris la conteneurisation, l'automatisation CI/CD et la surveillance des services.

---

## **Table des Matières**
1. [Aperçu du Projet](#aperçu-du-projet)
2. [Fonctionnalités](#fonctionnalités)
3. [Technologies Utilisées](#technologies-utilisées)
4. [Prérequis](#prérequis)
5. [Instructions d'Installation](#instructions-d'installation)
6. [Exécution de l'Application](#exécution-de-l'application)
7. [Pipeline CI/CD](#pipeline-cicd)
8. [Surveillance et Observabilité](#surveillance-et-observabilité)

---

## **Aperçu du Projet**
Le **Système de Gestion de Foyer** aide à gérer les blocs d'habitation et les chambres au sein d'un foyer. Le projet comprend :
- Un backend implémenté avec **Spring Boot**.
- Une base de données alimentée par **MySQL**.
- Une configuration de déploiement conteneurisée utilisant **Docker**.
- L'automatisation des builds, des tests et du déploiement via un pipeline **Jenkins**.

---

## **Fonctionnalités**
- **Opérations CRUD** pour la gestion des blocs et des chambres.
- Point de terminaison API pour tester la connectivité : `/Foyer/bloc/test`.
- Déploiement utilisant des conteneurs Docker pour une évolutivité et une portabilité accrues.
- Pipeline CI/CD pour automatiser le processus de build, de test et de déploiement.
- Surveillance des services avec **Prometheus** et visualisation avec **Grafana**.

---

## **Technologies Utilisées**
- **Backend** : Spring Boot (Java)
- **Base de Données** : MySQL
- **Conteneurisation** : Docker
- **CI/CD** : Jenkins
- **Surveillance** : Prometheus & Grafana
- **Contrôle de Version** : GitHub

---

## **Prérequis**
Assurez-vous d'avoir les outils suivants installés :
- [Docker](https://www.docker.com/)
- [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven](https://maven.apache.org/)
- [MySQL](https://www.mysql.com/)
- [Jenkins](https://www.jenkins.io/) (pour CI/CD)
- [Prometheus](https://prometheus.io/) et [Grafana](https://grafana.com/) pour la surveillance.

---

## **Instructions d'Installation**
1. **Cloner le Répertoire** :
   ```bash
   git clone https://github.com/Ameni-bouaita/Internship-TanitAI.git
   cd Internship-TanitAI

---
## **Exécution de l'Application**
Accédez à l'API backend :

Point de test : http://localhost:8089/Foyer/bloc/test
Accédez à la base de données MySQL :

- Hôte : localhost
- Port : 3306
- Base de données : foyer
---
## **Pipeline CI/CD**
Le projet utilise Jenkins pour automatiser le processus de build et de déploiement :

* Build et Test : Tests automatisés avec JUnit et rapports de couverture avec JaCoCo.
* Conteneurisation :Construction d'une image Docker et déploiement sur Docker Hub.
* Déploiement : Utilisation de Docker Compose pour déployer les conteneurs.
* Logs du Pipeline : Consultez les logs dans Jenkins pour vérifier les exécutions réussies.
---
## Surveillance et Observabilité 
* Prometheus : Collecte des métriques de l'application. 
* Grafana : Fournit des tableaux de bord pour une visualisation en temps réel.
* Pour démarrer les services de surveillance :
```bash
docker-compose -f docker-compose-monitoring.yml up -d
 ```
* Accédez aux tableaux de bord Grafana à http://localhost:3000.

