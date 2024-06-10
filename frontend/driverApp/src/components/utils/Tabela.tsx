import { Table, TableContainer, Thead, Tr, Th, Td, Tbody, Tfoot, Icon, Flex, Button } from '@chakra-ui/react'
import React from 'react'

import './Tabela.css'
import { DadosTabelaProps } from './Interfaces'

import { FaPencil } from "react-icons/fa6";
import { FaRegTrashAlt } from "react-icons/fa";
import { FaPlusCircle } from "react-icons/fa";

import styled from 'styled-components'

const Tabela: React.FC<DadosTabelaProps> = ({dadosTabela, setEditModal}) => {

    const handleClick = (cpf: string) => {
        setEditModal({
            editModal: true,
            identificadores: {
                cpf: cpf
            }
        })
    }

    return <TableContainer>
            <Table w={'100%'}>
                <Thead>
                    <Tr>
                        {/* Map de cabeçalhos */}
                        {dadosTabela.titulos.map((value, id)=><ThH key={id}>{value}</ThH>)}
                        <ThH>
                            <Flex
                                alignItems={'center'} gap={'5px'} justifyContent={'center'}
                                >
                                Ações <Button 
                                        _hover={{
                                            color: 'green',
                                        }}
                                        ><FaPlusCircle /></Button>
                            </Flex>
                                </ThH>
                    </Tr>
                </Thead>
                <Tbody>
                        {/* Map de dados */}
                    {dadosTabela.dados.map((value, id)=>
                        <Tr key={id}>
                            {value.map((valores, id)=><TdS key={id}>{valores}</TdS>)}
                            <TdS>
                                <Flex flexDirection={'column'} gap={'5px'} alignItems={'center'}>
                                    <Button 
                                        _hover={{
                                            color: 'red',
                                        }}
                                        ><FaRegTrashAlt /></Button>
                                    <Button
                                        _hover={{
                                            color: 'gray',
                                        }}
                                        onClick={() => handleClick(value[0].toString())}

                                        ><FaPencil /></Button>
                                </Flex>
                            </TdS>
                        </Tr>
                    )}
                    
                </Tbody>
                <Tfoot>
                    <Tr>
                        {/* Map de cabeçalhos */}
                        {dadosTabela.titulos.map((value, id)=><ThS key={id}>{value}</ThS>)}
                        <ThS />
                    </Tr>
                </Tfoot>
            </Table>
            </TableContainer>
}

export default Tabela;

const ThS = styled(Th)`
    padding: 8px;
    border-top: 1px solid #ddd;
    padding-left: 50px;
    &:first-child{
        padding-left: 20px;
    }
`;
const TdS = styled(Td)`
    padding: 8px;
    text-align: center;
    border-top: 1px solid #ddd;
    padding-left: 50px;
    &:first-child{
        padding-left: 20px;
    }
`;
const ThH = styled(Th)`
    background-color: #f0f0f0;
    color: #333;
    text-transform: uppercase;
    padding-left: 50px;
    &:first-child{
        padding-left: 20px;
    }
    `;