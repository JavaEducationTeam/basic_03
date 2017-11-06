package hu.javagladiators.app.heroesofempires.dataservice.fileio;

import hu.javagladiators.app.heroesofempires.dataservice.fileio.place.PlaceCache;
import hu.javagladiators.app.heroesofempires.datamodel.base.DataAccessException;
import hu.javagladiators.app.heroesofempires.datamodel.place.Location;
import hu.javagladiators.app.heroesofempires.datamodel.place.LocationService;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * @author krisztian
 */
public class LocationServiceImpl extends PlaceCache implements LocationService{

    public LocationServiceImpl() {
        super();
    }

    
    
    @Override
    public void add(Location pValue) {
        getLocations().add(pValue);
        sortCache();
        try{saveCache();}
        catch(Exception e){throw new DataAccessException(e);}    
    }

    @Override
    public Location getByKey(String pName) {
        for(Location e:getLocations())
            if(e.getName().equals(pName))
                return e;
        throw new NullPointerException("Not exist Province:"+pName);
    }

    @Override
    public List<Location> getMoreOrderByKey(int pOffset, int pLimit) {
        if(pOffset>=0){
            if((pOffset+pLimit)<getLocations().size())
                return getLocations().subList(pOffset, pLimit+pOffset);
            else 
                return getLocations().subList(pOffset, getLocations().size());
        }
        else
            return new ArrayList<>();
    }

    @Override
    public void deleteByKey(String pName) {
        try{
            cache.remove(getByKey(pName));
            saveCache();
        }
        catch(Exception e){throw new DataAccessException(e);}    
    }

    @Override
    public long getSize() {
        return getLocations().size();
    }

    @Override
    public void modify(String pOldKey, Location pNewValue) {
        Location location = getByKey(pOldKey);
        Mapper mapper = new DozerBeanMapper();  // a get/set metodusokkal atmasolja az adatokat
        mapper.map(pNewValue, location);
        sortCache();
        try{saveCache();}
        catch(Exception e){throw new DataAccessException(e);}
    }

       
    
}
