/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.scrap;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Tarea implements Callable<Resultado> {

    private final String url = "https://www.conversormonedas.com/valor-peso-argentino.php";
    private final Connection conexion = Jsoup.connect(url);
    private final Document html = conexion.get();
    private final String tagPrecio;
    private List<String> titulos;
    private final int numeroTarea;
    private final String tagMoneda;


    //Se crea constructor y se conecta tarea a la url de la página
    public Tarea(int numeroTarea, String tagMoneda, String tagPrecio) throws IOException {
        this.tagPrecio = tagPrecio;
        this.numeroTarea = numeroTarea;
        this.tagMoneda = tagMoneda;
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
