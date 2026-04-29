import './Home.css'
import { ProductHome, StatCard } from '../../components'
import { useFetch } from '../../hooks'
import type { ProductSellDTO, StatsDTO } from '../../interfaces';
import { useAuth } from '../../context/AuthContext';

export const Home = () => {
    const { data, error } = useFetch<ProductSellDTO[]>('/api/v1/product-listings', false);
    const { data: stats } = useFetch<StatsDTO>('/api/v1/stats', false);
    const isAdmin = useAuth().user?.roles.includes('ADMIN') ?? false;

    if (data == null || error) {
        return null;
    }

    return (
        <div id="Home">
            <section className="hero-section">
                <p className="hero-subtitle">Bienvenido a</p>
                <h1 className="hero-title">Tu Tienda Online</h1>
                <p className="hero-description">La mejor selección de productos al mejor precio</p>

                <div className="hero-stats">
                    <StatCard
                        label="Órdenes realizadas"
                        value={stats?.cantOrders ?? 0}
                        description="Pedidos procesados exitosamente."
                    />
                    <StatCard
                        label="Productos disponibles"
                        value={stats?.cantProducts ?? 0}
                        description="Catálogo en constante crecimiento."
                    />
                    <StatCard
                        label="Usuarios registrados"
                        value={stats?.cantUsers ?? 0}
                        description="Personas que ya confían en nosotros."
                    />
                </div>
            </section>

            <section className="featured">
                <h2>Productos destacados</h2>
                {error && <p className="error">{error}</p>}
                <div className="products-grid">
                    {data.map(listing => (
                        <ProductHome
                            key={listing.id}
                            id={listing.id}
                            idProduct={listing.product.id}
                            image={listing.product.image}
                            brand={listing.product.brand}
                            title={listing.title}
                            description={listing.product.description}
                            isAdmin={isAdmin}
                        />
                    ))}
                </div>
            </section>
        </div>
    )
}