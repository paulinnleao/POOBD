import React from 'react'
import './App.css'

import {
  ChakraBaseProvider,
  extendBaseTheme,
  theme as chakraTheme,
} from '@chakra-ui/react'
import { 
  createBrowserRouter,
  RouterProvider, 
} from 'react-router-dom'
import PaginaInicial from './components/paginas/PaginaInicial'
import { useColorMode } from '@chakra-ui/react';

const { Button } = chakraTheme.components

const theme = extendBaseTheme({
  initialColorMode: 'dark',
  useSystemColorMode: false,
  components: {
    Button,
  },
  

});


export const router = createBrowserRouter([
  {
    path: "/",
    element: <PaginaInicial />,
  },
]);

export const App = () => {
  return <ChakraBaseProvider theme={theme}>
        <RouterProvider router={router} />  
    </ChakraBaseProvider>
}