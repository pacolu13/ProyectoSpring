import './Home.css'
import { apiClient } from '../../services/apiClient'
import { useEffect, useState } from 'react'
import { ProductHome } from '../../components/index'

interface Product {
    id: number
    name: string
    description: string
    brand: string
    category: string
    image: string
    active: boolean
    creationDate: string
}

export const Home = () => {

    const [products, setProducts] = useState<Product[]>([])
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {

        const fetchProducts = async () => {

            const { data, error } = await apiClient.get<Product[]>("/products")

            if (error) {
                setError(error)
                return
            }

            setProducts(data ?? [])
        }

        fetchProducts()

    }, [])

    const featuredProducts = products.slice(0, 6)

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
                    {featuredProducts.map(product => (
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