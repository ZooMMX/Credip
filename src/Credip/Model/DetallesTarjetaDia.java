package Credip.Model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created with IntelliJ IDEA.
 * User: Octavio
 * Date: 19/01/13
 * Time: 10:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class DetallesTarjetaDia {
    private BigDecimal saldo;
    private BigDecimal interes;
    private BigDecimal cargos;
    private BigDecimal abonos;

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        saldo.setScale(2, RoundingMode.HALF_UP);
        this.saldo = saldo;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        interes.setScale(2, RoundingMode.HALF_UP);
        this.interes = interes;
    }

    public BigDecimal getCargos() {
        return cargos;
    }

    public void setCargos(BigDecimal cargos) {
        cargos.setScale(2, RoundingMode.HALF_UP);
        this.cargos = cargos;
    }

    public BigDecimal getAbonos() {
        return abonos;
    }

    public void setAbonos(BigDecimal abonos) {
        abonos.setScale(2, RoundingMode.HALF_UP);
        this.abonos = abonos;
    }
}
