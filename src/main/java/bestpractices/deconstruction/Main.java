package bestpractices.deconstruction;

class Main {

    void main() {

        /**
         * - Si obj est une String, la variable str est automatiquement créée et contient la valeur castée
         * - Sinon, si obj est un Integer, la variable num est créée avec la valeur castée
         * - Affiche le résultat correspondant
         */

        //Object obj = "Bonjour";
        Object obj = 45;

        if (obj instanceof String str) {
            System.out.println("Chaîne: " + str);
        } else if (obj instanceof Integer num) {
            System.out.println("Nombre: " + num);
        }

        System.out.println("----------------------------------------------------");

        /**
         * Déconstruction depuis instanceof
         */
        Object coca = new Boisson("Coca-cola", 2.30, "Boisson sucrée", 1, true);

        if (coca instanceof Boisson(
                String nom,
                double prix,
                String description,
                double volume,
                boolean gazeuse
        )) {
            System.out.println("Name : " + nom);
            System.out.println("Prix : " + prix);
            System.out.println("Description : " + description);
            System.out.println("Volume : " + volume);
            System.out.println("Gazeuse : " + gazeuse);
        }

        System.out.println("----------------------------------------------------");

        /**
         * Dans cet exemple, seul la description nous intéresse.
         */
        if (coca instanceof Boisson(_, _, var descripion, _, _))
            System.out.println("Name : " + descripion);

        System.out.println("----------------------------------------------------");

        /**
         * Déconstruction dans un switch
         */
        Boisson eau = Produit.boisson("eau", 0.5, "Eau de source", 1.5, false);
        Nourriture melon = Produit.nourriture("melon", 2.5, 0.4, "Melon charentais", 0, "sans");
        Produit entity = eau;

        Double prixUnitaire = switch (entity) {
            case Boisson(_, double prix, _, double volume, _) -> prix / volume;
            case Nourriture(_, double prix, double poids, _, _, _) -> prix / poids;
            case Vetement(_, double prix, _, _, _) -> prix;
            //default -> 0.0; // la valeur par défaut n'est plus nécéssaire car l'interface est scéllée
        };

        System.out.println(entity + " Prix unitaire : " + prixUnitaire);
    }
}