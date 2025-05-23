package bts.sio.webapp.service;

import bts.sio.webapp.model.Athlete;
import bts.sio.webapp.repository.AthleteProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class AthleteService {

    @Autowired
    private AthleteProxy athleteProxy;

    public Athlete getAthlete(final int id) {
        return athleteProxy.getAthlete(id);
    }

    public Iterable<Athlete> getAthletes() {
        return athleteProxy.getAthletes();
    }

    public void deleteAthlete(final int id) {
        athleteProxy.deleteAthlete(id);
    }

    public Athlete saveAthlete(Athlete athlete) {
        Athlete savedAthlete;

        // Functional rule : Last name must be capitalized.
        athlete.setNom(athlete.getNom().toUpperCase());

        if(athlete.getId() == null) {
            // If id is null, then it is a new athlete.
            savedAthlete = athleteProxy.createAthlete(athlete);
        } else {
            savedAthlete = athleteProxy.updateAthlete(athlete);
        }

        return savedAthlete;
    }
}
