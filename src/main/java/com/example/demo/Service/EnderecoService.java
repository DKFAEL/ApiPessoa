package com.example.demo.Service;

import com.example.demo.Dto.EnderecoDTO;
import com.example.demo.Domain.Endereco;
import com.example.demo.Domain.Pessoa;
import com.example.demo.Repository.EnderecoRepository;
import com.example.demo.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoDTO criarEndereco(Long pessoaId, EnderecoDTO enderecoDTO) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(pessoaId);
        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            Endereco endereco = convertEnderecoDTOToEntity(enderecoDTO);
            endereco.setPessoa(pessoa);
            return convertEnderecoEntityToDTO(enderecoRepository.save(endereco));
        }
        return null;
    }

    public List<EnderecoDTO> listarEnderecos(Long pessoaId) {
        List<Endereco> enderecos = enderecoRepository.findByPessoaId(pessoaId);
        return convertEnderecosEntityToDTOs(enderecos);
    }

    public EnderecoDTO editarEndereco(Long pessoaId, Long enderecoId, EnderecoDTO enderecoDTO) {
        Optional<Endereco> enderecoExistenteOptional = enderecoRepository.findById(enderecoId);
        if (enderecoExistenteOptional.isPresent()) {
            Endereco enderecoExistente = enderecoExistenteOptional.get();
            if (!enderecoExistente.getPessoa().getId().equals(pessoaId)) {
                return null;
            }
            Endereco endereco = convertEnderecoDTOToEntity(enderecoDTO);
            endereco.setId(enderecoId);
            return convertEnderecoEntityToDTO(enderecoRepository.save(endereco));
        }
        return null;
    }

    public EnderecoDTO definirEnderecoPrincipal(Long pessoaId, Long enderecoId) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(pessoaId);
        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            Optional<Endereco> enderecoAntigoOptional = enderecoRepository.findByPessoaIdAndPrincipalIsTrue(pessoaId);
            if (enderecoAntigoOptional.isPresent()) {
                Endereco enderecoAntigo = enderecoAntigoOptional.get();
                if (enderecoAntigo.getId().equals(enderecoId)) {
                    return null;
                }
                enderecoAntigo.setPrincipal(false);
                enderecoRepository.save(enderecoAntigo);
            }
            Optional<Endereco> enderecoNovoOptional = enderecoRepository.findById(enderecoId);
            if (enderecoNovoOptional.isPresent()) {
                Endereco enderecoNovo = enderecoNovoOptional.get();
                if (!enderecoNovo.getPessoa().getId().equals(pessoaId)) {
                    return null;
                }
                enderecoNovo.setPrincipal(true);
                enderecoRepository.save(enderecoNovo);
                return convertEnderecoEntityToDTO(enderecoNovo);
            }
        }
        return null;
    }

    private EnderecoDTO convertEnderecoEntityToDTO(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO(endereco.getId(), endereco.getLogradouro(),endereco.getCep(), endereco.getNumero(),endereco.getCidade(),endereco.isPrincipal());

        return enderecoDTO;
    }

    private List<EnderecoDTO> convertEnderecosEntityToDTOs(List<Endereco> enderecos) {
        return enderecos.stream()
                .map(this::convertEnderecoEntityToDTO)
                .collect(Collectors.toList());
    }

    private Endereco convertEnderecoDTOToEntity(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setId(enderecoDTO.getId());
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setPrincipal(enderecoDTO.isPrincipal());
        // Se você desejar mapear o objeto Pessoa, faça isso aqui
        return endereco;
    }
}
