import React, { useEffect, useState } from 'react';
import CardsMenu from '../../CardsMenu';
import Tabela from '../../../utils/Tabela';
import titulos from '../../../utils/titulos.json';
import { PessoaDTO } from '../../../utils/Interfaces';
import axios from 'axios';
import { Button, CircularProgress, Input, InputGroup, useColorMode } from '@chakra-ui/react';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import { toast } from 'react-toastify';
import { IoMdSearch } from "react-icons/io";

const Pessoa = () => {

  const [pessoas, setPessoas] = useState<PessoaDTO[]>([]);
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
        const response = await axios.get<PessoaDTO>(`http://localhost:8080/pessoas/${values.cpf}`);
        console.log('Pessoa encontrado:', response.data);
        setPessoas([response.data])
      } catch (error) {
        console.error('Erro ao buscar pessoa:', error);
        toast.error('Pessoa não encontrada!', {
          position: 'top-center',
          theme: "colored"
        });
      } finally {
        setSubmitting(false);
      }
    },
  });

  useEffect(()=>{
    const fetchPessoa = async () => {
      try{
        const response = await axios.get<PessoaDTO[]>('http://localhost:8080/pessoas');
        setPessoas(response.data);
      } catch (err) {
        setError('Erro ao buscar pessoas');
      } finally {
        setLoading(false);
      }

    }
    fetchPessoa();
  }, [formik.values]);

  if (loading) return <CircularProgress isIndeterminate color='blue.300' />;
  if (error) return <div>{error}</div>;


  const dadosTabela = {
    titulos: titulos.pessoa,
    dados: pessoas.map((pessoa)=>[
      pessoa.cpfPessoa.toString(),
      pessoa.eMail,
      pessoa.endereco,
      pessoa.telefone.toString(),
      pessoa.sexo,
      pessoa.eMail,
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
          placeholder='Buscar pessoa'
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

export default Pessoa