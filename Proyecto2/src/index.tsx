import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import './assets/index.css'
import './assets/toglebar.js'
import reportWebVitals from './reportWebVitals';
//rafce --> para poner las funciones automática
//git init
//git add . --> agregar todo
//git status
//

const root = ReactDOM.createRoot(
  document.getElementById('sb-nav-fixed') as HTMLElement
);

root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
