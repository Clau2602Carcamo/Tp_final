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
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author maygu
 */
public class ListaPartidos {
    private List<Partido> partidos;
    private String partidosCSV;

    public ListaPartidos(List<Partido> partidos, String partidosCSV) {
        this.partidos = partidos;
        this.partidosCSV = partidosCSV;
    }
    
    public ListaPartidos() {
        this.partidos = new ArrayList<Partido>();
        this.partidosCSV = "partidos.csv";
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public String getPartidosCSV() {
        return partidosCSV;
    }

    public void setPartidosCSV(String partidosCSV) {
        this.partidosCSV = partidosCSV;
    }
    
     public void addPartido(Partido p) {
        this.partidos.add(p);
    }
    public void removePartido(Partido p) {
        this.partidos.remove(p);
    }
    
     public Partido getPartido (Integer idPartido) {
       // Defini un objeto de tipo Partido en dónde va a ir mi resultado
        // Inicialmente es null, ya que no he encontrado el partido que 
        // buscaba todavía.
        Partido encontrado = null;
        // Recorro la lista de equipos que está cargada
        for (Partido eq : this.getPartidos()) {
            // Para cada equipo obtengo el valor del ID y lo comparo con el que
            // estoy buscando
            if (eq.getIdPartido() == idPartido) {
                // Si lo encuentro (son iguales) lo asigno como valor de encontrado
                encontrado = eq;
                // Y hago un break para salir del ciclo ya que no hace falta seguir buscando
                break;
            }
        }
          // Una vez fuera del ciclo retorno el equipo, pueden pasar dos cosas:
        // 1- Lo encontré en el ciclo, entonces encontrado tiene el objeto encontrado
        // 2- No lo encontré en el ciclo, entonces conserva el valor null del principio
        return encontrado;
        }
     

    @Override
    public String toString() {
        return "ListaPartidos{" + "partidos=" + partidos + ", partidosCSV=" + partidosCSV + '}';
    }
        
  
    public String listar() {
        String lista = "";
        for (Partido partido: partidos) {
            lista += "\n" + partido;
        }           
        return lista;
    }
    
    // cargar desde el archivo
    public void cargarDeArchivo() {
        // para las lineas del archivo csv
        String datosPartido;
        // para los datos individuales de cada linea
        String vectorPartido[];
        // para el objeto en memoria
        Partido partido;
        int fila = 0;
       
        try { 
            Scanner sc = new Scanner(new File(this.getPartidosCSV()));
            sc.useDelimiter("\n");   //setea el separador de los datos
                
            while (sc.hasNext()) {
                // levanta los datos de cada linea
                datosPartido = sc.next();
                // Descomentar si se quiere mostrar cada línea leída desde el archivo
                // System.out.println(datosEquipo);  //muestra los datos levantados 
                fila ++;
                // si es la cabecera la descarto y no se considera para armar el listado
                if (fila == 1)
                    continue;              
                 
                //Proceso auxiliar para convertir los string en vector
                // guarda en un vector los elementos individuales
                vectorPartido = datosPartido.split(",");   
                
                // graba el equipo en memoria
                //convertir un string a un entero;
                int idPartido = Integer.parseInt(vectorPartido[0]);
                String nombre = vectorPartido[1];
                String descripcion = vectorPartido[2];
                // crea el objeto en memoria
                partido = new Partido(idPartido, nombre, descripcion);
                
                // llama al metodo add para grabar el equipo en la lista en memoria
                this.addPartido(partido);
            }
            //closes the scanner
        } catch (IOException ex) {
                System.out.println("Mensaje: " + ex.getMessage());
        }       

    }

public void cargarDeBD(
        ListaEquipos listaequipos)
          
    {
        Connection conn = null;
        try {
            //establecer una conexion
            conn = DriverManager.getConnection("jdbc:sqlite:pronosticos.db");
            //crear el "statement" para enviar comandos
            Statement stmt = conn.createStatement();
            String sql = "SELECT"
                    + "idPartido,idEquipo1,idEquipo2,golesEquipo1,golesEquipo2"
                    + "FROM partidos";
                   
            ResultSet rs = stmt.executeQuery(sql); //ejecutar la consulta y obtener el resultado
            while (rs.next()){
            //Obtener los objetos que necesito para el constructor
            Equipo equipo1 = listaequipos.getEquipo(rs.getInt("idEquipo1"));
            Equipo equipo2 = listaequipos.getEquipo (rs.getInt("idEquipo2"));
            //crea el objeto en memoria
            Partido partido = new Partido(rs.getInt("idPartido"),equipo1,equipo2,
            rs.getInt("golesEquipo1"),rs.getInt("golesEquipo2"));
                                
            this.addPartido(partido);
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
}

     

