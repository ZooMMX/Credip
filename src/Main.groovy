package Credip

import Credip.BL.Procesador
import Credip.Model.Deudor
import Credip.Model.Tarjeta
import Credip.Model.Pago
import Credip.Model.T
import Credip.Model.EstadoDia
import java.text.SimpleDateFormat
import java.text.NumberFormat;
/**
 * Created with IntelliJ IDEA.
 * User: Octavio
 * Date: 19/01/13
 * Time: 11:49 PM
 * To change this template use File | Settings | File Templates.
 */
class Main {
    public static void main(String[] args) {
        Procesador procesador;
        Deudor deudor = generarDeudor();
        procesador = new Procesador(deudor);
        procesador.ejecutar();
        print(procesador);
    }

    private static void print(Procesador procesador) {
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd/MM/yyyy");

        NumberFormat nf = NumberFormat.getCurrencyInstance();

        for (T t in procesador.t) {
            Calendar c = Calendar.getInstance();
            c.setTime(t.dia);

            for (EstadoDia estado in t.estados.values()) {
                if (estado.tarjeta.diaCorte == c.get(Calendar.DAY_OF_MONTH) || estado.tarjeta.diaPago == c.get(Calendar.DAY_OF_MONTH))
                    println String.format("%-30s", "${df.format(t.dia)} [${estado.tarjeta.descripcion}]") +
                            String.format("%20s", "Cargos: ${nf.format(estado.detalles.cargos)}")+
                            String.format("%20s", "Abonos: ${nf.format(estado.detalles.abonos)}")+
                            String.format("%20s", "Interés: ${nf.format(estado.detalles.interes)}")+
                            String.format("%20s", "Saldo: ${nf.format(estado.detalles.saldo)}");
            }
        }
    }

    static Deudor generarDeudor() {
        Deudor d;
        Tarjeta t1;
        d = new Deudor(
                fechaInicial: Calendar.getInstance().getTime(),
                tarjetas: [
                    t1 = new Tarjeta(
                            id: 0,
                            descripcion: "Banorte",
                            lineaCredito: 5000,
                            capital: 5000.50,
                            tasa: 50,
                            porcentajeMinimo: 5,
                            diaCorte: 20,
                            diaPago: 25,
                            pagos: [
                                new Pago(cantidad: 1200, modo: Pago.MODO.EFECTIVO)
                            ]
                    ),
                    new Tarjeta(
                            id: 1,
                            descripcion: "IXE",
                            lineaCredito: 5000,
                            capital: 5000.50,
                            tasa: 50,
                            porcentajeMinimo: 5,
                            diaCorte: 20,
                            diaPago: 25,
                            pagos: [
                                    new Pago(cantidad: 300, modo: Pago.MODO.EFECTIVO),
                                    new Pago(cantidad: 50, modo: Pago.MODO.TARJETA, origen: t1)
                            ]
                    ),
                    new Tarjeta(
                            id: 1,
                            descripcion: "Santander",
                            lineaCredito: 5000,
                            capital: 5000.50,
                            tasa: 50,
                            porcentajeMinimo: 5,
                            diaCorte: 20,
                            diaPago: 25,
                            pagos: [
                                    new Pago(cantidad: Pago.PAGO_MINIMO, modo: Pago.MODO.EFECTIVO),
                                    new Pago(cantidad: 100, modo: Pago.MODO.EFECTIVO)
                            ]
                    ),
                    new Tarjeta(
                            id: 1,
                            descripcion: "HSBC",
                            lineaCredito: 50000,
                            capital: 19000.50,
                            tasa: 50,
                            porcentajeMinimo: 5,
                            diaCorte: 20,
                            diaPago: 25,
                            pagos: [
                                    new Pago(cantidad: Pago.PAGO_DISPONIBLE_80_PORC, modo: Pago.MODO.TARJETA, origen: t1)
                            ]
                    )
                ]
        );
    }
}
