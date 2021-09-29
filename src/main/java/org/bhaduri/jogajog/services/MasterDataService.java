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
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author susmita
 */
public class MasterDataService {

    private EntityManagerFactory emf;

    public MasterDataService() {
        emf = Persistence.createEntityManagerFactory("org.bhaduri_Taranga_jar_1.0-SNAPSHOTPU");
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

//     public void getDvripID(String scriptSym) {
//        MinutedataDA minutedataDA = new MinutedataDA(emf);
//        try {
//            MinutedataPK minDataPk = new MinutedataPK();
//            Minutedata minDataRecord = new Minutedata();
//
//            minDataPk.setScripid(scripData.getScripId());
//            minDataPk.setLastupdateminute(scripData.getLastUpdateTime());
//
//            minDataRecord.setMinutedataPK(minDataPk);
//            minDataRecord.setDayhighprice(scripData.getDayHighPrice());
//            minDataRecord.setDaylastprice(scripData.getDayLastPrice());
//            minDataRecord.setDaylowprice(scripData.getDayLowPrice());
//            minDataRecord.setOpenprice(scripData.getOpenPrice());
//            minDataRecord.setPrevcloseprice(scripData.getPrevClosePrice());
//            minDataRecord.setTotaltradedvolume(scripData.getTotalTradedVolume());
//
//            minutedataDA.create(minDataRecord);
////            return HedwigResponseCode.SUCCESS;
//        }  catch (PreexistingEntityException preexistingEntityException) {
//            System.out.println("data exists" + scripData.getScripId() + scripData.getLastUpdateTime());
//        } catch (Exception exception) {
//            System.out.println(exception + " has occurred in saveSripData.");
//        }
//    }
    public class getDetailsPerScripID {

        public void rapidAPICallForScripID() {

            HttpGet request = new HttpGet("https://nse-data1.p.rapidapi.com/nifty_fifty_indices_data");
            // add request headers
            request.addHeader("x-rapidapi-key", "7f737cfca9msh84109244b6b532ap1d491ejsne997fa69f709");
            request.addHeader("x-rapidapi-host", "nse-data1.p.rapidapi.com");

            // TODO code application logic here
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(request);
            } catch (IOException ex) {
                System.out.println("IOException with response" + response);
//                Logger.getLogger(PerMinuteResposeOfNSE.class.getName()).log(Level.SEVERE, null, ex);
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // return it as a String
                String n50Resp = "";
                try {
                    n50Resp = EntityUtils.toString(entity);
                } catch (IOException ex) {
                    System.out.println("IOException with n50Resp" + n50Resp);
//                    Logger.getLogger(PerMinuteResposeOfNSE.class.getName()).log(Level.SEVERE, null, ex);
                } catch (org.apache.http.ParseException ex) {
                    System.out.println("ParseException with n50Resp" + n50Resp);
//                    Logger.getLogger(PerMinuteResposeOfNSE.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception exception) {
                    System.out.println(exception + " has occurred in n50Resp." + n50Resp);
                }
                JSONObject perMinResp = null;
                try {
                    perMinResp = new JSONObject(n50Resp);
                } catch (JSONException ex) {
                    System.out.println("Problem with perMinResp" + perMinResp);
//                    Logger.getLogger(PerMinuteResposeOfNSE.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception exception) {
                    System.out.println(exception + " has occurred in perMinResp." + perMinResp);
                }
                //                   System.out.println(resultObject.get("body"));
                JSONObject bodyJsonObj = new JSONObject();
                try {
                    bodyJsonObj = perMinResp.getJSONObject("body");
                } catch (JSONException ex) {
                    System.out.println("Problem with bodyJsonObj" + bodyJsonObj);
//                    Logger.getLogger(PerMinuteResposeOfNSE.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception exception) {
                    System.out.println(exception + " has occurred in bodyJsonObj." + bodyJsonObj);
                }

                JSONArray dataArray = null;
                try {
                    dataArray = bodyJsonObj.getJSONArray("data");
                } catch (JSONException ex) {
                    System.out.println("Problem with dataArray" + dataArray);
//                    Logger.getLogger(PerMinuteResposeOfNSE.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception exception) {
                    System.out.println(exception + " has occurred in dataArray." + dataArray);
                }
                int dataArrayCount = dataArray.length();
                if (dataArrayCount > 0) {
                    for (int i = 0; i < dataArray.length(); i++) {

                        JSONObject scripObj = null;
                        try {
                            scripObj = dataArray.getJSONObject(i);
                            String sym = scripObj.get("symbol").toString();
                        } catch (JSONException ex) {
                            System.out.println("Problem with scripObj" + scripObj);
//                        Logger.getLogger(PerMinuteResposeOfNSE.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception exception) {
                            System.out.println(exception + " has occurred in scripObj." + scripObj);
                        }

//                    saveSripData(scripData);
//                        System.out.println("symbol" + scripData.getScripId());
//                        System.out.println("open" + scripData.getOpenPrice());
//                        System.out.println("day high" + scripData.getDayHighPrice());
//                        System.out.println("day low" + scripData.getDayLowPrice());
//                        System.out.println("day current" + scripData.getDayLastPrice());
//                        System.out.println("prev close" + scripData.getPrevClosePrice());
//                        System.out.println("trade volume" + scripData.getTotalTradedVolume());
//                        System.out.println("last update" + scripData.getLastUpdateTime());
                    }
                    //                System.out.println(n50Resp);
                } else {
                    System.out.println("Value of dataArrayCount" + dataArrayCount);
                }
            }
        }
    }
}
