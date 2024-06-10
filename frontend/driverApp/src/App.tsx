import React, { Component } from 'react';
import './App.css';

import 'react-toastify/dist/ReactToastify.css';

import {
  ChakraProvider,
  extendTheme,
} from '@chakra-ui/react';

import { 
  createBrowserRouter,
  RouterProvider, 
} from 'react-router-dom';

import PaginaInicial from './components/paginas/PaginaInicial';
import Cabecalho from './components/paginas/Cabecalho';
import Motorista from './components/paginas/paginasEntidades/Motorista';
import MotoristaVeiculo from './components/paginas/paginasEntidades/MotoristaVeiculo';
import Passageiro from './components/paginas/paginasEntidades/Passageiro';
import Pessoa from './components/paginas/paginasEntidades/Pessoa';
import TipoPgto from './components/paginas/paginasEntidades/TipoPgto';
import Veiculo from './components/paginas/paginasEntidades/Veiculo';
import Viagem from './components/paginas/paginasEntidades/Viagem';
import Proprietario from './components/paginas/paginasEntidades/Proprietario'
import { ToastContainer } from 'react-toastify';


const theme = extendTheme({
  components: {
  }
});


export const router = createBrowserRouter([
  {
    path: "/",
    element: <PaginaInicial />,
  },
  {
    path:"/motoristas",
    element:<Motorista />,
  },
  {
    path:'motoristas-veiculos',
    element:<MotoristaVeiculo />,
  },
  {
    path:"/passageiros",
    element:<Passageiro />
  },
  {
    path:"/pessoas",
    element:<Pessoa />
  },
  {
    path:"/tipos-pagamento",
    element:<TipoPgto />
  },
  {
    path:"/veiculos",
    element:<Veiculo />
  },
  {
    path:"/viagens",
    element:<Viagem />
  },
  {
    path:"/proprietarios",
    element:<Proprietario />
  }
]);

export const App = () => {
  return <>
      <ChakraProvider theme={theme}>
        <Cabecalho />
        <RouterProvider router={router} />  
        <ToastContainer />
      </ChakraProvider>
    </>
}