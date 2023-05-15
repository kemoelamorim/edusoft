package br.edusoft.domain.calculo;

import br.edusoft.domain.aluno.Aluno;

public class Calculador {
  public String calcular(Aluno aluno, Calculo calculo){
    return calculo.calcular(aluno);
  }
}
