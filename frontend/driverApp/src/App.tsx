import React from 'react'
import './App.css'

// 1. import `ChakraProvider` component
import {
  ChakraBaseProvider,
  extendBaseTheme,
  theme as chakraTheme,
} from '@chakra-ui/react'

const { Button } = chakraTheme.components

const theme = extendBaseTheme({
  components: {
    Button,
  },
})

export const App = () => {
  // 2. Wrap ChakraProvider at the root of your app
  return (
    <ChakraBaseProvider theme={theme}>
      <div> Jeçç</div>
    </ChakraBaseProvider>
  )
}