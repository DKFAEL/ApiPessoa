package com.example.demo.Dto;

import java.time.LocalDate;
import java.util.List;

public class PessoaDTO {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private List<EnderecoDTO> enderecos;

    // Construtores


    public PessoaDTO(Long id, String nome, LocalDate dataNascimento, List<EnderecoDTO> enderecos) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.enderecos = enderecos;
    }

    // getters e setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoDTO> enderecos) {
        this.enderecos = enderecos;
    }
}
