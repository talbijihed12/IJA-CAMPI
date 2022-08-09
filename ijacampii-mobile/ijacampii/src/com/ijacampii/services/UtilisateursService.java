package com.ijacampii.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.ijacampii.entities.Utilisateurs;
import com.ijacampii.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UtilisateursService {

    public static UtilisateursService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Utilisateurs> listUtilisateurss;


    private UtilisateursService() {
        cr = new ConnectionRequest();
    }

    public static UtilisateursService getInstance() {
        if (instance == null) {
            instance = new UtilisateursService();
        }
        return instance;
    }

    public ArrayList<Utilisateurs> getAll() {
        listUtilisateurss = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/utilisateurs");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listUtilisateurss = getList();
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

        return listUtilisateurss;
    }

    private ArrayList<Utilisateurs> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Utilisateurs utilisateurs = new Utilisateurs(
                        (int) Float.parseFloat(obj.get("id").toString()),

                        (String) obj.get("nom")

                );

                listUtilisateurss.add(utilisateurs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listUtilisateurss;
    }
}
