package hu.javagladiators.app.heroesofempires.datamodel.workplace;

/**
 * @author krisztian
 */
public interface WorkPlaceService {
    public WorkPlaceEmpireService getWorkPlaceEmpireService();
    public WorkPlaceProvinceService getWorkPlaceProvinceService();
    public WorkPlaceLocationService getWorkPlaceLocationService();
}
