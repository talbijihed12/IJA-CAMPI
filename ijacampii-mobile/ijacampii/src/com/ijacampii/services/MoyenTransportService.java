package com.ijacampii.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.ijacampii.entities.MoyenTransport;
import com.ijacampii.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MoyenTransportService {
    
    public ArrayList<MoyenTransport> tasks;
    public boolean resultOK;
    public static MoyenTransportService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<MoyenTransport> listMoyenTransports;


    private MoyenTransportService() {
        cr = new ConnectionRequest();
    }

    public static MoyenTransportService getInstance() {
        if (instance == null) {
            instance = new MoyenTransportService();
        }
        return instance;
    }

    public ArrayList<MoyenTransport> getAll() {
        listMoyenTransports = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/moyenTransport");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listMoyenTransports = getList();
                }

                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listMoyenTransports;
    }

    private ArrayList<MoyenTransport> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                MoyenTransport moyenTransport = new MoyenTransport(
                        (int) Float.parseFloat(obj.get("id").toString()),

                        (String) obj.get("type")

                );

                listMoyenTransports.add(moyenTransport);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listMoyenTransports;
    }
    
    
    
    public boolean ajouterTransport(MoyenTransport moy) {
        
        String url = Statics.BASE_URL +
                "/moyenTransport/newJson?type=" + moy.getType()+
                "&matricule=" + moy.getMatricule()+
                "&marque=" + moy.getMarque()+
                "&nbr_place=" + moy.getNbr_place()+
                "&frais=" + moy.getFrais();
        cr.setUrl(url);// Insertion de l'URL de notre demande de connexion        
        //cr.setPost(true);
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = cr.getResponseCode() == 200;
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cr);
        return resultOK;
    }
    
    public ArrayList<MoyenTransport> parseTasks(String jsonText){
            
         try {
               listMoyenTransports = new ArrayList<>();
               
               JSONParser j = new JSONParser();
               Map<String,Object> eventListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
               List<Map<String, Object>> list = (List<Map<String,Object>>)eventListJson.get("root");
               System.out.println(eventListJson.toString());
               
               
               for (Map<String, Object> obj : list) {
                   MoyenTransport moy = new MoyenTransport();
                   float id = Float.parseFloat(obj.get("idTransport").toString());
                    moy.setId((int)id);
                    
                   moy.setType(obj.get("type").toString());
                   moy.setMatricule(obj.get("matricule").toString());
                   moy.setMarque(obj.get("marque").toString());
                   //Integer nbr_place = Integer.parseInt(obj.get("nbrPlace").toString());
                   //moy.setNbr_place((int)nbr_place);
                   double frais = Double.parseDouble(obj.get("frais").toString());
                    moy.setFrais((double)frais);
                    
                   System.out.println(moy.toString());
                   listMoyenTransports.add(moy);
                   
               }
             
               
           } catch (IOException ex) {
             //  Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
           }
           return listMoyenTransports;
            }
    
    
    
    public ArrayList<MoyenTransport> getAllTransport (){
        String url = Statics.BASE_URL+"/moyen/transport/index/json/";
        cr.setUrl(url);
        cr.setPost(false);
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(cr.getResponseData()));
                cr.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cr);
        return tasks;
    }
    
    public boolean delete(int idTransport)
    {
        String url=Statics.BASE_URL + "/moyen/transport/json/delete/"+idTransport;
        cr.setUrl(url);
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseCodeListener(this);
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cr);
        return resultOK;
 
    }
}
