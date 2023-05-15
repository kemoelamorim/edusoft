package br.edusoft.aplicalcao.aluno;

import java.util.List;

import br.edusoft.domain.aluno.Aluno;
import br.edusoft.domain.aluno.RepositoryAluno;

public class BuscadorDeAlunos {
  private RepositoryAluno repository;
  public BuscadorDeAlunos(RepositoryAluno repository){
    this.repository = repository;
    this.repository.geraToken();
  }

  public List<Aluno> listaAlunosMatriculados(){
    return repository.listarTodosOsAlunos();
  }
  public void geraResultado(){
    repository.enviaResultado(new Aluno());
  }
}
