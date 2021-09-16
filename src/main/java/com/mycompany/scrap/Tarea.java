/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.scrap;

import java.util.concurrent.Callable;
import org.jsoup.nodes.Document;

public class Tarea implements Callable<Resultado> {

    private final String tagPrecio;
    private final int numeroTarea;
    private final String tagMoneda;
    private final Document html;

    //Se crea constructor y se conecta tarea a la url de la página
    public Tarea(int numeroTarea, String tagMoneda, String tagPrecio, Document html) {
        this.tagPrecio = tagPrecio;
        this.numeroTarea = numeroTarea;
        this.tagMoneda = tagMoneda;
        this.html = html;
    }

    @Override
    public Resultado call() throws Exception {

        System.out.println(Thread.currentThread().getName() + " Empezando tarea: " + numeroTarea);

        //Se obtienen los nombre y precios de las monedas correspondientes
        String moneda = html.select(tagMoneda).toString();
        String precio = html.select(tagPrecio).toString();

        moneda = moneda.substring(moneda.indexOf(">") + 1, moneda.indexOf("</"));
        precio = precio.substring(precio.indexOf(">") + 1, precio.indexOf("</"));

        return new Resultado(moneda, precio);
    }

}
