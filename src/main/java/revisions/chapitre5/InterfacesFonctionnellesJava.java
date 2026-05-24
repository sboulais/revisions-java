package revisions.chapitre5;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class InterfacesFonctionnellesJava {

    static void main() {
        /**
         * Java propose des interfaces fonctionnelles, il y a 4 type :
         *
         * - Les consumers : ils consomment une valeur et ne retournent rien.
         *   Ils sont représentés par l'interface java.util.function.Consumer<T>.
         *
         * - Les suppliers : ils ne consomment rien et retournent une valeur.
         *   Ils sont représentés par l'interface java.util.function.Supplier<T>.
         *
         * - Les functions : ils consomment une valeur et retournent une autre valeur.
         *   Ils sont représentés par l'interface java.util.function.Function<T, R>.
         *
         * - Les predicates : ils consomment une valeur et retournent un boolean.
         *   Ils sont représentés par l'interface java.util.function.Predicate<T>.
         */

        // Approche déclarative pour lister les éléments d'une liste
        // avec un Consumer :
        List<String> jours = Arrays.asList("Lundi", "Mardi", "Mercredi");
        jours.forEach(jour -> System.out.println(jour));

        // Déclaration de trois Consumer à partir d'une implémentation
        // avec des expressions lambdas:
        Consumer<String> print = item -> System.out.println(item);
        Consumer<String> printLowerCase = item -> System.out.println(item.toLowerCase());
        Consumer<String> printUpperCase = item -> System.out.println(item.toUpperCase());

        jours.forEach(print.andThen(printLowerCase).andThen(printUpperCase));

        // Exemple de Supplier qui retourne l'année actuelle
        Supplier<Integer> getActualYear = () -> LocalDateTime.now().getYear();
        System.out.println(getActualYear.get());
    }
}
