package hu.javagladiators.app.heroesofempires.dataservice.fileio;

import hu.javagladiators.app.heroesofempires.datamodel.base.DataAccessException;
import hu.javagladiators.app.heroesofempires.datamodel.hero.Hero;
import hu.javagladiators.app.heroesofempires.datamodel.level.AdministratorType;
import hu.javagladiators.app.heroesofempires.datamodel.level.AdministratorTypeService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * @author krisztian
 */
public class AdministratorTypeServiceImpl implements AdministratorTypeService, Comparator<AdministratorType>{

    private static final String defaultFilePath = "level.properties";
    private static final List<AdministratorType> cache = new ArrayList<>();

    public AdministratorTypeServiceImpl() {
        if(cache.isEmpty()){
            File f = new File(defaultFilePath);
            if(f.exists())
                try{
                    loadData();
                    sortCache();
                }
                catch(Exception e){/*hibakezeles*/}
            else{
                initData();                
                try{saveCache();}
                catch(Exception e){/*hibakezeles*/}
            }
        }
    }

        
    private void initData(){
        cache.add(new AdministratorType((byte)1));
        cache.add(new AdministratorType((byte)2));
        cache.add(new AdministratorType((byte)3));
    }
    
    private void loadData() throws FileNotFoundException, IOException{
        Properties properties = new Properties();
        properties.load(new FileReader(defaultFilePath));
        Enumeration<Object> enm = properties.elements();
        Object key;
        while(enm.hasMoreElements()){
            key = enm.nextElement();
            cache.add(new AdministratorType(Byte.parseByte((String)key)));
        }                    
    }
    
    private void saveCache() throws FileNotFoundException, IOException{
        Properties properties = new Properties();
        for(AdministratorType t : cache)
            properties.setProperty(""+t.getPrioritization(), "..");
        OutputStream output = new FileOutputStream(defaultFilePath);
        properties.store(output, null);
    }
    
    private void sortCache(){
        Collections.sort(cache, this);
    }
    
    @Override
    public int compare(AdministratorType o1, AdministratorType o2) {
        return (o1.getPrioritization()>o2.getPrioritization())?1:-1;
    }

    @Override
    public void add(AdministratorType pValue) {
        cache.add(pValue);
        try{
            sortCache();
            saveCache();
        }
        catch(Exception e){throw new DataAccessException(e);}
    }

    @Override
    public AdministratorType getByKey(Byte pName) {
        for(AdministratorType a : cache)
            if(a.getPrioritization() == pName )
                return a;
        throw new NullPointerException();
    }

    @Override
    public List<AdministratorType> getMoreOrderByKey(int pOffset, int pLimit) {
        if(pOffset>0 && (pOffset+pLimit)<cache.size())
            return cache.subList(pLimit, pLimit+pOffset);
        return new ArrayList<>();
    }

    @Override
    public void deleteByKey(Byte pName) {
        cache.remove(getByKey(pName));
        try{saveCache();}
        catch(Exception e){throw new DataAccessException(e);}
    }

    @Override
    public long getSize() {
        return cache.size();
    }

    @Override
    public void modify(Byte pOldKey, AdministratorType pNewValue) {
        getByKey(pOldKey).setPrioritization(pNewValue.getPrioritization());
        try{saveCache();}
        catch(Exception e){throw new DataAccessException(e);}
    }
        
}
