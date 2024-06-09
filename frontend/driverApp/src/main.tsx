import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import { App } from './App'
import { StoreProvider, action, createStore } from 'easy-peasy'
import { ToastContainer } from 'react-toastify'

const store = createStore({
  pedido: [],  
  adicionar: action((state :any, payload) => {
      state.pedido.push(payload);
  }),
});

ReactDOM.createRoot(document.getElementById('root')!).render(
  <StoreProvider store={store}>
    <App />
    <ToastContainer />
  </StoreProvider>
)
