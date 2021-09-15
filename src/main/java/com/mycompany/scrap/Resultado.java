/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.scrap;

public class Resultado {
    
    private String nombreMoneda;
    private String precioMoneda;

    public Resultado(String nombreMoneda, String precioMoneda) {
        this.nombreMoneda = nombreMoneda;
        this.precioMoneda = precioMoneda;
    }

    public void setNombreMoneda(String nombreMoneda) {
        this.nombreMoneda = nombreMoneda;
    }

    public void setPrecioMoneda(String precioMoneda) {
        this.precioMoneda = precioMoneda;
    }

    public String getNombreMoneda() {
        return nombreMoneda;
    }

    public String getPrecioMoneda() {
        return precioMoneda;
    }
    
  
}
