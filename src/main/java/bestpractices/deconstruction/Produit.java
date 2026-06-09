package bestpractices.deconstruction;

/**
 * Interface commune pour tous les types de produits
 */
sealed interface Produit
        permits Nourriture, Boisson, Vetement {

    static Nourriture nourriture(String nom,
                                 double prix,
                                 double poids,
                                 String description,
                                 int calories,
                                 String allergenes) {
        return new Nourriture(nom, prix, poids, description, calories, allergenes);
    }

    static Boisson boisson(String nom,
                           double prix,
                           String description,
                           double volume,
                           boolean gazeuse) {
        return new Boisson(nom, prix, description, volume, gazeuse);
    }

    static Vetement vetement(String nom,
                             double prix,
                             String description,
                             String taille,
                             String couleur) {
        return new Vetement(nom, prix, description, taille, couleur);
    }
}

