/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.javagladiators.app.heroesofempires.cliapplication;

import hu.javagladiators.app.heroesofempires.datamodel.hero.HeroService;
import hu.javagladiators.app.heroesofempires.datamodel.level.AdministratorTypeService;
import hu.javagladiators.app.heroesofempires.datamodel.place.Empire;
import hu.javagladiators.app.heroesofempires.datamodel.place.EmpireService;
import hu.javagladiators.app.heroesofempires.datamodel.workplace.WorkPlaceEmpire;
import hu.javagladiators.app.heroesofempires.datamodel.workplace.WorkPlaceEmpireService;
import hu.javagladiators.app.heroesofempires.dataservice.fileio.AdministratorTypeServiceImpl;
import hu.javagladiators.app.heroesofempires.dataservice.fileio.EmpireServiceImpl;
import hu.javagladiators.app.heroesofempires.dataservice.fileio.HeroServiceImpl;
import hu.javagladiators.app.heroesofempires.dataservice.fileio.WorkPlaceEmpireServiceImpl;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author krisztian
 */
public class Main {

    public static void main(String[] args){
        WorkPlaceEmpireService wpeService = new WorkPlaceEmpireServiceImpl();
        HeroService heroService = new HeroServiceImpl();
        EmpireService empireService = new EmpireServiceImpl();
        AdministratorTypeService aService =new AdministratorTypeServiceImpl();
        
        long ts = System.currentTimeMillis();
        
        Random rnd = new Random();
        long oneyear = 356*24*60*60*1000000;
        Empire empire = empireService.getMoreOrderByKey(0, (int)empireService.getSize()).get(0);
        heroService.getMoreOrderByKey(0, (int)heroService.getSize()).stream()
                .forEach(hero ->{
                    WorkPlaceEmpire wpe = new WorkPlaceEmpire();
                    wpe.setHero(hero);
                    wpe.setEmpire(empire);
                    wpe.setValidityStart(new Date(ts));
                    wpe.setValidityEnd(new Date(ts+oneyear));
                    wpe.setType(aService.getMoreOrderByKey(0, (int)aService.getSize()).get(rnd.nextInt((int)aService.getSize())));
                    wpeService.add(wpe);
                });
        
        List<WorkPlaceEmpire> protectives= wpeService.getWorkPlaceByType(empire, new Date(ts));
    
        protectives.stream().forEach(p -> 
                System.out.println(p.getHero().getName()+":"+
                        p.getEmpire().getName()+":"+
                        p.getType().getPrioritization()));
    }
}
