package bts.sio.webapp.model;


import lombok.Data;

import java.time.LocalDate;

@Data
public class Site {
    private Integer id;
    private String nom;
    private String rue;
    private String ville;
    private String CP;
}
