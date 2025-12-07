usuario teste:
{
    email: "sistema.teste@teste.com",
    nome: "Teste Sistema",
    senha: "teste123"
}

const result = await api.post('http://localhost:8080/usuarios/cadastro',{
    email: "sistema.teste@teste.com",
    nome: "Teste Sistema",
    senha: "teste123"
});
const result = await api.post('http://localhost:8080/usuarios/login',{
    email: "sistema.teste@teste.com",
    senha: "teste123"
});