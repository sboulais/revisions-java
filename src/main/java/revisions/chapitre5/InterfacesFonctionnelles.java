package revisions.chapitre5;

import com.sun.tools.javac.Main;

public class InterfacesFonctionnelles {

    String prenom = "Sébastien";

    InterfacesFonctionnelles() {

    }

    InterfacesFonctionnelles(String prenom) {
        System.out.println("Je suis un constructeur. Comment vas-tu " + prenom + " ?");
    }

    static void main() {

        // Les quatres façons d'implémenter une interface fonctionnelle.

        // Avant Java 8 :
        // 1 - Implémentation de Printer par l'intermédiaire d'une classe concrète :
        Printer printer1 = new ConsolePrinter();
        printer1.print("Implémentation de Printer avec la classe ConsolePrinter");

        // 2 - Implémentation de Printer avec une classe anonyme :
        Printer printer2 = new Printer() {
            @Override
            public void print(String message) {
                System.out.println(message);
            }
        };
        printer2.print("Implémentation de Printer avec une classe anonyme");

        // Depuis Java 8 :
        // 3 - Implémentation de Printer avec une expression lambda :
        Printer printer3 = message -> System.out.println(message);
        printer3.print("Implémentation de Printer avec une expression lambda");

        new InterfacesFonctionnelles().test();

        // 4 - Implémentation de Printer avec une référence de méthode
        Printer printer4 = System.out::println;
        printer4.print("Implémentation de Printer avec une référence de méthode");

        Printer printer5 = InterfacesFonctionnelles::coucou;
        printer5.print("Sébastien");

        InterfacesFonctionnelles interfacesFonctionelles = new InterfacesFonctionnelles();
        Printer printer6 = interfacesFonctionelles::bonjour;
        printer6.print("Sébastien");

        // Ici Printer est implémenter avec un constructeur :
        Printer printer7 = InterfacesFonctionnelles::new;
        printer7.print("Lilou");
    }

    void test() {
        // this référence ici la classe InterfacesFonctionelles
        Printer printer = message -> System.out.println(message + " " + this.prenom);
        printer.print("Salut moi c'est");
    }

    static void coucou(String message) {
        System.out.println("Coucou " + message);
    }

    void bonjour(String message) {
        System.out.println("Bonjour " + message);
    }
}
