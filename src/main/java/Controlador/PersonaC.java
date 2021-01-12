package Controlador;

import Dao.PersonaImpl;
import Modelo.Persona;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;

@Named(value = "personaC")
@SessionScoped
public class PersonaC implements Serializable {

    private PieChartModel pieModel;
    Persona persona;
    PersonaImpl daoPersona;
    List<Persona> listaPersona;

    public PersonaC() {
        persona = new Persona();
        daoPersona = new PersonaImpl();
        listaPersona = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        try {
            createPieModel();
            listar();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();
         
        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(10);
        values.add(8);
        dataSet.setData(values);
         
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 99, 132)");   
        dataSet.setBackgroundColor(bgColors);
         
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Masculino");
        labels.add("Femenino");
        data.setLabels(labels);
         
        pieModel.setData(data);
    }

    public void registrar() {
        try {
            daoPersona.registrar(persona);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado Correctamente", null));
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    public void listar() {
        try {
            listaPersona = daoPersona.listar();
            List<Persona> listaTemp = new ArrayList<>();
            for (Persona nextPersona : listaPersona) {
                switch (nextPersona.getSEXPER()) {
                    case "M":
                        nextPersona.setSEXPER("Masculino");
                        break;
                    case "F":
                        nextPersona.setSEXPER("Femenino");
                        break;
                }

                listaTemp.add(nextPersona);
            }
            listaPersona = listaTemp;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public PersonaImpl getDaoPersona() {
        return daoPersona;
    }

    public void setDaoPersona(PersonaImpl daoPersona) {
        this.daoPersona = daoPersona;
    }

    public List<Persona> getListaPersona() {
        return listaPersona;
    }

    public void setListaPersona(List<Persona> listaPersona) {
        this.listaPersona = listaPersona;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

}
