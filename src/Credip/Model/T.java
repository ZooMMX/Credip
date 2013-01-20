package Credip.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Octavio
 * Date: 19/01/13
 * Time: 10:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class T {
    private Date dia;
    private HashMap<Tarjeta, EstadoDia> estados;

    public T() {
        estados = new HashMap<Tarjeta, EstadoDia>();
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }


    public HashMap<Tarjeta, EstadoDia> getEstados() {
        return estados;
    }

    public void setEstados(HashMap<Tarjeta, EstadoDia> estados) {
        this.estados = estados;
    }
}
