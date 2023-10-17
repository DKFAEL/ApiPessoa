package com.example.demo.Service;

import com.example.demo.Domain.Endereco;
import com.example.demo.Domain.Pessoa;
import com.example.demo.Repository.EnderecoRepository;
import com.example.demo.Repository.PessoaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PopulateService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @PostConstruct
    public void populateData() {

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("João");
        pessoa1.setDataNascimento(LocalDate.of(1985, 3, 15));
        pessoaRepository.save(pessoa1);


        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Lucia");
        pessoa2.setDataNascimento(LocalDate.of(1995, 4, 24));
        pessoaRepository.save(pessoa2);

        Endereco endereco1Pessoa1 = new Endereco();
        endereco1Pessoa1.setLogradouro("Rua 1");
        endereco1Pessoa1.setNumero("123");
        endereco1Pessoa1.setCep("40720-375");
        endereco1Pessoa1.setCidade("São Paulo");
        endereco1Pessoa1.setPrincipal(true);
        endereco1Pessoa1.setPessoa(pessoa1);
         enderecoRepository.save(endereco1Pessoa1);

        Endereco endereco2Pessoa1 = new Endereco();
        endereco2Pessoa1.setLogradouro("Rua 2");
        endereco2Pessoa1.setNumero("456");
        endereco2Pessoa1.setCep("40720-344");
        endereco2Pessoa1.setCidade("São Paulo");
        endereco2Pessoa1.setPrincipal(false);
        endereco2Pessoa1.setPessoa(pessoa1);
         enderecoRepository.save(endereco2Pessoa1);

        Endereco endereco1Pessoa2 = new Endereco();
        endereco1Pessoa2.setLogradouro("Rua A");
        endereco1Pessoa2.setNumero("789");
        endereco1Pessoa2.setCep("40720-333");
        endereco1Pessoa2.setCidade("Rio de Janeiro");
        endereco1Pessoa2.setPrincipal(false);
        endereco1Pessoa2.setPessoa(pessoa2);
         enderecoRepository.save( endereco1Pessoa2);

        Endereco endereco2Pessoa2 = new Endereco();
        endereco2Pessoa2.setLogradouro("Rua F");
        endereco2Pessoa2.setNumero("789");
        endereco2Pessoa2.setCep("40720-111");
        endereco2Pessoa2.setCidade("Rio de Janeiro");
        endereco2Pessoa2.setPrincipal(true);
        endereco2Pessoa2.setPessoa(pessoa2);
         enderecoRepository.save(endereco2Pessoa2);
    }
}


