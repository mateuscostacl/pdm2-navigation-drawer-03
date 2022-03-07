package com.example.pdm2_navigation_drawer_03.pojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Questao {

    @SerializedName("questao")
    @Expose
    private String questao;
    @SerializedName("alternativas")
    @Expose
    private List<Alternativa> alternativas = null;

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Questao.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("questao");
        sb.append('=');
        sb.append(((this.questao == null) ? "<null>" : this.questao));
        sb.append(',');
        sb.append("alternativas");
        sb.append('=');
        sb.append(((this.alternativas == null) ? "<null>" : this.alternativas));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
