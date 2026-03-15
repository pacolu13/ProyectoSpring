export const FormatDate = (dateStr: string): string => {
    return new Intl.DateTimeFormat("es-AR", {
        day: "2-digit",
        month: "long",
        year: "numeric",
    }).format(new Date(dateStr));
};