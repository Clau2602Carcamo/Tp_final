/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpfinal;

/**
 *
 * @author maygu
 */
public class Pronostico {
    private Integer idPronostico;
    private String equipo;
    private int partido;
    private char resultado;

    public Pronostico(Integer idPronostico, String equipo, int partido, char resultado) {
        this.idPronostico = idPronostico;
        this.equipo = equipo;
        this.partido = partido;
        this.resultado = resultado;
    }
    
     public Pronostico() {
        this.idPronostico = null;
        this.equipo = "";
        this.partido = 0;
        this.resultado = 0;   
    }

    Pronostico(int idPronostico, Equipo equipo, Partido partido, char resultado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Integer getIdPronostico() {
        return idPronostico;
    }

    public void setIdPronostico(Integer idPronostico) {
        this.idPronostico = idPronostico;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public int getPartido() {
        return partido;
    }

    public void setPartido(int partido) {
        this.partido = partido;
    }

    public char getResultado() {
        return resultado;
    }

    public void setResultado(char resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Pronostico{" + "idPronostico=" + idPronostico + ", equipo=" + equipo + ", partido=" + partido + ", resultado=" + resultado + '}';
    }
 
    
}
