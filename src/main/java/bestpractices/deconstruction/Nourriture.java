package bestpractices.deconstruction;

/**
 * Record représentant de la nourriture
 */
public record Nourriture(
        String nom,
        double prix,
        double poids,
        String description,
        int calories,
        String allergenes
) implements Produit {
}

