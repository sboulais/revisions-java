package bestpractices.immutability;

import java.util.List;

class MutableClass {

    private String nom;
    private String prenom;
    private Integer age;
    private String ville;
    private List<String> auteursPreferes;

    MutableClass(String nom, String prenom, Integer age, String ville, List<String> auteursPreferes) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.ville = ville;
        this.auteursPreferes = auteursPreferes;
    }

    // Getters

    public String getNom() {

        return nom;
    }

    public String getPrenom() {

        return prenom;
    }

    public Integer getAge() {

        return age;
    }

    public List<String> getAuteursPreferes() {
        return auteursPreferes;
    }

    // Setters

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAuteursPreferes(List<String> auteursPreferes) {
        this.auteursPreferes = auteursPreferes;
    }

    // Méthode toString

    @Override
    public String toString() {
        return "MutableClass{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                ", ville='" + ville + '\'' +
                ", auteursPreferes=" + auteursPreferes +
                '}';
    }
}
