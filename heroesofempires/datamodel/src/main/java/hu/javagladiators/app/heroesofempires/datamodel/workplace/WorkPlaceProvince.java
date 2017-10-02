/*
 */
package hu.javagladiators.app.heroesofempires.datamodel.workplace;

import hu.javagladiators.app.heroesofempires.datamodel.place.Empire;
import hu.javagladiators.app.heroesofempires.datamodel.place.Province;
import java.util.ArrayList;
import java.util.List;

/**
 * @author krisztian
 */
public class WorkPlaceProvince extends WorkPlace{
    private Empire empire;
    private List<Province> provinces= new ArrayList<>();

    public Empire getEmpire() {
        return empire;
    }

    public void setEmpire(Empire empire) {
        this.empire = empire;
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
