package com.extensao.senac.backend.modelos;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Anexo {

    @Id
    @Column(length = 255)
    private String nomeAnexo;

    @Column
    private String contentType;

    @Column
    private Long size;

    @CreationTimestamp
    private Instant dataCricao;

    @UpdateTimestamp
    private Instant dataUpload;

    @Lob
    @Basic(fetch = FetchType.LAZY) 
    private byte[] contentBlob;

    public String getNomeAnexo() {
        return this.nomeAnexo;
    }

    public void setNomeAnexo(String nomeAnexo) {
        this.nomeAnexo = nomeAnexo;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return this.size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Instant getDataCricao() {
        return this.dataCricao;
    }

    public void setDataCricao(Instant dataCricao) {
        this.dataCricao = dataCricao;
    }

    public Instant getDataUpload() {
        return this.dataUpload;
    }

    public void setDataUpload(Instant dataUpload) {
        this.dataUpload = dataUpload;
    }

    public byte[] getContentBlob() {
        return this.contentBlob;
    }

    public void setContentBlob(byte[] contentBlob) {
        this.contentBlob = contentBlob;
    }
}
