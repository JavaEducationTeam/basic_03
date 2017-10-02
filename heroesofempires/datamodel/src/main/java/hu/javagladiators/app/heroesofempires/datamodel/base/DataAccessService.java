package hu.javagladiators.app.heroesofempires.datamodel.base;

import java.util.List;

/**
 * Alltalanos adat kezeles
 * @author krisztian
 * @param <T> kezelt etity
 * @param <K> kulcs tipus
 */
public interface DataAccessService<T,K> {
/**
 * Letrehozas
 * @param pValue 
 */    
    public void add(T pValue);
/**
 * Egy elem lekerdezese
 * @param pName
 * @return 
 */    
    public T getByKey(K pName);
/**
 * Tobb elem lekerdezese
 * @param pOffset
 * @param pLimit
 * @return 
 */    
    public List<T> getMoreOrderByKey(int pOffset, int pLimit);
/**
 * Torles
 * @param pName 
 */    
    public void deleteByKey(K pName);    
    
/**
 * Kezelt adatok szamamak meghatarozasa
 * @return Kezelt adatok szama
 */    
    public long getSize();
    
/**
 * Modositas
 * @param pOldKey
 * @param pNewValue 
 */    
    public void modify(K pOldKey, T pNewValue);
}
