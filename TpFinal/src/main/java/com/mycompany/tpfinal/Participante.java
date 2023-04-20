/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpfinal;

/**
 *
 * @author maygu
 */
public class Participante {
    private Integer idParticipante;
    private String nombre;
    private ListaPronosticos pronosticos;

    public Participante(Integer idParticipante, String nombre, ListaPronosticos pronosticos) {
        this.idParticipante = idParticipante;
        this.nombre = nombre;
        this.pronosticos = pronosticos;
    }

    public Participante(Integer idParticipante, String nombre) {
        this.idParticipante = idParticipante;
        this.nombre = nombre;
        this.pronosticos = new ListaPronosticos();
    } 

     public Participante() {
        this.idParticipante = null;
        this.nombre = null;
        this.pronosticos = new ListaPronosticos();
    } 

    public Integer getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(Integer idParticipante) {
        this.idParticipante = idParticipante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaPronosticos getPronosticos() {
        return pronosticos;
    }

    public void setPronosticos(ListaPronosticos pronosticos) {
        this.pronosticos = pronosticos;
    }
    
    public int getPuntaje(){
        int puntaje =0;
        for (Pronostico p : this.getPronosticos().getPronosticos()){
            puntaje += p.getPuntaje();
        }
        return puntaje;
    }
    @Override
    public String toString() {
        return "Participante{" + "idParticipante=" + idParticipante + ", nombre=" + nombre + ", pronosticos=" + pronosticos + '}';
    }
    
    public int compareTo (Participante o){
        int miPuntaje = this.getPuntaje();
        int otroPuntaje = o.getPuntaje();
        
        if (miPuntaje == otroPuntaje)
            return 0;
        else if (miPuntaje > otroPuntaje)
                return 1;
              else 
                return -1;
    }
    void cargarPronosticos (ListaEquipos equipos, ListaPartidos partidos){
        this.pronosticos.cargarDeDB(this.getIdParticipante(), equipos, partidos);
    }        
    }
