/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.scrap;

import java.util.List;
import java.util.concurrent.Callable;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class Tarea implements Callable<Resultado> {

    private final String url = "https://www.conversormonedas.com/valor-peso-argentino.php";
    private final String tag;
    private List<String> titulos;
    private final int nroTarea;
    private final String nombreTarea;
    private final Connection conexion;

    public Tarea(int numeroTarea, String tagPrecio, String tagTarea) {
        this.tag = tagPrecio;
        this.nroTarea = numeroTarea;
        this.nombreTarea = tagTarea;
        this.conexion = Jsoup.connect(url);
    }

    @Override
    public Resultado call() throws Exception {
   
        System.out.println(Thread.currentThread().getName() + " Empezando tarea: ");
        
        
        
        
        
        return null;
    }
    
       

    
    

}
