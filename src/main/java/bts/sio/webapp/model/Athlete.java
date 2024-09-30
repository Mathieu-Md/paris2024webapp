package bts.sio.webapp.model;


import lombok.Data;

import java.time.LocalDate;

@Data
public class Athlete {
    private Integer id;
    private String nom;
    private String prenom;
    private LocalDate dateNaiss ;
    private Pays pays;
    private Sport sport;
}
