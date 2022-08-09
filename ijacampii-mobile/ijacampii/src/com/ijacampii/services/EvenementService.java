package com.ijacampii.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.ijacampii.entities.*;
import com.ijacampii.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EvenementService {

    public static EvenementService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Evenement> listEvenements;


    private EvenementService() {
        cr = new ConnectionRequest();
    }

    public static EvenementService getInstance() {
        if (instance == null) {
            instance = new EvenementService();
        }
        return instance;
    }

    public ArrayList<Evenement> getAll() {
        listEvenements = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/evenement");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listEvenements = getList();
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

        return listEvenements;
    }

    private ArrayList<Evenement> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Evenement evenement = new Evenement(
                        (int) Float.parseFloat(obj.get("id").toString()),

                        makeHebergement((Map<String, Object>) obj.get("hebergement")),
                        makeUtilisateurs((Map<String, Object>) obj.get("utilisateurs")),
                        makeEquipement((Map<String, Object>) obj.get("equipement")),
                        makeMoyenTransport((Map<String, Object>) obj.get("moyenTransport")),
                        (String) obj.get("nomEvent"),
                        (String) obj.get("description"),
                        (String) obj.get("dateDebut"),
                        (String) obj.get("dateFin"),
                        (int) Float.parseFloat(obj.get("nbrReservation").toString()),
                        (int) Float.parseFloat(obj.get("prix").toString()),
                        (String) obj.get("activite")

                );

                listEvenements.add(evenement);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listEvenements;
    }

    public Hebergement makeHebergement(Map<String, Object> obj) {
        if (obj == null) {
            return null;
        }
        Hebergement hebergement = new Hebergement();
        hebergement.setId((int) Float.parseFloat(obj.get("id").toString()));
        hebergement.setName((String) obj.get("name"));
        return hebergement;
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

    public Equipement makeEquipement(Map<String, Object> obj) {
        if (obj == null) {
            return null;
        }
        Equipement equipement = new Equipement();
        equipement.setId((int) Float.parseFloat(obj.get("id").toString()));
        equipement.setNom((String) obj.get("nom"));
        return equipement;
    }

    public MoyenTransport makeMoyenTransport(Map<String, Object> obj) {
        if (obj == null) {
            return null;
        }
        MoyenTransport moyenTransport = new MoyenTransport();
        moyenTransport.setId((int) Float.parseFloat(obj.get("id").toString()));
        moyenTransport.setType((String) obj.get("type"));
        return moyenTransport;
    }

    public int add(Evenement evenement) {
        return manage(evenement, false);
    }

    public int edit(Evenement evenement) {
        return manage(evenement, true);
    }

    public int manage(Evenement evenement, boolean isEdit) {

        cr = new ConnectionRequest();


        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "/evenement/edit");
            cr.addArgument("id", String.valueOf(evenement.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "/evenement/add");
        }

        cr.addArgument("hebergement", String.valueOf(evenement.getHebergement().getId()));
        cr.addArgument("utilisateurs", String.valueOf(evenement.getUtilisateurs().getId()));
        cr.addArgument("equipement", String.valueOf(evenement.getEquipement().getId()));
        cr.addArgument("moyenTransport", String.valueOf(evenement.getMoyenTransport().getId()));
        cr.addArgument("nomEvent", evenement.getNomEvent());
        cr.addArgument("description", evenement.getDescription());
        cr.addArgument("dateDebut", evenement.getDateDebut());
        cr.addArgument("dateFin", evenement.getDateFin());
        cr.addArgument("nbrReservation", String.valueOf(evenement.getNbrReservation()));
        cr.addArgument("prix", String.valueOf(evenement.getPrix()));
        cr.addArgument("activite", evenement.getActivite());


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

    public int delete(int evenementId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/evenement/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(evenementId));

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
