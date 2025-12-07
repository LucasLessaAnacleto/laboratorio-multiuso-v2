import { useContext, useEffect, useState } from "react";
import { Layout } from "../../components/admin/layout/Layout.jsx";
import { PageHeader } from "../../components/admin/layout/PageHeader.jsx";
import { UsuarioContext } from "../../contexts/UsuarioContext.jsx";
import { useLoading } from "../../hooks/useLoading.jsx";
import { CartaoInfo } from "../../components/admin/CartaoInfo.jsx";
import { reservaService } from "../../services/reservaService.js";

import iconAgendamento from "../../assets/icon-agendamento.svg";
import { Table } from "../../components/admin/table.jsx";

export function Dashboard() {
    const { usuario, carregando } = useContext(UsuarioContext);
    const { setLoading } = useLoading();
    const [dadosReserva, setDadosReserva] = useState([]);
    
    useEffect(() => {
        setLoading(carregando);
        console.log('carregando:',carregando);
    }, [carregando, setLoading]);

    useEffect(() => {
        async function getData(){
            const dados = await reservaService.proximos7dias();
            console.log('Proximos 7 dias',dados);
            setDadosReserva(dados.map(dados => ({
                espaco: "a",
                data: "b",
                horario: "c"
            })))
        }
        getData();
    }, [])

    return (
        <Layout title="ADM Dashboard - Laboratório Multiuso" usuario={usuario}>
            <div id="content" className="dashboard">
                <PageHeader titulo={`Bem vindo(a), ${usuario?.nome}`} />
                <div className="group-x" style={{marginBottom: "2rem"}}>
                    <CartaoInfo  titulo="Agendamento" descricao="Próximos 7 dias" valor={8} imagem={iconAgendamento}/>
                    <CartaoInfo  titulo="Agendamento" descricao="Próximos 7 dias" valor={8} imagem={iconAgendamento}/>
                    <CartaoInfo  titulo="Agendamento" descricao="Próximos 7 dias" valor={8} imagem={iconAgendamento}/>
                </div>
                <Table dados={dadosReserva} headers={{ espaco: "espaço", data: "data", horario: "horário"}}>
                    Proximos Agendamentos
                </Table>
            </div>
        </Layout>
    )
}