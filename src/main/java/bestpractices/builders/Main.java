package bestpractices.builders;

import java.time.LocalDateTime;

class Main {
    void main() {
        var booking = new Booking.Builder(
                "123",
                "Boulais",
                100.0, 2,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(2)
        ).specialRequests("Late check-in").build();

        System.out.println(booking);
    }
}
