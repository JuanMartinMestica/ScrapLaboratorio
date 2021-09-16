/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.scrap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {

    public static void main(String[] args) throws Exception {

        //Executor Service
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);

        //Creación de array list para almacenar las tareas a realizar
        List<Tarea> tareasARealizar = cargaInicial();

        //Creación de lista para almacenar los resultados de tipo Future
        List<Future<Resultado>> listaResultados = null;

        try {
            //Se obtienen los resultados
            listaResultados = executor.invokeAll(tareasARealizar);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        executor.shutdown();

        System.out.println("\n======Un peso equivale a ========= || =======Moneda=======||");
    

        for (int i = 0; i < listaResultados.size(); i++) {
            Future<Resultado> future = listaResultados.get(i);
            try {
                Resultado result = future.get();
                System.out.print(result.getPrecioMoneda());
                System.out.println("                                    " + result.getNombreMoneda());
            } catch (InterruptedException | ExecutionException e) {
            }
        }
    }

    private static List<Tarea> cargaInicial() throws Exception {

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
