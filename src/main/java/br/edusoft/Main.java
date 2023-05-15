package br.edusoft;

import br.edusoft.aplicalcao.aluno.BuscadorDeAlunos;
import br.edusoft.infra.aluno.service.SeviceExternoAluno;

public class Main {
    public static void main(String[] args) {
        BuscadorDeAlunos alunos = new BuscadorDeAlunos(new SeviceExternoAluno());
        alunos.listaAlunosMatriculados();
        
    }
}
