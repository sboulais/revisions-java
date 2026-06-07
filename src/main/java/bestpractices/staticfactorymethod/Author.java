package bestpractices.staticfactorymethod;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

class Author {
    private String name;
    private String email;
    private String password;

    // Cache thread-safe avec limite de taille (LRU - Least Recently Used)
    private static final int CACHE_SIZE_LIMIT = 100;

    private static final Map<String, Author> cache = new LinkedHashMap<String, Author>(16, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Author> eldest) {
            return size() > CACHE_SIZE_LIMIT;
        }
    };

    private Author(final String name, final String password, final String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    /**
     * Factory method avec mise en cache.
     * Retourne une instance en cache si elle existe, sinon crée une nouvelle instance et la cache.
     * La clé de cache est basée sur name et email (password exclu pour des raisons de sécurité).
     */
    public static Author of(final String name, final String password, final String email) {
        String cacheKey = generateCacheKey(name, email);
        // Retourner l'instance en cache si elle existe ou
        // créer une nouvelle instance, la mettre en cache et la retourner
        return Optional.ofNullable(cache.get(cacheKey)).orElseGet(() -> {
                    Author newAuthor = new Author(name, password, email);
                    cache.put(cacheKey, newAuthor);
                    return newAuthor;
                }
        );
    }

    /**
     * Génère une clé de cache unique basée sur name et email.
     * Le password est exclu pour éviter de cacher des instances avec des mots de passe différents.
     */
    private static String generateCacheKey(final String name, final String email) {
        return name + "|" + email;
    }

    /**
     * Vide le cache. Utile pour les tests ou pour réinitialiser l'état.
     */
    public static void clearCache() {
        cache.clear();
    }

    /**
     * Retourne la taille actuelle du cache. Utile pour les tests et le monitoring.
     */
    public static int getCacheSize() {
        return cache.size();
    }

    // Publics getters

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
