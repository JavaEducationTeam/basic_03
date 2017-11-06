package hu.javagladiators.app.heroesofempires.dataservice.fileio;

import hu.javagladiators.app.heroesofempires.dataservice.fileio.place.PlaceCache;
import hu.javagladiators.app.heroesofempires.datamodel.base.DataAccessException;
import hu.javagladiators.app.heroesofempires.datamodel.place.Province;
import hu.javagladiators.app.heroesofempires.datamodel.place.ProvinceService;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * @author krisztian
 */
public class ProvinceServiceImpl extends PlaceCache implements ProvinceService{

    public ProvinceServiceImpl() {
        super();
    }

    
    
    @Override
    public void add(Province pValue) {
        getProvinces().add(pValue);
        sortCache();
        try{saveCache();}
        catch(Exception e){throw new DataAccessException(e);} 
    }

    @Override
    public Province getByKey(String pName) {
        for(Province e:getProvinces())
            if(e.getName().equals(pName))
                return e;
        throw new NullPointerException("Not exist Province:"+pName);
    }

    @Override
    public List<Province> getMoreOrderByKey(int pOffset, int pLimit) {
        if(pOffset>=0){
            if((pOffset+pLimit)<getProvinces().size())
                return getProvinces().subList(pOffset, pLimit+pOffset);
            else 
                return getProvinces().subList(pOffset, getProvinces().size());
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
        return getProvinces().size();
    }

    @Override
    public void modify(String pOldKey, Province pNewValue) {
        Province province = getByKey(pOldKey);
        Mapper mapper = new DozerBeanMapper();  // a get/set metodusokkal atmasolja az adatokat
        mapper.map(pNewValue, province);
        sortCache();
        try{saveCache();}
        catch(Exception e){throw new DataAccessException(e);} 
    }

       
    
}
