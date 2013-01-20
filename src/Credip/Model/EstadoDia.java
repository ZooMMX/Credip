package Credip.Model;

/**
 * Created with IntelliJ IDEA.
 * User: Octavio
 * Date: 19/01/13
 * Time: 10:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class EstadoDia {
    private Tarjeta tarjeta;
    private DetallesTarjetaDia detalles;

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public DetallesTarjetaDia getDetalles() {
        return detalles;
    }

    public void setDetalles(DetallesTarjetaDia detalles) {
        this.detalles = detalles;
    }
}
