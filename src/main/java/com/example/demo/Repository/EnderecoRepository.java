package com.example.demo.Repository;

import com.example.demo.Domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    List<Endereco> findByPessoaId(Long pessoaId);
    Optional<Endereco> findByPessoaIdAndPrincipalIsTrue(Long pessoaId);
}
