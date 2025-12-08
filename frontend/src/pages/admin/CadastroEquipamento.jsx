import React, { useState } from 'react';
import { FileInput } from '../../components/admin/FileInput';

import './style/cadastro-equipamento.css';
import { Layout } from '../../components/admin/layout/Layout';
import { PageHeader } from '../../components/admin/layout/PageHeader';
import { useUsuario } from '../../hooks/useUsuario';
import { Button } from '../../components/admin/Button';
function CadastroEquipamento() {
  const { usuario } = useUsuario();  
  const [equipamento, setEquipamento] = useState({
    nome: '',
    modeloFabricante: '',
    observacoes: '',
    responsavel: '',
    contato: '',
    andar: '',
    departamento: '',
    arquivo: null,
    arquivoNome: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEquipamento(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setEquipamento(prev => ({
        ...prev,
        arquivo: file,
        arquivoNome: file.name
      }));
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Aqui você fará a lógica de submit
    console.log('Equipamento cadastrado:', equipamento);
  };

  return (
    <Layout title="ADM Equipamentos - Laboratório Multiuso" usuario={usuario}>
        <div id="content" className="dashboard">
            <PageHeader titulo={`Equipamentos`} >
                <Button onclick={() => navigate("/admin/cadastro-equipamento")}>+ Equipamentos</Button>
            </PageHeader>
        <div className="cadastro-container">

        <div className="cadastro-main">
            <form onSubmit={handleSubmit} className="cadastro-form">
            
            {/* Seção 1: Cadastrar Novo Equipamento */}
            <section className="form-section">
                <div className="section-header">
                <h2>Cadastrar Novo Equipamento</h2>
                </div>

                <div className="form-group">
                <label htmlFor="nome">
                    Nome do equipamento
                    <span className="input-hint">Ex: Microscópio</span>
                </label>
                <input
                    type="text"
                    id="nome"
                    name="nome"
                    value={equipamento.nome}
                    onChange={handleChange}
                    placeholder="Digite o nome do equipamento"
                />
                </div>

                <div className="form-group">
                <label htmlFor="modeloFabricante">
                    Modelo/Fabricante
                    <span className="input-hint">Ex: TF-200 - Techlab</span>
                </label>
                <input
                    type="text"
                    id="modeloFabricante"
                    name="modeloFabricante"
                    value={equipamento.modeloFabricante}
                    onChange={handleChange}
                    placeholder="Digite o modelo e fabricante"
                />
                </div>

                <div className="form-group">
                <label htmlFor="observacoes">
                    Observações Gerais
                    <span className="input-hint">Ex: Para análises microscópicas</span>
                </label>
                <textarea
                    id="observacoes"
                    name="observacoes"
                    value={equipamento.observacoes}
                    onChange={handleChange}
                    placeholder="Digite observações sobre o equipamento"
                    rows="4"
                />
                </div>

                <div className="form-row">
                <div className="form-group half">
                    <label htmlFor="responsavel">
                    Responsável
                    <span className="input-hint">Ex: Carlos Mendes</span>
                    </label>
                    <input
                    type="text"
                    id="responsavel"
                    name="responsavel"
                    value={equipamento.responsavel}
                    onChange={handleChange}
                    placeholder="Nome do responsável"
                    />
                </div>

                <div className="form-group half">
                    <label htmlFor="contato">
                    Contato
                    <span className="input-hint">Ex: carlos@senac.com.br</span>
                    </label>
                    <input
                    type="email"
                    id="contato"
                    name="contato"
                    value={equipamento.contato}
                    onChange={handleChange}
                    placeholder="E-mail do responsável"
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

                <div className="form-group">
                <label htmlFor="andar">
                    Andar
                    <span className="input-hint">Ex: 1º Andar</span>
                </label>
                <input
                    type="text"
                    id="andar"
                    name="andar"
                    value={equipamento.andar}
                    onChange={handleChange}
                    placeholder="Digite o andar"
                />
                </div>

                <div className="form-group">
                <label htmlFor="departamento">
                    Departamento/Unidade
                    <span className="input-hint">Ex: Engenharia</span>
                </label>
                <input
                    type="text"
                    id="departamento"
                    name="departamento"
                    value={equipamento.departamento}
                    onChange={handleChange}
                    placeholder="Digite o departamento"
                />
                </div>

                <div className="form-group">
                <FileInput 
                    label={""}
                    accept={""}
                    onChange={console.log}
                />
                </div>
            </section>

            {/* Botões de Ação */}
            <div className="modal-footer">
                <button class="btn cancelar">Cancelar</button>
                <button class="btn salvar" type='submit'>Salvar</button>
            </div>
            </form>
        </div>
        </div>
        </div>
    </Layout>
  );
}

export default CadastroEquipamento;