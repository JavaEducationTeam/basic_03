package hu.javagladiators.app.heroesofempires.dataservice.fileio;

import hu.javagladiators.app.heroesofempires.datamodel.place.Empire;
import hu.javagladiators.app.heroesofempires.datamodel.place.EmpireService;
import hu.javagladiators.app.heroesofempires.datamodel.place.PlaceService;
import hu.javagladiators.app.heroesofempires.datamodel.place.Province;
import hu.javagladiators.app.heroesofempires.datamodel.place.ProvinceService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * @author krisztian
 */
public class ProvinceServiceImpl implements ProvinceService,Comparator<Province>{

    private PlaceService cache = new PlaceServiceImpl();
        
    
    @Override
    public void add(Province pValue) {
        cache.getProvinces().add(pValue);
        sortCache();
        cache.save();
    }

    @Override
    public Province getByKey(String pName) {
        for(Province e:cache.getProvinces())
            if(e.getName().equals(pName))
                return e;
        throw new NullPointerException("Not exist Province:"+pName);
    }

    @Override
    public List<Province> getMoreOrderByKey(int pOffset, int pLimit) {
        if(pOffset>0 && (pOffset+pLimit)<cache.getEmpires().size())
            return cache.getProvinces().subList(pLimit, pLimit+pOffset);
        return new ArrayList<>();
    }

    @Override
    public void deleteByKey(String pName) {
        cache.getProvinces().remove(getByKey(pName));
        cache.save();
    }

    @Override
    public long getSize() {
        return cache.getProvinces().size();
    }

    @Override
    public void modify(String pOldKey, Province pNewValue) {
        Province province = getByKey(pOldKey);
        Mapper mapper = new DozerBeanMapper();  // a get/set metodusokkal atmasolja az adatokat
        mapper.map(pNewValue, province);
        sortCache();
        cache.save();
    }

       
    private void sortCache(){
        Collections.sort(cache.getProvinces(), this);
    }
    
    @Override
    public int compare(Province o1, Province o2) {
        return o1.getName().compareTo(o2.getName());
    }
    
}
