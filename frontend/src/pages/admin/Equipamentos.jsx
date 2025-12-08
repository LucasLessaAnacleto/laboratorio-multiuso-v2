import { useEffect, useState } from "react";
import { Button } from "../../components/admin/Button";
import { CrudTable } from "../../components/admin/CrudTable";
import { Layout } from "../../components/admin/layout/Layout";
import { PageHeader } from "../../components/admin/layout/PageHeader";
import { useUsuario } from "../../hooks/useUsuario";
import { equipamentoService } from "../../services/equipamentoService.js";
import { Modal } from "../../components/admin/Modal.jsx";
import { EquipamentoModal } from "../../components/admin/equipamento/EquipamentoModal.jsx";
import { useNavigate } from "react-router-dom";

export function Equipamentos(){
    const { usuario } = useUsuario(); 
    const [dadosEquipamento, setDadosEquipamento] = useState([]);
    const [itemEdit, setItemEdit] = useState(null);
    const [itemView, setItemView] = useState(null);
    const [itemDel, setItemDel] = useState(null);
    const navigate = useNavigate();

    const headers = {
        icon: "",
        equipamento: "equipamento",
        codigo: "codigo",
        espaco: "espaço",
        status: "status",
        acao: "AÇÃO"
    };
    const columnTypes = {
        icon: "icon",
        equipamento: "",
        codigo: "",
        espaco: "link",
        status: "status",
        acao: "action"
    }
    const handleEdit = (item) => setItemEdit(item);
    const handleDelete = (item) => setItemDel(item);
    const handleView = (item) => {console.log('handleView', item);setItemView(item)};
    const handlePageChange = console.log;

    const actions = {
        view: {
            oncancelar: () => setItemView(null)
        },
        edit: {
            oncancelar: () => setItemEdit(null),
            onsalvar: async(form) => {
                console.log("salvar", JSON.stringify(form,null,2))
            },
            ondeletar: (item) => setItemDel(item)
            
        },
        del: {
            oncancelar: () => setItemDel(null),
            ondeletar: async(item) => {
                await equipamentoService.deletar(item.id);
                setItemDel(null);
                setDadosEquipamento((dados) => dados.filter(dado => dado.id !== item.id));
            }
        }
    }

    useEffect(() => {
        async function requestEquipamentos(){
            const data = await equipamentoService.listar(); 
            
            setDadosEquipamento(data.map(dado => ({
                form: {
                    id: dado.id,
                    equipamento: dado.nome,
                    codigo: dado.patrimonio,
                    espaco: dado.espaco?.nome,
                    status: dado.disponivel ? 'disponível' : 'indisponível',
                    url: `/publica/espaco/${dado?.espaco?.id}`
                },
                ...dado
            })));
            /* */
        }
        requestEquipamentos();
    },[])

    return (
        <Layout title="ADM Equipamentos - Laboratório Multiuso" usuario={usuario}>
            <div id="content" className="dashboard">
                <PageHeader titulo={`Equipamentos`} >
                    <Button onclick={() => navigate("/admin/cadastro-equipamento")}>+ Equipamentos</Button>
                </PageHeader>
                <CrudTable
                    dados={dadosEquipamento}
                    headers={headers}
                    columnTypes={columnTypes}
                    onEdit={handleEdit}
                    onDelete={handleDelete}
                    onView={handleView}
                    showPagination={false}
                    totalItems={dadosEquipamento.length} // Total de itens no banco de dados
                    currentPage={1}
                    itemsPerPage={10}
                    onPageChange={handlePageChange}
                />
            </div>
            <EquipamentoModal 
                mapper={(dado) => ({
                    id: dado.id,
                    nome: dado.nome,
                    categoria: dado.categoria,
                    descricao: dado.descricao,
                    patrimonio: dado.patrimonio,
                    disponivel: dado.disponivel ? true : false,
                    contatoResponsavel: dado.contatoResponsavel,
                    espaco: dado.espaco?.nome,
                    anexoImagem: dado.anexo?.nomeAnexo

                    /*
                        private String nome;
                        private String categoria;
                        private String descricao;
                        private String contatoResponsavel;
                        private String patrimonio;
                        private String anexoImagem;
                        private Boolean disponivel;
                        private String espacoId;
                    */
                })}
                itemEdit={itemEdit}
                itemView={itemView}
                itemDel={itemDel}
                actions={actions}
            />
        </Layout>
    )
}