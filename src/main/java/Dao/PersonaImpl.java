package Dao;

import Modelo.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PersonaImpl extends Conexion implements IGenerica<Persona> {

    @Override
    public void registrar(Persona modelo) throws Exception {
        String sql = "INSERT INTO PERSONA (NOMPER,SEXPER)VALUES(?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMPER());
            ps.setString(2, modelo.getSEXPER());
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error" + e);
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void editar(Persona modelo) throws Exception {

    }

    @Override
    public void eliminar(Persona modelo) throws Exception {

    }

    @Override
    public List<Persona> listar() throws Exception {
        List<Persona> listado;
        Persona persona;
        String sql = "SELECT NOMPER, SEXPER FROM PERSONA";
        try {
            listado = new ArrayList<>();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                persona = new Persona();
                persona.setNOMPER(rs.getString(1));
                persona.setSEXPER(rs.getString(2).equals("M") ? "Masculino" : "Femenino");
                listado.add(persona);
            }
            st.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Error" + e);
            throw e;
        }
        return listado;
    }

}
