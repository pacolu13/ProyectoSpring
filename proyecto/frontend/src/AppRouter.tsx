import { BrowserRouter, Routes, Route } from "react-router-dom"
import { Header, Footer } from "./components/index.ts"
import { Home, Login, Register, Cart, Products, ProductListing } from "./pages/index"
import { linksHeader, linksFooter, mensajeFooter } from "../public/Links.ts"

export const AppRouter = () => {
    return <>
        <BrowserRouter>
            <Header title={"Proyecto Web"} links={linksHeader} />
            <Routes>
                <Route path='/' element={<Home />} />
                <Route path='/login' element={<Login />} />
                <Route path='/register' element={<Register />} />
                <Route path='/register' element={<Register />} />
                <Route path='/products' element={<Products />} />
                <Route path='/products/:idProduct' element={<ProductListing />} />
                <Route path='/cart' element={<Cart />} />
            </Routes>
            <Footer text={mensajeFooter} links={linksFooter} />
        </BrowserRouter>
    </>
}