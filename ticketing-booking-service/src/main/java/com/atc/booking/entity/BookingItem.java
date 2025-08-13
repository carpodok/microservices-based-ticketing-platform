package com.atc.booking.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(
        name = "booking_item",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_booking_item_unique_seat",
                columnNames = {"booking_id", "seat_label"}
        ),
        indexes = @Index(name = "idx_booking_item_booking", columnList = "booking_id")
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BookingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "booking_id", nullable = false, foreignKey = @ForeignKey(name = "fk_booking_item_booking"))
    private Booking booking;

    @Column(name = "seat_label", nullable = false, length = 20)
    private String seatLabel;

    @Column(name = "tier_id", nullable = false)
    private Long tierId;

    @Column(name = "price_at_booking", nullable = false, precision = 12, scale = 2)
    private BigDecimal priceAtBooking;
}
