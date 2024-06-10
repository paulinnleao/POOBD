import React, { useState } from 'react';
import { CardBody, Card, Stack, Image, Flex, Button, useColorMode } from '@chakra-ui/react';
import { NavLink } from 'react-router-dom';
import { useSwipeable } from 'react-swipeable';
import imagens from '../utils/imagens.json';

const CardsMenu = () => {

    const {colorMode } = useColorMode();

    const [currentIndex, setCurrentIndex] = useState(0);
    const itemsPerPage = 3; // Número de itens visíveis por vez

    const handlePrevClick = () => {
    setCurrentIndex((prevIndex) => Math.max(prevIndex - itemsPerPage, 0));
    };

    const handleNextClick = () => {
    setCurrentIndex((prevIndex) =>
        Math.min(prevIndex + itemsPerPage, imagens.listaDeCards.length - itemsPerPage)
    );
    };

    const visibleItems = imagens.listaDeCards.slice(currentIndex, currentIndex + itemsPerPage);

    const handlers = useSwipeable({
    onSwipedLeft: () => handleNextClick(),
    onSwipedRight: () => handlePrevClick(),
    trackMouse: true, // Enable swipe with mouse as well
    });

    return (
    <Card>
        <CardBody>
        <Stack direction="row" spacing={2} {...handlers}>
            {visibleItems.map((itens, id) => (
            <NavLink key={id} to={itens.to}>
                <Flex
                _hover={{
                    boxShadow: `${colorMode==='light'?'0 0 10px rgba(0, 0, 0, 0.5)': '0 0 10px rgba(255, 255, 255, 1)'}`,
                }}
                borderRadius={'5px'}
                bgColor={'#F0FAFE'}
                paddingLeft={'10px'}
                w={'400px'}
                h={'56px'}
                m={'5px'}
                alignItems={'center'}
                gap={'10px'}
                color={'black'}
                >
                <Image w={42} h={42} src={itens.imagem[0]} />
                {itens.nome}
                </Flex>
            </NavLink>
            ))}
        </Stack>
        <Flex justifyContent="space-between" mb={4}>
            <Button
            _hover={{
                backgroundColor:`${colorMode === 'light' ? 'black' : 'white'}`,
                color: `${colorMode === 'light' ? 'white' : 'black'}`
            }}
            padding={'0 5px'}
            borderRadius={'25px'}
            border={`2px solid ${colorMode==='light'?'black':'white'}`}
            onClick={handlePrevClick} 
            disabled={currentIndex === 0}>
            &lt;
            </Button>
            <Button
            _hover={{
                backgroundColor:`${colorMode === 'light' ? 'black' : 'white'}`,
                color: `${colorMode === 'light' ? 'white' : 'black'}`
            }}
            padding={'0 5px'}
            borderRadius={'25px'}
            border={`2px solid ${colorMode==='light'?'black':'white'}`}
            onClick={handleNextClick}
            disabled={currentIndex >= imagens.listaDeCards.length - itemsPerPage}
            >
            &gt;
            </Button>
        </Flex>
        </CardBody>
    </Card>
    );
    };

export default CardsMenu;
