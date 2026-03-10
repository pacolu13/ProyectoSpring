import type { link } from '../../interfaces/links';
import { SocialMedia } from '../SocialMedia/SocialMedia';
import './Footer.css';

interface Props {
    text: string;
    links: link[];
}

export const Footer = ({ text, links }: Props) => {
    return (
        <footer className="footer">
            <p>{text}</p>
            <SocialMedia linksSocial={links} />
        </footer>
    );
}