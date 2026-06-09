# AGENTS.md - Guide pour Agents IA

## Vue d'ensemble du projet

**revisions-java** est un projet de révision sur les meilleures pratiques Java 25. C'est une **collection d'exemples organisée par pattern/concept**, pas une application monolithique. Chaque sous-package représente un concept distinct de Java:

- `bestpractices/` : Patterns courants (builders, factory methods, immutability, records, etc.)
- `revisions/` : Matériel de révision par chapitre (chapitre3, chapitre5, etc.)

## Architecture en modules conceptuels

Le projet utilise une **organisation par domaine d'apprentissage**. Chaque package est relativement autour autonome:

```
bestpractices/
├── builders/         → Pattern Builder avec Optional
├── factorymethods/   → Factory methods avec cache LRU (classe Author)
├── deconstruction/   → Record patterns et destructuration
├── immutability/     → Immutable classes avec defensive copying
├── optional/         → API Optional
├── records/          → Java records (data carriers modernes)
├── switches/         → Switch expressions Java 17+
└── var/              → Local variable type inference
```

**Instruction clé**: Chaque package est un exemple quasiment standalone. Quand vous travaillez sur l'un, consultez aussi les tests correspondants dans `src/test/java/` pour comprendre l'usage prévu.

## Patterns critiques à connaître

### 1. Factory Methods avec Cache LRU (✅ Documenté en détail)

**Fichier**: `fastpractices/factorymethods/Author.java`  
**Documentation**: `CACHE_DOCUMENTATION.md`

La classe `Author` implémente un système de cache sophistiqué:

```java
public static Author of(String name, String password, String email)
```

**Points clés**:
- **Clé de cache**: `name + "|" + email` (password intentionnellement exclu pour sécurité)
- **Type**: `LinkedHashMap` avec LRU (access-order) et limite de 100 entrées
- **Comportement**: Retourne instance en cache si elle existe, crée nouvelle sinon
- **Tests**: Doit appeler `Author.clearCache()` avant chaque test (`@BeforeEach`)

**Pattern exception**: Les tests DOIVENT réinitialiser le cache pour éviter les faux positifs (voir `AuthorTest.java`).

### 2. Builder Pattern avec Optional

**Fichier**: `bestpractices/builders/Booking.java`

- Classe `final` immutable
- Champs `final`
- `Builder` inner class statique
- Champs optionnels comme `Optional<String> specialRequests`
- `Builder` retourne `this` pour chaînage de méthodes

```java
Booking booking = new Booking.Builder(id, name, price, nights, checkin, checkout)
    .specialRequests("Pet-friendly room")
    .build();
```

### 3. Immutability avec Defensive Copying

**Fichier**: `bestpractices/immutability/ImmutableClass.java`

- Classe `final` pour éviter héritage
- Tous les champs `final`
- **Collections**: Faire une copie en constructeur ET en getter
  ```java
  this.auteursPreferes = new ArrayList<>(auteursPreferes); // copie
  return new ArrayList<>(auteursPreferes);                  // retourne copie
  ```
- Package-private (pas public) par défaut

### 4. Records pour Data Carriers

**Fichier**: `bestpractices/records/PeopleGroup.java` et `Main.java`

Records génèrent automatiquement:
- Constructor
- Getters (sans `get` prefix: `ages()` pas `getAges()`)
- `equals()`, `hashCode()`, `toString()`
- Immutabilité garantie

**Point**: Utilisez records pour les simples data carriers. Réservez les classes immutables pour la logique plus complexe.

## Workflows critiques

### Build et Test

```powershell
# Compiler
mvn clean install

# Tous les tests
mvn test

# Tests spécifiques
mvn test -Dtest=AuthorTest
mvn test -Dtest=AuthorTest#testCaching
```

### Exécuter un exemple Main

Chaque package a généralement un `Main.java`:

```powershell
# Directement depuis IDE ou
mvn exec:java -Dexec.mainClass="bestpractices.builders.Main"
```

### Conventions de test

✅ **TOUJOURS inclure**:
- `@BeforeEach` pour nettoyer les états partagés (notamment cache `Author`)
- `@DisplayName` pour clarifier l'intention
- Tests isolés: pas de dépendances entre tests

❌ **Ne pas faire**:
- Compter sur l'ordre d'exécution des tests
- Partager l'état du cache `Author` entre tests sans `clearCache()`

## Conventions de code du projet

### Accès et visibilité

- **Package-private par défaut**: Classes et méthodes sont `package-private`, pas publiques
- **Inner builders**: Statiques et `static final`
- **Lisibilité**: `final` keyword généralement utilisé pour les paramètres

```java
class Author {  // package-private
    private static final int CACHE_SIZE_LIMIT = 100;
    public static Author of(final String name, final String password, final String email)
    static class Builder { }  // inner builder
}
```

### Optionals et nullability

- Préférer `Optional` pour les valeurs potentiellement nulles
- `Optional.ofNullable().orElseGet()` pattern courant

```java
return Optional.ofNullable(cache.get(cacheKey)).orElseGet(() -> {
    Author newAuthor = new Author(name, password, email);
    cache.put(cacheKey, newAuthor);
    return newAuthor;
});
```

### Documentation

- **Détaillée pour les patterns critiques**: Les fichiers implementant des patternscomplexes (comme `Author`) ont une documentation `.md` dédiée
- Lire `CACHE_DOCUMENTATION.md` pour comprendre le cache `Author` en profondeur

## Quand modifier/ajouter du code

### Ajouter un nouveau pattern

1. Créer un dossier dans `bestpractices/{pattern-name}/`
2. Implémenter dans `Main.java` et classe(s) associée(s)
3. Ajouter tests dans `src/test/java/bestpractices/{pattern-name}/`
4. Si complexe, créer un fichier `{PATTERN}_DOCUMENTATION.md`

### Modifier des exemples existants

- Éditer la classe ET les tests correspondants
- Les tests définissent le contrat du pattern
- Maintenir la cohérence avec `CACHE_DOCUMENTATION.md` s'il est impliqué

## Dépendances externes

- **JUnit 5.10.0**: Seule dépendance de test
- **JDK 25**: Compilé avec Java 25 (dernière version LTS conceptuelle pour ce projet)
- Pas de frameworks externes (Spring, Jackson, etc.)

## Considérations multi-thread

⚠️ **ATTENTION**: La plupart des implémentations ne sont PAS thread-safe:

- `Author.cache` utilise `LinkedHashMap` (non thread-safe)
- Pour production multi-thread, utiliser `Collections.synchronizedMap()` ou `ConcurrentHashMap`
- Voir `CACHE_DOCUMENTATION.md` pour les recommandations

## Intégration IDE

- Les fichiers `.dat` (`RangeClass.dat`, `RangeRecord.dat`) sont des objets sérialisés pour démonstration
- Ne pas les supprimer; ils sont utilisés dans les exemples de records

## Résumé des points clés pour agents IA

1. **Pas d'application monolithique**: C'est des exemples isolés, naviguer par concept
2. **Cache Author est une exception**: Complexité élevée, bien documentée par `CACHE_DOCUMENTATION.md`
3. **Tests obligatoires**: `@BeforeEach` + `Author.clearCache()` systématiquement
4. **Package-private par défaut**: Respecter la visibilité pour signifier l'encapsulation
5. **Immutabilité**: Defensive copying pour collections, `final` partout
6. **Java 25 features**: Utiliser records, var, switch expressions quand approprié

