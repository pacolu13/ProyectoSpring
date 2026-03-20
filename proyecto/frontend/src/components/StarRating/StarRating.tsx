import './StarRating.css'

export const StarRating = ({ rating }: { rating: number }) => {
  return (
    <span className="plc__stars" aria-label={`${rating} de 5 estrellas`}>
      {[1, 2, 3, 4, 5].map((s) => (
        <svg
          key={s}
          className={`plc__star ${s <= Math.round(rating) ? "plc__star--filled" : ""}`}
          viewBox="0 0 12 12"
          fill="none"
        >
          <path
            d="M6 1l1.236 2.506L10 3.927l-2 1.947.472 2.752L6 7.25 3.528 8.626 4 5.874 2 3.927l2.764-.421L6 1z"
            fill="currentColor"
          />
        </svg>
      ))}
    </span>
  );
};