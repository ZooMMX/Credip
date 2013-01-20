package Credip.Model;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Octavio
 * Date: 19/01/13
 * Time: 10:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class Deudor {

    private List<Tarjeta> tarjetas;
    private Date fechaInicial;

    public List<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }
}
