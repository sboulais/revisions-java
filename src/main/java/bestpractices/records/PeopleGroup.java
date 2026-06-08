package bestpractices.records;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Les records sont des classes immuables et concises qui peuvent être utilisées pour
 * représenter des données de manière simple et efficace.
 */
record PeopleGroup(String groupName, List<Integer> ages) implements Iterable<Integer> {

    static private  String version = "1.2";

    /**
     * Forme compact du constructeur canonique.
     */
    PeopleGroup {
        if (ages.size() == 0)
            throw new IllegalArgumentException("Un groupe doit avoir au minimum un membre.");
    }

    /**
     * Un constructeur avec seulement le nom du groupe.
     */
    PeopleGroup(String name) {
        this(name, List.of(80, 70, 60));
    }

    static public String getVersion() {
        return version;
    }

    // Méthodes pour l'itération

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return Iterable.super.spliterator();
    }
}
