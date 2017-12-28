package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import modelo.Modelo;
import modelo.Empleado;
import vista.Agregar;
import vista.Eliminar;
import vista.Listar;
import vista.MenuInicial;
import vista.Consultas;


public class Controlador implements ActionListener, MouseListener {

    /**
     * instancia a nuestra interfaz de usuario
     */
    MenuInicial vista;
    Agregar vistaAgregar = new Agregar();
    Listar vistalist = new Listar();
    Eliminar vistaelim = new Eliminar();
    Consultas vistaConsulta = new Consultas();

    Empleado empleado = new Empleado();
    /**
     * instancia a nuestro modelo
     */
    Modelo modelo = new Modelo();

    /**
     * Se declaran en un ENUM las acciones que se realizan desde la interfaz de
     * usuario VISTA y posterior ejecución desde el controlador
     */
    public enum AccionMVC {
        MenuAgregar,
        MenuEliminar,
        MenuListar,
        BtnAgregarAgregar,
        BtnVolverAgregar,
        BtnEliminareliminar,
        BtnBuscarListar,
        BtnListarListar,
        BtnModificar,
        CboxEmpleado,
        BtnEliminarListar,
        BtnVoleverListar,
        MenuSalir,
      
        BtnConsulta1,
        BtnConsulta2,
        BtnConsulta3,
        BtnConsulta4,
        BtnConsultaVolver,
        BtnLimpiarCasillas,
        MenuConsulta

    }

    /**
     * Constrcutor de clase
     *
     * @param vista Instancia de clase interfaz
     */
    public Controlador(MenuInicial vista) {
        this.vista = vista;
    }

    /**
     * Inicia el skin y las diferentes variables que se utilizan
     */
    public void iniciar() {
        // Skin tipo WINDOWS
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vista);
            vista.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }

        //declara una acción y añade un escucha al evento producido por el componente
        this.vistaAgregar.BtnAgregarEmpleado.setActionCommand("BtnAgregarAgregar");
        this.vistaAgregar.BtnAgregarEmpleado.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vistaAgregar.BtnVolverAgregar.setActionCommand("BtnVolverAgregar");
        this.vistaAgregar.BtnVolverAgregar.addActionListener(this);

       

        // ----------------------------------------------------------
        //declara una acción y añade un escucha al evento producido por el componente menu
        this.vista.MenuAgregar.setActionCommand("MenuAgregar");
        this.vista.MenuAgregar.addActionListener(this);

        this.vista.MenuEliminar.setActionCommand("MenuEliminar");
        this.vista.MenuEliminar.addActionListener(this);

        this.vista.MenuListar.setActionCommand("MenuListar");
        this.vista.MenuListar.addActionListener(this);

        this.vista.MenuConsulta.setActionCommand("MenuConsulta");
        this.vista.MenuConsulta.addActionListener(this);

        this.vista.MenuSalir.setActionCommand("MenuSalir");
        this.vista.MenuSalir.addActionListener(this);

        //---------------------------Botoneslistar--------------
        this.vistalist.BtnListarListar.setActionCommand("BtnListarListar");
        this.vistalist.BtnListarListar.addActionListener(this);
//
        this.vistalist.BtnVoleverListar.setActionCommand("BtnVoleverListar");
        this.vistalist.BtnVoleverListar.addActionListener(this);

        this.vistalist.BtnEliminarListar.setActionCommand("BtnEliminarListar");
        this.vistalist.BtnEliminarListar.addActionListener(this);

//        añade e inicia el jtable con un DefaultTableModel vacio
        this.vistalist.TablaListarEmpleado.addMouseListener(this);
        this.vistalist.TablaListarEmpleado.setModel(new DefaultTableModel());

        this.vistalist.BtnModificar.setActionCommand("BtnModificar");
        this.vistalist.BtnModificar.addActionListener(this);

        this.vistalist.BtnBuscarListar.setActionCommand("BtnBuscarListar");
        this.vistalist.BtnBuscarListar.addActionListener(this);

//        ---------------------------BotonesEleminar------------------
        this.vistaelim.BtnEliminareliminar.setActionCommand("BtnEliminareliminar");
        this.vistaelim.BtnEliminareliminar.addActionListener(this);
//        this.vistalist.TxtCodigo.setEnabled(false);

        this.vistaAgregar.CBoxDepto.setActionCommand("CBoxDepto");
        this.vistaAgregar.CBoxDepto.addActionListener(this);
       

// Botones de la vista consulta  
       
        this.vistaConsulta.BtnConsulta1.setActionCommand("BtnConsulta1");
        this.vistaConsulta.BtnConsulta1.addActionListener(this);
        this.vistaConsulta.BtnConsulta2.setActionCommand("BtnConsulta2");
        this.vistaConsulta.BtnConsulta2.addActionListener(this);
        this.vistaConsulta.BtnConsulta3.setActionCommand("BtnConsulta3");
        this.vistaConsulta.BtnConsulta3.addActionListener(this);
        this.vistaConsulta.BtnConsulta4.setActionCommand("BtnConsulta4");
        this.vistaConsulta.BtnConsulta4.addActionListener(this);

        this.vistaConsulta.BtnConsultaVolver.setActionCommand("BtnConsultaVolver");
        this.vistaConsulta.BtnConsultaVolver.addActionListener(this);

        this.vistaAgregar.BtnLimpiarCasillas.setActionCommand("BtnLimpiarCasillas");
        this.vistaAgregar.BtnLimpiarCasillas.addActionListener(this);

    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {

        if (e.getButton() == 1)//boton izquierdo
        {

            //Muestro datos de producto a modificar
            int fila = this.vistalist.TablaListarEmpleado.rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.vistalist.TxtCodigoListar.setText(String.valueOf(this.vistalist.TablaListarEmpleado.getValueAt(fila, 0)));
                this.vistalist.TxtRutListar.setText(String.valueOf(this.vistalist.TablaListarEmpleado.getValueAt(fila, 1)));
                this.vistalist.TxtNombreListar.setText(String.valueOf(this.vistalist.TablaListarEmpleado.getValueAt(fila, 2)));
                this.vistalist.TxtApellidoListar.setText(String.valueOf(this.vistalist.TablaListarEmpleado.getValueAt(fila, 3)));
                this.vistalist.TxtCelularListar.setText(String.valueOf(this.vistalist.TablaListarEmpleado.getValueAt(fila, 4)));
                this.vistalist.TxtEmailListar.setText(String.valueOf(this.vistalist.TablaListarEmpleado.getValueAt(fila, 5)));
                this.vistalist.TxtSueldoBrutoListar.setText(String.valueOf(this.vistalist.TablaListarEmpleado.getValueAt(fila, 6)));
                this.vistalist.TxtEstadoCivilListar.setText(String.valueOf(this.vistalist.TablaListarEmpleado.getValueAt(fila, 7)));
                this.vistalist.TxtNombreDeptoListar.setText(String.valueOf(this.vistalist.TablaListarEmpleado.getValueAt(fila, 8)));
                
            
            }
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    //Control de eventos de los controles que tienen definido un "ActionCommand"
    public void actionPerformed(ActionEvent e) {
//        String cod1;
        switch (AccionMVC.valueOf(e.getActionCommand())) {

            case MenuAgregar:
                this.vistaAgregar.setVisible(true);
                this.vista.setVisible(false);
                break;

            case MenuConsulta:
                this.vistaConsulta.setVisible(true);
                this.vista.setVisible(false);
                break;

            case MenuSalir:
                this.vista.dispose();
                System.exit(0);
                break;

            case MenuListar:
                this.vistalist.setVisible(true);
                this.vistalist.TablaListarEmpleado.setModel(this.modelo.ListadoProducto());
                break;

            case BtnListarListar:
                //obtiene del modelo los registros en un DefaultTableModel y lo asigna en la vista
                this.vistalist.TablaListarEmpleado.setModel(this.modelo.ListadoProducto());
                break;

            //activa botob volver de la interfaz agregar
            case BtnVolverAgregar:
                this.vistaAgregar.setVisible(false);
                this.vista.setVisible(true);
                break;
//
            case BtnModificar:

                if (this.modelo.modificaEmpleado(
                        Integer.parseInt(this.vistalist.TxtCodigoListar.getText()),
                       Integer.parseInt( this.vistalist.TxtRutListar.getText()),
                       this.vistalist.TxtNombreListar.getText(),
                       this.vistalist.TxtApellidoListar.getText(),
                        
                        Integer.parseInt(this.vistalist.TxtCelularListar.getText()),
                        this.vistalist.TxtEmailListar.getText(),
                        
                        Integer.parseInt(this.vistalist.TxtSueldoBrutoListar.getText()),
                        this.vistalist.TxtEstadoCivilListar.getText(),
 //                       Integer.parseInt(this.vistalist.CBoxDeptoListar.getSelectedItem().toString()),
                        
           
                      this.vistalist.TablaListarEmpleado.setModel(this.modelo.ListadoProducto()); //actualiza JTable
                    JOptionPane.showMessageDialog(null, "Producto actualizado");
                    //Limpiamos textField
                    this.vistalist.TxtCodigoListar.setText("");
                    this.vistalist.TxtRutListar.setText("");
                    this.vistalist.TxtNombreListar.setText("");
                    this.vistalist.TxtApellidoListar.setText("");
                    this.vistalist.TxtCelularListar.setText("");
                    this.vistalist.TxtEmailListar.setText("");
                    this.vistalist.TxtSueldoBrutoListar.setText("");
                    this.vistalist.TxtEstadoCivilListar.setText("");
                    this.vistalist.CBoxDeptoListar.setSelectedIndex(0);
                    
                    

                }
                break;

            case BtnAgregarAgregar:
                //añade un nuevo registro

                if (this.modelo.NuevoProducto(
                        Integer.parseInt(this.vistaAgregar.TxtCodigo.getText()),
                        Integer.parseInt(this.vistaAgregar.TxtRut.getText()),
                        this.vistaAgregar.TxtNombre.getText(),
                        this.vistaAgregar.TxtApellido.getText(),
                        Integer.parseInt(this.vistaAgregar.TxtCelular.getText()),
                        this.vistaAgregar.TxtEmail.getText(),
                        Integer.parseInt(this.vistaAgregar.TxtSueldoBruto.getText()),
                        this.vistaAgregar.TxtEstadoCivil.getText(),
                        this.vistaAgregar.CBoxDepto.getSelectedItem().toString() ))
                        
                      
                        
                        
                        {
                    JOptionPane.showMessageDialog(null, "Producto agregado correctamente");
                }

                break;

       

            case BtnEliminareliminar:
                int codigo1;
                codigo1 = Integer.parseInt(this.vistaelim.Txtcodigoeliminar.getText());
                this.modelo.eliminarEmpleado(codigo1);
                break;

            case BtnEliminarListar:
                int codigo2;
                codigo2 = Integer.parseInt(this.vistalist.TxtCodigoListar.getText());
                this.modelo.eliminarEmpleado(codigo2);
                 {
                    this.vistalist.TablaListarEmpleado.setModel(this.modelo.ListadoProducto()); //actualiza JTable
                    JOptionPane.showMessageDialog(null, "Producto actualizado");
                    //Limpiamos textField
                    
                  
                    this.vistalist.TxtCodigoListar.setText("");
                    this.vistalist.TxtRutListar.setText("");
                    this.vistalist.TxtNombreListar.setText("");
                    this.vistalist.TxtApellidoListar.setText("");
                    this.vistalist.TxtCelularListar.setText("");
                    this.vistalist.TxtEmailListar.setText("");
                    this.vistalist.TxtSueldoBrutoListar.setText("");
                    this.vistalist.TxtEstadoCivilListar.setText("");
                    this.vistalist.CBoxDeptoListar.setSelectedIndex(0);
    

                }

                break;

            case BtnVoleverListar:
                this.vistalist.setVisible(false);
                this.vista.setVisible(true);
                break;

            case BtnBuscarListar:
                try {
                    String codigo = this.vistalist.TxtCodigoListar.getText();

                    DefaultTableModel modeloT = new DefaultTableModel();
                    vistalist.TablaListarEmpleado.setModel(modeloT);

                    modeloT.addColumn("Codigo");
                    modeloT.addColumn("Rut");                 
                    modeloT.addColumn("EstadoCivil");
                    modeloT.addColumn("Nombre");
                    modeloT.addColumn("Apellido");
                    modeloT.addColumn("Celular");
                    modeloT.addColumn("Email");
                    modeloT.addColumn("SueldoBruto");
                    modeloT.addColumn("NombreDepto");

                    Object[] columna = new Object[9];

                    int numRegistros = modelo.buscarPorCodigo(codigo).size();

                    for (int i = 0; i < numRegistros; i++) {
                        columna[0] = modelo.buscarPorCodigo(codigo).get(i).getCodigo();
                        columna[1] = modelo.buscarPorCodigo(codigo).get(i).getRut();
                        columna[2] = modelo.buscarPorCodigo(codigo).get(i).getNombre();
                        columna[3] = modelo.buscarPorCodigo(codigo).get(i).getApellido();
                        columna[4] = modelo.buscarPorCodigo(codigo).get(i).getCelular();
                        columna[5] = modelo.buscarPorCodigo(codigo).get(i).getEmail();
                        columna[6] = modelo.buscarPorCodigo(codigo).get(i).getSueldoBruto();
                        columna[7] = modelo.buscarPorCodigo(codigo).get(i).getEstadoCivil();
                        columna[8] = modelo.buscarPorCodigo(codigo).get(i).getNombreDepto();
                  
                        modeloT.addRow(columna);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Empleado no encontrado");
                }
                break;
       
            case BtnConsulta1:
                this.vistaAgregar.setVisible(true);
                this.vistaConsulta.setVisible(false);
                //Limpiamos textField
                this.vistaAgregar.TxtCodigo.setText("");
                this.vistaAgregar.TxtRut.setText("");
                this.vistaAgregar.TxtNombre.setText("");
                this.vistaAgregar.TxtApellido.setText("");
                this.vistaAgregar.TxtCelular.setText("");
                this.vistaAgregar.TxtEmail.setText("");
                this.vistaAgregar.TxtSueldoBruto.setText("");
                this.vistaAgregar.TxtEstadoCivil.setText("");
                this.vistaAgregar.CBoxDepto.setSelectedIndex(0);
                this.vistaAgregar.TxtCodigo.setFocusable(true);
                break;
                
                
               

///////////////////////////////////////////////////////////                 
            case BtnLimpiarCasillas:

                //Limpiamos textField
                this.vistaAgregar.TxtCodigo.setText("");
                this.vistaAgregar.TxtRut.setText("");
                this.vistaAgregar.TxtNombre.setText("");
                 this.vistaAgregar.TxtApellido.setText("");
                this.vistaAgregar.TxtCelular.setText("");
                this.vistaAgregar.TxtEmail.setText("");
                this.vistaAgregar.TxtSueldoBruto.setText("");
                this.vistaAgregar.TxtEstadoCivil.setText("");
                this.vistaAgregar.CBoxDepto.setSelectedIndex(0);
                this.vistaAgregar.TxtCodigo.setFocusable(true);
                break;

            case BtnConsulta2:
                this.vistalist.setVisible(true);
                this.vistaConsulta.setVisible(false);
                try {
                    String NombreDepto = "Redes";

                    DefaultTableModel modeloT = new DefaultTableModel();
                    vistalist.TablaListarEmpleado.setModel(modeloT);

                    modeloT.addColumn("Codigo");
                    modeloT.addColumn("Rut");
                    modeloT.addColumn("Nombre");
                    modeloT.addColumn("Apellido");
                    modeloT.addColumn("Celular");
                    modeloT.addColumn("Email");
                    modeloT.addColumn("SueldoBruto");
                    modeloT.addColumn("EstadoCivil");
                    modeloT.addColumn("NombreDepto");
                    Object[] columna = new Object[9];

                    int numRegistros = modelo.buscarNombreDepto(NombreDepto).size();

                    for (int i = 0; i < numRegistros; i++) {
                        columna[0] = modelo.buscarPorNombreDepto(NombreDepto).get(i).getCodigo();
                        columna[1] = modelo.buscarPorNombreDepto(NombreDepto).get(i).getRut();
                        columna[2] = modelo.buscarPorNombreDepto(NombreDepto).get(i).getNombre();
                        columna[3] = modelo.buscarPorNombreDepto(NombreDepto).get(i).getApellido();
                        columna[4] = modelo.buscarPorNombreDepto(NombreDepto).get(i).getCelular();
                        columna[5] = modelo.buscarPorNombreDepto(NombreDepto).get(i).getEmail();
                        columna[6] = modelo.buscarPorNombreDepto(NombreDepto).get(i).getSueldoBruto();
                        columna[7] = modelo.buscarPorNombreDepto(NombreDepto).get(i).getEstadoCivil();
                        columna[8] = modelo.buscarPorNombreDepto(NombreDepto).get(i).getNombreDepto();
                  
                        modeloT.addRow(columna);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Empleado no encontrado");
                }
                break;

            case BtnConsulta3:
                int sueldo1 = 120000;
                this.modelo.eliminarSueldo(sueldo1);
                 {
                    this.vistalist.TablaListarEmpleado.setModel(this.modelo.ListadoProducto()); //actualiza JTable
                    JOptionPane.showMessageDialog(null, "Empleado eliminado");
                }
                break;

            case BtnConsulta4:
            if (this.modelo.modificaSueldo1());
            { JOptionPane.showMessageDialog(null, "Sueldos modificados");
                }

                break;
                

        }
        
   }



