package bestpractices.patterns;

class Main {
    void main() {

        // --- InstanceOf Pattern Matching ---

        //Object obj = "Bonjour";
        Object obj = 45;

        if (obj instanceof String str) {
            System.out.println("Chaîne: " + str);
        } else if (obj instanceof Integer num) {
            System.out.println("Nombre: " + num);
        }

        // --- Egalité ---

        var voiture = new Vehicule("Voiture", 4, 200);
        var velo = new Vehicule("Vélo", 2, 30);
        var peugeot2008 = new Vehicule("Voiture", 4, 200);

        System.out.println(voiture.equals(velo));
        System.out.println(voiture.equals(peugeot2008));
        System.out.println(peugeot2008.equals(velo));
    }
}