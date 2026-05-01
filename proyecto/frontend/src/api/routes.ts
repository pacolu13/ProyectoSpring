export const version = "v1"

export const productsRoutes = {
    list: `/api/${version}/product-listings`,
    create: `/api/${version}/product-listings`,
    update: (id: number) => `/api/${version}/product-listings/${id}`,
    delete: (id: number) => `/api/${version}/product-listings/${id}`,
    get: (id: string | undefined) => `/api/${version}/product-listings/${id}`
}

export const userRoutes = {
    login: `/api/${version}/auth/login`,
    register: `/api/${version}/auth/register`,
    logout: `/api/${version}/auth/logout`,
    sellerListings: `/api/${version}/product-listings/seller`
}

export const cartRoutes = {
    get: `/api/${version}/carts`,
    update: `/api/${version}/carts`,
    submit: `/api/${version}/orders/submit`,
    delete: (productListingId: number) => `/api/${version}/carts/${productListingId}`
}

export const statsRoutes = {
    get: `/api/${version}/stats`
}

export const categoriesRoutes = {
    list: `/api/${version}/categories`
}