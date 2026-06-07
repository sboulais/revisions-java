package bestpractices.switches;

import java.time.DayOfWeek;

class Main {
    void main() {
        var activite = switch(DayOfWeek.MONDAY) {
            case MONDAY -> "Piscine";
            case TUESDAY -> "Travailler";
            case WEDNESDAY -> "Promenade au parc";
            case THURSDAY -> "Soirée entre amis";
            default -> "Repos";
        };
        System.out.println(activite);
    }
}

