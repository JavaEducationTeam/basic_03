/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.javagladiators.app.heroesofempires.dataservice.fileio.work;

import hu.javagladiators.app.heroesofempires.datamodel.workplace.WorkPlace;
import hu.javagladiators.app.heroesofempires.datamodel.workplace.WorkPlaceEmpire;
import hu.javagladiators.app.heroesofempires.datamodel.workplace.WorkPlaceLocation;
import hu.javagladiators.app.heroesofempires.datamodel.workplace.WorkPlaceProvince;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author krisztian
 */
public class WorkPlaceCache {
    
    private static final String FILEPATH="workplacesp.data";
    protected static final List<WorkPlace> CACHE = new ArrayList<>();
    private WorkPlaceStoreDataSerializer wpsds = new WorkPlaceStoreDataSerializer();
    
    public WorkPlaceCache() {
        try {
            loadCache();
        } catch (Exception ex) {ex.printStackTrace();}
        
    }
    
    protected void loadCache() throws FileNotFoundException, IOException, ClassNotFoundException{
        RandomAccessFile raf = new RandomAccessFile(FILEPATH,"r");
        byte[] bb;
        raf.seek(0);
        while(raf.getFilePointer()<raf.length()){
            bb = new byte[raf.readInt()];//adatmeret
            raf.read(bb);//adat
            CACHE.add(wpsds.deserializer(bb));
        }        
        raf.close();
    }

    protected void saveCache() throws FileNotFoundException, IOException, ClassNotFoundException{
        RandomAccessFile raf = new RandomAccessFile(FILEPATH,"rws");
        byte[] objectData;
        raf.getChannel().position(0);
        for(WorkPlace wp : CACHE){
            objectData = wpsds.serialize(wp);          
            raf.writeInt(objectData.length);  //adatmeret
            raf.write(objectData); //adat            
        }                 
        raf.close();
    }

 
    public List<WorkPlaceEmpire> getWorkPlaceEmpires() {
        return (List<WorkPlaceEmpire>)CACHE.stream()
            .filter(tmp -> tmp instanceof WorkPlaceEmpire)
            .collect((Collector<? super WorkPlace, ?, ?>) Collectors.toList());
    }


    public List<WorkPlaceProvince> getWorkPlaceProvinces() {
        return (List<WorkPlaceProvince>) CACHE.stream()
            .filter(tmp -> tmp instanceof WorkPlaceProvince)
            .collect((Collector<? super WorkPlace, ?, ?>) Collectors.toList());
    }


    public List<WorkPlaceLocation> getWorkPlaceLocations() {
        return (List<WorkPlaceLocation>) CACHE.stream()
            .filter(tmp -> tmp instanceof WorkPlaceLocation)
            .collect((Collector<? super WorkPlace, ?, ?>) Collectors.toList());
    }

    
}
