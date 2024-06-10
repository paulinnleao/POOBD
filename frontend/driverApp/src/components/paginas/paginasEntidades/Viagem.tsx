import React, { useEffect, useState } from 'react'
import CardsMenu from '../CardsMenu'
import Tabela from '../../utils/Tabela'
import { ViagensDTO } from '../../utils/Interfaces'
import axios from 'axios';
import titulos from '../../utils/titulos.json'
import { Button, CircularProgress, Input, InputGroup, useColorMode } from '@chakra-ui/react';
import moment from 'moment';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import {toast} from 'react-toastify'
import { IoMdSearch } from "react-icons/io";

const Viagem = () => {

  const [viagens, setViagens] = useState<ViagensDTO[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const {colorMode} = useColorMode();

  const [date, setDate] = useState(moment().format("YYYY-MM-DDTHH:mm:ss"));

  const formik = useFormik({
    initialValues: {
      cpfPassag: '',
      cpfMotorista: '',
      placa: '',
      dtHoraInicio:  moment().format("YYYY-MM-DDTHH:mm:ss"),
    },
    onSubmit: async (values, { setSubmitting, setErrors }) => {
      try{
        console.log(values)
        const response = await axios.get<ViagensDTO>(
          `http://localhost:8080/viagens/id?cpfPassag=${values.cpfPassag}&cpfMotorista=${values.cpfMotorista}&placa=${values.placa}&dtHoraInicio=${moment(values.dtHoraInicio).format("YYYY-MM-DDTHH:mm:ss")}`
        )
        console.log('Viagem encontrada', response.data);
        setViagens([response.data])
      }catch (error) {
        console.error('Erro ao buscar viagem', error);
        toast.error('Viagem não encontrado!', {
          position: 'top-center',
          theme: "colored"
        });
      } finally {
        setSubmitting(false);
      }

    },
    validationSchema: Yup.object().shape({
      cpfPassag: Yup.string().required('Campo Obrigatório').matches(/^[0-9]*$/, 'Apenas números são permitidos')
      .min(11, 'CPF deve ter pelo menos 11 dígitos!')
      .max(11, 'CPF deve ter no máximo 11 dígitos!'),
      cpfMotorista: Yup.string().required('Campo Obrigatório').matches(/^[0-9]*$/, 'Apenas números são permitidos')
      .min(11, 'CPF deve ter pelo menos 11 dígitos!')
      .max(11, 'CPF deve ter no máximo 11 dígitos!'),
      placa: Yup.string().required('Campo Obrigatório')
      .min(7, 'Placa deve ter pelo menos 7 caracteres!')
      .max(7, 'Placa deve ter no máximo 7 caracteres!')
    }),

  })

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    setDate(value);;
  };

  useEffect(()=>{
    const fetchViagens = async () =>{
      try{
        const response = await axios.get<ViagensDTO[]>('http://localhost:8080/viagens');
        setViagens(response.data);
      }catch(err){
        setError("Não foi possível carregar as viagens.");
      }finally{
        setLoading(false);
      }
    }
    fetchViagens()
  }, [formik.values]);

  
  if (loading) return <CircularProgress isIndeterminate color='blue.300' />;
  if(error) return <div>{error}</div>;

  const dadosTabela = {
    titulos: titulos.viagem,
    dados: viagens.map((viagem)=>[
      viagem.cpfPassag,
      viagem.cpfMotorista,
      viagem.placa,
      viagem.localOrigViag,
      viagem.localDestViag,
      moment(viagem.dtHoraInicio).format('DD/MM/YYYY HH:mm:ss'),
      moment(viagem.dtHoraFim).format('DD/MM/YYYY HH:mm:ss'),
      viagem.qtdePass,
      viagem.formaPagto,
      viagem.valorPagto,
      viagem.cancelamMot,
      viagem.cancelamPass,
      viagem.codPagto,
    ])
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
          w={'250px'}
          name='cpfPassag'
          onChange={formik.handleChange}
          onBlur={(e) => {
            formik.handleBlur(e);
            formik.setFieldError('cpfPassag', '');
          }}
          color={`${colorMode === 'light' ? 'black' : 'white'}`}
          borderRadius={'10px'}
          pr='4.5rem' 
          padding={'15px'}
          placeholder='CPF passageiro'
          _hover={{
            boxShadow: `${colorMode==='light'?'0 0 10px rgba(0, 0, 0, 0.5)': '0 0 10px rgba(255, 255, 255, 1)'}`,
            }}
          border={`2px solid ${colorMode==='light'?'black':'white'}`}
          borderColor={formik.touched.cpfPassag && formik.values.cpfPassag.length>0 && formik.errors.cpfPassag ? 'red' : undefined}
          maxLength={11}
          minLength={11}
          />
          <Input 
          w={'250px'}
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
          placeholder='CPF motorista'
          _hover={{
            boxShadow: `${colorMode==='light'?'0 0 10px rgba(0, 0, 0, 0.5)': '0 0 10px rgba(255, 255, 255, 1)'}`,
            }}
          border={`2px solid ${colorMode==='light'?'black':'white'}`}
          borderColor={formik.touched.cpfMotorista && formik.values.cpfMotorista.length>0 && formik.errors.cpfMotorista ? 'red' : undefined}
          maxLength={11}
          minLength={11}
          />
          <Input
          w={'250px'}
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
          <Input
          w={'250px'}
          name='dtHoraInicio'
          onChange={formik.handleChange}
          onBlur={(e) => {
            formik.handleBlur(e);
            formik.setFieldError('dtHoraInicio', '');
          }}
          color={`${colorMode === 'light' ? 'black' : 'white'}`}
          borderRadius={'10px'}
          pr='4.5rem' 
          padding={'15px'}
          placeholder='Data e hora'
          _hover={{
            boxShadow: `${colorMode==='light'?'0 0 10px rgba(0, 0, 0, 0.5)': '0 0 10px rgba(255, 255, 255, 1)'}`,
            }}
          border={`2px solid ${colorMode==='light'?'black':'white'}`}
          borderColor={formik.touched.dtHoraInicio && formik.values.dtHoraInicio.length>0 && formik.errors.dtHoraInicio ? 'red' : undefined}
          type="datetime-local"
          />
          <Button h='1.75rem' size='sm' leftIcon={<IoMdSearch />} type='submit'> Buscar</Button>
      </InputGroup>
      {formik.touched.cpfPassag && formik.errors.cpfPassag && formik.values.cpfPassag.length>0 ? (
        <div style={{ color: 'red' }}>{formik.errors.cpfPassag}</div>
      ) : null}
      {formik.touched.cpfMotorista && formik.errors.cpfMotorista && formik.values.cpfMotorista.length>0 ? (
        <div style={{ color: 'red' }}>{formik.errors.cpfMotorista}</div>
      ) : null}
      {formik.touched.placa && formik.errors.placa && formik.values.placa.length>0 ? (
        <div style={{ color: 'red' }}>{formik.errors.placa}</div>
      ) : null}
      {formik.touched.dtHoraInicio && formik.errors.dtHoraInicio && formik.values.dtHoraInicio.length>0 ? (
        <div style={{ color: 'red' }}>{formik.errors.dtHoraInicio}</div>
      ) : null}
    </form>
    <Tabela dadosTabela={dadosTabela} />
  </>
}

export default Viagem