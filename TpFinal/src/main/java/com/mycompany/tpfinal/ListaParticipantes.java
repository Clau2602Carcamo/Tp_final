/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpfinal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ListaParticipantes {
    //atributo
    private List<Participante> participantes;
    private String participantesCSV;

    public ListaParticipantes(List<Participante> participantes, String participantesCSV) {
        this.participantes = participantes;
        this.participantesCSV = participantesCSV;
    }
    
   public ListaParticipantes() {
        this.participantes = new ArrayList<Participante>();
        this.participantesCSV = "participantes.csv";
    } 

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    public String getParticipantesCSV() {
        return participantesCSV;
    }

    public void setParticipantesCSV(String participantesCSV) {
        this.participantesCSV = participantesCSV;
    }
   
    //add y remove elementos
    public void addParticipante(Participante p){
        this.participantes.add(p);
    }
    public void removeParticipante (Participante p){
        this.participantes.remove(p);
    }
    
    public Participante getParticipante (int idParticipante){
        Participante encontrado = null;
        for (Participante eq : this.getParticipantes()){
            if (eq.getIdParticipante()== idParticipante){
                encontrado = eq;
                break;
            }
        }
        return encontrado;
    }

    @Override
    public String toString() {
        return "ListaParticipantes{" + "participantes=" + participantes + ", participantesCSV=" + participantesCSV + '}';
    }
    
    public String Listar(){
        String lista = "";
        for (Participante participante: participantes){
            lista += "\n" + participante;
        }
        return lista;
        }
    
    public void cargarDeArchivo(){
        String datosParticipante;
        String vectorParticipante [];
        Participante participante;
        int fila = 0;
        
        try {
            Scanner sc = new Scanner (new File(this.getParticipantesCSV()));
            sc.useDelimiter("\n");
            
            while (sc.hasNext()){
                //levanta los datos de cada linea
                datosParticipante = sc.next();
                //descomentar si se quiere mostrar cada linesa leida desde el archivo
                //System.out.println(datosParticimante);//muestra los datos levnatados
                fila ++;
                //si es la cabecera la descarta y no se considera para armar el listado
                if(fila ==1)
                    continue;
                
                //proceso auxiliar para convertir los string en vector 
                // guarda en un vector los elemnetos individuales
                vectorParticipante = datosParticipante.split(",");
                
                //graba el equipo en memoria
                //convertir un string a un entero;
                int idParticipante = Integer.parseInt(vectorParticipante[0]);
                String nombre = vectorParticipante[1];
                //crea el objeto en memoria
                participante = new Participante(idParticipante, nombre, null);
                
                //llama al metodo add para grabar el participante en la lista memoria
                this.addParticipante(participante);
                              
            }
        }catch (IOException ex){    
                System.out.println("mensaje:"+ ex.getMessage());
                
                }
        }
    public String listarOrdenadosPorPuntaje(){
        List<Participante> ordenados = this.getOrdenadosPorPuntaje();
        String lista = "";
        for (Participante participante : participantes){
            lista += "\n" + participante;
        }
        return lista;
    }  
    public List<Participante> getOrdenadosPorPuntaje(){
        List<Participante> ordenados = new ArrayList<Participante>();
        ordenados.addAll(participantes);
        
    Collections.sort (ordenados,Collections.reverseOrder());
    return ordenados;
   
}
}