/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.scrap;

import java.util.List;
import java.util.concurrent.Callable;

public class Tarea implements Callable<Resultado> {

    private final String url;
    private final String tag;
    private List<String> titulos;

    public Tarea(String url, String tag) {
        this.url = url;
        this.tag = tag;
    }

    @Override
    public Resultado call() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
       

    
    

}
