type Props = {
  label: string;
  register: any;
  name: string;
  required?: boolean;
  type?: string;
  placeholder?: string;
};

export const InputField = ({ label, register, name, required, type = "text", placeholder }: Props) => (
  <div className="ps-field">
    <label className="ps-label">
      {label} {required && "*"}
    </label>
    <input
      className="ps-input"
      type={type}
      placeholder={placeholder}
      {...register(name, { required })}
    />
  </div>
);