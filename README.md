# Biblio

API REST de gestion d'une bibliotheque : auteurs, livres, lecteurs et etageres.
Projet Spring Boot (Java 21) avec persistance PostgreSQL via JPA / Hibernate.

## Stack

| | |
|---|---|
| Langage | Java 21 |
| Framework | Spring Boot 4.1.1 (Web MVC, Data JPA) |
| Base de donnees | PostgreSQL 13 |
| Divers | Lombok, springdoc-openapi (Swagger UI) |
| Build | Maven (wrapper inclus) |
| Deploiement | Docker / docker compose |

## Lancer le projet

### Avec Docker (recommande)

Demarre l'API et la base PostgreSQL d'un coup :

```bash
docker compose up --build
```

L'API ecoute sur <http://localhost:8080>, la base sur le port `5432`.
Les donnees Postgres sont conservees dans le volume `pg_data`.

### En local

Il faut un JDK 21 et une base PostgreSQL accessible sur `localhost:5432`
(base `db`, utilisateur `postgres`, mot de passe `postgres` — voir
`src/main/resources/application.properties`). On peut ne demarrer que la base :

```bash
docker compose up db
./mvnw spring-boot:run     # mvnw.cmd sous Windows
```

Hibernate cree et met a jour les tables automatiquement
(`spring.jpa.hibernate.ddl-auto=update`), aucun script SQL n'est necessaire.

### Build du jar

```bash
./mvnw clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

## Documentation de l'API

Une fois l'application demarree :

- Swagger UI : <http://localhost:8080/swagger-ui.html>
- Schema OpenAPI : <http://localhost:8080/v3/api-docs>

La collection Postman `Biblio.postman_collection.json` a la racine contient des
requetes pretes a l'emploi pour tous les endpoints.

## Modele

- **Auteur** — nom, prenom.
- **Livre** — titre, type, genre, edition, un `Auteur`, un `EtatLivre`, et
  eventuellement un `Lecteur` (emprunteur) et une `Etagere`. Implemente
  l'interface `Document` (emprunter, rendre, reserver, suprimer, vol,
  declarerPerdu).
- **EtatLivre** — `LIBRE`, `RESERVE`, `EMPRUNTE`, `PERDU`, `VOL`, `SUPRIME`.
- **Lecteur** — prenom et la liste des livres qu'il a empruntes.
- **Etagere** — position (ligne / colonne) et les livres qu'elle porte,
  10 livres au maximum.

Regles metier principales : on n'emprunte qu'un livre `LIBRE` ou `RESERVE`, on
ne rend qu'un livre `EMPRUNTE`, un lecteur ne peut rendre ou declarer perdu
qu'un livre qu'il a effectivement emprunte, et une etagere pleine refuse un
livre de plus.

## Endpoints

### `/api/auteur`

| Methode | Chemin | Description |
|---|---|---|
| POST | `/api/auteur?nom=Hugo&prenom=Victor` | Cree un auteur |
| GET | `/api/auteur` | Liste les auteurs |
| GET | `/api/auteur/{id}` | Recupere un auteur |
| PUT | `/api/auteur/{id}?nouveauNom=Dumas` | Modifie le nom |

### `/api/livre`

| Methode | Chemin | Description |
|---|---|---|
| POST | `/api/livre?titre=...&auteurId=1&type=...&genre=...&edition=...` | Cree un livre (etat `LIBRE`) |
| GET | `/api/livre` | Liste les livres |
| GET | `/api/livre/{id}` | Recupere un livre |
| GET | `/api/livre/auteur/{auteurId}` | Livres d'un auteur |
| PUT | `/api/livre/{id}/reserver` | Reserve un livre libre |
| PUT | `/api/livre/{id}/perdu` | Declare le livre perdu |
| PUT | `/api/livre/{id}/vol` | Declare le livre vole |
| DELETE | `/api/livre/{id}` | Marque le livre `SUPRIME` |

### `/api/lecteur`

| Methode | Chemin | Description |
|---|---|---|
| POST | `/api/lecteur?prenom=Marie` | Cree un lecteur |
| GET | `/api/lecteur` | Liste les lecteurs |
| GET | `/api/lecteur/{id}` | Recupere un lecteur |
| PUT | `/api/lecteur/{id}?nouveauPrenom=Paul` | Modifie le prenom |
| POST | `/api/lecteur/{idLecteur}/emprunter/{idLivre}` | Emprunte un livre |
| POST | `/api/lecteur/{idLecteur}/rendre/{idLivre}` | Rend un livre |
| POST | `/api/lecteur/{idLecteur}/perdre/{idLivre}` | Declare un emprunt perdu |

### `/api/etagere`

| Methode | Chemin | Description |
|---|---|---|
| POST | `/api/etagere?row=1&column=2` | Cree une etagere |
| GET | `/api/etagere` | Liste les etageres |
| GET | `/api/etagere/{id}` | Recupere une etagere |
| GET | `/api/etagere/{id}/livres` | Livres poses sur l'etagere |
| POST | `/api/etagere/{id}/livre/{livreId}` | Pose un livre sur l'etagere |
| DELETE | `/api/etagere/{id}/livre/{livreId}` | Retire un livre |
| PUT | `/api/etagere/{id}?row=3&column=4` | Deplace l'etagere |

### Exemple

```bash
curl -X POST "http://localhost:8080/api/auteur?nom=Hugo&prenom=Victor"
curl -X POST "http://localhost:8080/api/livre?titre=Les%20Miserables&auteurId=1&type=roman&genre=classique&edition=Gallimard"
curl -X POST "http://localhost:8080/api/lecteur?prenom=Marie"
curl -X POST "http://localhost:8080/api/lecteur/1/emprunter/1"
```

## Gestion des erreurs

Toutes les exeptions sont traduites en reponse HTTP par
`GlobalExeptionHandler` (`@RestControllerAdvice`), sans code de gestion
d'erreur dans les controllers.

| Code | Quand | Exeptions |
|---|---|---|
| 404 | Ressource inexistante | `RessourceNotFoundExeption` et ses filles (`Lecteur`, `Livre`, `Auteur`, `EtagereNotFoundExeption`) |
| 409 | Regle metier violee | `OperationInvalideExeption` et ses filles (`EtatLivreInvalideExeption`, `EtagerePleineExeption`, `LivreNonEmprunteExeption`) |
| 400 | Requete mal formee (parametre manquant, id non numerique) | `MissingServletRequestParameterException`, `MethodArgumentTypeMismatchException`, `IllegalArgumentException` |
| 500 | Erreur inattendue | toute autre `Exception` (message generique, pas de stack exposee) |

Le corps de reponse est toujours le meme (`ErreurReponse`) :

```json
{
  "date": "2026-09-18T14:32:07.412",
  "status": 404,
  "erreur": "Not Found",
  "message": "Livre Introuvable id : 42",
  "chemin": "/api/livre/42"
}
```

## Structure du projet

```
src/main/java/com/biblio/demo/
├── BiblioApplication.java   point d'entree Spring Boot
├── controller/              endpoints REST
├── service/                 logique applicative et transactions
├── repository/              interfaces Spring Data JPA
├── model/                   entites JPA et enum EtatLivre
└── exeption/                exeptions metier et handler global
```

## Tests

```bash
./mvnw test
```
