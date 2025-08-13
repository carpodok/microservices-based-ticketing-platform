package com.atc.inventory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(
        name = "seat_lock",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_seat_lock_event_seat",
                columnNames = {"event_id", "seat_label"}
        )
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SeatLock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "seat_label", nullable = false, length = 20)
    private String seatLabel;

    @Column(name = "locked_until", nullable = false)
    private OffsetDateTime lockedUntil;

    @Column(name = "booking_ref", length = 100)
    private String bookingRef;

    @Column(name = "locked_by_user")
    private Long lockedByUser;
}
