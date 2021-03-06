/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bhaduri.jogajog.services;

import org.bhaduri.jogajog.DA.MinutedataDA;
import org.bhaduri.jogajog.JPA.exceptions.PreexistingEntityException;
import org.bhaduri.jogajog.entities.Minutedata;
import org.bhaduri.jogajog.entities.MinutedataPK;
import org.bhaduri.nouka.DTO.ScripData;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import org.bhaduri.jogajog.DA.ScripsDA;
import org.bhaduri.jogajog.entities.Scrips;
import org.bhaduri.nouka.DTO.ListScripData;

/**
 *
 * @author susmita
 */
public class MasterDataService {

    private EntityManagerFactory emf;

    public MasterDataService() {
        emf = Persistence.createEntityManagerFactory("org.bhaduri_Jogajog_jar_1.0-SNAPSHOTPU");
    }

    public void insertIntoMinutedata(ScripData scripData) {
        MinutedataDA minutedataDA = new MinutedataDA(emf);
        try {
            MinutedataPK minDataPk = new MinutedataPK();
            Minutedata minDataRecord = new Minutedata();

            minDataPk.setScripid(scripData.getScripId());
            minDataPk.setLastupdateminute(scripData.getLastUpdateTime());

            minDataRecord.setMinutedataPK(minDataPk);
            minDataRecord.setDayhighprice(scripData.getDayHighPrice());
            minDataRecord.setDaylastprice(scripData.getDayLastPrice());
            minDataRecord.setDaylowprice(scripData.getDayLowPrice());
            minDataRecord.setOpenprice(scripData.getOpenPrice());
            minDataRecord.setPrevcloseprice(scripData.getPrevClosePrice());
            minDataRecord.setTotaltradedvolume(scripData.getTotalTradedVolume());

            minutedataDA.create(minDataRecord);
//            return HedwigResponseCode.SUCCESS;
        } catch (PreexistingEntityException preexistingEntityException) {
            System.out.println("data exists" + scripData.getScripId() + scripData.getLastUpdateTime());
        } catch (Exception exception) {
            System.out.println(exception + " has occurred in saveSripData.");
        }
    }

    public ListScripData getScripID() {
        ScripsDA scripsDA = new ScripsDA(emf);

        try {
            ListScripData scripdatalist = new ListScripData();
            List<ScripData> listofscripdata = new ArrayList<>();
            List<Scrips> scripidList = scripsDA.findScripsEntities();
            for (int i = 0; i < scripidList.size(); i++) { 
                ScripData scripData = new ScripData();
                scripData.setScripId(scripidList.get(i).getScripid());
                listofscripdata.add(scripData);
            } 
            scripdatalist.setScripdatalist(listofscripdata);
            return scripdatalist;
        } catch (Exception exception) {
            System.out.println(exception + " has occurred in saveSripData.");
            return null;
        }

    }
    
    public ListScripData getDataForScripID(ScripData scripid) {
        MinutedataDA minutedataDA = new MinutedataDA(emf);
        try {
            ListScripData mindatalist = new ListScripData();
            List<ScripData> listofscripdata = new ArrayList<>();
            
            List<Minutedata> minutedatas = minutedataDA.findByScripid(scripid.getScripId());
            for (int i = 0; i < minutedatas.size(); i++) { 
                ScripData scripData = new ScripData();
                scripData.setScripId(minutedatas.get(i).getMinutedataPK().getScripid());
                scripData.setLastUpdateTime(minutedatas.get(i).getMinutedataPK().getLastupdateminute());
                
                scripData.setDayHighPrice(minutedatas.get(i).getDayhighprice());
                scripData.setDayLastPrice(minutedatas.get(i).getDaylastprice());
                scripData.setDayLowPrice(minutedatas.get(i).getDaylowprice());
                scripData.setOpenPrice(minutedatas.get(i).getOpenprice());
                scripData.setPrevClosePrice(minutedatas.get(i).getPrevcloseprice());
                scripData.setTotalTradedVolume(minutedatas.get(i).getTotaltradedvolume());
                listofscripdata.add(scripData);
            } 
            mindatalist.setScripdatalist(listofscripdata);
            return mindatalist;
        } catch (Exception exception) {
            System.out.println(exception + " has occurred in saveSripData.");
            return null;
        }

    }
}
