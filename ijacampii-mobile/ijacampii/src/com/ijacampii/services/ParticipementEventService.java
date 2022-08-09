package com.ijacampii.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.ijacampii.entities.Evenement;
import com.ijacampii.entities.ParticipementEvent;
import com.ijacampii.entities.Utilisateurs;
import com.ijacampii.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParticipementEventService {

    public static ParticipementEventService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<ParticipementEvent> listParticipementEvents;


    private ParticipementEventService() {
        cr = new ConnectionRequest();
    }

    public static ParticipementEventService getInstance() {
        if (instance == null) {
            instance = new ParticipementEventService();
        }
        return instance;
    }

    public ArrayList<ParticipementEvent> getAll() {
        listParticipementEvents = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/participementEvent");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listParticipementEvents = getList();
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

        return listParticipementEvents;
    }

    private ArrayList<ParticipementEvent> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                ParticipementEvent participementEvent = new ParticipementEvent(
                        (int) Float.parseFloat(obj.get("id").toString()),

                        makeUtilisateurs((Map<String, Object>) obj.get("utilisateurs")),
                        (int) Float.parseFloat(obj.get("nbrParticipement").toString()),
                        makeEvenement((Map<String, Object>) obj.get("evenement"))

                );

                listParticipementEvents.add(participementEvent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listParticipementEvents;
    }

    public Utilisateurs makeUtilisateurs(Map<String, Object> obj) {
        if (obj == null) {
            return null;
        }
        Utilisateurs utilisateurs = new Utilisateurs();
        utilisateurs.setId((int) Float.parseFloat(obj.get("id").toString()));
        utilisateurs.setNom((String) obj.get("nom"));
        return utilisateurs;
    }

    public Evenement makeEvenement(Map<String, Object> obj) {
        if (obj == null) {
            return null;
        }
        Evenement evenement = new Evenement();
        evenement.setId((int) Float.parseFloat(obj.get("id").toString()));
        evenement.setNomEvent((String) obj.get("nomEvent"));
        return evenement;
    }

    public int add(ParticipementEvent participementEvent) {
        return manage(participementEvent, false);
    }

    public int edit(ParticipementEvent participementEvent) {
        return manage(participementEvent, true);
    }

    public int manage(ParticipementEvent participementEvent, boolean isEdit) {

        cr = new ConnectionRequest();


        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "/participementEvent/edit");
            cr.addArgument("id", String.valueOf(participementEvent.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "/participementEvent/add");
        }

        cr.addArgument("utilisateurs", String.valueOf(participementEvent.getUtilisateurs().getId()));
        cr.addArgument("nbrParticipement", String.valueOf(participementEvent.getNbrParticipement()));
        cr.addArgument("evenement", String.valueOf(participementEvent.getEvenement().getId()));


        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = cr.getResponseCode();
                cr.removeResponseListener(this);
            }
        });
        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception ignored) {

        }
        return resultCode;
    }

    public int delete(int participementEventId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/participementEvent/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(participementEventId));

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cr.getResponseCode();
    }
}
