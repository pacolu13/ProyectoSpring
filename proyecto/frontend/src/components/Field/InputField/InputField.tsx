import type { RegisterOptions } from "react-hook-form";
import './InputField.css'

type Props = {
  label: string;
  register: any;
  name: string;
  rules?: RegisterOptions;
  type?: string;
  placeholder?: string;
};

export const InputField = ({ label, register, name, rules, type = "text", placeholder }: Props) => (
  <div className="ps-field">
    <label className="ps-label">
      {label} {rules?.required && "*"}
    </label>
    <input
      className="ps-input"
      type={type}
      placeholder={placeholder}
      {...register(name, rules)}
    />
  </div>
);