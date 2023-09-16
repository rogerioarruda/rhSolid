package rh.service;

import rh.ValidacaoException;
import rh.model.Cargo;
import rh.model.Funcionario;

public class PromocaoService {

    public void Promover(Funcionario funcionario, boolean metaBatida){
        if (Cargo.GERENTE == funcionario.getDadosPessoais().getCargo()){
            throw new ValidacaoException("Gerentes não podem ser promovidos!");
        }
        if (metaBatida){
            Cargo novoCargo = funcionario.getDadosPessoais().getCargo().getProximoCargo();
            funcionario.promover(novoCargo);
        } else {
            throw  new ValidacaoException("Funcionario não atingiu a meta!");
        }
    }
}
