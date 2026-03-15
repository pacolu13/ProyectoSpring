import './Home.css'
import { apiClient } from '../../services/apiClient'
import { useEffect, useState } from 'react'
import { ProductHome } from '../../components/index'
import { useFetch } from '../../hooks'

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

    const { data, error, isLoading } = useFetch<Product[]>('/api/v1/products');

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