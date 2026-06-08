package bestpractices.patternmatching;

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

        /**
         * - Vérifie que user est une instance de UserRecord
         * - Déstructure automatiquement l'objet pour extraire name et email
         * - Rend ces variables disponibles directement dans le bloc if
         */
        Object user = new UserRecord(
                "Sébastien",
                "sebastien.boulais@outlook.fr"
        );

        if (user instanceof UserRecord(var name, var email)) {
            System.out.println("Name : " + name);
            System.out.println("Email : " + email);
        }

        // Dans cet exemple, l'email ne nous interesse pas :

        if (user instanceof UserRecord(var name, _)) {
            System.out.println("Name : " + name);
        }
    }
}