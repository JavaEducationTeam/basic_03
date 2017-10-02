package hu.javagladiators.app.heroesofempires.dataservice.fileio;

import hu.javagladiators.app.heroesofempires.datamodel.place.Location;
import hu.javagladiators.app.heroesofempires.datamodel.place.LocationService;
import hu.javagladiators.app.heroesofempires.datamodel.place.PlaceService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * @author krisztian
 */
public class LocationServiceImpl implements LocationService,Comparator<Location>{

    private PlaceService cache = new PlaceServiceImpl();
        
    
    @Override
    public void add(Location pValue) {
        cache.getLocations().add(pValue);
        sortCache();
        cache.save();
    }

    @Override
    public Location getByKey(String pName) {
        for(Location e:cache.getLocations())
            if(e.getName().equals(pName))
                return e;
        throw new NullPointerException("Not exist Province:"+pName);
    }

    @Override
    public List<Location> getMoreOrderByKey(int pOffset, int pLimit) {
        if(pOffset>0 && (pOffset+pLimit)<cache.getLocations().size())
            return cache.getLocations().subList(pLimit, pLimit+pOffset);
        return new ArrayList<>();
    }

    @Override
    public void deleteByKey(String pName) {
        cache.getLocations().remove(getByKey(pName));
        cache.save();
    }

    @Override
    public long getSize() {
        return cache.getLocations().size();
    }

    @Override
    public void modify(String pOldKey, Location pNewValue) {
        Location location = getByKey(pOldKey);
        Mapper mapper = new DozerBeanMapper();  // a get/set metodusokkal atmasolja az adatokat
        mapper.map(pNewValue, location);
        sortCache();
        cache.save();
    }

       
    private void sortCache(){
        Collections.sort(cache.getLocations(), this);
    }
    
    @Override
    public int compare(Location o1, Location o2) {
        return o1.getName().compareTo(o2.getName());
    }
    
}
