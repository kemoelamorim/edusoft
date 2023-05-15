package br.edusoft.infra.aluno.service;

import java.io.IOException;

import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

import java.util.List;
import br.edusoft.domain.aluno.Aluno;
import br.edusoft.domain.aluno.RepositoryAluno;
import br.edusoft.domain.aluno.Resultado;
import br.edusoft.domain.calculo.Calculador;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class SeviceExternoAluno implements RepositoryAluno{
  public String token;
  
  private static final String URL_BASE = "http://desenvolvimento.edusoft.inf.br/desenvolvimentoMentorWebG5/rest/servicoexterno/execute/";

  private static final String urlToken = "https://desenvolvimento.edusoft.inf.br/desenvolvimentoMentorWebG5/rest/servicoexterno/token/recuperaAlunos";

  private static final String username = "mentor";
  private static final String password = "123456";
  @Override
  public void geraToken() {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
        .url(urlToken)
        .get()
        .addHeader("usuario", username)
        .addHeader("senha", password)
        .build();
        try {
          Response response = client.newCall(request).execute();
          String responseBody = response.body().string();
          token = responseBody;
        } catch (IOException e) {
          e.printStackTrace();
        }
  }

  @Override
  public List<Aluno> listarTodosOsAlunos() {
    CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(URL_BASE + "recuperaAlunos");
        httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        CloseableHttpResponse response;
        try {
          response = httpClient.execute(httpGet);
          System.out.println(response.toString());
          response.close();
          httpClient.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    return null;
  }

  
  @Override
  public void enviaResultado(Aluno aluno) {
    String url = "http://desenvolvimento.edusoft.inf.br/desenvolvimentoMentorWebG5/rest/servicoexterno/execute/gravaResultado";
    MediaType mediaType = MediaType.parse("application/json");
    Resultado resultado = new Resultado();
    resultado.setMedia(aluno.calculaMedia());
    resultado.setResultado(aluno.calculaResultado());
    resultado.setCod(aluno.cod);
    resultado.setNome(aluno.nome);

    // Converte o objeto em um JSON
    Gson gson = new Gson();
    String json = gson.toJson(resultado);

    // Cria o corpo da requisição com o JSON
    RequestBody body = RequestBody.create(mediaType, json);
  }
  private final OkHttpClient client = new OkHttpClient();

  public Aluno createAluno(Aluno aluno) throws IOException {
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(mediaType, aluno.toJsonString());
    Request request = new Request.Builder()
            .url(URL_BASE + "/alunos")
            .post(body)
            .build();
    try (Response response = client.newCall(request).execute()) {
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        JSONObject jsonResponse = new JSONObject(response.body().string());
        return Aluno.fromJson(jsonResponse);
    }
}

public Aluno getAlunoById(int id) throws IOException {
    Request request = new Request.Builder()
            .url(URL_BASE + "/alunos/" + id)
            .get()
            .build();
    try (Response response = client.newCall(request).execute()) {
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        JSONObject jsonResponse = new JSONObject(response.body().string());
        return Aluno.fromJson(jsonResponse);
    }
}

public List<Aluno> getAllAlunos() throws IOException {
    Request request = new Request.Builder()
            .url(URL_BASE + "/alunos")
            .get()
            .build();
    try (Response response = client.newCall(request).execute()) {
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        JSONArray jsonResponse = new JSONArray(response.body().string());
        List<Aluno> alunos = new ArrayList<>();
        for (int i = 0; i < jsonResponse.length(); i++) {
            JSONObject jsonObject = jsonResponse.getJSONObject(i);
            Aluno aluno = Aluno.fromJson(jsonObject);
            alunos.add(aluno);
        }
        return alunos;
    }
}

public Aluno updateAluno(Aluno aluno) throws IOException {
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(mediaType, aluno.toJsonString());
    Request request = new Request.Builder()
            .url(URL_BASE + "/alunos/" + aluno.cod)
            .put(body)
            .build();
    try (Response response = client.newCall(request).execute()) {
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        JSONObject jsonResponse = new JSONObject(response.body().string());
        return Aluno.fromJson(jsonResponse);
    }
}

public void deleteAluno(int id) throws IOException {
    Request request = new Request.Builder()
            .url(URL_BASE + "/alunos/" + id)
            .delete()
            .build();
    try (Response response = client.newCall(request).execute()) {
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
    }
  }
}
