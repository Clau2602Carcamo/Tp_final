/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpfinal;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author maygu
 */
public class ListaPronosticos {
   
    private ArrayList<Pronostico> pronosticos;
    private String nombreArchivo;

    public ListaPronosticos(ArrayList<Pronostico> pronosticos, String nombreArchivo) {
        this.pronosticos = pronosticos;
        this.nombreArchivo = nombreArchivo;
    }
    public ListaPronosticos(){
        this.pronosticos = new ArrayList<>();
        this.nombreArchivo = "./src//archivos//pronosticos";
    }

    public ArrayList<Pronostico> getPronosticos() {
        return pronosticos;
    }

    public Pronostico getPronostico(int idPronostico) {
        Pronostico retorno = null;

        for (Pronostico pro : this.getPronosticos()) {
            if (pro.getIdPronostico() == idPronostico) {
                retorno = pro;
                break;
            }
        }
        return retorno;
    }

    public void setPronosticos(ArrayList<Pronostico> pronosticos) {
        this.pronosticos = pronosticos;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void addPronostico(Pronostico pronostico) {
        this.getPronosticos().add(pronostico);
    }

    public void removePronostico(Pronostico pronostico) {
        this.getPronosticos().remove(pronostico);
    }


    public void cargaDeArchivo(int idPart, ListaEquipos listaEquipos, ListaPartidos listaPartidos) {
        String datosPronostico;
        String vectorPronostico[];


        int fila = 0;
        try {
            Scanner scan = new Scanner(new File(this.getNombreArchivo()));
            scan.useDelimiter("\n");

            while (scan.hasNext()) {
                datosPronostico = scan.next();
                fila++;
                if (fila == 1)
                    continue;
                vectorPronostico = datosPronostico.split(",");

                int idPronostico = Integer.parseInt(vectorPronostico[0]);
                int idParticipante = Integer.parseInt(vectorPronostico[1]);
                int idPartido = Integer.parseInt(vectorPronostico[2]);
                int idEquipo = Integer.parseInt(vectorPronostico[3]);
                char resultado = vectorPronostico[1].charAt(0);

                if(idParticipante == idPart){
                    Partido partido = listaPartidos.getPartido(idPartido);
                    Equipo equipo = listaEquipos.getEquipo(idEquipo);
                    Pronostico pronostico = new Pronostico(idPronostico,equipo,partido,resultado);
                    this.addPronostico(pronostico);
                }

           }
        } catch (IOException ex) {
            System.out.println("Mensaje" + ex.getMessage());
        }
    }
    
    public void cargarDeBD(
          int idParticipante,//id del participante que realizo el pronostico
          ListaEquipos listaequipos,//lista de equipos
          ListaPartidos listapartidos//lista partidos
    )
    {
        Connection conn = null;
        try {
            //establecer una conexion
            conn = DriverManager.getConnection("jdbc:sqlite:pronosticos.db");
            //crear el "statement" para enviar comandos
            Statement stmt = conn.createStatement();
            String sql = "SELECT"
                    + "idPronostico, idParticipante,idPartido,idEquipo,resultado"
                    + "FROM pronosticos"
                    + "WHERE idParticipante = " + idParticipante;
            ResultSet rs = stmt.executeQuery(sql); //ejecutar la consulta y obtener el resultado
            while (rs.next()){
            //Obtener los objetos que necesito para el constructor
            Partido partido = listapartidos.getPartido(rs.get.Int("idPartido"));
            Equipo equipo = listaequipos.getEquipo (rs.getInt("idEquipo"));
            //crea el objeto en memoria
            Pronostico pronostico = new Pronostico(
            rs.getInt("idPronostico"),
            equipo,
            partido,
            rs.getString ("resultado").charAt(0),
            idParticipante
                    );
            this.addPronostico(pronostico);
        }
            
    } catch (SQLException e) {
        System.out.println(e.getMessage());
      
    } finally {
            try {
                if (conn != null){
                conn.close();
            }
            }catch (SQLException e ){
                System.out.println(e.getMessage());
            }
        }
    }

    void cargarDeDB(Integer idParticipante, ListaEquipos equipos, ListaPartidos partidos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

    
