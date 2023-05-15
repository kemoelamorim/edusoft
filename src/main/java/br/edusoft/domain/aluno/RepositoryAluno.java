package br.edusoft.domain.aluno;

import java.util.List;

public interface RepositoryAluno {
  public void geraToken();
  public List<Aluno> listarTodosOsAlunos();
  public void enviaResultado(Aluno aluno);
}
