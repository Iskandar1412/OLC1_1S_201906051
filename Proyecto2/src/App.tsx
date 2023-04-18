import React from 'react';
// import React, { Component } from 'react';
// import React, { Fragment, useState } from 'react';
// import logo from './logo.svg';
// import './App.css';
import Navbar from './Navbar';
import 'https://use.fontawesome.com/releases/v6.1.0/js/all.js';
import 'https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js';
import Home from './pages/Home';
import About from './pages/About';
import Inicio from './pages/Inicio';

// <img src={logo} className="App-logo" alt="logo" />
function App(): JSX.Element {
  // const [newTask, setNewTask] = useState('');
  let component 
    switch(window.location.pathname){
      case "/":
        component = <Home />
        break;
      case "/About":
        component = <About />
        break;
      case "/Inicio":
        component = <Inicio />
        break;
  }

  return (
    <>
    <nav className='sb-topnav navbar navbar-expand navbar-dark bg-dark'>
      <a className='navbar-brand ps-3' href="/">Proyecto 2</a>
      <button className='btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0' id='sidebarToggle'><i className='fas fa-bars'></i></button>
      <form className='d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0'>
          <div className='input-group'>
              <input className='form-control' type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch"/>
              <button className='btn btn-primary' id='btnNavbarSearch' type='button'><i className='fas fa-search'></i></button>
          </div>
      </form>
      <ul className='navbar-nav ms-auto ms-md-0 me-3 me-lg-4'>
          <li className='nav-item dropdown'>
              <a className='nav-link dropdown-toggle' id='navbarDropdown' href="#" role='button' data-bs-toggle='dropdown' aria-expanded='false'><i className='fas fa-user fa-fw'></i></a>
              <ul className='dropdown-menu dropdown-menu-end' aria-labelledby="navbarDropdown">
                  <li><a className="dropdown-item" href="/">Settings</a></li>
                  <li><a className="dropdown-item" href="/">Activity Log</a></li>
                  <li><hr className="dropdown-divider" /></li>
                  <li><a className="dropdown-item" href="/">Logout</a></li>
              </ul>
          </li>
      </ul>
    </nav>
    <div id='layoutSidenav'>
      <Navbar />
      {component}
    </div>
    </>
  );
};

export default App;
