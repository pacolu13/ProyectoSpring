
interface Props {
    quantity: number;
    onIncrease: () => void;
    onDecrease: () => void;
}

export const HandleQuantity = ({ quantity, onIncrease, onDecrease }: Props) => {

    return (
        <div className="card__quantity">
            <button onClick={onDecrease}>−</button>
            <span>{quantity}</span>
            <button onClick={onIncrease}>+</button>
        </div>
    );
}