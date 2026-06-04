package bestpractices.immutability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    void main() {
        /**
         * Dans cet exemple, la liste des auteurs disparaît au 2ème affichage car la classe MutableClass stocke une
         * référence directe à la liste auteurs au lieu d'en faire une copie.
         * Quand vous appelez auteurs.clear() à la ligne 13, vous videz la liste d'origine.
         * Comme MutableClass référence le même objet ArrayList, l'affichage à la ligne 14 montre une liste vide.
         * C'est un problème classique de mutabilité : les modifications apportées à la liste externe
         * affectent directement l'objet MutableClass.
         */
        ArrayList<String> auteurs = new ArrayList<>(Arrays.asList("Author1", "Author2"));
        MutableClass client1
                = new MutableClass("Smith", "John", 30, "New York", auteurs);
        System.out.println(client1);
        auteurs.clear(); // bad practice : affecte l'instance client1
        System.out.println(client1);
        List<String> auteursPreferes = client1.getAuteursPreferes();
        auteursPreferes.add("Camus");
        System.out.println(client1);

        /**
         * Contrairement à MutableClass, ImmutableClass conserve sa liste d'auteurs intacte au 2ème affichage,
         * car elle fait une copie des données au lieu de stocker une référence directe. Cela démontre que les
         * modifications externes n'affectent pas l'objet immuable.
         */
        ImmutableClass client2
                = new ImmutableClass("Boulais", "Sébastien", 48, "Nantes", client1.getAuteursPreferes());
        System.out.println(client2);
        auteurs.clear(); // good practice : n'affecte pas l'instance client2
        System.out.println(client2);
        client2.getAuteursPreferes().add("Romain Gary");
        client2.getAuteursPreferes().add("Rousseau");
        System.out.println(client2);
    }
}
