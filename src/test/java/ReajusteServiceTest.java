import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rh.ValidacaoException;
import rh.model.Cargo;
import rh.model.Funcionario;
import rh.service.ReajusteService;
import rh.service.ValidacaoPercentualReajuste;
import rh.service.ValidacaoPeriodicidadeReajuste;
import rh.service.ValidacaoReajuste;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ReajusteServiceTest {

    private List<ValidacaoReajuste> validacoes;
    private Funcionario funcionario;

    @BeforeEach
    private void setUp(){
        validacoes = new ArrayList<>();
        validacoes.add(new ValidacaoPercentualReajuste());
        validacoes.add(new ValidacaoPeriodicidadeReajuste());
        funcionario = new Funcionario
                ("Rogério","074.066.199-01", Cargo.ANALISTA,new BigDecimal("10000"), LocalDate.now().minus(7, ChronoUnit.MONTHS));

    }
    @Test
    public void testAjusteSalarialValidoDezPorCento(){ReajusteService reajusteService = new ReajusteService(validacoes);
        reajusteService.reajustarSalarioDoFuncionario(funcionario,new BigDecimal("1000"));

        Assertions.assertEquals(new BigDecimal("11000"),funcionario.getDadosPessoais().getSalario());

    }

    @Test
    public void testAjusteSalarialInvalidoCinquentaPorCento(){
        ReajusteService reajusteService = new ReajusteService(validacoes);

        Assertions.assertThrows(ValidacaoException.class, () ->
                reajusteService.reajustarSalarioDoFuncionario(funcionario,new BigDecimal("5000")));

    }

    @Test
    public void testAjusteSalarialQuarentaPorCento(){
        ReajusteService reajusteService = new ReajusteService(validacoes);
        reajusteService.reajustarSalarioDoFuncionario(funcionario,new BigDecimal("4000"));

        Assertions.assertEquals(new BigDecimal("14000"),funcionario.getDadosPessoais().getSalario());

    }

    @Test
    public void testAjusteSalarialDataMaiorQueSeisMeses(){
        ReajusteService reajusteService = new ReajusteService(validacoes);
        reajusteService.reajustarSalarioDoFuncionario(funcionario,new BigDecimal("4000"));
        Assertions.assertEquals(LocalDate.now(),funcionario.getDataUltimoReajuste());
    }

    @Test
    public void testAjusteSalarialDataMenorQueSeisMeses(){
        funcionario.setDataUltimoReajuste(LocalDate.now());
        ReajusteService reajusteService = new ReajusteService(validacoes);
        Assertions.assertThrows(ValidacaoException.class, () ->
                reajusteService.reajustarSalarioDoFuncionario(funcionario,new BigDecimal("4000")));

    }

    @Test
    public void testAjusteSalarialDataIgualeSeisMeses(){
        LocalDate dataAjuste = LocalDate.now().minus(6, ChronoUnit.MONTHS);
        funcionario.setDataUltimoReajuste(dataAjuste);
        ReajusteService reajusteService = new ReajusteService(validacoes);
        reajusteService.reajustarSalarioDoFuncionario(funcionario,new BigDecimal("4000"));
        Assertions.assertEquals(LocalDate.now(),funcionario.getDataUltimoReajuste());
    }
}
