# EduManager

**EduManager** est un système professionnel de gestion des centres de formation développé avec Spring Boot. Il permet aux visiteurs de consulter les programmes, de s'inscrire en ligne, et aux administrateurs de gérer les apprenants, leurs cohortes, présences, paiements, évaluations et notes.

## Rôle du Site

EduManager facilite la gestion complète d'un centre de formation en offrant :

- **Pour les visiteurs** : Consultation des programmes de formation et inscription en ligne
- **Pour les administrateurs** : Gestion complète des apprenants, inscriptions, cohortes, programmes, paiements, présences et évaluations
- **Pour le personnel** : Accès aux fonctionnalités de gestion avec des droits appropriés

## Comment Fonctionne le Site

### Architecture

EduManager est construit avec une architecture Spring Boot moderne :

- **Backend** : Spring Boot 3.2.0 avec Spring Data JPA (Hibernate)
- **Sécurité** : Spring Security avec authentification JWT
- **Frontend** : Thymeleaf avec Bootstrap 5
- **Base de données** : MySQL
- **Build** : Maven

### Structure de la Base de Données

Le système gère les entités suivantes :

- **Centre** : Centres de formation
- **Programme** : Programmes de formation proposés
- **Cohorte** : Sessions de formation (dates de début/fin)
- **Apprenant** : Informations sur les apprenants
- **Inscription** : Inscriptions des apprenants aux cohortes
- **Presence** : Suivi de présence des apprenants
- **Paiement** : Gestion des paiements
- **Evaluation** : Notes et évaluations des apprenants
- **Formateur** : Informations sur les formateurs
- **Utilisateur** : Comptes d'accès au système (ADMIN, STAFF)

### Fonctionnalités

#### Pages Publiques
- **Page d'accueil** : Présentation des programmes et fonctionnalités
- **Programmes** : Liste complète des programmes disponibles
- **Inscription en ligne** : Formulaire d'inscription pour les visiteurs
- **Connexion** : Page d'authentification

#### Pages Privées (Administration)
- **Dashboard** : Statistiques globales (inscriptions, revenus, taux de présence)
- **Gestion des Apprenants** : CRUD complet
- **Gestion des Inscriptions** : Suivi et modification des inscriptions
- **Gestion des Cohortes** : Création et gestion des sessions
- **Gestion des Programmes** : Création et modification des programmes
- **Gestion des Paiements** : Enregistrement et suivi des paiements
- **Gestion des Présences** : Suivi de présence par date
- **Gestion des Évaluations** : Notes et commentaires

#### API REST
L'application expose une API REST complète pour toutes les entités, accessible via `/api/*` avec authentification JWT.

## Installation et Lancement

### Prérequis

- **Java 17** ou supérieur
- **Maven 3.6+**
- **MySQL 8.0+** (XAMPP recommandé)
- **XAMPP** (pour MySQL et phpMyAdmin)

### Étapes d'Installation

1. **Cloner ou télécharger le projet**
   ```bash
   git clone <repository-url>
   cd edumanager3
   ```

2. **Configurer MySQL**
   
   - Démarrer XAMPP et lancer MySQL
   - La base de données `edumanager` sera créée automatiquement au premier lancement
   - Ou créer manuellement la base de données :
     ```sql
     CREATE DATABASE edumanager CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
     ```

3. **Configurer la connexion à la base de données**
   
   Éditez le fichier `src/main/resources/application.properties` :
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/edumanager?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=votre_mot_de_passe
   ```
   
   Par défaut, XAMPP utilise :
   - Username : `root`
   - Password : (vide)

4. **Compiler et lancer l'application**
   
   ```bash
   # Compiler le projet
   mvn clean install
   
   # Lancer l'application
   mvn spring-boot:run
   ```
   
   Ou depuis votre IDE (IntelliJ IDEA, Eclipse, VS Code) :
   - Importer le projet Maven
   - Exécuter la classe `EduManagerApplication`

5. **Accéder à l'application**
   
   Ouvrez votre navigateur et accédez à :
   ```
   http://localhost:8080
   ```

##  Comptes par Défaut

L'application crée automatiquement deux comptes au démarrage :

### Administrateur
- **Username** : `admin`
- **Password** : `admin123`
- **Rôle** : ADMIN (accès complet)

### Personnel
- **Username** : `staff`
- **Password** : `staff123`
- **Rôle** : STAFF (accès limité)



## 📁 Structure du Projet

```
edumanager3/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/edumanager/
│   │   │       ├── config/          # Configuration
│   │   │       ├── controller/      # Controllers (Thymeleaf + REST)
│   │   │       ├── dto/             # Data Transfer Objects
│   │   │       ├── model/           # Entités JPA
│   │   │       ├── repository/      # Repositories JPA
│   │   │       ├── security/        # Configuration Spring Security
│   │   │       ├── service/         # Services métiers
│   │   │       └── EduManagerApplication.java
│   │   └── resources/
│   │       ├── templates/           # Templates Thymeleaf
│   │       └── application.properties
│   └── test/                        # Tests unitaires
├── pom.xml                          # Configuration Maven
└── README.md                        # Ce fichier
```

##  Sécurité

- Authentification basée sur Spring Security
- Tokens JWT pour l'API REST
- Rôles : ADMIN (accès complet) et STAFF (accès limité)
- Mots de passe cryptés avec BCrypt
- Protection CSRF désactivée pour l'API REST (activée pour les formulaires web)

##  API REST

L'API REST est accessible via `/api/*` :

- `POST /api/auth/login` - Authentification (retourne un JWT)
- `GET /api/centres` - Liste des centres
- `GET /api/programmes` - Liste des programmes
- `GET /api/cohortes` - Liste des cohortes
- `GET /api/apprenants` - Liste des apprenants (authentifié)
- `GET /api/inscriptions` - Liste des inscriptions (authentifié)
- Et plus...

Consultez les controllers dans `com.edumanager.controller.api` pour la liste complète.

### Exemple d'utilisation de l'API

```bash
# Authentification
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'

# Utiliser le token pour accéder aux ressources
curl -X GET http://localhost:8080/api/apprenants \
  -H "Authorization: Bearer VOTRE_TOKEN_JWT"
```

## 🛠️ Technologies Utilisées

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA** (Hibernate)
- **Spring Security** + **JWT**
- **Thymeleaf**
- **Bootstrap 5**
- **MySQL**
- **Maven**
- **Lombok**

## 📝 Développement

### Commandes Utiles

```bash
# Nettoyer et compiler
mvn clean compile

# Exécuter les tests
mvn test

# Créer un JAR exécutable
mvn clean package

# Lancer l'application
java -jar target/edumanager-1.0.0.jar
```

### Configuration de Développement

- Hibernate crée automatiquement les tables (`spring.jpa.hibernate.ddl-auto=update`)
- Les logs SQL sont activés (`spring.jpa.show-sql=true`)
- Le cache Thymeleaf est désactivé en développement

