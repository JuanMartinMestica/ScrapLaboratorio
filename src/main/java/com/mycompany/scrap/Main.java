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

public class Main {

    public static void main(String[] args) {

        //Executor Service
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        //Creación de array list para almacenar las tareas a realizar
        List<Tarea> tareasARealizar = cargaInicial();

        //Creación de lista para almacenar los resultados de tipo Future
        List<Future<Resultado>> listaResultados = null;

        for (int i = 0; i < 10; i++) {
            Tarea tarea = new Tarea("https://www.conversormonedas.com/valor-peso-argentino.php", "a", i, ("Tarea " + i));
            tareasARealizar.add(tarea);
        }

        try {
            //Se obtienen los resultados
            listaResultados = executor.invokeAll(tareasARealizar);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static List<Tarea> cargaInicial() {

        return null;

    }

}
