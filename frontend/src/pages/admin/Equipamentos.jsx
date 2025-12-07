import { Button } from "../../components/admin/Button";
import { Layout } from "../../components/admin/layout/Layout";
import { PageHeader } from "../../components/admin/layout/PageHeader";
import { useUsuario } from "../../hooks/useUsuario";

export function Equipamentos(){
    const { usuario } = useUsuario(); 
    return (
        <Layout title="ADM Equipamentos - Laboratório Multiuso" usuario={usuario}>
            <div id="content" className="dashboard">
                <PageHeader titulo={`Equipamentos`} >
                    <Button>+ Equipamentos</Button>
                </PageHeader>
            </div>
        </Layout>
    )
}