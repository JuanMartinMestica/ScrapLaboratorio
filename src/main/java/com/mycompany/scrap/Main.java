/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.scrap;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws Exception {
        Carga cargador = new Carga();

        //Executor Service
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);

        //Creación de array list para almacenar las tareas a realizar
        List<Tarea> tareasARealizar = cargador.cargaInicial();

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

}
