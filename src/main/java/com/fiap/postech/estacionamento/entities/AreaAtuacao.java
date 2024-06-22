package com.fiap.postech.estacionamento.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="tb_areaAtuacao")
public class AreaAtuacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeArea;
    private String cidade;
    private String estado;
    private boolean ativo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaModificacao;

    public AreaAtuacao(){}

    public AreaAtuacao(Long id, String nomeArea, String cidade, String estado, boolean ativo, LocalDateTime dataCriacao, LocalDateTime dataUltimaModificacao) {
        this.id = id;
        this.nomeArea = nomeArea;
        this.cidade = cidade;
        this.estado = estado;
        this.ativo = ativo;
        this.dataCriacao = dataCriacao;
        this.dataUltimaModificacao = dataUltimaModificacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeArea() {
        return nomeArea;
    }

    public void setNomeArea(String nomeArea) {
        this.nomeArea = nomeArea;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getDataUltimaModificacao() {
        return dataUltimaModificacao;
    }

    public void setDataUltimaModificacao(LocalDateTime dataUltimaModificacao) {
        this.dataUltimaModificacao = dataUltimaModificacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AreaAtuacao that = (AreaAtuacao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AreaAtuacao{" +
                "id=" + id +
                ", nomeArea='" + nomeArea + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", ativo='" + ativo + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", dataUltimaModificacao=" + dataUltimaModificacao +
                '}';
    }
}
