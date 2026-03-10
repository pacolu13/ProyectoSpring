import type { link } from '../../interfaces/links';
import './Header.css';

interface Props {
    title: string;
    links: link[];
}

export const Header = ({ title, links }: Props) => {
    return (
        <header className='header'>
            <h1>{title}</h1>
            <nav>
                <ul>
                    {links.map((link, index) => (
                        <li key={index}>
                            <a href={link.url}>{link.name}</a>
                        </li>
                    ))}
                </ul>
            </nav>
        </header>
    );
}
