package rh.service;

import rh.ValidacaoException;
import rh.model.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ValidacaoPeriodicidadeReajuste implements ValidacaoReajuste{
    @Override
    public void validar(Funcionario funcionario, BigDecimal aumento) {
        LocalDate dataUltimoReajuste = funcionario.getDataUltimoReajuste();
        LocalDate dataAtual = LocalDate.now();
        long mesesUltimoReajuste = ChronoUnit.MONTHS.between(dataUltimoReajuste,dataAtual);
        if(mesesUltimoReajuste < 6){
            throw new ValidacaoException("Intervalo entre reajustes não pode ser menor que seis meses!");
        }
    }
}
