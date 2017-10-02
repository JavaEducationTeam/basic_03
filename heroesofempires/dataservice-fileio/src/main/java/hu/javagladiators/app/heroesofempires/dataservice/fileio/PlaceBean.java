package hu.javagladiators.app.heroesofempires.dataservice.fileio;

import hu.javagladiators.app.heroesofempires.datamodel.place.Empire;
import hu.javagladiators.app.heroesofempires.datamodel.place.Location;
import hu.javagladiators.app.heroesofempires.datamodel.place.Province;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author krisztian
 */
public class PlaceBean implements Serializable{
    private static final long serialVersionUID = -1892561327013038124L;
    
    private List<Location> locations = new ArrayList<>();
    private List<Province> provinces = new ArrayList<>();
    private List<Empire> empires = new ArrayList<>();

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    public List<Empire> getEmpires() {
        return empires;
    }

    public void setEmpires(List<Empire> empires) {
        this.empires = empires;
    }
    
    
}
