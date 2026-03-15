import './Home.css'
import { ProductHome } from '../../components/index'
import { useFetch } from '../../hooks'
import type { ProductDTO } from '../../interfaces';

export const Home = () => {

    const { data, error, isLoading } = useFetch<ProductDTO[]>('/api/v1/products');

    if (data == null || error) {
        return null; // Generar un error
    }

    // Utilizar el isLoading

    return (
        <div id="Home">

            <section className="hero">
                <div className="hero-content">
                    <h1>Bienvenido a nuestra tienda</h1>
                    <p>Descubre tecnología, accesorios y productos de alta calidad.</p>
                    <button className="hero-button">Explorar productos</button>
                </div>
            </section>

            <section className="featured">
                <h2>Productos destacados</h2>

                {error && <p className="error">{error}</p>}

                <div className="products-grid">
                    {data.map(product => (
                        <ProductHome
                            id={product.id}
                            image={product.image}
                            brand={product.brand}
                            name={product.name}
                            description={product.description}
                        />
                    ))}
                </div>
            </section>
        </div>
    )
}