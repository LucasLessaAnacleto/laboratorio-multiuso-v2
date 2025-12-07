import './style/cartao-info.css';

export function CartaoInfo({titulo, valor, descricao, imagem}){
    return (
        <div className="cartao-info">
            <div className="cartao-info-conteudo">
                <div className="cartao-info-circulo">
                    <div className="cartao-info-circulo-interno">
                        <img src={imagem} alt="imagem cartão info" className='cartao-info-img'/>
                    </div>
                </div>
                
                <div className="cartao-info-texto">
                    <h3 className="cartao-info-titulo">{titulo}</h3>
                    <p className="cartao-info-valor">{valor}</p>
                    <p className="cartao-info-descricao">{descricao}</p>
                </div>
            </div>
        </div>
    );
}

