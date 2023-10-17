

package com.example.demo.Service;

import com.example.demo.Domain.Endereco;
import com.example.demo.Domain.Pessoa;
import com.example.demo.Dto.EnderecoDTO;
import com.example.demo.Dto.PessoaDTO;
import com.example.demo.Repository.EnderecoRepository;
import com.example.demo.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;


    public PessoaDTO criarPessoa(PessoaDTO pessoaDTO) {
        Pessoa pessoa = convertPessoaDTOToEntity(pessoaDTO); // Converta o DTO para uma entidade Pessoa
        Pessoa pessoaCriada = pessoaRepository.save(pessoa);
        return convertPessoaEntityToDTO(pessoaCriada); // Converta a entidade criada de volta para um DTO
    }


    public PessoaDTO editarPessoa(Long id, PessoaDTO pessoaDTO) {
        Optional<Pessoa> pessoaExistenteOptional = pessoaRepository.findById(id);
        if (pessoaExistenteOptional.isPresent()) {
            Pessoa pessoaExistente = pessoaExistenteOptional.get();
            Pessoa pessoaEditada = convertPessoaDTOToEntity(pessoaDTO);
            pessoaEditada.setId(id);
            pessoaExistente = pessoaRepository.save(pessoaExistente);
            return convertPessoaEntityToDTO(pessoaExistente);
        }
        return null;
    }


    public PessoaDTO consultarPessoa(Long id) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        if (pessoaOptional.isPresent()) {
            return convertPessoaEntityToDTO(pessoaOptional.get());
        }
        return null;
    }

    public List<PessoaDTO> listarPessoas() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoas.stream().map(this::convertPessoaEntityToDTO).collect(Collectors.toList());
    }

    private PessoaDTO convertPessoaEntityToDTO(Pessoa pessoa) {
        return new PessoaDTO(pessoa.getId(), pessoa.getNome(), pessoa.getDataNascimento(), convertEnderecosToDTOs(pessoa.getEnderecos()));
    }

    private List<EnderecoDTO> convertEnderecosToDTOs(List<Endereco> enderecos) {
        return enderecos.stream().map(this::convertEnderecoToDTO).collect(Collectors.toList());
    }

    private EnderecoDTO convertEnderecoToDTO(Endereco endereco) {
        return new EnderecoDTO(endereco.getId(), endereco.getLogradouro(), endereco.getCep(), endereco.getNumero(), endereco.getCidade(), endereco.isPrincipal());
    }

    private Pessoa convertPessoaDTOToEntity(PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaDTO.getId());
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setDataNascimento(pessoaDTO.getDataNascimento());
        pessoa.setEnderecos(convertEnderecosToEntities(pessoaDTO.getEnderecos()));
        return pessoa;
    }

    private List<Endereco> convertEnderecosToEntities(List<EnderecoDTO> enderecoDTOs) {
        return enderecoDTOs.stream().map(this::convertEnderecoToEntity).collect(Collectors.toList());
    }

    private Endereco convertEnderecoToEntity(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setId(enderecoDTO.getId());
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setPrincipal(enderecoDTO.isPrincipal());
        return endereco;
    }

}

