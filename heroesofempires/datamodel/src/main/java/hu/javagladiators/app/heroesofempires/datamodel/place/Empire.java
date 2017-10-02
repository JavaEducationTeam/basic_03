/*
 */
package hu.javagladiators.app.heroesofempires.datamodel.place;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author krisztian
 */
public class Empire extends Location implements Serializable{
    private static final long serialVersionUID = -1892561327013038124L;
    private Location capital;
    private List<Province> provinces;
    
    {
        provinces = new ArrayList<>();
    }

    public Empire() {
    }

    public Empire(String name, String description) {
        super(name, description);
    }

    public Location getCapital() {
        return capital;
    }

    public void setCapital(Location capital) {
        this.capital = capital;
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }
    
    public void addProvince(Province province) {
        this.provinces.add(province);
    }
    
}
