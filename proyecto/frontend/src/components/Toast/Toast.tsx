import { useEffect, useState, type JSX } from 'react'
import styles from './Toast.module.css'
import type { ToastType } from '../ToastProvider/ToastContext'

interface ToastProps {
  type: ToastType
  message: string
  onClose: () => void
}

interface ToastConfig {
  label: string
  color: string
  icon: JSX.Element
}

const CONFIG: Record<ToastType, ToastConfig> = {
  confirm: {
    label: 'Confirmado',
    color: '#4caf50',
    icon: (
      <svg width="16" height="16" viewBox="0 0 16 16" fill="none">
        <polyline points="2,8 6,12 14,4" stroke="#4caf50" strokeWidth="1.8"
          strokeLinecap="round" strokeLinejoin="round" />
      </svg>
    ),
  },
  warning: {
    label: 'Advertencia',
    color: '#f5a623',
    icon: (
      <svg width="16" height="16" viewBox="0 0 16 16" fill="none">
        <path d="M8 2L14 13H2L8 2Z" stroke="#f5a623" strokeWidth="1.5"
          strokeLinejoin="round" />
        <line x1="8" y1="7" x2="8" y2="10" stroke="#f5a623" strokeWidth="1.5"
          strokeLinecap="round" />
        <circle cx="8" cy="12" r="0.8" fill="#f5a623" />
      </svg>
    ),
  },
  error: {
    label: 'Error',
    color: '#e53935',
    icon: (
      <svg width="16" height="16" viewBox="0 0 16 16" fill="none">
        <line x1="3" y1="3" x2="13" y2="13" stroke="#e53935" strokeWidth="1.8"
          strokeLinecap="round" />
        <line x1="13" y1="3" x2="3" y2="13" stroke="#e53935" strokeWidth="1.8"
          strokeLinecap="round" />
      </svg>
    ),
  },
}

export function Toast({ type, message, onClose }: ToastProps) {
  const [visible, setVisible] = useState(false)
  const { label, color, icon } = CONFIG[type]

  useEffect(() => {
    const t = requestAnimationFrame(() => setVisible(true))
    return () => cancelAnimationFrame(t)
  }, [])

  return (
    <div className={`${styles.wrapper} ${visible ? styles.visible : ''}`}>
      <div className={styles.bar} style={{ background: color }} />
      <div className={styles.inner} style={{ borderLeftColor: color }}>
        <span className={styles.icon}>{icon}</span>
        <div className={styles.content}>
          <span className={styles.label}>{label}</span>
          <span className={styles.message}>{message}</span>
        </div>
        <button className={styles.close} onClick={onClose}>×</button>
      </div>
    </div>
  )
}