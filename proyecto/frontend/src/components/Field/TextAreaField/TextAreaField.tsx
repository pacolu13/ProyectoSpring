export const TextAreaField = ({ label, register, name, placeholder }: any) => (
  <div className="ps-field">
    <label className="ps-label">{label}</label>
    <textarea
      className="ps-input ps-textarea"
      placeholder={placeholder}
      {...register(name)}
    />
  </div>
);