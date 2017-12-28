package modelo;

import javax.swing.JOptionPane;

public class Empleado {
    int codigo;
    String rut;
    String nombre;
    String apellido;
    int celular;
    String email;
    int sueldo;
    String estadocivil;
    String nombredepto;

    public Empleado() {
    }

    public Empleado( int codigo,String rut,String nombre,String apellido,
            int celular,String email,int sueldo,String estadocivil,String nombredepto)
    
   {
        this.codigo = codigo;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.email=email;
        this.sueldo=sueldo;
        this.estadocivil=estadocivil;
        this.nombredepto=nombredepto;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public String getNombredepto() {
        return nombredepto;
    }

    public void setNombredepto(String nombredepto) {
        this.nombredepto = nombredepto;
    }
    

   
    @Override
    public String toString() {
        return "Empleado{" + "codigo=" + codigo + ", rut=" + rut + ", nombre=" + nombre + ", apellido=" + apellido + ", celular=" + celular + ", email=" + email + ", sueldo=" + sueldo + ", estadocivil=" + estadocivil + ", nombredepto=" + nombredepto + '}';
    }

    void setCodigo(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setNombre(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setSueldobruto(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
