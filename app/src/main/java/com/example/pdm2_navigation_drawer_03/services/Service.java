package com.example.pdm2_navigation_drawer_03.services;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pdm2_navigation_drawer_03.pojo.Alternativa;
import com.example.pdm2_navigation_drawer_03.pojo.Questao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Service extends AppCompatActivity {


    private String TAG = Service.class.getSimpleName();
    private static String URL = "https://my-json-server.typicode.com/mateuscostacl/pdm2-navigation-drawer-03/perguntas";
    Questao questao = new Questao();
    List<Questao> questaoList = new ArrayList<>();

    public List<Questao> getPerguntas() {
        List<Questao> questao = null;
        try {
            return new PerguntaService().execute(this.URL).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return questao;
    }

    private class PerguntaService extends AsyncTask<String, Void, List<Questao>> {

        @Override
        protected List<Questao> doInBackground(String... params) {

            HttpHandler sh = new HttpHandler();

            String jsonStr = sh.usarServico(params[0]);

            Log.e(TAG, "Endereço da URL: " + params[0]);
            Log.e(TAG, "Resposta da URL: " + jsonStr);

            Alternativa alternativa = new Alternativa();

            if (jsonStr != null) {
                try {

                    JSONArray jsonArray = new JSONArray(jsonStr);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject q = jsonArray.getJSONObject(i);

                        questao.setQuestao(q.getString("questao"));

                        JSONArray jsonAlt = q.getJSONArray("alternativas");
                        for (int j = 0; i < jsonAlt.length(); i++) {
                            JSONObject alt = jsonAlt.getJSONObject(i);

                            alternativa.setTexto(alt.getString("texto"));
                            alternativa.setCerto(alt.getInt("certo"));

                            questao.getAlternativas().add(alternativa);
                        }
                        questaoList.add(questao);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "JSON erro: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Service.this,
                                    "JSON erro: " + e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
            return questaoList;
        }
    }
}
