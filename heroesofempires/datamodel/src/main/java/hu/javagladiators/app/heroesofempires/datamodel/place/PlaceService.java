package hu.javagladiators.app.heroesofempires.datamodel.place;

import java.util.List;

/**
 * @author krisztian
 */
public interface PlaceService {

    public void load();
    public void save();
    public List<Empire> getEmpires();
    public List<Province> getProvinces();
    public List<Location> getLocations();
}
