package hu.javagladiators.app.heroesofempires.datamodel.workplace;

import hu.javagladiators.app.heroesofempires.datamodel.hero.Hero;
import java.util.Date;
import java.util.List;

/**
 * @author krisztian
 * @param <T>
 * @param <K>
 */
public interface WorkPlaceService<T,K> {
    public List<T> getWorkPlaceByHero(Hero pHero, Date pDate);
    public List<T> getWorkPlaceByType(K pWorkPlace, Date pDate);
    public void add(T pWorkPlace);
    public void delete(T pWorkPlace);
}
