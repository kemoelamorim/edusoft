package br.edusoft.domain.aluno;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.edusoft.domain.calculo.Media;

public class Aluno {
    public String cod;
    public String nome;
    public int totalDeAulas;
    public List<Double> nota;
    public int falta;

    public String calculaMedia(){
      return new Media().calcular(this);
    }
    public String calculaResultado(){
      return "";
    }
    public String toJsonString() {
      JSONObject json = new JSONObject();
      json.put("cod", this.cod);
      json.put("nome", this.nome);
      json.put("totalDeAulas", this.totalDeAulas);
      return json.toString();
    }
    public static Aluno fromJson(JSONObject jsonResponse) {
      JSONObject jsonObject = new JSONObject(jsonResponse);
      String codInserte = jsonObject.getString("cod");
      String nome = jsonObject.getString("nome");
      Aluno aluno = new Aluno();
      aluno.cod = codInserte;
      aluno.nome = nome;
 

      return aluno;
  }
  
}
