import React from 'react'
import { Box, Button, Flex, Heading, useColorMode } from '@chakra-ui/react'; // Importar useColorMode
import CardsMenu from './CardsMenu';
import Atividade01 from './personalizadas/Atividade01';
import { NavLink } from 'react-router-dom';

const PaginaInicial = () => {
  const { colorMode } = useColorMode(); // Desestruturar o objeto retornado por useColorMode
  return (
    <Box>
      <CardsMenu />
      <Heading>Consultas Personalizadas</Heading>
      <Flex>
        <NavLink to={'/atividade01'}>
          <Button>
            Fase 02 - Atividade 01
          </Button>
        </NavLink>
      </Flex>
    </Box>
    
  )
}

export default PaginaInicial