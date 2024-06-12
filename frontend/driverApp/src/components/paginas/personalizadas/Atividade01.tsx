import { Box, Button, Input, InputGroup, useColorMode } from '@chakra-ui/react';
import { useFormik } from 'formik';
import React, { useState } from 'react';
import axios from 'axios';
import { DeleteEntity, EditEntity, VeiculosDTO } from '../../utils/Interfaces';
import { toast } from 'react-toastify';
import { IoMdSearch } from 'react-icons/io';
import Tabela from '../../utils/Tabela';
import moment from 'moment';
import titulos from '../../utils/titulos.json';
import CardsMenu from '../CardsMenu';

interface VeiculoFaturamentoDTO {
    nomeProprietario: string,
    placa: string,
    descricaoPagamento: string,
    valorTotalFaturado: number,
    valorMedioFaturamento: number
}

const Atividade01 = () => {
  
  const [deleteEntity, setDeleteEntity] = useState<DeleteEntity>({
    deletar: false,
    identificador: -1
  });
  const [editEntity, setEditEntity] = useState<EditEntity>({
    editar: false,
    identificadores: -1
  });
  const [createEntity, setCreateEntity] = useState<boolean>(false);

  const [veiculos, setVeiculos] = useState<VeiculosDTO[]>([]);


    const formik = useFormik({
        initialValues:{
            data: '',
            horaInicial: moment().format("YYYY-MM-DDTHH:mm:ss"),
            horaFinal: moment().format("YYYY-MM-DDTHH:mm:ss"),
        },
        onSubmit: async (values) => {
            try{
                const response = await axios.get<VeiculosDTO>(`http://localhost:8080/veiculos/data=${values?.data}/hora-inicial=${values?.horaInicial}/hora-final=${values?.horaFinal}`)
                setVeiculos([response.data])
                toast.success('Veiculo buscado com sucesso!');
            }catch(err){
                toast.error('Erro ao buscar veiculo');
            }
        }
    })
    const {colorMode} = useColorMode();
    const dadosTabela = {
        titulos: titulos.veiculo,
        dados: veiculos.map((veiculo)=>[
          veiculo.placa,
          veiculo.marca,
          veiculo.modelo,
          veiculo.anoFabric,
          veiculo.capacidadePass,
          veiculo.cor,
          veiculo.tipoCombust,
          veiculo.potenciaMotor,
          veiculo.cpfProp,
        ]),
      }
    return (
        <div>
            
        <CardsMenu />
            <form onSubmit={formik.handleSubmit}>
                <InputGroup 
                    right={'10px'}
                    gap={'10px'}
                    justifyContent={'end'}
                    marginBottom={'10px'}
                    alignItems={'center'}
                    >

                    <Input 
                        name='data'
                        placeholder='Data'
                        size='lg'
                        pr='4.5rem' 
                        w={'300px'}
                        onChange={formik.handleChange}
                        onBlur={(e) => {
                        formik.handleBlur(e);
                        formik.setFieldError('cpf', '');
                        }}
                        color={`${colorMode === 'light' ? 'black' : 'white'}`}
                        borderRadius={'10px'}
                        padding={'15px'}
                        border={`2px solid ${colorMode==='light'?'black':'white'}`}
                        borderColor={formik.touched.data && formik.values.data.length>0 && formik.errors.data ? 'red' : undefined}
                        _hover={{
                        boxShadow: `${colorMode==='light'?'0 0 10px rgba(0, 0, 0, 0.5)': '0 0 10px rgba(255, 255, 255, 1)'}`,
                        }}
                        type="datetime-local"
                        />

                        <Input
                                w={'250px'}
                                name='horaInicial'
                                onChange={formik.handleChange}
                                onBlur={(e) => {
                                    formik.handleBlur(e);
                                    formik.setFieldError('horaInicial', '');
                                }}
                                color={`${colorMode === 'light' ? 'black' : 'white'}`}
                                borderRadius={'10px'}
                                pr='4.5rem' 
                                padding={'15px'}
                                placeholder='Data e hora inicial'
                                _hover={{
                                    boxShadow: `${colorMode==='light'?'0 0 10px rgba(0, 0, 0, 0.5)': '0 0 10px rgba(255, 255, 255, 1)'}`,
                                    }}
                                border={`2px solid ${colorMode==='light'?'black':'white'}`}
                                borderColor={formik.touched.horaInicial && formik.values.horaInicial.length>0 && formik.errors.horaInicial ? 'red' : undefined}
                                type="datetime-local"
                                />

                                <Input
                                    w={'250px'}
                                    name='horaFinal'
                                    onChange={formik.handleChange}
                                    onBlur={(e) => {
                                        formik.handleBlur(e);
                                        formik.setFieldError('horaFinal', '');
                                    }}
                                    color={`${colorMode === 'light' ? 'black' : 'white'}`}
                                    borderRadius={'10px'}
                                    pr='4.5rem' 
                                    padding={'15px'}
                                    placeholder='Data e hora final'
                                    _hover={{
                                        boxShadow: `${colorMode==='light'?'0 0 10px rgba(0, 0, 0, 0.5)': '0 0 10px rgba(255, 255, 255, 1)'}`,
                                        }}
                                    border={`2px solid ${colorMode==='light'?'black':'white'}`}
                                    borderColor={formik.touched.horaFinal && formik.values.horaFinal.length>0 && formik.errors.horaFinal ? 'red' : undefined}
                                    type="datetime-local"
                                    />

                        <Button h='1.75rem' size='sm' leftIcon={<IoMdSearch />} type='submit'> Buscar</Button>
                </InputGroup>
            </form>
            <Tabela dadosTabela={dadosTabela} setCreateEntity={setCreateEntity} setDeleteEntity={setDeleteEntity} setEditEntity={setEditEntity} />
        </div>
    )
}

export default Atividade01