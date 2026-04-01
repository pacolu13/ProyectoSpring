export const FormatDate = (dateStr: Date): string => {
    return new Intl.DateTimeFormat("es-AR", {
        day: "2-digit",
        month: "long",
        year: "numeric",
    }).format(dateStr);
};