package com.example.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MiHipotecaViewModel extends AndroidViewModel {
    Executor executor;

    SimuladorHipoteca simulador;

    //result
    MutableLiveData<Integer> edad = new MutableLiveData<Integer>();

    //errores min y max de año/mes
    MutableLiveData<Integer> errorYearMin = new MutableLiveData<Integer>();
    MutableLiveData<Integer> errorYearMax = new MutableLiveData<Integer>();
    MutableLiveData<Integer> errorMesMin = new MutableLiveData<Integer>();
    MutableLiveData<Integer> errorMesMax = new MutableLiveData<>();

    //calculando
    MutableLiveData<Boolean> calculando = new MutableLiveData<Boolean>();

    public MiHipotecaViewModel(@NonNull Application application) {
        super(application);

        executor = Executors.newSingleThreadExecutor();
        simulador = new SimuladorHipoteca();
    }

    public void calcular(int year, int mes) {

        final SimuladorHipoteca.Solicitud solicitud = new SimuladorHipoteca.Solicitud(year, mes);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                simulador.calcular(solicitud, new SimuladorHipoteca.Callback() {


                    @Override
                    public void cuandoHayaErrorDeYearMin(int yearMinimo) {
                        errorYearMin.postValue(yearMinimo);
                    }

                    @Override
                    public void cuandoHayaErrorDeYearMax(int yearMaximo) {
                        errorYearMax.postValue(yearMaximo);
                    }


                    @Override
                    public void cuandoHayaErrorDeMesMin(int mesMinimo) {
                        errorMesMin.postValue(mesMinimo);
                    }

                    @Override
                    public void cuandoHayaErrorDeMesMax(int mesMaximo) {
                        errorMesMax.postValue(mesMaximo);
                    }

                    @Override
                    public void cuandoEmpieceElCalculo() {
                        calculando.postValue(true);
                    }

                    @Override
                    public void cuandoFinaliceElCalculo() {
                        calculando.postValue(false);
                    }

                    @Override
                    public void cuandoEsteCalculadaLaEdad(int resultado) {
                        errorYearMin.postValue(null);
                        errorYearMax.postValue(null);
                        errorMesMax.postValue(null);
                        errorMesMin.postValue(null);

                        edad.postValue(resultado);
                    }
                });
            }
        });
    }
}
