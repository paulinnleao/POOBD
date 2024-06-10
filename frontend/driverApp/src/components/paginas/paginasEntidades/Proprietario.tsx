import React, { useEffect, useState } from 'react'
import CardsMenu from '../CardsMenu'
import Tabela from '../../utils/Tabela'
import { Button, CircularProgress, Input, InputGroup, useColorMode } from '@chakra-ui/react';
import axios from 'axios';
import titulos from '../../utils/titulos.json';
import { ProprietarioDTO } from '../../utils/Interfaces';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import { toast } from 'react-toastify';
import { IoMdSearch } from "react-icons/io";
const Proprietario = () => {

    const [proprietarios, setProprietarios] = useState<ProprietarioDTO[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);
    const {colorMode} = useColorMode();
  
    const formik = useFormik({
      initialValues: {
        cpf: '',
      },
      validationSchema: Yup.object().shape({
        cpf: Yup.string().required('Campo Obrigatório').matches(/^[0-9]*$/, 'Apenas números são permitidos')
        .min(11, 'CPF deve ter pelo menos 11 dígitos!')
        .max(11, 'CPF deve ter no máximo 11 dígitos!')
      }),
      onSubmit: async (values, { setSubmitting, setErrors }) => {
        try {
          const response = await axios.get<ProprietarioDTO>(`http://localhost:8080/proprietarios/${values.cpf}`);
          console.log('Proprietario encontrado:', response.data);
          setProprietarios([response.data])
        } catch (error) {
          console.error('Erro ao buscar proprietario:', error);
          toast.error('Proprietario não encontrado!', {
            position: 'top-center',
            theme: "colored"
          });
        } finally {
          setSubmitting(false);
        }
      },
    });
  

    useEffect(()=>{
        const fetchProprietario = async () => {
          try{
            const response = await axios.get<ProprietarioDTO[]>('http://localhost:8080/proprietarios');
            setProprietarios(response.data);
          } catch (err) {
            setError('Erro ao buscar pessoas');
          } finally {
            setLoading(false);
          }
    
        }
        fetchProprietario();
      }, [formik.values]);
    
  if (loading) return <CircularProgress isIndeterminate color='blue.300' />;
  if (error) return <div>{error}</div>;
  
    const dadosTabela = {
        titulos: titulos.proprietario,
        dados: proprietarios.map((proprietario)=>[
            proprietario.cpfProp,
            proprietario.cnhProp,
            proprietario.bancoProp,
            proprietario.agenciaProp,
            proprietario.contaProp,
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
          name='cpf'
          onChange={formik.handleChange}
          onBlur={(e) => {
            formik.handleBlur(e);
            formik.setFieldError('cpf', '');
          }}
          color={`${colorMode === 'light' ? 'black' : 'white'}`}
          borderRadius={'10px'}
          pr='4.5rem' 
          padding={'15px'}
          placeholder='Buscar proprietário'
          _hover={{
            boxShadow: `${colorMode==='light'?'0 0 10px rgba(0, 0, 0, 0.5)': '0 0 10px rgba(255, 255, 255, 1)'}`,
            }}
          border={`2px solid ${colorMode==='light'?'black':'white'}`}
          borderColor={formik.touched.cpf && formik.values.cpf.length>0 && formik.errors.cpf ? 'red' : undefined}
          maxLength={11}
          minLength={11}
          />
          <Button h='1.75rem' size='sm' leftIcon={<IoMdSearch />} type='submit'> Buscar</Button>
      </InputGroup>
      {formik.touched.cpf && formik.errors.cpf && formik.values.cpf.length>0 ? (
        <div style={{ color: 'red' }}>{formik.errors.cpf}</div>
      ) : null}
    </form>
    <Tabela dadosTabela={dadosTabela} />
  </>
}

export default Proprietario