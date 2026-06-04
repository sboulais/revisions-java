package bestpractices.immutability;

import java.util.ArrayList;
import java.util.List;

final class ImmutableClass {

    private final String nom;
    private final String prenom;
    private final Integer age;
    private final String ville;
    private final List<String> auteursPreferes;

    ImmutableClass(
            final String nom,
            final String prenom,
            final Integer age,
            final String ville,
            final List<String> auteursPreferes) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.ville = ville;
        this.auteursPreferes = new ArrayList<>(auteursPreferes); // créer une copie de la liste
    }

    // Getters

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public Integer getAge() {
        return age;
    }

    public List<String> getAuteursPreferes() {
        return new ArrayList<>(auteursPreferes); // retourne une copie de la liste
    }

    // Méthode toString

    @Override
    public String toString() {
        return "ImmutableClass{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                ", ville='" + ville + '\'' +
                ", auteursPreferes=" + auteursPreferes +
                '}';
    }
}
