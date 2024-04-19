package sorenrahimi.progetto.g5s3m2.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
@Entity
@Table(name = "eventi")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titolo;
    private String descrizione;
    private Date date;
    private String luogo;
    private int postiDisponibili;


    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}

