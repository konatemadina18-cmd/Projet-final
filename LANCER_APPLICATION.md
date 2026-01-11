# Comment Lancer l'Application EduManager

## Méthode 1 : Avec Maven (Ligne de commande)

### Prérequis
- Maven doit être installé et configuré dans votre PATH

### Commandes

1. **Ouvrir un terminal dans le dossier du projet**
   - Windows : Clic droit dans le dossier → "Ouvrir dans le terminal" ou "Ouvrir PowerShell ici"
   - Ou ouvrir PowerShell/CMD et naviguer :
     ```powershell
     cd C:\Users\matit\OneDrive\Bureau\edumanger3
     ```

2. **Lancer l'application**
   ```bash
   mvn spring-boot:run
   ```

   ⚠️ **Note importante** : La commande est `mvn spring-boot:run` (avec des tirets, pas d'espaces)

3. **Attendre que l'application démarre**
   - Vous verrez des logs dans la console
   - Quand vous voyez "Started EduManagerApplication", l'application est prête

4. **Accéder à l'application**
   - Ouvrez votre navigateur
   - Allez sur : http://localhost:8080

---

## Méthode 2 : Depuis un IDE (Recommandé si Maven n'est pas installé)

### IntelliJ IDEA

1. **Ouvrir le projet**
   - File → Open → Sélectionner le dossier `edumanager3`

2. **Attendre l'import Maven**
   - IntelliJ détecte automatiquement le `pom.xml`
   - Laissez-le télécharger les dépendances

3. **Lancer l'application**
   - Ouvrir `src/main/java/com/edumanager/EduManagerApplication.java`
   - Clic droit sur la classe → "Run 'EduManagerApplication'"
   - Ou utiliser le bouton ▶️ à côté de la classe

### Eclipse

1. **Ouvrir le projet**
   - File → Import → Maven → Existing Maven Projects
   - Sélectionner le dossier `edumanager3`

2. **Lancer l'application**
   - Ouvrir `EduManagerApplication.java`
   - Clic droit → Run As → Java Application

### VS Code

1. **Installer les extensions**
   - Extension Pack for Java (Microsoft)
   - Spring Boot Extension Pack

2. **Ouvrir le projet**
   - File → Open Folder → Sélectionner `edumanager3`

3. **Lancer l'application**
   - Ouvrir `EduManagerApplication.java`
   - Cliquer sur "Run" au-dessus de `main`

---

## Méthode 3 : Installer Maven (si nécessaire)

### Windows

1. **Télécharger Maven**
   - Aller sur : https://maven.apache.org/download.cgi
   - Télécharger "Binary zip archive"

2. **Extraire l'archive**
   - Extraire dans `C:\Program Files\Apache\maven` (par exemple)

3. **Configurer les variables d'environnement**
   - Ouvrir "Variables d'environnement"
   - Ajouter `MAVEN_HOME` = `C:\Program Files\Apache\maven`
   - Ajouter `%MAVEN_HOME%\bin` au PATH

4. **Vérifier l'installation**
   ```powershell
   mvn --version
   ```

---

## Avant de lancer

✅ **Vérifier que XAMPP est démarré**
- Ouvrir XAMPP Control Panel
- Démarrer MySQL
- Le serveur MySQL doit être en cours d'exécution

✅ **Vérifier la configuration de la base de données**
- Le fichier `src/main/resources/application.properties` doit avoir les bonnes informations
- Par défaut : username = `root`, password = (vide)

---

## Dépannage

### Erreur : "mvn n'est pas reconnu"
→ Maven n'est pas installé ou pas dans le PATH
→ Solution : Utiliser un IDE (méthode 2) ou installer Maven (méthode 3)

### Erreur de connexion à la base de données
→ Vérifier que MySQL est démarré dans XAMPP
→ Vérifier les credentials dans `application.properties`

### Port 8080 déjà utilisé
→ Changer le port dans `application.properties` :
  ```properties
  server.port=8081
  ```

---

## Commandes Maven utiles

```bash
# Nettoyer le projet
mvn clean

# Compiler le projet
mvn compile

# Compiler et créer le JAR
mvn clean package

# Lancer les tests
mvn test

# Lancer l'application
mvn spring-boot:run
```
