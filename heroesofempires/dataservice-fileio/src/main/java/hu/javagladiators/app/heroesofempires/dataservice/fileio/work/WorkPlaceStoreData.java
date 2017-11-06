package hu.javagladiators.app.heroesofempires.dataservice.fileio.work;

import hu.javagladiators.app.heroesofempires.datamodel.workplace.WorkPlace;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A WorkPlace objektumok hivatkoznak más objektumok példányaira melyek tárolása már valamilyen más
 * formában meg vannak oldva így a hivatkozott példányokat felesleges újra serialaizálni és tárolni.
 * 
 * 
 * @author krisztian
 */
public class WorkPlaceStoreData implements Serializable{
    private String nameHero;
    private String nameEmpire;
    private List<String> nameProvinces;
    private List<String> nameLocations;
    private byte prioritization;
    private Date start, stop;

    public WorkPlaceStoreData() {
        nameLocations = new ArrayList<>();
        nameProvinces = new ArrayList<>();
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getStop() {
        return stop;
    }

    public void setStop(Date stop) {
        this.stop = stop;
    }
    
    

    public String getNameHero() {
        return nameHero;
    }

    public void setNameHero(String nameHero) {
        this.nameHero = nameHero;
    }

    public String getNameEmpire() {
        return nameEmpire;
    }

    public void setNameEmpire(String nameEmpire) {
        this.nameEmpire = nameEmpire;
    }

    public List<String> getNameProvinces() {
        return nameProvinces;
    }

    public void setNameProvinces(List<String> nameProvinces) {
        this.nameProvinces = nameProvinces;
    }

    public void addNameProvinces(String nameProvince) {
        this.nameProvinces.add(nameProvince);
    }

    public List<String> getNameLocations() {
        return nameLocations;
    }

    public void setNameLocations(List<String> nameLocations) {
        this.nameLocations = nameLocations;
    }

    public void addNameLocations(String nameLocation) {
        this.nameLocations.add(nameLocation);
    }

    public byte getPrioritization() {
        return prioritization;
    }

    public void setPrioritization(byte prioritization) {
        this.prioritization = prioritization;
    }
            
}
