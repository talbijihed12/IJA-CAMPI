
package com.pidev.entities;

import java.sql.ResultSet;


public class participement_event {
    private int id_participement;
    private int id_user;
    private int id_Event;
    private int nbr_prticipement;
    private String nom_user;
    private String nom_Event;

    public participement_event() {
    }

   

   

 

    public int getId_participement() {
        return id_participement;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id_participement;
        hash = 67 * hash + this.id_user;
        hash = 67 * hash + this.id_Event;
        hash = 67 * hash + this.nbr_prticipement;
        
        return hash;
    }

    public participement_event(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final participement_event other = (participement_event) obj;
        if (this.id_participement != other.id_participement) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.id_Event != other.id_Event) {
            return false;
        }
        if (this.nbr_prticipement != other.nbr_prticipement) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "participement_event{" + "id_participement=" + id_participement + ", id_user=" + id_user + ", id_Event=" + id_Event + ", nbr_prticipement=" + nbr_prticipement + '}';
    }

    public participement_event(int id_participement,int id_Event,int id_user, int nbr_prticipement, String nom_user) {
        this.id_participement = id_participement;
        this.id_user = id_user;
        this.id_Event = id_Event;
        this.nbr_prticipement = nbr_prticipement;
        this.nom_user = nom_user;
    }
    public participement_event(int id_participement, int id_Event, int id_user, int nbr_prticipement) {
        this.id_participement = id_participement;
        this.id_user = id_user;
        this.id_Event = id_Event;
        this.nbr_prticipement = nbr_prticipement;
        
    }

    public participement_event( int id_user, int id_Event, int nbr_prticipement) {
        this.id_user = id_user;
        this.id_Event = id_Event;
        this.nbr_prticipement = nbr_prticipement;
    }
    
    

    public void setNbr_prticipement(int nbr_prticipement) {
        this.nbr_prticipement = nbr_prticipement;
    }

    public int getNbr_prticipement() {
        return nbr_prticipement;
    }

    public void setId_participement(int id_participement) {
        this.id_participement = id_participement;
    }

    public int getId_user() {
        return id_user;
    }
    public int getId_Event() {
        return id_Event;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }
    public String getNom_user() {
        return nom_user;
    }
    

    public void setId_Event(int id_Event) {
        this.id_Event = id_Event;
    }
     public void setNom_Event(String nom_Event) {
        this.nom_Event = nom_Event;
    }
      public String getNom_Event() {
        return nom_Event;
    }
    
}
