import React, { useState } from 'react';
import { FileInput } from '../../components/admin/FileInput';

import { Layout } from '../../components/admin/layout/Layout';
import { PageHeader } from '../../components/admin/layout/PageHeader';
import { useUsuario } from '../../hooks/useUsuario';
import { Button } from '../../components/admin/Button';

import { ModalField } from '../../components/admin/ModalField';
import './style/cadastro-equipamento.css';

export function CadastroEquipamento() {
    const { usuario } = useUsuario();  
    const [equipamento, setEquipamento] = useState({});
    function alterarCampo(nome, valor) {
        setEquipamento(prev => ({ ...prev, [nome]: valor }));
    }
    /*
    {
    nome: "",
    categoria: "",
    descricao?: "",
    contatoResponsavel?: "",
    patrimonio: "",
    espacoId: "836c92b4-23ac-449d-b127-7a12051fae53",
    anexoImagem?: "",
    disponivel?: true
    }
    */
    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Equipamento cadastrado:', equipamento);
    };

  return (
    <Layout title="ADM Equipamentos - Laboratório Multiuso" usuario={usuario}>
        <div id="content" className="dashboard">
            <PageHeader titulo={`Equipamentos`} >
            </PageHeader>
        <div className="cadastro-container">

        <div className="cadastro-main">
            <form onSubmit={handleSubmit} className="cadastro-form">
            
            {/* Seção 1: Cadastrar Novo Equipamento */}
            <section className="form-section">
                <div className="section-header">
                <h2>Cadastrar Novo Equipamento</h2>
                </div>

                <ModalField
                  campo={{
                    nome: "nome",
                    tipo: "text",
                    label: "Nome do equipamento",
                    hint: "Ex: Microscópio",
                  }}
                  value={equipamento.nome}
                  onChange={valor => atualizarCampo("nome", valor)}
                />

                <ModalField
                  campo={{
                    nome: "categoria",
                    tipo: "text",
                    label: "Categoria do equipamento",
                    hint: "Ex: TF-200 - Techlab",
                  }}
                  value={equipamento.modeloFabricante}
                  onChange={valor => atualizarCampo("modeloFabricante", valor)}
                />

                <ModalField
                  campo={{
                    nome: "descricao",
                    tipo: "textarea",
                    label: "Descrição do equipamento",
                    hint: "Ex: Para análises microscópicas",
                    rows: 4,
                  }}
                  value={equipamento.observacoes}
                  onChange={valor => atualizarCampo("observacoes", valor)}
                />

                <div className="form-row">
                  <div className="half">
                    <ModalField
                      campo={{
                        nome: "responsavel",
                        tipo: "text",
                        label: "Responsável",
                        hint: "Ex: Carlos Mendes",
                      }}
                      value={equipamento.responsavel}
                      onChange={valor => atualizarCampo("responsavel", valor)}
                    />
                  </div>

                  <div className="half">
                    <ModalField
                      campo={{
                        nome: "contatoResponsavel",
                        tipo: "text",
                        label: "Contato Responsável",
                        hint: "Ex: carlos@senac.com.br",
                      }}
                      value={equipamento.contato}
                      onChange={valor => atualizarCampo("contato", valor)}
                    />
                  </div>
                </div>
              </section>

              <div className="section-divider"></div>

              {/* Seção 2: Localização */}
              <section className="form-section">
                <div className="section-header">
                  <h2>Localização</h2>
                </div>

                <ModalField
                  campo={{
                    nome: "andar",
                    tipo: "text",
                    label: "Andar",
                    hint: "Ex: 1º Andar",
                  }}
                  value={equipamento.andar}
                  onChange={valor => atualizarCampo("andar", valor)}
                />

                <ModalField
                  campo={{
                    nome: "departamento",
                    tipo: "text",
                    label: "Departamento/Unidade",
                    hint: "Ex: Engenharia",
                  }}
                  value={equipamento.departamento}
                  onChange={valor => atualizarCampo("departamento", valor)}
                />

                <ModalField
                  campo={{
                    nome: "arquivoNome",
                    tipo: "file",
                    label: "Anexo (opcional)",
                    accept: ".pdf,.doc,.docx,.jpg,.png",
                  }}
                  value={equipamento.arquivoNome}
                  onChange={valor => atualizarCampo("arquivoNome", valor)}
                />
            </section>

            {/* Botões de Ação */}
            <div className="modal-footer">
                <button className="btn cancelar">Cancelar</button>
                <button className="btn salvar" type='submit'>Salvar</button>
            </div>
            </form>
        </div>
        </div>
        </div>
    </Layout>
  );
}