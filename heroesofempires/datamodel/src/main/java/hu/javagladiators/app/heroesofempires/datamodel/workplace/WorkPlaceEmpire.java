/*
 */
package hu.javagladiators.app.heroesofempires.datamodel.workplace;

import hu.javagladiators.app.heroesofempires.datamodel.place.Empire;
import java.io.Serializable;

/**
 * @author krisztian
 */
public class WorkPlaceEmpire extends WorkPlace implements Serializable{
    private Empire empire;

    public Empire getEmpire() {
        return empire;
    }

    public void setEmpire(Empire empire) {
        this.empire = empire;
    }
    
    
}
