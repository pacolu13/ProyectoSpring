import type { ConditionDTO } from '../../interfaces';
import './ConditionBadge.css'

interface ConditionBadgeProps {
    condition: ConditionDTO;
}

export const ConditionBadge = ({ condition }: ConditionBadgeProps) => (
    <span className={`plc__badge plc__badge--${condition}`}>
        {condition === "NEW" ? "Nuevo" : condition === "USED" ? "Usado" : "Undefined"}
    </span>
);