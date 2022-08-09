/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijacampii.gui.back.participementEvent;

/**
 *
 * @author user
 */

//import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.ijacampii.entities.Equipement;
import com.ijacampii.services.ServiceEquipement;

import java.io.IOException;
import java.io.OutputStream;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.net.URI;
import java.net.URISyntaxException;


/**
 *
 * @author brahim
 */
public class EquipementAjout extends Form{
    
//     FileChooser FileChooser;
     ;
   

   
    /*
                public void Filechooser(TextField tfImage){
                    
                    if (FileChooser.isAvailable()) {
                    FileChooser.showOpenDialog(".png,  .jpg", e2-> {
                        String file = null;
                        try {
                            file = (String)e2.getSource();
                    } catch (Exception e) {
                        Dialog.show("ERROR", "Veuillez choisir une image valide", new Command("OK"));
                    }
                    if (file == null) {
                    tfImage.setText("");
                    revalidate();
                    } else {
                    String extension = null;
                    if (file.lastIndexOf(".") > 0) {
                    extension = file.substring(file.lastIndexOf(".")+1);
                    }
                    file=file.substring(file.lastIndexOf("/")+1);
                    tfImage.setText(file);
                    }
                    revalidate();
                    });
                    }
                    }
    */
    protected String saveFileToDevice(String hi, String ext) throws IOException {
        URI uri;
        try {
            uri = new URI(hi);
            String path = uri.getPath();
            int index = hi.lastIndexOf("/");
            hi = hi.substring(index + 1);
            return hi;
        } catch (URISyntaxException ex) {
        }
        return "hh";
    }
                
    
    public EquipementAjout(Form previous) {
          
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Ajouter equipement");
        setLayout(BoxLayout.y());
        
        
        TextField tfNom = new TextField("","nom");
        TextField tfMarque = new TextField("","Marque");
        TextField tfCategorie = new TextField("","categorie");
        TextArea tfDescription = new TextField("","Description");
        TextField tfPrix = new TextField("","Prix");
        TextField tfImage = new TextField("","image");
        Button btnValider = new Button("Ajouter Equipement");
        

    /*    tfImage.addPointerPressedListener(l-> {
            if (FileChooser.isAvailable()) {
                FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(".jpg, .jpeg, .png/plain", (ActionEvent e2) -> {
                    if (e2 == null || e2.getSource() == null) {
                        add("No file was selected");
                        revalidate();
                        return;
                    }

                    String file = (String) e2.getSource();
                    if (file == null) {
                        add("No file was selected");
                        revalidate();
                    } else {
                        Image logo;
                        try {
                            logo = Image.createImage(file).scaledHeight(500);;
                            add(logo);
                            String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "photo.png";

                            try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                                System.out.println(imageFile);
                                ImageIO.getImageIO().save(logo, os, ImageIO.FORMAT_PNG, 1);
                            } catch (IOException err) {
                            }
                        } catch (IOException ex) {
                        }
                        String extension = null;
                        if (file.lastIndexOf(".") > 0) {
                            extension = file.substring(file.lastIndexOf(".") + 1);
                            StringBuilder hi = new StringBuilder(file);
                            if (file.startsWith("file://")) {
                                hi.delete(0, 7);
                            }
                            int lastIndexPeriod = hi.toString().lastIndexOf(".");
                            Log.p(hi.toString());
                            String ext = hi.toString().substring(lastIndexPeriod);
                            String hmore = hi.toString().substring(0, lastIndexPeriod - 1);
                            try {
                                String namePic = saveFileToDevice(file, ext);
                                tfImage.setText(namePic);
                                System.out.println(namePic);
                            } catch (IOException ex) {
                            }
                            revalidate();
                    }
                    }
                        });
            }
        });*/
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfMarque.getText().length()==0)||(tfDescription.getText().length()==0)||(tfPrix.getText().length()==0)||(tfCategorie.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    
                    try {
            Equipement h = new Equipement(tfNom.getText(),tfMarque.getText(),parseFloat(tfPrix.getText()),tfDescription.getText(),tfCategorie.getText(),tfImage.getText());

                        if( ServiceEquipement.getInstance().addEquipement(h))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                       // new EquipementList(previous).show();
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "prix et nombre de lits doivent être des nombres!", new Command("OK"));
                    }
                    
                }   
            }
        });
        
        addAll(tfNom,tfCategorie,tfDescription,tfPrix,tfImage,tfMarque,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
}