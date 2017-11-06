package hu.javagladiators.app.heroesofempires.dataservice.fileio.work;

import hu.javagladiators.app.heroesofempires.dataservice.fileio.work.WorkPlaceStoreData;
import hu.javagladiators.app.heroesofempires.datamodel.hero.HeroService;
import hu.javagladiators.app.heroesofempires.datamodel.level.AdministratorTypeService;
import hu.javagladiators.app.heroesofempires.datamodel.place.EmpireService;
import hu.javagladiators.app.heroesofempires.datamodel.place.Location;
import hu.javagladiators.app.heroesofempires.datamodel.place.LocationService;
import hu.javagladiators.app.heroesofempires.datamodel.place.Province;
import hu.javagladiators.app.heroesofempires.datamodel.place.ProvinceService;
import hu.javagladiators.app.heroesofempires.datamodel.workplace.WorkPlace;
import hu.javagladiators.app.heroesofempires.datamodel.workplace.WorkPlaceEmpire;
import hu.javagladiators.app.heroesofempires.datamodel.workplace.WorkPlaceLocation;
import hu.javagladiators.app.heroesofempires.datamodel.workplace.WorkPlaceProvince;
import hu.javagladiators.app.heroesofempires.dataservice.fileio.AdministratorTypeServiceImpl;
import hu.javagladiators.app.heroesofempires.dataservice.fileio.EmpireServiceImpl;
import hu.javagladiators.app.heroesofempires.dataservice.fileio.HeroServiceImpl;
import hu.javagladiators.app.heroesofempires.dataservice.fileio.LocationServiceImpl;
import hu.javagladiators.app.heroesofempires.dataservice.fileio.ProvinceServiceImpl;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * WorkPlace-ek tárolásást megvalósító WorkPlaceStoreData objektum példányok szerializációja
 * @author krisztian
 */
public class WorkPlaceStoreDataSerializer {

    private final AdministratorTypeService aservice = new AdministratorTypeServiceImpl();
    private final HeroService hservice = new HeroServiceImpl();

    private final EmpireService empireService = new EmpireServiceImpl();
    private final ProvinceService provinceService = new ProvinceServiceImpl();
    private final LocationService locationService = new LocationServiceImpl();

    public byte[] serialize(WorkPlace wp) throws IOException {
        WorkPlaceStoreData save = new WorkPlaceStoreData();
        save.setNameHero(wp.getHero().getName());
        save.setStart(wp.getValidityStart());
        save.setStop(wp.getValidityEnd());
        save.setPrioritization(wp.getType().getPrioritization());

        if (wp instanceof WorkPlaceEmpire) {
            save.setNameEmpire(((WorkPlaceEmpire) wp).getEmpire().getName());
        }

        if (wp instanceof WorkPlaceProvince) {
            WorkPlaceProvince wpp = (WorkPlaceProvince) wp;
            save.setNameEmpire(wpp.getEmpire().getName());
            wpp.getProvinces().forEach((tmp) -> {
                save.addNameProvinces(tmp.getName());
            });
        }

        if (wp instanceof WorkPlaceLocation) {
            WorkPlaceLocation wpl = (WorkPlaceLocation) wp;
            save.setNameEmpire(wpl.getEmpire().getName());
            save.addNameProvinces(wpl.getProvince().getName());
            wpl.getLocations().forEach((tmp) -> {
                save.addNameLocations(tmp.getName());
            });
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(save);
        oos.flush();
        oos.close();
        return bos.toByteArray();
    }

    public WorkPlace deserializer(byte[] pValue) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(pValue);
        ObjectInputStream ois = new ObjectInputStream(bis);
        WorkPlaceStoreData stored = (WorkPlaceStoreData) ois.readObject();

        WorkPlace wp;        
        if (stored.getNameProvinces().isEmpty()) {
            wp = new WorkPlaceEmpire();
            ((WorkPlaceEmpire) wp).setEmpire(empireService.getByKey(stored.getNameEmpire()));
        } else if (stored.getNameLocations().isEmpty()) {
            wp = new WorkPlaceProvince();
            ((WorkPlaceProvince) wp).setEmpire(empireService.getByKey(stored.getNameEmpire()));
            stored.getNameProvinces().forEach((name) -> {
                ((WorkPlaceProvince) wp).addProvince(provinceService.getByKey(name));
            });
        } else {
            wp = new WorkPlaceLocation();
            ((WorkPlaceLocation) wp).setEmpire(empireService.getByKey(stored.getNameEmpire()));
            ((WorkPlaceLocation) wp).setProvince(provinceService.getByKey(stored.getNameProvinces().get(0)));
            stored.getNameLocations().forEach((name) -> {
                ((WorkPlaceLocation) wp).addLocation(locationService.getByKey(name));
            });
        }
        
        wp.setValidityStart(stored.getStart());
        wp.setValidityEnd(stored.getStop());
        wp.setHero(hservice.getByKey(stored.getNameHero()));
        wp.setType(aservice.getByKey(stored.getPrioritization()));
        
        return wp;
    }

}
