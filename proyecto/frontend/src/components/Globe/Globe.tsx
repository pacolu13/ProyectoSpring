import { useEffect, useRef } from "react";
import "./Globe.css";

const GLOBE_SIZE = 560;

interface Dot {
  phi: number;
  theta: number;
}

interface Point {
  x: number;
  y: number;
  z: number;
}

function generateDots(): Dot[] {
  const dots: Dot[] = [];
  for (let lat = -90; lat <= 90; lat += 5.5) {
    for (let lon = -180; lon <= 180; lon += 5.5) {
      dots.push({
        phi: (lat * Math.PI) / 180,
        theta: (lon * Math.PI) / 180,
      });
    }
  }
  return dots;
}

const DOTS: Dot[] = generateDots();

function project(phi: number, theta: number, rotY: number): Point {
  const x = Math.cos(phi) * Math.sin(theta + rotY);
  const y = Math.sin(phi);
  const z = Math.cos(phi) * Math.cos(theta + rotY);
  return { x, y, z };
}

export const Globe = () => {
  const canvasRef = useRef<HTMLCanvasElement>(null);
  const rotRef = useRef<number>(0);
  const animRef = useRef<number>(0);

  useEffect(() => {
    const canvas = canvasRef.current;
    if (!canvas) return;

    const ctx = canvas.getContext("2d");
    if (!ctx) return;

    const R = GLOBE_SIZE / 2;
    const cx = R;
    const cy = R;

    function draw(): void {
      if (!ctx) return;

      ctx.clearRect(0, 0, GLOBE_SIZE, GLOBE_SIZE);
      const rot = rotRef.current;

      // Atmosphere glow
      const atmo = ctx.createRadialGradient(cx, cy, R * 0.72, cx, cy, R * 1.05);
      atmo.addColorStop(0, "rgba(200,200,200,0)");
      atmo.addColorStop(0.6, "rgba(180,180,180,0.035)");
      atmo.addColorStop(1, "rgba(150,150,150,0.12)");
      ctx.beginPath();
      ctx.arc(cx, cy, R * 1.05, 0, Math.PI * 2);
      ctx.fillStyle = atmo;
      ctx.fill();

      // Latitude lines
      for (let lat = -75; lat <= 75; lat += 15) {
        const phi = (lat * Math.PI) / 180;
        ctx.beginPath();
        let first = true;
        for (let lon = -180; lon <= 182; lon += 2) {
          const theta = (lon * Math.PI) / 180;
          const p = project(phi, theta, rot);
          if (p.z < -0.05) { first = true; continue; }
          const sx = cx + p.x * R * 0.87;
          const sy = cy - p.y * R * 0.87;
          if (first) { ctx.moveTo(sx, sy); first = false; }
          else ctx.lineTo(sx, sy);
        }
        ctx.strokeStyle = lat === 0
          ? "rgba(200,200,200,0.13)"
          : "rgba(180,180,180,0.055)";
        ctx.lineWidth = lat === 0 ? 0.8 : 0.4;
        ctx.stroke();
      }

      // Longitude lines
      for (let lon = 0; lon < 180; lon += 15) {
        const theta = (lon * Math.PI) / 180;
        ctx.beginPath();
        let first = true;
        for (let lat = -90; lat <= 90; lat += 2) {
          const phi = (lat * Math.PI) / 180;
          const p = project(phi, theta, rot);
          if (p.z < -0.05) { first = true; continue; }
          const sx = cx + p.x * R * 0.87;
          const sy = cy - p.y * R * 0.87;
          if (first) { ctx.moveTo(sx, sy); first = false; }
          else ctx.lineTo(sx, sy);
        }
        ctx.strokeStyle = "rgba(180,180,180,0.05)";
        ctx.lineWidth = 0.4;
        ctx.stroke();
      }

      // Dots sorted by depth (z)
      const projected: Point[] = DOTS.map((d) =>
        project(d.phi, d.theta, rot)
      );
      projected.sort((a, b) => a.z - b.z);

      projected.forEach(({ x, y, z }) => {
        const sx = cx + x * R * 0.87;
        const sy = cy - y * R * 0.87;
        const visible = z > 0;
        const alpha = visible ? 0.08 + z * 0.65 : 0.02 + (z + 1) * 0.03;
        const size = visible ? 0.7 + z * 1.1 : 0.5;
        ctx.beginPath();
        ctx.arc(sx, sy, size, 0, Math.PI * 2);
        ctx.fillStyle = `rgba(215,215,215,${alpha})`;
        ctx.fill();
      });

      // Globe rim
      ctx.beginPath();
      ctx.arc(cx, cy, R * 0.87, 0, Math.PI * 2);
      ctx.strokeStyle = "rgba(200,200,200,0.1)";
      ctx.lineWidth = 1;
      ctx.stroke();

      // Specular highlight
      const spec = ctx.createRadialGradient(
        cx - R * 0.28, cy - R * 0.28, 0,
        cx - R * 0.1,  cy - R * 0.05, R * 0.65
      );
      spec.addColorStop(0, "rgba(255,255,255,0.13)");
      spec.addColorStop(0.35, "rgba(255,255,255,0.05)");
      spec.addColorStop(1, "rgba(255,255,255,0)");
      ctx.beginPath();
      ctx.arc(cx, cy, R * 0.87, 0, Math.PI * 2);
      ctx.fillStyle = spec;
      ctx.fill();

      // Dark limb shading
      const limb = ctx.createRadialGradient(cx, cy, R * 0.55, cx, cy, R * 0.88);
      limb.addColorStop(0, "rgba(0,0,0,0)");
      limb.addColorStop(0.7, "rgba(0,0,0,0)");
      limb.addColorStop(1, "rgba(0,0,0,0.35)");
      ctx.beginPath();
      ctx.arc(cx, cy, R * 0.88, 0, Math.PI * 2);
      ctx.fillStyle = limb;
      ctx.fill();

      rotRef.current += 0.0015;
      animRef.current = requestAnimationFrame(draw);
    }

    draw();
    return () => cancelAnimationFrame(animRef.current);
  }, []);

  return (
    <canvas
      ref={canvasRef}
      width={GLOBE_SIZE}
      height={GLOBE_SIZE}
      className="globe-canvas"
    />
  );
};
