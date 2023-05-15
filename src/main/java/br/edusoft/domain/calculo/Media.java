package br.edusoft.domain.calculo;

import br.edusoft.domain.aluno.Aluno;
import br.edusoft.domain.aluno.Nota;

public class Media extends Calculo {

  @Override
  protected String realizarCalculo(Aluno aluno) {
    double soma = 0;
    for (double nota : aluno.nota) {
        soma += nota;
    }
    double media = soma / aluno.nota.size();

    if (media >= 7.0) {
        return "AP";
    } else {
        return "RM";
    }
  }
}
