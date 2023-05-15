package br.edusoft.domain.calculo;

import br.edusoft.domain.aluno.Aluno;

public class Frequencia extends Calculo{

  @Override
  protected String realizarCalculo(Aluno aluno) {
   

    double percentualFrequencia = ((aluno.totalDeAulas - aluno.falta) / (double) aluno.totalDeAulas) * 100;

    if (percentualFrequencia < 70) {
        return "RF";
    } else {
        return aluno.calculaMedia();
    }
  }
  
}
