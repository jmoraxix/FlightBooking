/*
 * Programacion Concurrente Cliente Servidor
 * 
 * Emilio Evans Rodriguez
 * Jose David Mora Loria
 * Carlos Oreamuno Alfaro
 * 
 * Tercer cuatrimestre, 2017
 * Ulacit
 */
package indieairways.client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
import java.util.ResourceBundle;
//import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FXMLDatosReservaController implements Initializable {

    @FXML private Button bn2; //Boton de ok
    @FXML private ComboBox cBF; //ComboBox From
    @FXML private ComboBox cBTO; //ComboBoz To
    @FXML private DatePicker dPF; //DatePicker From
    @FXML private DatePicker dPTO; //DatePicker To
    @FXML private RadioButton rBOW; //RadioButton One-Way
    @FXML private RadioButton rBRT; //RadioButton Round Trip
    @FXML private TextField tFLuggage; //TextField for quantity of luggage
    @FXML private TextField tFPassan; //TExtField for quantity of passangers
    @FXML private RadioButton rBEconomy; //Radio Button for Economy Class
    @FXML private RadioButton rBBuis; //Radio Button for Buisness Class
    @FXML private RadioButton rBFirClass; //Radio Button for First Class

    private IndieAirwaysClient application;

    private final ToggleGroup tipoVueloTG = new ToggleGroup();
    private final ToggleGroup tipoClaseTG = new ToggleGroup();

    private DateCell fromCell, toCell = null;
    private LocalDate fromDate, toDate = null;
    //private String pattern1 = "dd-MM";

    private int tipoVuelo; //0 si es One-Way, 1 si es Round Trip 
    private String ciudadOrigen, ciudadDestino;
    private int ddF, ffF, ddT, ffT; //dias y mes de ida, dia y mes de venida.
    private int tipoClase;
    private int numPasajeros;
    private int numMaletas;

    ObservableList<String> ciudades = FXCollections.observableArrayList("San Jose", "Tokyo", "Milan", "Barcelona", "Cairo");

    @FXML
    private void handleOneWay(ActionEvent event) {
        //dPTO.disableProperty();
        dPTO.setEditable(false);
    }

    @FXML
    private void handleBn2Action(ActionEvent event) throws IOException {

        try { //Errores que pueden ocurrir con las Radio Button
            if (rBOW.isSelected()) {
                tipoVuelo = 0;
            } else if (rBRT.isSelected()) {
                tipoVuelo = 1;
            }
        } catch (Exception e) {
            //System.out.println("Escoga solamente una caja");
            JOptionPane.showMessageDialog(null, "Mensaje");
        }

        try { //Errores que pueden ocurrir con las Choice Boxes
            if (cBF.getValue().equals(cBTO.getValue())) {
                System.out.println("No puede ingresar ambos lugares iguales");
            } else {
                ciudadOrigen = (String) cBF.getValue();
                ciudadDestino = (String) cBTO.getValue();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Mensaje");
        }

        try {
            fromDate = dPF.getValue();
            toDate = dPTO.getValue();
            if (fromDate.equals(toDate)) {
                JOptionPane.showMessageDialog(null, "Fecha invalida");
            }

            if (A_menor(fromDate, toDate)) {
                JOptionPane.showMessageDialog(null, "Fecha invalida");
            }

        } catch (Exception e) {

        }

        //Ponerle un maximo y excepcion en el caso que no sea un numero
        numPasajeros = Integer.parseInt(tFPassan.getText());
        numMaletas = Integer.parseInt(tFLuggage.getText());

        if (rBEconomy.isSelected()) {
            tipoClase = 0;
        } else if (rBFirClass.isSelected()) {
            tipoClase = 1;
        } else if (rBBuis.isSelected()) {
            tipoClase = 2;
        }

        System.out.println("Tipo de vuelo es " + tipoVuelo + "\nCiudad Origen: " + ciudadOrigen
                + "\nCiudad Destino: " + ciudadDestino + "\nFecha Salida: " + fromDate
                + "\nFecha de Regreso: " + toDate + "\nNumero de pasajeros: " + numPasajeros
                + "\nNumero de maletas: " + numMaletas + "\nTipo de Clase: " + tipoClase);

        //goNextWindow(event, tipoVuelo);
        //application.gotoReservaOneWay();
    }

    public boolean A_menor(LocalDate l1, LocalDate l2) { //Revisa si la fecha es menor
        boolean esMenor = false;

        if (l1.getYear() > l2.getYear()) {
            esMenor = true;
        } else if ((l1.getYear() == l2.getYear()) && (l1.getMonthValue() > l2.getMonthValue())) {
            esMenor = true;
        } else if ((l1.getYear() == l2.getYear()) && (l1.getMonthValue() == l2.getMonthValue()) && (l1.getDayOfMonth() > l2.getDayOfMonth())) {
            esMenor = true;
        }

        return esMenor;
    }

    public void setApp(IndieAirwaysClient application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cBF.getItems().addAll(ciudades);
        cBTO.getItems().addAll(ciudades);

        rBOW.setToggleGroup(tipoVueloTG);
        rBRT.setToggleGroup(tipoVueloTG);

        rBEconomy.setToggleGroup(tipoClaseTG);
        rBBuis.setToggleGroup(tipoClaseTG);
        rBFirClass.setToggleGroup(tipoClaseTG);

        bn2.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            System.out.println("Primero");
            if(tipoVuelo == 0)
                application.gotoReservaOneWay();
            else if(tipoVuelo == 1)
                application.gotoReservaRoundTrip();
            });

    }
}//Fin de la clase FXMLDatosReservaController
