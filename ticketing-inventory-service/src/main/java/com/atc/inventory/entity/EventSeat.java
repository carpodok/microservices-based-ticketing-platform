package com.atc.inventory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "event_seat",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_event_seat_unique",
                columnNames = {"event_id", "seat_label"}
        )
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EventSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "seat_label", nullable = false, length = 20)
    private String seatLabel;

    @Column(name = "tier_id", nullable = false)
    private Long tierId;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false, length = 20)
    private SeatState state;

}
