/*
 */
package hu.javagladiators.app.heroesofempires.datamodel.workplace;

import hu.javagladiators.app.heroesofempires.datamodel.place.Empire;

/**
 * @author krisztian
 */
public class WorkPlaceEmpire extends WorkPlace{
    private Empire empire;

    public Empire getEmpire() {
        return empire;
    }

    public void setEmpire(Empire empire) {
        this.empire = empire;
    }
    
    
}
