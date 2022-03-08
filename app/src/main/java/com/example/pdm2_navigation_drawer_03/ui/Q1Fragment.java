package com.example.pdm2_navigation_drawer_03.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.airbnb.lottie.LottieAnimationView;
import com.example.pdm2_navigation_drawer_03.MainActivity;
import com.example.pdm2_navigation_drawer_03.R;
import com.example.pdm2_navigation_drawer_03.pojo.Alternativa;
import com.example.pdm2_navigation_drawer_03.pojo.Questao;
import com.example.pdm2_navigation_drawer_03.services.Service;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Q1Fragment extends Fragment {

    View root;

    TextView tvPergunta;
    TextView tvA1;
    TextView tvA2;
    TextView tvA3;

    LottieAnimationView avAnimacao;

    int idx = -1;
    int resultado = 0;

    private Service service;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_q1, container, false);

        conectarComViewport(root);
        escutarCliques();

        idx = -1;
        enviarPerguntaPraTela(++idx);


        return root;
    }

    private void conectarComViewport(View root){
        tvPergunta = root.findViewById(R.id.tv_pergunta);
        tvA1 = root.findViewById(R.id.tv_a1);
        tvA2 = root.findViewById(R.id.tv_a2);
        tvA3 = root.findViewById(R.id.tv_a3);
    }

    private void escutarCliques(){
        tvA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resposta(view);
            }
        });
        tvA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resposta(view);
            }
        });
        tvA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resposta(view);
            }
        });
    }

    private List<Questao> perguntas(){
        service = new Service();
        return service.getPerguntas();
    }

    private void enviarPerguntaPraTela(int i){
//        Log.e(Q1Fragment.class.getSimpleName(), "###### "+ i);

        if(i >= 3){
            mostrarMensagemAlertDialog();
//            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new Q2Fragment()).commit();
//            Navigation.findNavController(root.getRootView()).navigate(R.id.nav_q2);
//            getParentFragmentManager().beginTransaction().replace(R.id.fragment_layout, new Q2Fragment()).commit();
            return;
        }

        List<Questao> perguntas = new ArrayList<>();
        perguntas = perguntas();
        tvPergunta.setText(perguntas.get(i).getQuestao());

        List<Alternativa> alternativas = new ArrayList<>();
        alternativas = perguntas.get(i).getAlternativas();
        tvA1.setText(alternativas.get(0).getTexto());
        tvA1.setTag(alternativas.get(0).getCerto());

        tvA2.setText(alternativas.get(1).getTexto());
        tvA2.setTag(alternativas.get(1).getCerto());

        tvA3.setText(alternativas.get(2).getTexto());
        tvA3.setTag(alternativas.get(2).getCerto());
    }

    public void resposta(View v){
        resultado += Integer.parseInt(v.getTag().toString());
        enviarPerguntaPraTela(++idx);
    }


    public void mostrarMensagemAlertDialog(){

        if(resultado <= 1) {
            Snackbar.make(getView(), "Você precisa estudar mais.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            MainActivity.animar(R.raw.estudando);
        }
        if(resultado == 2) {
            Snackbar.make(getView(), "Parabéns, você está no caminho certo!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            MainActivity.animar(R.raw.crianca_feliz);
        }
        if(resultado == 3) {
            Snackbar.make(getView(), "Muito bem, você é \\\"quase\\\" um gênio!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            MainActivity.animar(R.raw.trofeu);
        }
    }

    @Override
    public void onDestroyView() {
        MainActivity.desanimar();
        super.onDestroyView();
    }
}