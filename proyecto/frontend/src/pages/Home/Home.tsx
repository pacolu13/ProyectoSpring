import './Home.css'
import { Button, ProductHome } from '../../components'
import { useFetch } from '../../hooks'
import type { ProductSellDTO, StatsDTO } from '../../interfaces';

export const Home = () => {

    const { data, error } = useFetch<ProductSellDTO[]>('/api/v1/product-listings', false);
    const { data: stats } = useFetch<StatsDTO>('/api/v1/stats', false);

    if (data == null || error) {
        return null; // Generar un error
    }

    return (
        <div id="Home">

            <section className="hero">
                <div className="hero-content">
                    <h1>Bienvenido a nuestra tienda</h1>
                    <p>Descubre tecnología, accesorios y productos de alta calidad.</p>
                    <Button label={'Explorar productos'} parentMethod={function (): void {
                        throw new Error('Function not implemented.');
                    }}></Button>
                </div>
                <div className='hero-stats'>
                    <p>{stats?.cantOrders}</p>
                    <p>{stats?.cantProducts}</p>
                    <p>{stats?.cantUsers}</p>
                </div>
            </section>

            <section className="featured">
                <h2>Productos destacados</h2>

                {error && <p className="error">{error}</p>}

                <div className="products-grid">
                    {data.map(listing => (
                        <ProductHome
                            key={listing.id}
                            id={listing.product.id}
                            image={listing.product.image}
                            brand={listing.product.brand}
                            name={listing.title}
                            description={listing.product.description}
                        />
                    ))}
                </div>
            </section>
        </div>
    )
}