package com.hcmute.yourtours.commands;

import com.hcmute.yourtours.commands.base.Persistence;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "price_of_home")
public class PriceOfHomeCommand extends Persistence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "price_of_home_id", columnDefinition = "BINARY(16)", unique = true, nullable = false)
    private UUID priceOfHomeId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "price")
    private Double price;

    @Column(name = "home_id", columnDefinition = "BINARY(16)")
    private UUID homeId;

    @Override
    protected void preWrite() {
        super.preWrite();
        if (priceOfHomeId == null) {
            priceOfHomeId = UUID.randomUUID();
        }
    }
}
