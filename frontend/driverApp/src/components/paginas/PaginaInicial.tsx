import React from 'react'
import { useColorMode } from '@chakra-ui/react'; // Importar useColorMode
import CardsMenu from './CardsMenu';

const PaginaInicial = () => {
  const { colorMode } = useColorMode(); // Desestruturar o objeto retornado por useColorMode
  return (
    <div>
      <CardsMenu />
    </div>
    
  )
}

export default PaginaInicial