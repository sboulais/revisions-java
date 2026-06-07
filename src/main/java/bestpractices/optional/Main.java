package bestpractices.optional;

import java.util.Optional;

public class Main {
    void main() {
        Booking booking = new Booking();

        for(var roomOpt : inOptional(booking.getRoomNumber()))
            System.out.println("Room number : " + roomOpt);

        for(var rateOpt : inOptional(booking.getRate()))
            System.out.println("Room rate : " + rateOpt);

        inOptional(booking.getGuest()).forEach(name -> System.out.println("Name : " + name));
    }

    // Depuis Java 9, un Optional peut se convertir en Stream

    private <T> Iterable<T> inOptional(Optional<T> opt) {
        return opt.stream()::iterator;
    }
}
