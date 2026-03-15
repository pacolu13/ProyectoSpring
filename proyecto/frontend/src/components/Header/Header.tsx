import type { link } from '../../interfaces/links';
import { SearchInput, UserMenu } from '../index';
import './Header.css';

interface Props {
    title: string;
    links: link[];
}

export const Header = ({ title, links }: Props) => {
    return (
        <header className='header'>
            <h1>{title}</h1>
            <SearchInput onSearch={(value) => console.log("Buscar:", value)} />
            <nav>
                <ul>
                    {links.map((link, index) => (
                        <li key={index}>
                            <a href={link.url}>{link.name}</a>
                        </li>
                    ))}
                </ul>
                <UserMenu />
            </nav>
        </header>
    );
}
