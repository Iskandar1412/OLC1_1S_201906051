import { url } from "inspector";
import React from "react";
import logo from "../a2.jpg"


/*
const HomePage: React.FunctionComponent<IHomePageProps> = (props) => {
    return (
        <nav class= "sb-topnav navbar navbar-expand navbar-dark bg-dark">

        </nav>
    );
};*/
function HomePage(): JSX.Element {
    return (
        <>
        
        <div id='layoutSidenav_content'>
            <main>
                <div className="container-fluid px-4">
                    <h1 className="mt-4">Bienvenido a la Aplicación</h1>
                    <ol className="breadcrumb mb-4">
                        <li className="breadcrumb-item active">Dashboard</li>
                    </ol>
                    <img src={logo} className="Logo-As" alt="logo" />
                </div>
            </main>
        </div>
        </>
    );
}

export default HomePage;