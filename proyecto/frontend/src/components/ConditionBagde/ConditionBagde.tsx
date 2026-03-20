import './ConditionBadge.css'

interface ConditionBadgeProps {
    condition: "new" | "used";
}

export const ConditionBadge = ({ condition }: ConditionBadgeProps) => (
    <span className={`plc__badge plc__badge--${condition}`}>
        {condition === "new" ? "Nuevo" : "Usado"}
    </span>
);