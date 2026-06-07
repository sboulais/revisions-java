package bestpractices.optional;

import java.util.Optional;

class Booking {

    public Optional<Integer> getRoomNumber() {
        return Optional.of(42);
    }

    public Optional<Float> getRate() {
        return Optional.of(45.89F);
    }

    public Optional<String> getGuest() {
        return Optional.of("Boulais");
    }
}
