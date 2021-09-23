/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.scrap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Fernando
 */
public class Carga {

    public static List<Tarea> cargaInicial() throws Exception {

        //Declaración de variables y ruta del archivo de texto
        String path = "C:\\Users\\MARTIN\\Documents\\NetBeansProjects\\Scrap\\src\\main\\java\\com\\mycompany\\scrap\\Lectura\\cargaInicial.txt";
        int nroTarea;
        String tagNombre, tagPrecio, bfRead;

        String url = "https://www.conversormonedas.com/valor-peso-argentino.php";
        Connection conexion = Jsoup.connect(url);

        Document html = conexion.get();

        //Creación de lista de tareas callable
        List<Tarea> tareasARealizar = new ArrayList<>();

        try {
            //Lectura del archivo de texto
            BufferedReader bf = new BufferedReader(new FileReader(path));

            while ((bfRead = bf.readLine()) != null) {

                String[] atributos = bfRead.split(",");

                nroTarea = Integer.parseInt(atributos[0]);
                tagNombre = atributos[1];
                tagPrecio = atributos[2];

                //Creación de tarea nueva y se suma a la lista
                Tarea nuevaTarea = new Tarea(nroTarea, tagNombre, tagPrecio, html);

                tareasARealizar.add(nuevaTarea);

            }

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tareasARealizar;
    }
}
