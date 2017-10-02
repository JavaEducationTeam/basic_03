package hu.javagladiators.app.heroesofempires.dataservice.fileio;

import hu.javagladiators.app.heroesofempires.datamodel.place.Empire;
import hu.javagladiators.app.heroesofempires.datamodel.place.EmpireService;
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
public class EmpireServiceImpl implements EmpireService,Comparator<Empire>{

    private PlaceService cache = new PlaceServiceImpl();
        
    
    @Override
    public void add(Empire pValue) {
        cache.getEmpires().add(pValue);
        sortCache();
        cache.save();
    }

    @Override
    public Empire getByKey(String pName) {
        for(Empire e:cache.getEmpires())
            if(e.getName().equals(pName))
                return e;
        throw new NullPointerException("Not exist Empire:"+pName);
    }

    @Override
    public List<Empire> getMoreOrderByKey(int pOffset, int pLimit) {
        if(pOffset>0 && (pOffset+pLimit)<cache.getEmpires().size())
            return cache.getEmpires().subList(pLimit, pLimit+pOffset);
        return new ArrayList<>();
    }

    @Override
    public void deleteByKey(String pName) {
        cache.getEmpires().remove(getByKey(pName));
        cache.save();
    }

    @Override
    public long getSize() {
        return cache.getEmpires().size();
    }

    @Override
    public void modify(String pOldKey, Empire pNewValue) {
        Empire empire = getByKey(pOldKey);
        Mapper mapper = new DozerBeanMapper();  // a get/set metodusokkal atmasolja az adatokat
        mapper.map(pNewValue, empire);
        sortCache();
        cache.save();
    }

       
    private void sortCache(){
        Collections.sort(cache.getEmpires(), this);
    }
    
    @Override
    public int compare(Empire o1, Empire o2) {
        return o1.getName().compareTo(o2.getName());
    }
    
}
