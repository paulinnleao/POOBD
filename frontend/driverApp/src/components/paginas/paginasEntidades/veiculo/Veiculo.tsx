import React, { useEffect, useState } from 'react';
import CardsMenu from '../../CardsMenu';
import Tabela from '../../../utils/Tabela';
import { VeiculosDTO } from '../../../utils/Interfaces';
import axios from 'axios';
import titulos from '../../../utils/titulos.json';
import { Button, CircularProgress, Input, InputGroup, useColorMode } from '@chakra-ui/react';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import {toast} from 'react-toastify';
import { IoMdSearch } from "react-icons/io";;

const Veiculo = () => {

  const [veiculos, setVeiculos] = useState<VeiculosDTO[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const {colorMode} = useColorMode();

  const formik = useFormik({
    initialValues: {
      placa: ''
    },
    onSubmit: async (values, { setSubmitting, setErrors }) => {
      try{
        console.log(values)
        const response = await axios.get<VeiculosDTO>(
          `http://localhost:8080/veiculos/${values.placa}`
        )
        console.log('Veículo encontrado:', response.data);
        setVeiculos([response.data])
      }catch (error) {
        console.error('Erro ao buscar veículo:', error);
        toast.error('Veículo não encontrado!', {
          position: 'top-center',
          theme: "colored"
        });
      } finally {
        setSubmitting(false);
      }

    },
    validationSchema: Yup.object().shape({
      placa: Yup.string().required('Campo Obrigatório')
      .min(7, 'Placa deve ter pelo menos 7 caracteres!')
      .max(7, 'Placa deve ter no máximo 7 caracteres!')
    }),

  })

  useEffect(()=>{
    const fetchVeiculos = async () =>{
      try{
        const response = await axios.get<VeiculosDTO[]>('http://localhost:8080/veiculos');
        setVeiculos(response.data);
      }catch(err){
        setError("Não foi possível carregar os veículos.");
      }finally{
        setLoading(false);
      }
    }
    fetchVeiculos()
  }, [formik.values]);

  if (loading) return <CircularProgress isIndeterminate color='blue.300' />;
  if(error) return <div>{error}</div>;

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
      {formik.touched.placa && formik.errors.placa && formik.values.placa.length>0 ? (
        <div style={{ color: 'red' }}>{formik.errors.placa}</div>
      ) : null}
    </form>
    <Tabela dadosTabela={dadosTabela} />
  </>
}

export default Veiculo