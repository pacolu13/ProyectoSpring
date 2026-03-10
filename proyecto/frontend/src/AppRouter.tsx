import { BrowserRouter, Routes, Route } from "react-router-dom"
import { Header } from "./components/Header/Header"
import { Footer } from "./components/Footer/Footer"
import { Home,Login,Register,Products } from "./pages/index"

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