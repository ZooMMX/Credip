package Credip.Model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Octavio
 * Date: 19/01/13
 * Time: 10:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class MovimientoProgramado {
    private Calendar fecha;
    private BigDecimal cargo;
    private BigDecimal abono;
    private String descripcion;

    public MovimientoProgramado() {
        cargo = BigDecimal.ZERO;
        abono = BigDecimal.ZERO;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getCargo() {
        return cargo;
    }

    public void setCargo(BigDecimal cargo) {
        cargo.setScale(2, RoundingMode.HALF_UP);
        this.cargo = cargo;
    }

    public BigDecimal getAbono() {
        return abono;
    }

    public void setAbono(BigDecimal abono)
    {
        abono.setScale(2, RoundingMode.HALF_UP);
        this.abono = abono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
