
package cl.jRenderLove.model;

import cl.jRenderLove.bd.Conexion;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import java.util.Random;
import javax.swing.ImageIcon;
import cl.jRenderLove.miniGamesClass.*;


public class Data {
    private Conexion connect;
    private ResultSet rs;
    private String query;
    private List<Chica> chicas;
    private List<Passion> pasiones;
    private List<Diversion> diversiones;
    private List<Atraccion> atracciones;
    private Pregunta nuevaPregunta;
    private Image sprite;
    private AbieGame abieGames;
    

    
    public Data() throws SQLException {
        connect=new Conexion("localhost","bd_renderPop","root","");
    
    }
    
    public Chica getChica(int idChica) throws SQLException{
        query="select * from chica where id='"+idChica+"'";
        Chica chica=null;
        chicas= new ArrayList<>();
        rs=connect.ejecutarSelect(query);
        
        if(rs.next()){
            chica= new Chica();
            chica.setId(rs.getInt(1));
            chica.setNombre(rs.getString(2));
            chica.setEdad(rs.getInt(3));
            chica.setOcupacion(rs.getString(4));
            
            chicas.add(chica);
        
        }
        connect.desconectar();
        
       return chica; 
    }
    
    public Passion getPasion( int idChica) throws SQLException{
        query="select * from medidorPassion where idChica='"+idChica+"'";
        Passion p=null;
        pasiones =new ArrayList<>();
        rs=connect.ejecutarSelect(query);
        
        if(rs.next()){
            p= new Passion();
            p.setId(rs.getInt(1));
            p.setPassion(rs.getInt(2));
            p.setIdChica(rs.getInt(3));
            pasiones.add(p);
        }
        
        connect.desconectar();
        return p;
    }
    
    public Atraccion getAtracion(int idChica) throws SQLException{
        query="select * from medidorAtraccion where idChica='"+idChica+"'";
        Atraccion a=null;
        atracciones= new ArrayList<>();
        rs=connect.ejecutarSelect(query);
        if(rs.next()){
            a= new Atraccion();
            a.setId(rs.getInt(1));
            a.setAtraccion(rs.getInt(2));
            a.setIdChica(rs.getInt(3));
            
            atracciones.add(a);
        }
        connect.desconectar();
        return a;
    }
    
    public Diversion getDiversion(int idChica) throws SQLException{
        query="select * from medidorDiversion where idChica='"+idChica+"'";
        Diversion d=null;
        diversiones= new ArrayList<>();
        rs=connect.ejecutarSelect(query);
        if(rs.next()){
            d= new Diversion();
            d.setId(rs.getInt(1));
            d.setDiversion(rs.getInt(2));
            d.setIdChica(rs.getInt(3));
            diversiones.add(d);
            
        }
        connect.desconectar();
        return d;
    }
    
    public void updatePasion(Passion p) throws SQLException{
        query="update medidorPassion set passion= '"+p.getPassion()+"'"
                + " where idChica='"+p.getIdChica()+"'";
        
        connect.ejecutar(query);
    }
    
    public void updateAtracion(Atraccion a) throws SQLException{
        query="update medidorAtraccion set atraccion= '"+a.getAtraccion()+"'"
                + " where idChica='"+a.getIdChica()+"'";
        
        connect.ejecutar(query);
    
    
    }
    
    public void updateDiversion(Diversion d) throws SQLException{
      query="update medidorDiversion set diversion= '"+d.getDiversion()+"'"
                + " where idChica='"+d.getIdChica()+"'";
        
        connect.ejecutar(query);
    }
    
    public Pregunta takePregunta(int idChica){
        
        nuevaPregunta=null;
         Random r= new Random();
         int pregunta=(int)(r.nextDouble() * 6+1);
        if(idChica==1){
            
            if(pregunta==1){
            nuevaPregunta= new Pregunta();
            nuevaPregunta.setIdChica(idChica);
            nuevaPregunta.setPregunta(""
                    + "Te consideras alguien atrevido?");
            
            nuevaPregunta.setIdPregunta(1);
            
            nuevaPregunta.setRespuesta("la verdad, si");
            nuevaPregunta.setRespuesta2("No, que es eso");
            nuevaPregunta.setRespuesta3("dejame ver debajo de tu falda?");
            
            }else if(pregunta==2){
                nuevaPregunta = new Pregunta();
                nuevaPregunta.setIdChica(idChica);
                nuevaPregunta.setPregunta(""
                        + "Eres como,Byron y Jose,pelados?");
                nuevaPregunta.setIdPregunta(2);

                nuevaPregunta.setRespuesta("Ellos si,yo no");
                nuevaPregunta.setRespuesta2("Khe?");
                nuevaPregunta.setRespuesta3("Ire a estudiar Java");
            
            
            }else if(pregunta==3){
                nuevaPregunta = new Pregunta();
                nuevaPregunta.setIdChica(idChica);
                nuevaPregunta.setPregunta(""
                        + "Prefieres el helado de?");

                nuevaPregunta.setRespuesta("Chocolate");
                nuevaPregunta.setRespuesta2("Vainilla");
                nuevaPregunta.setRespuesta3("Pasas al ron");
                
                nuevaPregunta.setIdPregunta(3);
            
            
            }else if(pregunta==4){
                nuevaPregunta = new Pregunta();
                nuevaPregunta.setIdChica(idChica);
                nuevaPregunta.setPregunta(""
                        + "Que te gusta hacer?");
                
                nuevaPregunta.setIdPregunta(4);

                nuevaPregunta.setRespuesta("Leer comic");
                nuevaPregunta.setRespuesta2("Dibujar");
                nuevaPregunta.setRespuesta3("Jugar videojuegos");
            
            
            }else if(pregunta==5){
                nuevaPregunta = new Pregunta();
                nuevaPregunta.setIdChica(idChica);
                nuevaPregunta.setPregunta(""
                        + "Cual es mi edad?");
                
                nuevaPregunta.setIdPregunta(5);

                nuevaPregunta.setRespuesta("18");
                nuevaPregunta.setRespuesta2("19");
                nuevaPregunta.setRespuesta3("17");
            
            
            }else if(pregunta==6){
                nuevaPregunta = new Pregunta();
                nuevaPregunta.setIdChica(idChica);
                nuevaPregunta.setPregunta(""
                        + "Que crees que me gusta hacer");
                
                nuevaPregunta.setIdPregunta(6);

                nuevaPregunta.setRespuesta("Estudiar");
                nuevaPregunta.setRespuesta2("Ir de compras");
                nuevaPregunta.setRespuesta3("Sexo?");
            
            
            }
            
            
        
        }
            
        
   
        return nuevaPregunta;
    }
    
    public AbieGame darPalabra(){
         abieGames=null;
         Random r= new Random();
         int palabra=(int)(r.nextDouble() * 5+1);
         
         if(palabra==1){
             abieGames= new AbieGame();
             abieGames.setIdPalabra(1);
             abieGames.setPalabra("Maniqui");
         }else if(palabra==2){
             abieGames= new AbieGame();
             abieGames.setIdPalabra(2);
             abieGames.setPalabra("Perfume");
         
         }else if(palabra==3){
             abieGames= new AbieGame();
             abieGames.setIdPalabra(3);
             abieGames.setPalabra("Universidad");
         
         }else if(palabra==4){
             abieGames= new AbieGame();
             abieGames.setIdPalabra(4);
             abieGames.setPalabra("Laptop");
         
         }else if(palabra==5){
             abieGames= new AbieGame();
             abieGames.setIdPalabra(5);
             abieGames.setPalabra("Estudiar");
         
         }
         
         
         
         
         
        return  abieGames;
    }
    
    
    
}
    
