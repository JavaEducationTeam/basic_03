package hu.javagladiators.app.heroesofempires.dataservice.fileio;

import hu.javagladiators.app.heroesofempires.dataservice.fileio.place.PlaceCache;
import hu.javagladiators.app.heroesofempires.datamodel.base.DataAccessException;
import hu.javagladiators.app.heroesofempires.datamodel.place.Empire;
import hu.javagladiators.app.heroesofempires.datamodel.place.EmpireService;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * @author krisztian
 */
public class EmpireServiceImpl extends PlaceCache implements EmpireService{

    public EmpireServiceImpl() {
        super();
    }

    
    
    @Override
    public void add(Empire pValue) {
        getEmpires().add(pValue);
        sortCache();
        try{saveCache();}
        catch(Exception e){throw new DataAccessException(e);} 
    }

    @Override
    public Empire getByKey(String pName) {
        for(Empire e:getEmpires())
            if(e.getName().equals(pName))
                return e;
        throw new NullPointerException("Not exist Empire:"+pName);
    }

    @Override
    public List<Empire> getMoreOrderByKey(int pOffset, int pLimit) {
        int count = getEmpires().size();
        if(count<=0) return new ArrayList<>();
            
        if(pOffset<0) pOffset =0;
        if(pOffset>=count) pOffset = count-1;
        if((pOffset+pLimit) >= count) pLimit = count-pOffset;
        
        return getEmpires().subList(pOffset, pLimit+pOffset);            
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
        return getEmpires().size();
    }

    @Override
    public void modify(String pOldKey, Empire pNewValue) {
        Empire empire = getByKey(pOldKey);
        Mapper mapper = new DozerBeanMapper();  // a get/set metodusokkal atmasolja az adatokat
        mapper.map(pNewValue, empire);
        sortCache();
        try{saveCache();}
        catch(Exception e){throw new DataAccessException(e);} 
    }

    
}
