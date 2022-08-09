package com.ijacampii.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.ijacampii.entities.Hebergement;
import com.ijacampii.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HebergementService {

    public static HebergementService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Hebergement> listHebergements;


    private HebergementService() {
        cr = new ConnectionRequest();
    }

    public static HebergementService getInstance() {
        if (instance == null) {
            instance = new HebergementService();
        }
        return instance;
    }

    public ArrayList<Hebergement> getAll() {
        listHebergements = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/hebergement");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listHebergements = getList();
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

        return listHebergements;
    }

    private ArrayList<Hebergement> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Hebergement hebergement = new Hebergement(
                        (int) Float.parseFloat(obj.get("id").toString()),

                        (String) obj.get("name")

                );

                listHebergements.add(hebergement);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listHebergements;
    }
}
