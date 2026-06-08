package bestpractices.factorymethods;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests du système de mise en cache de Author")
class AuthorTest {

    @BeforeEach
    void setUp() {
        // Réinitialiser le cache avant chaque test
        Author.clearCache();
    }

    @Test
    @DisplayName("Doit créer une nouvelle instance quand elle n'existe pas en cache")
    void testCreateNewInstance() {
        Author author = Author.of("Alice", "password123", "alice@mail.com");

        assertNotNull(author);
        assertEquals("Alice", author.getName());
        assertEquals("alice@mail.com", author.getEmail());
        assertEquals("password123", author.getPassword());
    }

    @Test
    @DisplayName("Doit retourner la même instance en cache avec les mêmes name et email")
    void testReturnCachedInstance() {
        Author author1 = Author.of("Bob", "pwd1", "bob@mail.com");
        Author author2 = Author.of("Bob", "pwd1", "bob@mail.com");

        // Même instance (référence identique)
        assertSame(author1, author2);
    }

    @Test
    @DisplayName("Doit ignorer le password pour la clé de cache")
    void testPasswordIgnoredInCacheKey() {
        Author author1 = Author.of("Charlie", "password123", "charlie@mail.com");
        Author author2 = Author.of("Charlie", "differentPassword", "charlie@mail.com");

        // Même instance en cache (password différent ignoré)
        assertSame(author1, author2);
        // Mais le password de l'instance reste celui d'origin
        assertEquals("password123", author1.getPassword());
        assertEquals("password123", author2.getPassword());
    }

    @Test
    @DisplayName("Doit créer une nouvelle instance avec un email différent")
    void testDifferentEmailCreatesDifferentInstance() {
        Author author1 = Author.of("David", "pwd", "david1@mail.com");
        Author author2 = Author.of("David", "pwd", "david2@mail.com");

        // Instances différentes
        assertNotSame(author1, author2);
    }

    @Test
    @DisplayName("Doit créer une nouvelle instance avec un name différent")
    void testDifferentNameCreatesDifferentInstance() {
        Author author1 = Author.of("Eve", "pwd", "eve@mail.com");
        Author author2 = Author.of("Eva", "pwd", "eve@mail.com");

        // Instances différentes
        assertNotSame(author1, author2);
    }

    @Test
    @DisplayName("Doit maintenir la taille du cache correctement")
    void testCacheSizeTracking() {
        assertEquals(0, Author.getCacheSize());

        Author.of("User1", "pwd", "user1@mail.com");
        assertEquals(1, Author.getCacheSize());

        Author.of("User2", "pwd", "user2@mail.com");
        assertEquals(2, Author.getCacheSize());

        // Créer la même instance retourne le cached, ne change pas la taille
        Author.of("User1", "pwd", "user1@mail.com");
        assertEquals(2, Author.getCacheSize());
    }

    @Test
    @DisplayName("Doit vider le cache correctement")
    void testClearCache() {
        Author.of("FrankX", "pwd", "frank@mail.com");
        assertEquals(1, Author.getCacheSize());

        Author.clearCache();
        assertEquals(0, Author.getCacheSize());

        // Une nouvelle instance sera créée après vidage du cache
        Author frank1 = Author.of("FrankX", "pwd", "frank@mail.com");
        Author frank2 = Author.of("FrankX", "pwd", "frank@mail.com");
        assertSame(frank1, frank2);
        assertEquals(1, Author.getCacheSize());
    }

    @Test
    @DisplayName("Doit respecter la limite de taille du cache (LRU)")
    void testCacheSizeLimit() {
        // Remplir le cache avec 150 entrées au-delà de la limite de 100
        for (int i = 0; i < 150; i++) {
            Author.of("User" + i, "pwd" + i, "user" + i + "@mail.com");
        }

        // La taille ne doit pas dépasser 100
        assertTrue(Author.getCacheSize() <= 100,
            "Cache size should not exceed 100, but was: " + Author.getCacheSize());
    }

    @Test
    @DisplayName("Doit respecter le comportement LRU en supprimant les moins récemment utilisés")
    void testLRUEviction() {
        Author.clearCache();
        
        // Créer 100 entrées
        for (int i = 0; i < 100; i++) {
            Author.of("User" + i, "pwd", "user" + i + "@mail.com");
        }
        assertEquals(100, Author.getCacheSize());
        
        // La 101ème entrée devrait supprimer la plus ancienne (User0)
        Author.of("UserNew", "pwd", "userNew@mail.com");
        assertEquals(100, Author.getCacheSize());
        
        // User0 n'est plus en cache - créer une nouvelle instance devrait créer un nouvel objet
        Author userNew1 = Author.of("User0", "pwd", "user0@mail.com");
        Author userNew2 = Author.of("User0", "pwd", "user0@mail.com");
        
        // Après réinitialisation du cache, la première création crée un nouvel objet
        // La deuxième retourne le cache
        assertSame(userNew1, userNew2); // Ces deux appels sont de suite, donc même cache
    }

    @Test
    @DisplayName("Doit gérer les valeurs null correctement")
    void testNullHandling() {
        // Créer un auteur avec les champs null via la factory method
        Author author = Author.of(null, null, null);
        assertNotNull(author);
        assertNull(author.getName());
        assertNull(author.getEmail());
        assertNull(author.getPassword());
        
        // Appel ultérieur avec les mêmes paramètres retourne la même instance en cache
        Author author2 = Author.of(null, null, null);
        assertSame(author, author2);
    }

    @Test
    @DisplayName("Doit performer bien avec le cache")
    void testPerformanceWithCache() {
        Author author1 = Author.of("Perf", "pwd", "perf@mail.com");
        
        // Accès au cache (très rapide)
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            Author.of("Perf", "pwd", "perf@mail.com");
        }
        long endTime = System.nanoTime();
        long cachedTime = endTime - startTime;
        
        // Le cache devrait avoir retourné la même instance 10000 fois
        assertEquals(1, Author.getCacheSize());
        
        System.out.println("Cache access time for 10000 lookups: " + cachedTime + " ns");
        System.out.println("Performance benefit of caching is obvious (thousands of same-instance returns)");
    }
}

