import './Button.css';

interface Props {
    label: string,
    parentMethod: () => void
}

export const Button = ({ label, parentMethod }: Props) => {
    return (
        <button className="button" onClick={parentMethod}>
            <span className="button_lg">
                <span className="button_sl"></span>
                <span className="button_text">{label}</span>
            </span>
        </button>

    )
}