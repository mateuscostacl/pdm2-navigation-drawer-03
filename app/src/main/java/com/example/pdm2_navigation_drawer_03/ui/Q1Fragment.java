package com.example.pdm2_navigation_drawer_03.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.pdm2_navigation_drawer_03.R;
import com.example.pdm2_navigation_drawer_03.pojo.Alternativa;
import com.example.pdm2_navigation_drawer_03.pojo.Questao;
import com.example.pdm2_navigation_drawer_03.services.Service;

import java.util.List;

public class Q1Fragment extends Fragment {

    TextView tvPergunta;
    TextView tvA1;
    TextView tvA2;
    TextView tvA3;

    int idx = -1;
    int resultado = 0;

    private Service service;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_q1, container, false);

        conectarComViewport(root);
        enviarPerguntaPraTela(idx++);


        return root;
    }

    private void conectarComViewport(View root){
        tvPergunta = root.findViewById(R.id.tv_pergunta);
        tvA1 = root.findViewById(R.id.tv_a1);
        tvA2 = root.findViewById(R.id.tv_a2);
        tvA3 = root.findViewById(R.id.tv_a3);
    }

    private List<Questao> perguntas(){
        return service.getPerguntas();
    }

    private void enviarPerguntaPraTela(int i){

        if(i >= 3){

        }

        List<Questao> perguntas = perguntas();
        tvPergunta.setText(perguntas.get(i).getQuestao());

        List<Alternativa> alternativas = perguntas.get(i).getAlternativas();
        tvA1.setText(alternativas.get(0).getTexto());
        tvA1.setTag(alternativas.get(0).getCerto());

        tvA2.setText(alternativas.get(1).getTexto());
        tvA2.setTag(alternativas.get(1).getCerto());

        tvA3.setText(alternativas.get(2).getTexto());
        tvA3.setTag(alternativas.get(2).getCerto());

        idx += 1;
    }

    public void resposta(View v){
        resultado += Integer.parseInt(v.getTag().toString());
        enviarPerguntaPraTela(idx++);
    }

}