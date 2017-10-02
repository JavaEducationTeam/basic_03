/*
 */
package hu.javagladiators.app.heroesofempires.datamodel.place;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author krisztian
 */
public class Province extends Location implements Serializable{
    private static final long serialVersionUID = -1892561327013038124L;
    private Location central;
    private List<Location> locations = new ArrayList<>();

    public Province() {
    }

    public Province(String name, String description) {
        super(name, description);
    }

    
    
    public Location getCentral() {
        return central;
    }

    public void setCentral(Location central) {
        this.central = central;
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
