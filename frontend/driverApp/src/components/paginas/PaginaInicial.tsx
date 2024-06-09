import React from 'react'
import { useColorMode } from '@chakra-ui/react'; // Importar useColorMode

const PaginaInicial = () => {
  const { colorMode } = useColorMode(); // Desestruturar o objeto retornado por useColorMode
  console.log(colorMode)
  return (
    <div
      style={{
        backgroundColor:  colorMode === "light" ? "#f0f0f0" : "#303030",
        padding: 20,
        color: colorMode === "light" ? "#000" : "#fff",
      }}
    >
      <h1>Chakra UI Theme Example</h1>
      <p>Current Color Mode: {colorMode}</p>
      {/* Add more content and components here */}
    </div>
  )
}

export default PaginaInicial