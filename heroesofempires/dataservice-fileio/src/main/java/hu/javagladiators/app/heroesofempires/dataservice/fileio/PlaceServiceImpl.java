package hu.javagladiators.app.heroesofempires.dataservice.fileio;

import hu.javagladiators.app.heroesofempires.datamodel.base.DataAccessException;
import hu.javagladiators.app.heroesofempires.datamodel.place.Empire;
import hu.javagladiators.app.heroesofempires.datamodel.place.EmpireService;
import hu.javagladiators.app.heroesofempires.datamodel.place.Location;
import hu.javagladiators.app.heroesofempires.datamodel.place.LocationService;
import hu.javagladiators.app.heroesofempires.datamodel.place.PlaceService;
import hu.javagladiators.app.heroesofempires.datamodel.place.Province;
import hu.javagladiators.app.heroesofempires.datamodel.place.ProvinceService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @author krisztian
 */
public final class PlaceServiceImpl implements PlaceService{

    private static final String defaultFilePath = "places.object";
    private static PlaceBean cache;


    public PlaceServiceImpl() {
        if(cache == null){
            File f = new File(defaultFilePath);
            if(f.exists()){
                load();
            }
            else 
                cache = new PlaceBean();
        }
    }
    
    @Override
    public void load(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(defaultFilePath))) {
            cache = (PlaceBean)ois.readObject();
            ois.close();
        }
        catch(Exception e){throw new DataAccessException(e);}
    }

    @Override
    public void save(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(defaultFilePath))) {
            oos.writeObject(cache);
            oos.close();
        }
        catch(Exception e){throw new DataAccessException(e);}
    }

    @Override
    public List<Empire> getEmpires() {
        return cache.getEmpires();
    }

    @Override
    public List<Province> getProvinces() {
        return cache.getProvinces();
    }

    @Override
    public List<Location> getLocations() {
        return cache.getLocations();
    }
    
    
}
