package com.mycompany.tqs.gohouse;

import dbclasses.PropertyType;
import java.time.LocalDate;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author joaos
 */
@ManagedBean(name = "beanAddPropriedade", eager = true)
@SessionScoped
public class BeanAddPropriedade {

    private String id;
    private String latitude;
    private String longitude;
    private String endereço;
    private String bloco;
    private String piso;
    private String tipoPropriedade;
    private String message;

    private final DBHandler dBHandler = new DBHandler();

    public BeanAddPropriedade() {
        this.message = "";
    }

    public void submitProperty() {
        assert id != null && latitude != null && longitude != null && endereço != null && bloco != null && piso != null && tipoPropriedade != null;
        assert id != "" && latitude != "" && longitude != "" && endereço != "" && bloco != "" && piso != "" && tipoPropriedade != "";
        try {
            //dBHandler.registerUser("joao@outlook.com", "dhdhd", LocalDate.of(1997, 3, 3), true);

            char bloc = bloco.toCharArray()[0];
            boolean d = false;
            if ("Casa".equals(tipoPropriedade)) {
                d = dBHandler.addNewProperty(Integer.parseInt(id), Float.parseFloat(longitude), Float.parseFloat(latitude), endereço, PropertyType.HOUSE, bloc, Integer.parseInt(piso), null);
            } else {
                d = dBHandler.addNewProperty(Integer.parseInt(id), Float.parseFloat(longitude), Float.parseFloat(latitude), endereço, PropertyType.APARTMENT, bloc, Integer.parseInt(piso), null);
            }

            if (d == true) {
                message = "Propriedade criada com sucesso!";

            } else {
                message = "Propriedade não criada!";
            }
            showDialog();
            //Clear all values
            clearVars();

        } catch (NumberFormatException e) {

            message = "Erro na informação";
            showDialog();
        }

    }

    /**
     * Method that cleans all variables. USAGE: After the submit
     */
    private void clearVars() {
        id = "";
        latitude = "";
        longitude = "";
        endereço = "";
        bloco = "";
        piso = "";
        tipoPropriedade = "";
    }

    private void showDialog() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlg1').show();");
    }

    //Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getTipoPropriedade() {
        return tipoPropriedade;
    }

    public void setTipoPropriedade(String tipoPropriedade) {
        this.tipoPropriedade = tipoPropriedade;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
