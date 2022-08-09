package com.ijacampii.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.ijacampii.entities.Equipement;
import com.ijacampii.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EquipementService {

    public static EquipementService instance = null;
    public int resultCode;
     public boolean resultOK;
    private ConnectionRequest cr;
    private ArrayList<Equipement> listEquipements;

         public ArrayList<Equipement> tasks;

    private EquipementService() {
        cr = new ConnectionRequest();
    }

    public static EquipementService getInstance() {
        if (instance == null) {
            instance = new EquipementService();
        }
        return instance;
    }

    public ArrayList<Equipement> getAll() {
        listEquipements = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/equipement");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listEquipements = getList();
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

        return listEquipements;
    }

    private ArrayList<Equipement> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Equipement equipement = new Equipement(
                        (int) Float.parseFloat(obj.get("id").toString()),

                        (String) obj.get("nom")

                );

                listEquipements.add(equipement);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listEquipements;
    }
    
    
    
    
    
    public boolean addEquipement(Equipement h) {
        String url = Statics.BASE_URL + "/equipement/json/new?nom=" + h.getNom() + "&photo=" + h.getPhoto()+
                "&description=" + h.getDescription() + "&marque=" + h.getMarque()+ "&prix=" + h.getPrix()+ "&categorie=" + h.getCategorie();
        cr.setUrl(url);// Insertion de l'URL de notre demande de connexion        
        //req.setPost(true);
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = cr.getResponseCode() == 200;
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cr);
        return resultOK;
    }
   
    public ArrayList<Equipement> parseTasks(String jsonText){
            
         try {
               listEquipements = new ArrayList<>();
               
               JSONParser j = new JSONParser();
               Map<String,Object> eventListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
               List<Map<String, Object>> list = (List<Map<String,Object>>)eventListJson.get("root");
               System.out.println(eventListJson.toString());
               
               
               for (Map<String, Object> obj : list) {
                   Equipement e = new Equipement();
                   float id = Float.parseFloat(obj.get("id").toString());
                    e.setId((int)id);
              //     e.setStatut(obj.get("statut").toString());
          //    e.setStatut(obj.get("statut").toString());
                   e.setDescription(obj.get("description").toString());
                   e.setNom(obj.get("nom").toString());
                   e.setCategorie(obj.get("categorie").toString());
                   e.setMarque(obj.get("marque").toString());
                   //e.setPhoto(obj.get("photo").toString());
                   float prix=Float.parseFloat(obj.get("prix").toString());
                   e.setPrix(prix);
                   System.out.println(e.toString());
                   listEquipements.add(e);
                   
               }
             
               
           } catch (IOException ex) {
             //  Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
           }
           return listEquipements;
            }
            
            
 

    
    public ArrayList<Equipement> getAllEquipements (){
        String url = Statics.BASE_URL+"/equipement/json/show/";
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
    
    public boolean delete(int id)
    {
        String url=Statics.BASE_URL + "/equipement/json/delete/"+id;
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
