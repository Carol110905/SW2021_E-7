package com.ProyectoAccesibilidad.Principal.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MateriaDAO {
    private Conexion conexion = new Conexion();

    public String insertarMateria(Materia m) {
        Connection conn = null;
        PreparedStatement prestm = null;
        String msj = "";

        conn = conexion.getConnection();
        try {
            String sql = "INSERT INTO Materia (IdMateria, Nombre, IdProfesor) VALUES (?, ?, ?)";
            prestm = conn.prepareStatement(sql);
            prestm.setInt(1, m.getIdMateria());
            prestm.setString(2, m.getNombre());
            prestm.setInt(3, m.getIdProfesor());
            if (prestm.executeUpdate() >0) 
                msj = "Materia Agregada";
            else
                msj = "Materia No Agregada";
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (prestm != null){
                try {
                    prestm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return msj;
    }

    public String examenCreado(String nombre){
        Connection conn = null;
        PreparedStatement prestm = null;
        String msj = "";

        conn = conexion.getConnection();
        try {
            String sql = "UPDATE materia SET examen=1 WHERE Nombre=?";
            prestm = conn.prepareStatement(sql);
            prestm.setString(1, nombre);
            if (prestm.executeUpdate() >0) 
                msj = "Examen creado";
            else
                msj = "Examen no creado";
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msj;
    }
    public String reiniciarExamen(){
        Connection conn = null;
        PreparedStatement prestm = null;
        String msj = "";

        conn = conexion.getConnection();
        try {
            String sql = "UPDATE materia SET examen=0";
            prestm = conn.prepareStatement(sql);
            if (prestm.executeUpdate() >0) 
                msj = "Examenes Reiniciados";
            else
                msj = "Examenes Reiniciados";
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msj;
    }
    public ArrayList<Integer> BuscarIDAlumnoMateria(int idMateria){
        int IdMateria = idMateria;
        ArrayList <Integer> ids = new ArrayList<Integer>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        conn = conexion.getConnection();
        try{
            String sql = "SELECT IdAlumno FROM cursa WHERE IdMateria = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, IdMateria);
            rs = stm.executeQuery();
            while (rs.next()){
                ids.add(rs.getInt("IdAlumno"));
            }
        }catch(Exception e){

        }
        return ids;
    }
    public int BuscarIdMateria(String NombreMateria){
        String nombreMateria = NombreMateria;
        int idMateria=0;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        conn = conexion.getConnection();
        try{
            String sql = "SELECT IdMateria FROM materia WHERE Nombre = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, nombreMateria);
            rs = stm.executeQuery();
            if(rs.next() != false){
                idMateria = rs.getInt("IdMateria");
            }
        }catch(Exception e){

        }
        return idMateria;
    }    
    public List<Materia> BuscarMateriaProfesor(int IdProfesor){
        List <Materia> materias = new ArrayList<Materia>();
        int id = IdProfesor;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        conn = conexion.getConnection();
        try{
            String sql = "SELECT * FROM materia WHERE IdProfesor = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            
            while (rs.next()){

                Materia m = new Materia(rs.getInt("IdMateria"), rs.getString("Nombre"),  rs.getInt("IdProfesor"), rs.getBoolean("Examen") );
                materias.add(m);
            }
            return materias;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Materia> BuscarMateriaAlumno(int IdAlumno){
        List <Materia> materias = new ArrayList<Materia>();
        int id = IdAlumno;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        conn = conexion.getConnection();
        try{
            String sql = "SELECT IdMateria FROM cursa WHERE IdAlumno = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();

            while (rs.next()){

                int IdMateria = rs.getInt("IdMateria");

                PreparedStatement stm2 = null;
                ResultSet rs2 = null;

                String sql2 = "SELECT * FROM materia WHERE IdMateria = ? and examen = true";
                stm2 = conn.prepareStatement(sql2);
                stm2.setInt(1, IdMateria);
                rs2 = stm2.executeQuery();
                if(rs2.next()){
                    Materia m = new Materia(rs2.getInt("idMateria"), rs2.getString("Nombre"),  rs2.getInt("IdProfesor"), rs2.getBoolean("Examen"));
                    materias.add(m);            
                }
                
            }
            return materias;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}



    /*public void BorrarUsuarios(String ID){
        PreparedStatement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        conn = conexion.getConnection();

        try{
            String sql = "DELETE FROM usuario WHERE email=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, ID);
            stm.executeUpdate();
            stm.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ModificarCompleto(String NombreOld, String NombreNew, String Pass, String Pass2){
        PreparedStatement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        conn = conexion.getConnection();

        try{
            String sql = "UPDATE usuario SET email = ?, password = ? WHERE email=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, NombreNew);
            stm.setString(2, Pass);
            stm.setString(3, NombreOld);
            stm.executeUpdate();
            stm.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ModificarPass(String Name, String Pass){
        PreparedStatement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        conn = conexion.getConnection();

        try{
            String sql = "UPDATE usuario SET password = ? WHERE email=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, Pass);
            stm.setString(2, Name);
            stm.executeUpdate();
            stm.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ModificarName(String NameOld, String NameNew){
        PreparedStatement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        conn = conexion.getConnection();

        try{
            String sql = "UPDATE usuario SET email = ? WHERE email=?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, NameNew);
            stm.setString(2, NameOld);
            stm.executeUpdate();
            stm.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

 

    public List<Usuario> listadoUsuario() {
        Statement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Usuario> resultado = new ArrayList<>(); 

        conn = conexion.getConnection();
        try {
            String sql = "SELECT * FROM usuario";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()){
                Usuario u = new Usuario(rs.getString("id"), rs.getString("email"), rs.getString("password"));
                resultado.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (stm != null){
                try {
                    stm.close();
                } catch (SQLException e) {
                    stm = null;
                    e.printStackTrace();
                }
            }
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    rs = null;
                    e.printStackTrace();
                }
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }
}
*/