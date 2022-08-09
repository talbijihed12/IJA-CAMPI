
package com.pidev.service;

import com.pidev.entities.Offre_Location;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.pidev.service.IServices;
import com.pidev.utils.DBConnection;

/**
 *
 * @author Omar Amri
 */
public class Offre_LocationService implements IServices<Offre_Location>{
    
    Connection cnx = DBConnection.getInstance().getCnx();

    @Override
    public void ajouter(Offre_Location entity) throws SQLException {

            String req="INSERT INTO reservation_moyen_transport(date_debut,date_fin,place) VALUES(?,?,?)";
            
            PreparedStatement pst = cnx.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
            
            pst.setDate(1, entity.getDate_debut());
            pst.setDate(2, entity.getDate_fin());
            pst.setString(3, entity.getPlace());
            
            pst.executeUpdate();
            
            ResultSet res=pst.getGeneratedKeys();
            while(res.next())
            {
               entity.id_reservation(res.getInt(1));
            }
            
            System.out.println("Rservée..");

    }

    @Override
    public void supprimer(Offre_Location e) throws SQLException {

            PreparedStatement preparedStmt = cnx.prepareStatement("DELETE FROM reservation_moyen_transport WHERE id_reservation= ?");
            
            System.out.println(e.id_reservation());
            preparedStmt.setInt(1, e.id_reservation());
            
            preparedStmt.executeUpdate();
            
            System.out.println("Reservation bien Supprimé");
    }
    

    @Override
    public void modifier(Offre_Location entity) throws SQLException {

    }

    @Override
    public ArrayList<Offre_Location> afficher() {
        
         ArrayList<Offre_Location> lesReservations = new ArrayList<>();
            
        String req="SELECT * FROM reservation_moyen_transport";
        
        try {
            
            Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
            
            while (rst.next()){
                
                Offre_Location offre = new Offre_Location();
                
                offre.id_reservation(rst.getInt("id_reservation"));
                offre.setDate_debut(rst.getDate("date_debut"));
                offre.setDate_fin(rst.getDate("date_fin"));
                offre.setPlace(rst.getString("place"));
                lesReservations.add(offre);
            }
            
            st.executeQuery(req);
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
        return lesReservations;
     
        
    }
    

   
    
}
