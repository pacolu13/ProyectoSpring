import { BrowserRouter, Routes, Route } from "react-router-dom"
import { Header, Footer } from "./components/index.ts"
import { Home, Login, Register, Cart, ProductListing, ProductSell, ManageListings } from "./pages"
import { linksHeader, linksFooter, mensajeFooter } from "../public/Links.ts"
import { PrivateRoute } from "./components/PrivateRoute/PrivateRoute.tsx"

export const AppRouter = () => {
    return <>
        <BrowserRouter>
            <Header title={"Proyecto Web"} links={linksHeader} />
            <Routes>
                <Route path='/' element={<Home />} />
                <Route path='/login' element={<Login />} />
                <Route path='/register' element={<Register />} />
                <Route path='/products/:idProduct' element={<ProductListing />} />

                <Route path="/cart" element={
                    <PrivateRoute roles={['CLIENT']}>
                        <Cart />
                    </PrivateRoute>
                } />

                <Route path="/products-sell" element={
                    <PrivateRoute roles={['SELLER']}>
                        <ProductSell />
                    </PrivateRoute>
                } />
                <Route path="/manage-listings" element={
                    <PrivateRoute roles={['SELLER']}>
                        <ManageListings />
                    </PrivateRoute>
                } />

            </Routes>
            <Footer text={mensajeFooter} links={linksFooter} />
        </BrowserRouter>
    </>
}