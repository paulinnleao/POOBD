import React, { useEffect, useState } from 'react'
import { DeleteEntity, EditEntity, MotoristaVeiculoDTO } from '../../../utils/Interfaces'
import axios from 'axios';
import { Button, CircularProgress, Input, InputGroup, useColorMode } from '@chakra-ui/react';
import CardsMenu from '../../CardsMenu';
import Tabela from '../../../utils/Tabela';
import titulos from '../../../utils/titulos.json'
import { useFormik } from 'formik';
import * as Yup from 'yup';
import {toast} from 'react-toastify'
import { IoMdSearch } from "react-icons/io";
import { ModalMotoristaVeiculo} from './ModalMotoristaVeiculo';
import ModalDeleteMotoristaVeiculo from './ModalDeleteMotoristaVeiculo';
import ModalDeCriacaoMotoristaVeiculo from './ModalDeCriacaoMotoristaVeiculo';

const MotoristaVeiculo = () => {
  const [motoristaVeiculo, setMotoristaVeiculo] = useState<MotoristaVeiculoDTO[]>([]);
  const {colorMode} = useColorMode();
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const [atualizarPagina, setAtualizarPagina] = useState<boolean>(false);
  
  const [deleteEntity, setDeleteEntity] = useState<DeleteEntity>({
    deletar: false,
    identificador: -1
  });
  const [editEntity, setEditEntity] = useState<EditEntity>({
    editar: false,
    identificadores: -1
  });
  const [createEntity, setCreateEntity] = useState<boolean>(false);


  const formik = useFormik({
    initialValues: {
      cpfMotorista: '',
      placa: ''
    },
    onSubmit: async (values, { setSubmitting, setErrors }) => {
      try{
        console.log(values)
        const response = await axios.get<MotoristaVeiculoDTO>(
          `http://localhost:8080/motoristas-veiculos/id?cpfMotorista=${values.cpfMotorista}&placa=${values.placa}`
        )
        console.log('Motorista do veículo encontrado:', response.data);
        setMotoristaVeiculo([response.data])
      }catch (error) {
        console.error('Erro ao buscar motorista do veículo:', error);
        toast.error('Motorista do veículo não encontrado!', {
          position: 'top-center',
          theme: "colored"
        });
      } finally {
        setSubmitting(false);
      }

    },
    validationSchema: Yup.object().shape({
      cpfMotorista: Yup.string().required('Campo Obrigatório').matches(/^[0-9]*$/, 'Apenas números são permitidos')
      .min(11, 'CPF deve ter pelo menos 11 dígitos!')
      .max(11, 'CPF deve ter no máximo 11 dígitos!'),
      placa: Yup.string().required('Campo Obrigatório')
      .min(7, 'Placa deve ter pelo menos 7 caracteres!')
      .max(7, 'Placa deve ter no máximo 7 caracteres!')
    }),

  })


  useEffect(() => {
    const fetchMotoristasVeiculos = async () => {
      try {
        const response = await axios.get<MotoristaVeiculoDTO[]>('http://localhost:8080/motoristas-veiculos');
        setMotoristaVeiculo(response.data);
      } catch (err) {
        setError('Erro ao buscar os motoristas de veículos');
      } finally {
        setLoading(false);
      }
    }
  
    fetchMotoristasVeiculos();
  }, [formik.values.cpfMotorista, formik.values.placa]);

  if (loading) return <CircularProgress isIndeterminate color='blue.300' />;
  if (error) return <div>{error}</div>;

  const dadosTabela = {
    titulos: titulos.motoristaVeiculo,
    dados:motoristaVeiculo.map((motorista) => [
            motorista.cpfMotorista.toString(),
            motorista.placa,
        ])
};
  return <>
    <CardsMenu />
    <form onSubmit={formik.handleSubmit}>
      <InputGroup
        right={'10px'}
        gap={'10px'}
        justifyContent={'end'} size={'md'}
        marginBottom={'10px'}
        alignItems={'center'}
        >
          <Input
          w={'300px'}
          name='cpfMotorista'
          onChange={formik.handleChange}
          onBlur={(e) => {
            formik.handleBlur(e);
            formik.setFieldError('cpfMotorista', '');
          }}
          color={`${colorMode === 'light' ? 'black' : 'white'}`}
          borderRadius={'10px'}
          pr='4.5rem' 
          padding={'15px'}
          placeholder='CPF'
          _hover={{
            boxShadow: `${colorMode==='light'?'0 0 10px rgba(0, 0, 0, 0.5)': '0 0 10px rgba(255, 255, 255, 1)'}`,
            }}
          border={`2px solid ${colorMode==='light'?'black':'white'}`}
          borderColor={formik.touched.cpfMotorista && formik.values.cpfMotorista.length>0 && formik.errors.cpfMotorista ? 'red' : undefined}
          maxLength={11}
          minLength={11}
          />
          <Input
          w={'300px'}
          name='placa'
          onChange={formik.handleChange}
          onBlur={(e) => {
            formik.handleBlur(e);
            formik.setFieldError('placa', '');
          }}
          color={`${colorMode === 'light' ? 'black' : 'white'}`}
          borderRadius={'10px'}
          pr='4.5rem' 
          padding={'15px'}
          placeholder='PLACA'
          _hover={{
            boxShadow: `${colorMode==='light'?'0 0 10px rgba(0, 0, 0, 0.5)': '0 0 10px rgba(255, 255, 255, 1)'}`,
            }}
          border={`2px solid ${colorMode==='light'?'black':'white'}`}
          borderColor={formik.touched.placa && formik.values.placa.length>0 && formik.errors.placa ? 'red' : undefined}
          maxLength={7}
          minLength={7}
          />
          <Button h='1.75rem' size='sm' leftIcon={<IoMdSearch />} type='submit'> Buscar</Button>
      </InputGroup>
      {formik.touched.cpfMotorista && formik.errors.cpfMotorista && formik.values.cpfMotorista.length>0 ? (
        <div style={{ color: 'red' }}>{formik.errors.cpfMotorista}</div>
      ) : null}
      {formik.touched.placa && formik.errors.placa && formik.values.placa.length>0 ? (
        <div style={{ color: 'red' }}>{formik.errors.placa}</div>
      ) : null}
    </form>
    <Tabela 
    dadosTabela={dadosTabela} 
    setCreateEntity={setCreateEntity} 
    setDeleteEntity={setDeleteEntity}
    setEditEntity={setEditEntity}/>

    {!!editEntity && 
    <ModalMotoristaVeiculo 
    motoristaVeiculo={motoristaVeiculo[editEntity?.identificadores]} 
    atualizarPagina={atualizarPagina} 
    editEntity={editEntity}
    setEditEntity={setEditEntity} 
    setAtualizarPagina={setAtualizarPagina}
    />}
    {!!deleteEntity && 
    <ModalDeleteMotoristaVeiculo 
      motoristaVeiculo={motoristaVeiculo[deleteEntity?.identificador]}
      atualizarPagina={atualizarPagina}
      deleteEntity={deleteEntity}
      setDeleteEntity={setDeleteEntity}
      setAtualizarPagina={setAtualizarPagina}
    />}
    {createEntity && 
    <ModalDeCriacaoMotoristaVeiculo 
      createEntity={createEntity} 
      atualizarPagina={atualizarPagina}
      setCreateEntity={setCreateEntity}
      setAtualizarPagina={setAtualizarPagina} />}
  </>
}

export default MotoristaVeiculo