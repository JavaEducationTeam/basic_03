package hu.javagladiators.app.heroesofempires.dataservice.fileio;

import hu.javagladiators.app.heroesofempires.datamodel.base.DataAccessException;
import hu.javagladiators.app.heroesofempires.datamodel.hero.Hero;
import hu.javagladiators.app.heroesofempires.datamodel.hero.HeroService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * @author krisztian
 */
public class HeroServiceImpl implements HeroService, Comparator<Hero>{
    private static final String TABULATOR = "\t";
    private static String defaultFilePath = new String("heroes.data");
    private static List<Hero> cache = new ArrayList<>();
        
    public HeroServiceImpl() {
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
        cache.add(new Hero("Pókember", "...", true));
        cache.add(new Hero("Denevérember", "...", true));
        cache.add(new Hero("Hulk", "...", false));
        cache.add(new Hero("Alien", "...", false));
        cache.add(new Hero("Törp papa", "...", true));
    }
    
    private void loadData() throws FileNotFoundException, IOException{
        File f = new File(defaultFilePath);
        if(f.exists()){
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            String[] lineData;
            while((line=br.readLine())!=null){
                lineData = line.split(TABULATOR);
                if(lineData.length == 3)
                    cache.add(new Hero(lineData[0], lineData[2], Boolean.getBoolean(lineData[1])));
            }
            br.close();
        }
    }

    private void saveCache() throws IOException{
        File f = new File(defaultFilePath);
        if(!f.exists())
            f.createNewFile();
        FileWriter fw = new FileWriter(f);
        for(Hero h:cache){
            fw.write(h.getName());
            fw.write(TABULATOR);
            fw.write((new Boolean(h.isAvailable())).toString());
            fw.write(TABULATOR);
            fw.write(h.getDescription());
            fw.write(System.lineSeparator());
            fw.flush();
        }
        fw.close();
    }
    
    private void sortCache(){
        Collections.sort(cache, this);
    }
    
    @Override
    public void add(Hero pValue) {
        cache.add(pValue);
        sortCache();
        try{saveCache();}
        catch(Exception e){throw new DataAccessException(e);}
    }

    @Override
    public Hero getByKey(String pName) throws NullPointerException{
        for(Hero h : cache)
            if(h.getName().equals(pName))
                return h;
        throw new NullPointerException("Not Available:"+pName);
    }

    @Override
    public List<Hero> getMoreOrderByKey(int pOffset, int pLimit) {
        if(pOffset>=0 && (pOffset+pLimit)<cache.size())
            return cache.subList(pOffset, pOffset+pLimit);
        return new ArrayList<>();
    }

    @Override
    public void deleteByKey(String pName) {
        Hero tmp = getByKey(pName);
        cache.remove(tmp);
        try{saveCache();}
        catch(Exception e){throw new DataAccessException(e);}
    }

    @Override
    public int compare(Hero o1, Hero o2) {
        return o1.getName().compareTo(o2.getName());
    }

    @Override
    public long getSize() {
        return cache.size();
    }

    @Override
    public void modify(String pOldKey, Hero pNewValue) {
        Hero hero = getByKey(pOldKey);
        Mapper mapper = new DozerBeanMapper();  // a get/set metodusokkal atmasolja az adatokat
        mapper.map(pNewValue, hero);
    }
    
}
