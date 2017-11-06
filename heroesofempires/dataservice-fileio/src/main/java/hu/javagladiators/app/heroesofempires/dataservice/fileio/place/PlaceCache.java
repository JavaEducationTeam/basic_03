package hu.javagladiators.app.heroesofempires.dataservice.fileio.place;

import hu.javagladiators.app.heroesofempires.datamodel.base.DataAccessException;
import hu.javagladiators.app.heroesofempires.datamodel.place.Empire;
import hu.javagladiators.app.heroesofempires.datamodel.place.Location;
import hu.javagladiators.app.heroesofempires.datamodel.place.Province;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author krisztian
 */
public class PlaceCache implements Serializable,Comparator<Location>{
    
    protected static List<Location> cache = null;
    private final String filePath ="places.objects";

    public PlaceCache() {
        if(cache == null){
            File f = new File(filePath);
            if(f.exists()){
                try{
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
                    cache = (List<Location>)ois.readObject();
                    ois.close();
                }
                catch(IOException | ClassNotFoundException e){cache = new ArrayList<>();}
            }
            else{
                initData();
                try{saveCache();}
                catch(Exception e){throw new DataAccessException(e);}    
            }
        }
    }

    
    
    private void initData(){
        cache = new ArrayList<>();
        cache.add(new Location("City 01", ".."));
        cache.add(new Location("City 02", ".."));
        cache.add(new Location("City 03", ".."));
        cache.add(new Province("Province 01", ".."));
        ((Province)cache.get(cache.size()-1)).addLocation(cache.get(0));
        ((Province)cache.get(cache.size()-1)).addLocation(cache.get(1));
        ((Province)cache.get(cache.size()-1)).setCentral(cache.get(0));
        cache.add(new Empire("Empire 01", ".."));
        ((Empire)cache.get(cache.size()-1)).addProvince((Province)cache.get(3));
        ((Empire)cache.get(cache.size()-1)).setCapital(cache.get(0));
    }
    
    protected void saveCache() throws FileNotFoundException, IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
        oos.writeObject(cache);
        oos.flush();
        oos.close();
    
    }

    public List<Location> getLocations() {
        return cache.stream()
            .filter(tmp -> tmp instanceof Location)
            .collect(Collectors.toList());
    }


    public List<Province> getProvinces() {
        return (List<Province>) cache.stream()
            .filter(tmp -> tmp instanceof Province)
            .collect((Collector<? super Location, ?, ?>) Collectors.toList());
    }


    public List<Empire> getEmpires() {
        return (List<Empire>) cache.stream()
            .filter(tmp -> tmp instanceof Empire)
            .collect((Collector<? super Location, ?, ?>) Collectors.toList());
    }

    protected void sortCache(){
        Collections.sort(cache, this);
    }
        
    @Override
    public int compare(Location o1, Location o2) {
        return o1.getName().compareTo(o2.getName());
    }
    
}
