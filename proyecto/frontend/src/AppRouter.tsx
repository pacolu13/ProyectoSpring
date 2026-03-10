import { BrowserRouter, Routes, Route } from "react-router-dom"
import { Header, Footer } from "./components/index.ts"
import { Home, Login, Register, Products } from "./pages/index.ts"

export const AppRouter = () => {
    return <>
        <Header title={""} links={[]} />
        <BrowserRouter>
            <Routes>
                <Route path='/' element={<Home />} />
                <Route path='/login' element={<Login />} />
                <Route path='/register' element={<Register />} />
                <Route path='/products' element={<Products />} />
            </Routes>
        </BrowserRouter>
        <Footer text={""} links={[]} />
    </>
}