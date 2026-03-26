import { createContext, useContext, useState, useCallback, useRef, type ReactNode } from 'react'
import { Toast } from '../Toast/Toast'

export type ToastType = 'confirm' | 'warning' | 'error'

interface ToastState {
  type: ToastType
  message: string
  key: number
}

interface ToastContextValue {
  showToast: (type: ToastType, message: string, duration?: number) => void
}

const ToastContext = createContext<ToastContextValue | null>(null)

export function ToastProvider({ children }: { children: ReactNode }) {
  const [toast, setToast] = useState<ToastState | null>(null)
  const timerRef = useRef<ReturnType<typeof setTimeout> | null>(null)

  const showToast = useCallback((type: ToastType, message: string, duration = 4000) => {
    if (timerRef.current) clearTimeout(timerRef.current)
    setToast({ type, message, key: Date.now() })
    timerRef.current = setTimeout(() => setToast(null), duration)
  }, [])

  const hideToast = useCallback(() => {
    if (timerRef.current) clearTimeout(timerRef.current)
    setToast(null)
  }, [])

  return (
    <ToastContext.Provider value={{ showToast }}>
      {children}
      {toast && <Toast key={toast.key} type={toast.type} message={toast.message} onClose={hideToast} />}
    </ToastContext.Provider>
  )
}

export function useToast(): (type: ToastType, message: string, duration?: number) => void {
  const ctx = useContext(ToastContext)
  if (!ctx) throw new Error('useToast must be used inside <ToastProvider>')
  return ctx.showToast
}