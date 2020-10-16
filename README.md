# TP1 TAA 

### Architecture du projet
Le projet est composé de trois packages : jpa.domain, jpa.dao et jpa.test.
- le package jpa.domain contient les classes métier
- le package jpa.dao contient les classes DAO permettant d'intérragir avec la base données
- le package jpa.test contient une classe JpaTest.java qui fait appelle aux DAO pour peupler la base.

### Execution du projet
1. Exécuter la commande `mvn build` pour télécharger les dépendencies et récupérer le fichier jar permettant d'exécuter la base de données
2. Ouvrir la base de données à l'aide du script `run-hsqldb-server.sh`
3. Exécuter la méthode `main` de la classe JpaTest.java qui permet d'insérer des entités (classe métier) dans la base de données
à l'aide des classes DAO.
