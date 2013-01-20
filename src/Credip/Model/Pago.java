package Credip.Model;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Octavio
 * Date: 19/01/13
 * Time: 10:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Pago {

    public MODO getModo() {
        return modo;
    }

    public void setModo(MODO modo) {
        this.modo = modo;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Tarjeta getOrigen() {
        return origen;
    }

    public void setOrigen(Tarjeta origen) {
        this.origen = origen;
    }

    public enum MODO { EFECTIVO, TARJETA };
    public static BigDecimal PAGO_MINIMO     = new BigDecimal(-1);
    public static BigDecimal PAGO_DISPONIBLE_80_PORC = new BigDecimal(-2);

    private MODO modo;
    private BigDecimal cantidad;
    private Tarjeta origen;

}
