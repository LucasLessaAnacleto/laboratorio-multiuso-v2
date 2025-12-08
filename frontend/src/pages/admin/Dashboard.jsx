import { useEffect, useState } from "react";
import { Layout } from "../../components/admin/layout/Layout.jsx";
import { PageHeader } from "../../components/admin/layout/PageHeader.jsx";
import { CartaoInfo } from "../../components/admin/CartaoInfo.jsx";
import { reservaService } from "../../services/reservaService.js";
import { Table } from "../../components/admin/table.jsx";
import { parseDate } from "../../utils/parseDate.js";
import { useUsuario } from "../../hooks/useUsuario.jsx";

import iconAgendamento from "../../assets/icon-agendamento.svg";

export function Dashboard() {
    const { usuario } = useUsuario(); 

    const [dadosReserva, setDadosReserva] = useState([]);
    const [dadosDash, setDadosDash ] = useState({
        qtdReserva: 0,
        qtdEspaco: 0,
        qtdEquipamento: 0
    });

    useEffect(() => {
        function formatter(dt) {
            const { raw } = parseDate(dt); // raw deve ser o objeto Date retornado por parseDate
            const start = new Date(raw);
            const end = new Date(raw);

            end.setHours(end.getHours() + 1);

            const pad = n => n.toString().padStart(2, '0');

            const startStr = `${pad(start.getHours())}:${pad(start.getMinutes())}`;
            const endStr   = `${pad(end.getHours())}:${pad(end.getMinutes())}`;

            return `${startStr} - ${endStr}`;
        }
        async function requestDataTable(){
            const dados = await reservaService.proximos7dias();
            setDadosReserva(dados.map(dados => {
                return({
                espaco: dados?.espaco?.nome,
                data: parseDate(dados?.dataReserva).dataFormatada,
                horario: formatter(dados?.dataReserva)
            })}))
        }
        async function requestDash(){
            const dados = await reservaService.dashboard();
            setDadosDash(dados)
        }
        
        requestDataTable();
        requestDash();
    }, [])

    return (
        <Layout title="ADM Dashboard - Laboratório Multiuso" usuario={usuario}>
            <div id="content" className="dashboard">
                <PageHeader titulo={`Bem vindo(a), ${usuario?.nome}`} />
                <div className="group-x" style={{marginBottom: "2rem"}}>
                    <CartaoInfo  titulo="Agendamento" descricao="Próximos 7 dias" valor={dadosDash.qtdReserva} imagem={iconAgendamento}/>
                    <CartaoInfo  titulo="Equipamentos" descricao="Disponiveis agora" valor={dadosDash.qtdEquipamento} imagem={iconAgendamento}/>
                    <CartaoInfo  titulo="Espaços" descricao="Disponiveis hoje" valor={dadosDash.qtdEspaco} imagem={iconAgendamento}/>
                </div>
                <Table dados={dadosReserva} headers={{ espaco: "espaço", data: "data", horario: "horário"}}>
                    Proximos Agendamentos
                </Table>
            </div>
        </Layout>
    )
}