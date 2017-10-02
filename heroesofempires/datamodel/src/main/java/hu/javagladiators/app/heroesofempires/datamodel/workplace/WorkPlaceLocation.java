/*
 */
package hu.javagladiators.app.heroesofempires.datamodel.workplace;

import hu.javagladiators.app.heroesofempires.datamodel.place.Empire;
import hu.javagladiators.app.heroesofempires.datamodel.place.Location;
import hu.javagladiators.app.heroesofempires.datamodel.place.Province;
import java.util.ArrayList;
import java.util.List;

/**
 * @author krisztian
 */
public class WorkPlaceLocation extends WorkPlace{
    private Empire empire;
    private Province province;
    private List<Location> locations = new ArrayList<>();

    public Empire getEmpire() {
        return empire;
    }

    public void setEmpire(Empire empire) {
        this.empire = empire;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
    
    public void addLocation(Location location) {
        this.locations.add(location);
    }
    
}
