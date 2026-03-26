import './Home.css'
import { Button, ProductHome } from '../../components'
import { useFetch } from '../../hooks'
import type { ProductDTO } from '../../interfaces';

export const Home = () => {

    const { data, error } = useFetch<ProductDTO[]>('/api/v1/products', false);

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
            </section>

            <section className="featured">
                <h2>Productos destacados</h2>

                {error && <p className="error">{error}</p>}

                <div className="products-grid">
                    {data.map(product => (
                        <ProductHome
                            key={product.id}
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