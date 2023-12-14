package com.paolacristine.prjEmpresa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paolacristine.prjEmpresa.entities.Departamento;
import com.paolacristine.prjEmpresa.entities.Funcionario;
import com.paolacristine.prjEmpresa.repositorios.FuncionarioRepositorio;

@Service
public class FuncionarioService {

	
	
	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;

	public List<Funcionario> getAllFuncionarios() {
		return funcionarioRepositorio.findAll();
	}

	public Funcionario getFuncionarioById(long funcodigo) {
		return funcionarioRepositorio.findById(funcodigo).orElse(null);
	}

	public Funcionario saveFuncionario(Funcionario funcionario) {
		return funcionarioRepositorio.save(funcionario);
	}

	public List<Funcionario> getFuncionariosByFunnomeAproximado(String funnome) {
        return funcionarioRepositorio.findByNomeContaining(funnome);
    }

	public boolean deleteFuncionario(Long id) {
		Optional<Funcionario> funcionarioExistente = funcionarioRepositorio.findById(id);
		if (funcionarioExistente.isPresent()) {
			funcionarioRepositorio.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public Funcionario updateFuncionario(Long funcodigo, Funcionario novoFuncionario) {
		Optional<Funcionario> funcionarioOptional = funcionarioRepositorio.findById(funcodigo);
		if (funcionarioOptional.isPresent()) {
			Funcionario funcionarioExistente = funcionarioOptional.get();
			funcionarioExistente.setFunnome(novoFuncionario.getFunnome());
			funcionarioExistente.setFunnascimento(novoFuncionario.getFunnascimento());
			funcionarioExistente.setFunsalario(novoFuncionario.getFunsalario());

			// Atualize o departamento
			if (novoFuncionario.getDepartamento() != null) {
				funcionarioExistente.setDepartamento(novoFuncionario.getDepartamento());
			}		
			return funcionarioRepositorio.save(funcionarioExistente);
		} else {
			return null; // Ou lançar uma exceção indicando que o funcionário não foi encontrado
		}
	}
  	

}
