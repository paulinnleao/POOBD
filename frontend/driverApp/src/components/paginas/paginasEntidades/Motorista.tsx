import React, { useEffect, useState } from 'react';
import Tabela from '../../utils/Tabela';
import CardsMenu from '../CardsMenu';
import titulos from '../../utils/titulos.json'
import { MotoristaDTO } from '../../utils/Interfaces';
import axios from 'axios';
import { IoMdSearch } from "react-icons/io";
import { CircularProgress, Button, InputGroup, Input, useColorMode } from '@chakra-ui/react';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import { toast } from 'react-toastify';

const Motorista = () => {
    const {colorMode} = useColorMode();
    const [motoristas, setMotoristas] = useState<MotoristaDTO[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

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
          const response = await axios.get<MotoristaDTO>(`http://localhost:8080/motoristas/${values.cpf}`);
          console.log('Motorista encontrado:', response.data);
          setMotoristas([response.data])
        } catch (error) {
          console.error('Erro ao buscar motorista:', error);
          toast.error('Motorista não encontrado!', {
            position: 'top-center',
            theme: "colored"
          });
        } finally {
          setSubmitting(false);
        }
      },
    });

    useEffect(() => {
      const fetchMotoristas = async () => {
        try {
          const response = await axios.get<MotoristaDTO[]>('http://localhost:8080/motoristas');
          setMotoristas(response.data);
        } catch (err) {
          setError('Erro ao buscar motoristas');
        } finally {
          setLoading(false);
        }
      };
  
      fetchMotoristas();
    }, [formik.values.cpf]);
  
    if (loading) return <CircularProgress isIndeterminate color='blue.300' />;
    if (error) return <div>{error}</div>;

    
    const dadosTabela = {
            titulos: titulos.motorista,
            dados:motoristas.map((motorista) => [
                    motorista.cpfMotorista,
                    motorista.cnh,
                    motorista.bancoMot,
                    motorista.agenciaMot,
                    motorista.contaMot,
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
          placeholder='Buscar motorista'
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

export default Motorista