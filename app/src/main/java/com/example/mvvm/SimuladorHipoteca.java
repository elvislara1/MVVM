package com.example.mvvm;

public class SimuladorHipoteca {

    public static class Solicitud {
        public int año;
        public int mes;

        public Solicitud(int año, int mes) {
            this.año = año;
            this.mes = mes;
        }
    }

    interface Callback {
        void cuandoHayaErrorDeYearMin(int yearMinimo);
        void cuandoHayaErrorDeYearMax(int yearMaximo);

        void cuandoHayaErrorDeMesMin(int mesMinimo);
        void cuandoHayaErrorDeMesMax(int mesMaximo);


        void cuandoEmpieceElCalculo();
        void cuandoFinaliceElCalculo();

        void cuandoEsteCalculadaLaEdad(int resultado);
    }


    public void calcular(Solicitud solicitud, Callback callback) {
        int yearMinimo = 0;
        int mesMinimo = 0;

        int yearMaximo = 0;
        int mesMaximo = 0;
        int mesActual = 0;

        int resultado = 0;
        callback.cuandoEmpieceElCalculo();
        // resto de sentencias


        try {
            Thread.sleep(2500);  // long run operation
            yearMinimo = 1900;
            mesMinimo = 1;

            yearMaximo = 2020;
            mesMaximo = 12;
            mesActual = 12;
            resultado = yearMaximo - solicitud.año;

        } catch (InterruptedException e) {}
        boolean error = false;
        if (solicitud.año < yearMinimo) {
            callback.cuandoHayaErrorDeYearMin(yearMinimo);
            error = true;
        }

        if (solicitud.año > yearMaximo){
            callback.cuandoHayaErrorDeYearMax(yearMaximo);
            error = true;
        }
        if (solicitud.mes < mesMinimo) {
            callback.cuandoHayaErrorDeMesMin(mesMinimo);
            error = true;
        }
        if (solicitud.mes > mesMaximo){
            callback.cuandoHayaErrorDeMesMax(mesMaximo);
            error = true;
        }
        if(!error) {
            if (solicitud.mes > mesActual){
                resultado = resultado - 1;
                callback.cuandoEsteCalculadaLaEdad(resultado);
            }else {
                callback.cuandoEsteCalculadaLaEdad(resultado);
            }
        }
        callback.cuandoFinaliceElCalculo();
    }
}
