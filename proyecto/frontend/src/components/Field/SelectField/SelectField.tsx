export const SelectField = ({ label, register, name, children }: any) => (
  <div className="ps-field">
    <label className="ps-label">{label}</label>
    <select className="ps-input ps-select" {...register(name)}>
      {children}
      <option value="Otra">Otra</option>
    </select>

  </div>
);