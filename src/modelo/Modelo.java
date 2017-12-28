package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Empleado;
import javax.swing.JComboBox;



public class Modelo extends Conexion {
    static Statement sentencia;
    static ResultSet resultado;
    private ResultSet rs;
    private Statement st;
     public boolean NuevoProducto(int codigo,String rut,String nombre,String apellido,
            int celular,String email,int sueldo,String estadocivil,String nombredepto) {
//         ----> Validaciones que deben agregarse posteriormente. <--------
        if (valida_datos(codigo, rut, nombre,apellido,celular,email,sueldo,estadocivil,nombredepto)) {

//            Se arma la consulta
            String q = " INSERT INTO empleado (codigo, rut, nombre,apellido,celular,email,sueldo,estadocivil,nombredepto) "
                    + "VALUES ( " + codigo + "," + rut + ", '" + nombre
                    + "','" + apellido + "','" + celular + "," + email + ", '" + sueldo
                    + "','" + estadocivil + "','" + nombredepto + "') ";
//            se ejecuta la consulta

            try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;

            } catch (SQLException e) {
                System.err.println(e.getMessage());

            }
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese datos correctamente");
        }
        return false;
    } 
     
     
         //Metodo para validar datos
    private boolean valida_datos(int codigo,String rut,String nombre,String apellido,
            int celular,String email,int sueldo,String estadocivil,String nombredepto) {
        if (codigo > 0 && codigo <=100) {
            return false;
 //       } else if (nombre.?() && sueldo >= 120000 && (estadocivil.equals("S")||estadocivil.equals("C")||estadocivil.equals("V")&& rut ? && celular.length()=9)) {

 //           return true;
        } else {
            return false;
        }
    }
     
         //Modificar producto seleccionado
    public boolean modificaEmpleado(int codigo,String rut,String nombre,String apellido,
            int celular,String email,int sueldo,String estadocivil,String nombredepto) {
        String q = "UPDATE empresa.empleado SET " + "codigo=" + codigo + ", rut=" + rut + ", nombre=" + nombre + ", apellido=" + apellido + ", celular=" + celular + ", email=" + email + ", sueldo=" + sueldo + ", estadocivil=" + estadocivil + ", nombredepto=" + nombredepto + "' ";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    
    //eliminar empleado
     public boolean eliminarEmpleado(int codigo) {
        boolean res = false;
        String q = " DELETE FROM empresa.empleado WHERE codigo=" + codigo + " ";
        try {
            PreparedStatement pstm = getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            res = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return res;
    }
     
     //eliminar empleado boton consulta 3
     public boolean eliminarEmpleadoSueldo(int sueldo) {
        boolean res = false;
        String q = " DELETE FROM empresa.empleado WHERE sueldo=" + sueldo + " ";
        try {
            PreparedStatement pstm = getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            res = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return res;
    }
     
      public ArrayList<Empleado> buscarPorCodigo(String codigo) {
        ArrayList<Empleado> listaEmpleado = new ArrayList<Empleado>();
        try {
            Connection conexion = getConexion();
            String query = "SELECT codigo, rut, nombre,apellido,celular,email,sueldo,estadocivil,nombredepto From empleado where codigo=?";
            PreparedStatement buscarPorCodigo = conexion.prepareStatement(query);
            buscarPorCodigo.setString(1, codigo);
            ResultSet rs = buscarPorCodigo.executeQuery();
            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setCodigo(rs.getInt("codigo"));
                empleado.setRut(rs.getString("rut"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));
                empleado.setCelular(rs.getInt("celular"));
                empleado.setEmail(rs.getString("email"));
                empleado.setSueldobruto(rs.getInt("sueldobruto"));
                empleado.setEstadocivil(rs.getString("estadocivil"));
                empleado.setNombredepto(rs.getString("nombredepto"));
                
                listaEmpleado.add(empleado);
            }
        } catch (SQLException s) {
            System.out.println("Error SQL al listar empleado" + s.getMessage());
        } catch (Exception e) {
            System.out.println("Error al listar empleado" + e.getMessage());
        }
        return listaEmpleado;
    }
      


     
  public DefaultTableModel ListadoProducto() {
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        
        String[] columNames = {"Codigo", "Rut", "Nombre", "Apellido", "Celular","Email","Sueldobruto","Estadocivil","Nombredepto"};
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT count(*) as total FROM empleado");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        Object[][] data = new String[registros][9];
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM empleado");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getInt("codigo");
                data[i][1] = res.getString("rut");
                data[i][2] = res.getString("nombre");
                data[i][3] = res.getString("apellido");
                data[i][4] = res.getInt("celular");
                data[i][5] = res.getString("email");
                data[i][6] = res.getInt("sueldobruto");
                data[i][7] = res.getString("estadocivil");
                data[i][8] = res.getString("nombredepto");
                i++;
            }
            res.close();
            tablemodel.setDataVector(data, columNames);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablemodel;
        
    } //consulta4
        public boolean modificaSueldo1() {
        String q = "UPDATE empresa.empleado SET sueldo= (sueldo*1.10)";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
        }


     }




