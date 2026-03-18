import './Grid.css'
interface GridProps {
  columns: number;
  children: React.ReactNode;
}

export const Grid = ({ columns, children }: GridProps) => {
  return (
    <div
      className="grid-container"
      style={{ gridTemplateColumns: `repeat(${columns}, 1fr)` }}
    >
      {children}
    </div>
  );
};