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
import { ToastContainer } from 'react-toastify';
import Motorista from './components/paginas/paginasEntidades/motorista/Motorista';
import MotoristaVeiculo from './components/paginas/paginasEntidades/motoristaVeiculo/MotoristaVeiculo';
import Passageiro from './components/paginas/paginasEntidades/passageiro/Passageiro';
import Pessoa from './components/paginas/paginasEntidades/pessoa/Pessoa';
import TipoPgto from './components/paginas/paginasEntidades/tipopgto/TipoPgto';
import Veiculo from './components/paginas/paginasEntidades/veiculo/Veiculo';
import Viagem from './components/paginas/paginasEntidades/viagem/Viagem';
import Proprietario from './components/paginas/paginasEntidades/proprietario/Proprietario';
import Atividade01 from './components/paginas/personalizadas/Atividade01';


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
  },
  {
    path:"/atividade01",
    element: <Atividade01 />
  }
]);

export const App = () => {
  return <>
      <ChakraProvider theme={theme}>
        <RouterProvider router={router} />  
        <ToastContainer />
      </ChakraProvider>
    </>
}