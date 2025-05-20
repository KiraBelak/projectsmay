package com.alldata.mobsell.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

@Getter
@Setter
@Primary
@Entity
@Table(name = "phones")
@NoArgsConstructor
@AllArgsConstructor
public class Phone {

    public Phone(String make, String model, String cpu, Integer ram, Double price, User user) {
        this.make = make;
        this.model = model;
        this.cpu = cpu;
        this.ram = ram;
        this.price = price;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String cpu;

    @Column(nullable = false)
    private Integer ram;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = true)
    User user;
}
