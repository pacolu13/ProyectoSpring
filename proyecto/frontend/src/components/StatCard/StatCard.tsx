import { useEffect, useRef, useState } from "react";
import "./StatCard.css";

interface StatCardProps {
  label: string;
  value: number;
  description: string;
  duration?: number;
}

export const StatCard = ({ label, value, description, duration = 1800 }: StatCardProps) => {
  const [display, setDisplay] = useState(0);
  const rafRef = useRef<number | null>(null);

  useEffect(() => {
    const start = performance.now();

    function step(now: number) {
      const t = Math.min((now - start) / duration, 1);
      const ease = 1 - Math.pow(1 - t, 3);
      setDisplay(Math.round(ease * value));
      if (t < 1) rafRef.current = requestAnimationFrame(step);
    }

    rafRef.current = requestAnimationFrame(step);
    return () => { if (rafRef.current) cancelAnimationFrame(rafRef.current); };
  }, [value, duration]);

  return (
    <div className="stat-card">
      <p className="stat-label">{label}</p>
      <p className="stat-number">{display.toLocaleString("es-AR")}</p>
      <p className="stat-description">{description}</p>
    </div>
  );
}