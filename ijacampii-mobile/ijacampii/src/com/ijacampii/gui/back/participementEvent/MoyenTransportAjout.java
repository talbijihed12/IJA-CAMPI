/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijacampii.gui.back.participementEvent;

import com.codename1.ui.Form;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.ijacampii.entities.MoyenTransport;
import com.ijacampii.services.MoyenTransportService;

import java.io.IOException;
import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author user
 */
public class MoyenTransportAjout extends Form {
     Form current;
      
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
      
      
      
      public MoyenTransportAjout(Form previous) {
           current = this; 
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Ajouter Moyen Transport");
        setLayout(BoxLayout.y());
        
        
        TextField tfType = new TextField("","Type");
        TextField tfMatricule = new TextField("","Matricule");
        TextField tfMarque = new TextField("","Marque");
        TextArea tfNbrPlace = new TextField("","Nombre de Places");
        TextField tfFrais = new TextField("","Frais");
        
        Button btnValider = new Button("Ajouter Transport");
        

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
                if ((tfType.getText().length()==0)||(tfMarque.getText().length()==0)||(tfMatricule.getText().length()==0)||(tfFrais.getText().length()==0)||(tfNbrPlace.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    
                    try {
            MoyenTransport moy = new MoyenTransport(tfType.getText(),tfMatricule.getText(),tfMarque.getText(),parseInt(tfNbrPlace.getText()),parseDouble(tfFrais.getText()));

                        if( MoyenTransportService.getInstance().ajouterTransport(moy))
                            Dialog.show("Success","Moyen Transport Ajouté",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                       // new EquipementList(previous).show();
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Frais et nombre de Places doivent être des nombres!", new Command("OK"));
                    }
                    
                }   
            }
        });
        
        addAll(tfType,tfMatricule,tfMarque,tfFrais,tfNbrPlace,btnValider);
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
}
