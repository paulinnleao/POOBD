import React, { useEffect, useState } from 'react';
import CardsMenu from '../../CardsMenu';
import Tabela from '../../../utils/Tabela';
import { DeleteEntity, EditEntity, PassageiroDTO } from '../../../utils/Interfaces';
import axios from 'axios';
import { CircularProgress, Input, InputGroup, useColorMode, Button } from '@chakra-ui/react';
import titulos from '../../../utils/titulos.json';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import { toast } from 'react-toastify';
import { IoMdSearch } from "react-icons/io";
import ModalDeCriacaoPassageiro from './ModalDeCriacaoPassageiro';
import ModalDeletePassageiro from './ModalDeletePassageiro';
import { ModalPassageiro } from './ModalPassageiro';

const Passageiro = () => {

  const [passageiros, setPassageiros] = useState<PassageiroDTO[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const {colorMode} = useColorMode();

  
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
      cpf: '',
    },
    validationSchema: Yup.object().shape({
      cpf: Yup.string().required('Campo Obrigatório').matches(/^[0-9]*$/, 'Apenas números são permitidos')
      .min(11, 'CPF deve ter pelo menos 11 dígitos!')
      .max(11, 'CPF deve ter no máximo 11 dígitos!')
    }),
    onSubmit: async (values, { setSubmitting, setErrors }) => {
      try {
        const response = await axios.get<PassageiroDTO>(`http://localhost:8080/passageiros/${values.cpf}`);
        console.log('Passageiro encontrado:', response.data);
        setPassageiros([response.data])
      } catch (error) {
        console.error('Erro ao buscar passageiro:', error);
        toast.error('Passageiro não encontrado!', {
          position: 'top-center',
          theme: "colored"
        });
      } finally {
        setSubmitting(false);
      }
    },
  });

  useEffect(() => {
    const fetchPassageiros = async () => {
      try{
        const response = await axios.get<PassageiroDTO[]>('http://localhost:8080/passageiros');
        setPassageiros(response.data);
      } catch (err) {
        setError('Erro ao buscar os passageiros');
      } finally {
        setLoading(false);
      }
    }
    
    fetchPassageiros();
  }, [formik.values]);

  if (loading) return <CircularProgress isIndeterminate color='blue.300' />;
  if (error) return <div>{error}</div>;
  const dadosTabela = {
    titulos: titulos.passageiro,
    dados:passageiros.map((passageiro)=>[
      passageiro.cpfPassg.toString(),
      passageiro.cartaoCred,
      passageiro.bandeiraCartao,
      passageiro.cidadeOrig,
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
          placeholder='Buscar passageiro'
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
    <Tabela 
      dadosTabela={dadosTabela}
      setCreateEntity={setCreateEntity}
      setDeleteEntity={setDeleteEntity}
      setEditEntity={setEditEntity}/>
      
    {!!editEntity && 
    <ModalPassageiro 
    passageiro={passageiros[editEntity?.identificadores]} 
    atualizarPagina={atualizarPagina} 
    editEntity={editEntity}
    setEditEntity={setEditEntity} 
    setAtualizarPagina={setAtualizarPagina}
    />}
    {!!deleteEntity && 
    <ModalDeletePassageiro 
      passageiro={passageiros[deleteEntity?.identificador]}
      atualizarPagina={atualizarPagina}
      deleteEntity={deleteEntity}
      setDeleteEntity={setDeleteEntity}
      setAtualizarPagina={setAtualizarPagina}
    />}
    {createEntity && 
    <ModalDeCriacaoPassageiro 
      createEntity={createEntity} 
      atualizarPagina={atualizarPagina}
      setCreateEntity={setCreateEntity}
      setAtualizarPagina={setAtualizarPagina} />}
  </>
}

export default Passageiro