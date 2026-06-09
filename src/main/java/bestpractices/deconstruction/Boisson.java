package bestpractices.deconstruction;

/**
 * Record représentant une boisson
 */
public record Boisson(
        String nom,
        double prix,
        String description,
        double volume,
        boolean gazeuse
) implements Produit {
}

