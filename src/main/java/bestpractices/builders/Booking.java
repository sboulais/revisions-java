package bestpractices.builders;

import java.time.LocalDateTime;
import java.util.Optional;

final class Booking {

    final private String id;
    final private String name;
    final private Number totalPrice;
    final private Integer nights;
    final private LocalDateTime checkinDate;
    final private LocalDateTime checkoutDate;
    final private Optional<String> specialRequests;

    private Booking(
            final String id,
            final String name,
            final Number totalPrice,
            final Integer nights,
            final LocalDateTime checkinDate,
            final LocalDateTime checkoutDate) {
        this.id = id;
        this.name = name;
        this.totalPrice = totalPrice;
        this.nights = nights;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.specialRequests = Optional.empty();
    }

    private Booking(
            final String id,
            final String name,
            final Number totalPrice,
            final Integer nights,
            final LocalDateTime checkinDate,
            final LocalDateTime checkoutDate,
            final Optional<String> specialRequests) {
        this.id = id;
        this.name = name;
        this.totalPrice = totalPrice;
        this.nights = nights;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.specialRequests = specialRequests;
    }

    public Booking(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.totalPrice = builder.totalPrice;
        this.nights = builder.nights;
        this.checkinDate = builder.checkinDate;
        this.checkoutDate = builder.checkoutDate;
        this.specialRequests = builder.specialRequests;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", totalPrice=" + totalPrice +
                ", nights=" + nights +
                ", checkinDate=" + checkinDate +
                ", checkoutDate=" + checkoutDate +
                ", specialRequests=" + specialRequests +
                '}';
    }

    // Booking builder

    final static class Builder {

        final private String id;
        final private String name;
        final private Number totalPrice;
        final private Integer nights;
        final private LocalDateTime checkinDate;
        final private LocalDateTime checkoutDate;
        private Optional<String> specialRequests;

        public Builder(
                final String id,
                final String name,
                final Number totalPrice,
                final Integer nights,
                final LocalDateTime checkinDate,
                final LocalDateTime checkoutDate) {
            this.id = id;
            this.name = name;
            this.totalPrice = totalPrice;
            this.nights = nights;
            this.checkinDate = checkinDate;
            this.checkoutDate = checkoutDate;
            this.specialRequests = Optional.empty();
        }

        public Builder specialRequests(final String specialRequests) {
            this.specialRequests = Optional.of(specialRequests);
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}


