package Credip.BL;

import Credip.Model.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Octavio
 * Date: 19/01/13
 * Time: 10:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Procesador {
    public ArrayList<T> t;
    Deudor deudor;

    public Procesador(Deudor d) {
        deudor = d;
        t = new ArrayList<T>();
    }

    public void ejecutar() {
        inicializar();
        Date fecha;

        while(t.get(t.size()-1).getEstados().size() > 0) {
            T ultimoT = t.get(t.size()-1);

            fecha = t.get(t.size()-1).getDia();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            calendar.add(Calendar.DATE, 1);
            fecha = calendar.getTime();

            T nextT = new T();
            nextT.setDia(fecha);

            // Para cada tarjeta restante
            for(Tarjeta tarjeta : ultimoT.getEstados().keySet()) {
                EstadoDia estadoDia = calcularDiaTarjeta(calendar, tarjeta);
                //if(estadoDia.getDetalles().getSaldo().compareTo(new BigDecimal(0)) >= 0) {
                if(ultimoT.getEstados().get(tarjeta).getDetalles().getSaldo().compareTo(new BigDecimal(0)) >= 0) {
                    nextT.getEstados().put(tarjeta, estadoDia);
                }
            }
            t.add(nextT);
        }
    }

    private EstadoDia calcularDiaTarjeta(Calendar dia, Tarjeta tarjeta) {
        EstadoDia estadoDia      = new EstadoDia();
        DetallesTarjetaDia detalles = new DetallesTarjetaDia();
        EstadoDia estadoAnterior = t.get(t.size()-1).getEstados().get(tarjeta);

        MathContext mc = new MathContext(8, RoundingMode.HALF_UP);

        BigDecimal cargos = new BigDecimal(0, mc).setScale(2, RoundingMode.HALF_UP);
        BigDecimal abonos = new BigDecimal(0, mc).setScale(2, RoundingMode.HALF_UP);
        BigDecimal interes = new BigDecimal(0, mc).setScale(2, RoundingMode.HALF_UP);
        BigDecimal saldo   = new BigDecimal(0, mc).setScale(2, RoundingMode.HALF_UP);

        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd/MM/yyyy");

        // A partir de aquí los cálculos
        // Se calculan pagos
        for(Pago pago : tarjeta.getPagos()) {
            if(dia.get(Calendar.DAY_OF_MONTH) == tarjeta.getDiaPago()) {
                //¿Cantidad a Pagar?
                if(pago.getCantidad() == Pago.PAGO_DISPONIBLE_80_PORC) {
                    BigDecimal saldoCtaDestino = t.get(t.size()-1).getEstados().get(pago.getOrigen()).getDetalles().getSaldo();
                    abonos = pago.getOrigen().getLineaCredito().subtract(saldoCtaDestino).multiply(new BigDecimal(0.8));
                } else if (pago.getCantidad() == Pago.PAGO_MINIMO) {
                    BigDecimal saldoAnterior = t.get(t.size()-1).getEstados().get(tarjeta).getDetalles().getSaldo();
                    abonos = saldoAnterior.multiply(tarjeta.getPorcentajeMinimo()).divide(new BigDecimal(100)).add(BigDecimal.ONE);
                } else {
                    abonos = pago.getCantidad().add(abonos);
                }

                // ¿Método de pago?
                switch(pago.getModo()) {
                    case EFECTIVO: break;
                    case TARJETA:
                        MovimientoProgramado mp = new MovimientoProgramado();
                        Calendar f = (Calendar) dia.clone();
                        f.add(Calendar.DATE, 1);
                        mp.setFecha(f);
                        mp.setDescripcion("Pago a " + tarjeta.getDescripcion() + " con transferencia");
                        mp.setCargo(abonos);
                        pago.getOrigen().getMovimientosProgramados().add(mp);
                        break;
                }

            }
        }
        // Se calculan intereses
        if(dia.get(Calendar.DAY_OF_MONTH) == tarjeta.getDiaCorte()) {
            BigDecimal porcentajeInteres = tarjeta.getTasa().multiply(new BigDecimal("0.01"))
                    .divide(new BigDecimal("12.00"), RoundingMode.HALF_UP);
            interes = estadoAnterior.getDetalles().getSaldo().multiply(porcentajeInteres).add(interes);
            cargos = cargos.add(interes);
        }
        // Se revisan movimientos programados del día
        for(MovimientoProgramado mp : tarjeta.getMovimientosProgramados()) {
            if(df.format( mp.getFecha().getTime() ).equals( df.format( dia.getTime() ))) {
                cargos = cargos.add(mp.getCargo());
                abonos = abonos.add(mp.getAbono());
            }
        }

        //Saldo final
        saldo = estadoAnterior.getDetalles().getSaldo().add(cargos).subtract(abonos);
        //Se aplica total de cargos y abonos
        detalles.setAbonos( abonos );

        //Lleno el respectivo Bean con datos finales
        detalles.setAbonos(abonos);
        detalles.setCargos(cargos);
        detalles.setInteres(interes);
        detalles.setSaldo(saldo);

        estadoDia.setTarjeta(tarjeta);
        estadoDia.setDetalles(detalles);

        return estadoDia;
    }

    private void inicializar() {
        Date fecha;
        fecha = deudor.getFechaInicial();
        T primerDia = new T();
        primerDia.setDia(fecha);
        for(Tarjeta tarjeta : deudor.getTarjetas()) {
            EstadoDia ed = new EstadoDia();
            ed.setTarjeta(tarjeta);
            DetallesTarjetaDia dtd = new DetallesTarjetaDia();
            dtd.setAbonos(new BigDecimal(0));
            dtd.setCargos(new BigDecimal(0));
            dtd.setInteres(new BigDecimal(0));
            dtd.setSaldo(tarjeta.getCapital());
            ed.setDetalles(dtd);
            primerDia.getEstados().put(tarjeta, ed);
        }
        t.add(primerDia);
    }


}
