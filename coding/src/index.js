import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import { Auth0Provider } from "@auth0/auth0-react"
import { Provider } from 'react-redux';
import store from './components/redux/store';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  //<React.StrictMode>
  <Provider store={store}>
    {/* <Auth0Provider
      domain="dev-wimnkwqbr6n5y77p.us.auth0.com"
      clientId="qVUAS6irPOEmozwgwLoEFHpOiTtuUnsk"
      authorizationParams={{
        redirect_uri: window.location.origin
      }}
    > */}
    <App />
  </Provider>
  //</React.StrictMode>
);

