package rh.service;

import rh.ValidacaoException;
import rh.model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

public class ReajusteService {
    private List<ValidacaoReajuste> validacoes;

    public ReajusteService(List<ValidacaoReajuste> validacoes){
        this.validacoes = validacoes;
    }
    public void reajustarSalarioDoFuncionario(Funcionario funcionario, BigDecimal aumento) {
        validacoes.forEach(v -> v.validar(funcionario,aumento));
        funcionario.atualizarSalario(funcionario.getDadosPessoais().getSalario().add(aumento));

    }
}
