import type { link } from "../../interfaces/links"
import './SocialMedia.css'

interface Props {
    linksSocial: link[]
}

export const SocialMedia = ({ linksSocial }: Props) => {
    return (
        <div id="SocialMedia">
            <div id="RedesSocialesLinks">
                {linksSocial.map(link => (
                    <a key={link.name} href={link.url} target="_blank" rel="noopener noreferrer">
                        <i className={`fa-brands fa-${link.name.toLowerCase()}`}></i>
                    </a>
                ))}
            </div>
        </div>
    )
}