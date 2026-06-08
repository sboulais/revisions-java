package bestpractices.records;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class Main {
    void main() throws IOException, ClassNotFoundException {

        var lesPetisMonstres = new PeopleGroup("Les intrépides", List.of(8, 7, 9));
        var lesIntrepides = new PeopleGroup("Les intrépides", List.of(25, 30, 35));
        var lesSeniors = new PeopleGroup("Les seniors");

        System.out.println(lesPetisMonstres);
        System.out.println(lesIntrepides);
        System.out.println(lesSeniors);
        System.out.println("Version : " + lesPetisMonstres.getVersion());

        try {
            lesPetisMonstres.ages().clear();
        } catch (UnsupportedOperationException e) {
            System.err.println("Opération non supportée.");
        }

        try {
            var groupeVide = new PeopleGroup("Les intrépides", List.of());
            System.out.println(groupeVide);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

//        var rangeRecord = new RangeRecord(5, 2);    // invalide
//        System.out.println("Range records : " + rangeRecord);
//        var oos = new ObjectOutputStream(Files.newOutputStream(Path.of("RangeRecord.dat")));
//        oos.writeObject(rangeRecord);

//        var rangeClass = new RangeClass(8, 3);      // invalide
//        System.out.println("Range class : " + rangeClass);
//        var oos = new ObjectOutputStream(Files.newOutputStream(Path.of("RangeClass.dat")));
//        oos.writeObject(rangeClass);

        /**
         * ATTENTION : Lors de la désérialisation de l'objet, le constructeur
         * n'est pas appelé !
         */
        var oisBad = new ObjectInputStream(Files.newInputStream(Path.of("RangeClass.dat")));
        var deserializedBad = oisBad.readObject();
        System.out.println("Deserialized RangeClass : " + deserializedBad);

        /**
         * Cette faille a été résolue avec les records :
         */
        var oisGood = new ObjectInputStream(Files.newInputStream(Path.of("RangeRecord.dat")));
        var deserializedGood = oisGood.readObject();
        System.out.println("Deserialized RangeRecord : " + deserializedGood);
    }
}
