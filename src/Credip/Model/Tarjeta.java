package Credip.Model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Octavio
 * Date: 19/01/13
 * Time: 10:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tarjeta {
    private Integer id;
    private String descripcion;
    private BigDecimal lineaCredito;
    private BigDecimal capital;
    private List<MovimientoProgramado> movimientosProgramados;
    private BigDecimal tasa;
    private BigDecimal porcentajeMinimo;
    private Integer diaCorte;
    private Integer diaPago;
    private List<Pago> pagos;
    MathContext mc = new MathContext(10, RoundingMode.HALF_UP);

    public Tarjeta() {
        movimientosProgramados = new ArrayList<MovimientoProgramado>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getLineaCredito() {
        return lineaCredito;
    }

    public void setLineaCredito(BigDecimal lineaCredito) {
        lineaCredito.setScale(2, RoundingMode.HALF_UP);
        this.lineaCredito = lineaCredito;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        capital.setScale(2, RoundingMode.HALF_UP);
        this.capital = capital;
    }

    public List<MovimientoProgramado> getMovimientosProgramados() {
        return movimientosProgramados;
    }

    public void setMovimientosProgramados(List<MovimientoProgramado> movimientosProgramados) {
        this.movimientosProgramados = movimientosProgramados;
    }

    public BigDecimal getTasa() {
        return tasa;
    }

    public void setTasa(BigDecimal tasa) {
        tasa.setScale(2, RoundingMode.HALF_UP);
        this.tasa = tasa;
    }

    public BigDecimal getPorcentajeMinimo() {
        return porcentajeMinimo;
    }

    public void setPorcentajeMinimo(BigDecimal porcentajeMinimo) {
        porcentajeMinimo.setScale(2, RoundingMode.HALF_UP);
        this.porcentajeMinimo = porcentajeMinimo;
    }


    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public Integer getDiaCorte() {
        return diaCorte;
    }

    public void setDiaCorte(Integer diaCorte) {
        this.diaCorte = diaCorte;
    }

    public Integer getDiaPago() {
        return diaPago;
    }

    public void setDiaPago(Integer diaPago) {
        this.diaPago = diaPago;
    }
}
