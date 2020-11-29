package com.example.mvvm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm.databinding.FragmentMiHipotecaBinding;

public class MiHipotecaFragment extends Fragment {
     private FragmentMiHipotecaBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                return (binding = FragmentMiHipotecaBinding.inflate(inflater, container, false)).getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final MiHipotecaViewModel miHipotecaViewModel = new ViewModelProvider(this).get(MiHipotecaViewModel.class);

        binding.calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int year = Integer.parseInt(binding.year.getText().toString());
                int mes = Integer.parseInt(binding.mes.getText().toString());

                miHipotecaViewModel.calcular(year, mes);
            }
        });

        miHipotecaViewModel.edad.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer edad) {
                //calcular edad
                binding.edad.setText(String.valueOf("Tienes " + edad + " años!"));
            }
        });


        miHipotecaViewModel.errorYearMin.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer yearMinimo) {
                if (yearMinimo != null){
                    binding.year.setError("NO puede ser MENOR al año " + yearMinimo);
                } else{
                    binding.edad.setError(null);
                }
            }
        });
        miHipotecaViewModel.errorYearMax.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer yearMaximo) {
                if (yearMaximo != null){
                    binding.year.setError("NO puede ser MAYOR al año " + yearMaximo);
                } else{
                    binding.edad.setError(null);
                }
            }
        });
        miHipotecaViewModel.errorMesMin.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer mesMinimo) {
                if (mesMinimo != null) {
                    binding.mes.setError("NO puede ser MENOR al mes " + mesMinimo);
                } else {
                    binding.mes.setError(null);
                }
            }
        });

        miHipotecaViewModel.errorMesMax.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer mesMaximo) {
                if (mesMaximo != null) {
                    binding.mes.setError("NO puede ser MAYOR al mes " + mesMaximo);
                } else {
                    binding.mes.setError(null);
                }
            }
        });
        miHipotecaViewModel.calculando.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean calculando) {
                if (calculando){
                    binding.calculando.setVisibility(View.VISIBLE);
                    binding.edad.setVisibility(View.GONE);
                }else{
                    binding.calculando.setVisibility(view.GONE);
                    binding.edad.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}