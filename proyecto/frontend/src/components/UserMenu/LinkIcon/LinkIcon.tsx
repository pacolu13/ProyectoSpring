import type { JSX } from "react";

interface Props {
    label: string;
    url: string;
    icon: JSX.Element;
}

export const LinkIcon = ({ label, url, icon }: Props) => {
    return (
        <a href={url} className="user_dropdown__item"  rel="noopener noreferrer">
            <span className="user_dropdown__item__icon">{icon}</span>
            {label}
        </a>
    );
}