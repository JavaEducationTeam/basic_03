/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.javagladiators.app.heroesofempires.cliapplication;

import hu.javagladiators.app.heroesofempires.datamodel.place.Location;
import hu.javagladiators.app.heroesofempires.datamodel.place.LocationService;
import hu.javagladiators.app.heroesofempires.dataservice.fileio.LocationServiceImpl;

/**
 * @author krisztian
 */
public class Main {

    public static void main(String[] args){
        LocationService ls = new LocationServiceImpl();
        for(Location l:ls.getMoreOrderByKey(0, 2))
            System.out.println(l.getName());
        ls.deleteByKey("City 01");
        
        
    }
}
