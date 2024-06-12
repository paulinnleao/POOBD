import React from 'react';
import { IoIosMoon } from "react-icons/io";
import { IoSunny } from "react-icons/io5";
import { Box, Button, Flex, Heading, useColorMode } from '@chakra-ui/react';
import { NavLink } from 'react-router-dom';

const Cabecalho = () => {

    const {colorMode, toggleColorMode} = useColorMode();
  return <Flex
            top={0}
            w={'100%'}
            bgColor={colorMode === 'light'?'#171648':'white'}
            justifyContent={"space-between"}
            padding={'15px'}
            borderRadius={'10px'}
            >
              
        <NavLink to={'/'}>
          <Heading color={colorMode === 'light'?'white':'black'}>
              Driver App
          </Heading>
      </NavLink>
      <Button 
        _hover={{
          backgroundColor: `${colorMode === 'light'?'black':'white'}`,
          color:`${colorMode === 'light'?'white':'black'}`,
          border: `1px solid ${colorMode === 'light'?'white':'black'}`,
        }}
        borderRadius={'15px'}
        leftIcon={colorMode === 'light' ? <IoSunny /> : undefined}
        rightIcon={colorMode === 'dark' ? <IoIosMoon /> : undefined}
        onClick={toggleColorMode}
        color={colorMode === 'light'?'black':'white'}
        bgColor={colorMode === 'light'?'white':'black'}
        />
  </Flex>
}

export default Cabecalho