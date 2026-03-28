type Props = {
    label: string;
    register: any;
    name: string;
    states: string[];
    watch: any;
};

export const StatesField = ({ label, register, name, states, watch }: Props) => {
    const state = watch(name);

    return (
        <div className="ps-field">
            <label className="ps-label">{label}</label>
            <div className="ps-state-group">
                {states.map((s: string) => (
                    <button
                        type="button"
                        key={s}
                        className={`ps-state-btn ${state === s ? "ps-state-btn--active" : ""}`}
                        onClick={() => {
                            // setValue manual
                            document.querySelector<HTMLInputElement>(`input[name="state"][value="${s}"]`)?.click();
                        }}
                    >
                        {s}
                    </button>
                ))}
            </div>
            {states.map((s: string) => (
                <input
                    key={s}
                    type="radio"
                    value={s}
                    {...register(name)}
                    style={{ display: "none" }}
                />
            ))}
        </div>
    );
};