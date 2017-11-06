package hu.javagladiators.app.heroesofempires.dataservice.fileio;

import hu.javagladiators.app.heroesofempires.datamodel.base.DataAccessException;
import hu.javagladiators.app.heroesofempires.datamodel.hero.Hero;
import hu.javagladiators.app.heroesofempires.datamodel.place.Empire;
import hu.javagladiators.app.heroesofempires.datamodel.workplace.WorkPlaceEmpire;
import hu.javagladiators.app.heroesofempires.dataservice.fileio.work.WorkPlaceCache;
import hu.javagladiators.app.heroesofempires.datamodel.workplace.WorkPlaceEmpireService;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author krisztian
 */
public class WorkPlaceEmpireServiceImpl extends WorkPlaceCache implements WorkPlaceEmpireService{

    public WorkPlaceEmpireServiceImpl() {
        super();
    }

    @Override
    public List<WorkPlaceEmpire> getWorkPlaceByHero(Hero pHero, Date pDate) {
        return getWorkPlaceEmpires().stream()
            .filter(
                tmp -> tmp.getHero().equals(pHero) && 
                       (tmp.getValidityEnd().after(pDate) || tmp.getValidityEnd().equals(pDate)) && 
                       (tmp.getValidityStart().before(pDate) || tmp.getValidityStart().equals(pDate))
            )
            .sorted((wp1, wp2) -> Byte.compare(wp1.getType().getPrioritization(),wp2.getType().getPrioritization()))    
            .collect(Collectors.toList());
    }

    @Override
    public List<WorkPlaceEmpire> getWorkPlaceByType(Empire pWorkPlace, Date pDate) {
        return getWorkPlaceEmpires().stream()
            .filter(
                tmp -> tmp.getEmpire().equals(pWorkPlace) && 
                       (tmp.getValidityEnd().after(pDate) || tmp.getValidityEnd().equals(pDate)) && 
                       (tmp.getValidityStart().before(pDate) || tmp.getValidityStart().equals(pDate))
            )
            .sorted((wp1, wp2) -> Byte.compare(wp1.getType().getPrioritization(),wp2.getType().getPrioritization()))    
            .collect(Collectors.toList());
    }

    @Override
    public void add(WorkPlaceEmpire pWorkPlace) {
        CACHE.add(pWorkPlace);
        try{saveCache();}
        catch(IOException | ClassNotFoundException e){throw new DataAccessException(e);}
    }

    @Override
    public void delete(WorkPlaceEmpire pWorkPlace) {
        CACHE.add(pWorkPlace);
        try{saveCache();}
        catch(IOException | ClassNotFoundException e){throw new DataAccessException(e);}
    }

    
    
}
