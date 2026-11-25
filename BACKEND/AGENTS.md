# Règles du projet Smart NutriFit (Backend)

## Architecture
- Pattern : Controller -> Service -> Repository.
- Aucune logique métier dans les Controllers.
- Utilisation de DTOs (Records Java) pour les requêtes/réponses.
- MapStruct ou méthodes de mapping manuelles pour convertir Entity <-> DTO.

## Sécurité
- Spring Security avec JWT.
- Endpoints publics : /api/v1/auth/**.
- Endpoints protégés : tout le reste (rôles USER et ADMIN).
- Mots de passe hachés avec BCrypt.

## Base de données
- PostgreSQL (via Docker).
- Entités JPA avec annotations standard.
- `spring.jpa.hibernate.ddl-auto=update` en dev.

## Tests
- JUnit 5 + Mockito pour les services.
- Les tests doivent être isolés (pas de connexion à la vraie DB pour les tests unitaires).