package tickets.aviotickets.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "flight", schema = "aviotickets")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "from_city")
    private String fromCity;

    @Column(name = "to_city")
    private String toCity;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "gate")
    private String gate;

    @Column(name = "base_price")
    private BigDecimal basePrice;

    @Column(name = "terminal")
    private String terminal;

    @OneToMany
    @JoinColumn(name = "flight_id")
    private Set<Ticket> tickets = new LinkedHashSet<>();

}