export function parseDate(dateStr) {
    const date = new Date(dateStr);

    if (isNaN(date)) return null;

    return {
        raw: date,
        dia: String(date.getDate()).padStart(2, "0"),
        mes: String(date.getMonth() + 1).padStart(2, "0"),
        ano: date.getFullYear(),
        horas: String(date.getHours()).padStart(2, "0"),
        minutos: String(date.getMinutes()).padStart(2, "0"),
        segundos: String(date.getSeconds()).padStart(2, "0"),
        formatado: date.toLocaleString("pt-BR"),
        dataFormatada: date.toLocaleDateString("pt-BR"),
        horaFormatada: date.toLocaleTimeString("pt-BR"),
    };
}

export function parseDateInstant(dateStr) {
    const fixed = dateStr.replace(/(\.\d{3})\d+$/, "$1");

    const date = new Date(fixed);
    if (isNaN(date)) return null;

    return {
        raw: date,
        dia: String(date.getDate()).padStart(2, "0"),
        mes: String(date.getMonth() + 1).padStart(2, "0"),
        ano: date.getFullYear(),
        horas: String(date.getHours()).padStart(2, "0"),
        minutos: String(date.getMinutes()).padStart(2, "0"),
        segundos: String(date.getSeconds()).padStart(2, "0"),
        formatado: date.toLocaleString("pt-BR"),
        dataFormatada: date.toLocaleDateString("pt-BR"),
        horaFormatada: date.toLocaleTimeString("pt-BR"),
    };
}
