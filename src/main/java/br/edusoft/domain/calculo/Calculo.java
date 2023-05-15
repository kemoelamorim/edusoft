package br.edusoft.domain.calculo;

import br.edusoft.domain.aluno.Aluno;

public abstract class Calculo {
  private Calculo outroCalculo;

  protected abstract String realizarCalculo(Aluno aluno);
  public String calcular(Aluno aluno){
    String valor = realizarCalculo(aluno);
    String valorOutroCalculo = "";
    if(outroCalculo != null){
      valorOutroCalculo = outroCalculo.realizarCalculo(aluno);
    }
    return valor = valorOutroCalculo;
  }
}
