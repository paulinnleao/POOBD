import React, { useEffect, useState } from 'react';
import CardsMenu from '../CardsMenu';
import Tabela from '../../utils/Tabela';
import titulos from '../../utils/titulos.json';
import { TipoPgToDTO } from '../../utils/Interfaces';
import axios from 'axios';
import { Button, CircularProgress, Input, InputGroup, useColorMode } from '@chakra-ui/react';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import { toast } from 'react-toastify';
import { IoMdSearch } from "react-icons/io";

const TipoPgto = () => {

  const [tipoPgto, setTipoPgto] = useState<TipoPgToDTO[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const {colorMode} = useColorMode();

  const formik = useFormik({
    initialValues: {
      id: '',
    },
    validationSchema: Yup.object().shape({
      id: Yup.string().required('Campo Obrigatório').matches(/^[0-9]*$/, 'Apenas números são permitidos')
    }),
    onSubmit: async (values, { setSubmitting, setErrors }) => {
      try {
        const response = await axios.get<TipoPgToDTO>(`http://localhost:8080/tipos-pagamento/${values.id}`);
        console.log('Tipo de Pagamento encontrado:', response.data);
        setTipoPgto([response.data])
      } catch (error) {
        console.error('Erro ao buscar Tipo de Pagamento:', error);
        toast.error('Tipo de Pagamento não encontrado!', {
          position: 'top-center',
          theme: "colored"
        });
      } finally {
        setSubmitting(false);
      }
    },
  });

  useEffect(()=>{
    const fetchTipoPgto = async () => {
      try{
        const response = await axios.get<TipoPgToDTO[]>('http://localhost:8080/tipos-pagamento')
        setTipoPgto(response.data)
      } catch (err) {
        setError('Erro ao buscar os tipos de pagamento');
      }finally {
        setLoading(false);
      }
    }
    fetchTipoPgto();
  }, [formik.values]);


  if (loading) return <CircularProgress isIndeterminate color='blue.300' />;
  if (error) return <div>{error}</div>;


  const dadosTabela = {
    titulos : titulos.tipoPgto,
    dados: tipoPgto.map((tipo)=>[
      tipo.codPagto.toString(),
      tipo.descPagto,
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
          w={'300px'}
          name='id'
          onChange={formik.handleChange}
          onBlur={(e) => {
            formik.handleBlur(e);
            formik.setFieldError('id', '');
          }}
          color={`${colorMode === 'light' ? 'black' : 'white'}`}
          borderRadius={'10px'}
          pr='4.5rem' 
          padding={'15px'}
          placeholder='ID Tipo de Pagamento'
          _hover={{
            boxShadow: `${colorMode==='light'?'0 0 10px rgba(0, 0, 0, 0.5)': '0 0 10px rgba(255, 255, 255, 1)'}`,
            }}
          border={`2px solid ${colorMode==='light'?'black':'white'}`}
          borderColor={formik.touched.id && formik.values.id.length>0 && formik.errors.id ? 'red' : undefined}
          />
          <Button h='1.75rem' size='sm' leftIcon={<IoMdSearch />} type='submit'> Buscar</Button>
      </InputGroup>
      {formik.touched.id && formik.errors.id && formik.values.id.length>0 ? (
        <div style={{ color: 'red' }}>{formik.errors.id}</div>
      ) : null}
    </form>
    <Tabela dadosTabela={dadosTabela} />
  </>
}

export default TipoPgto