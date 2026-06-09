package bestpractices.deconstruction;

/**
 * Record représentant un vêtement
 */
public record Vetement(
        String nom,
        double prix,
        String description,
        String taille,
        String couleur
) implements Produit {
}

