import { useFormik } from 'formik';
import React from 'react';
import * as Yup from 'yup';
import axios from 'axios';
import { Modal, ModalOverlay, ModalContent, ModalHeader, Heading, ModalBody, FormControl, FormLabel, Input, FormErrorMessage, ModalFooter, Button, useDisclosure } from '@chakra-ui/react';
import { PessoaModalCreateProps } from '../../../utils/Interfaces';
import { toast } from 'react-toastify';

const ModalDeCriacao: React.FC<PessoaModalCreateProps> = ({createEntity, atualizarPagina, setCreateEntity, setAtualizarPagina}) => {
    const formik = useFormik({
        initialValues:{
            cpfPessoa: '',
            cnh: '',
            bancoMot: '',
            agenciaMot: '',
            contaMot: ''
        },
        onSubmit: async (values) => {
            try{
                const response = await axios.post('http://localhost:8080/pessoas',values);
                toast.success('Pessoa cadastrado com sucesso!',{
                    position: 'top-center',
                    theme: "colored"
                })
                toast.success(`CPF: ${response?.data?.cpfPessoa}`,{
                    position: 'top-center',
                    theme: "colored"
                })
            }catch(err){
                toast.error('Erro ao cadastrar pessoa - '+err,{
                    position: 'top-center',
                    theme: "colored"
                })
            }finally{
                setAtualizarPagina(!atualizarPagina);
                sairModal
            }
        },
        validationSchema: Yup.object().shape({
          cpfPessoa: Yup.string()
                           .required('O CPF é Obrigatório')
                           .matches(/^[0-9]*$/, 'Apenas números são permitidos')
                           .min(11, 'CPF deve ter pelo menos 11 dígitos!')
                           .max(11, 'CPF deve ter no máximo 11 dígitos!'),
          cnh: Yup.string()
                  .matches(/^\d{11}$/, 'CNH deve conter exatamente 11 dígitos numéricos.')
                  .required('CNH é obrigatória.'),
          bancoMot: Yup.string()
                       .required('Banco é obrigatório')
                       .matches(/^[0-9]*$/, 'Apenas número são permitidos'),
          agenciaMot: Yup.string()
                         .required('Banco é obrigatório')
                         .matches(/^[0-9]*$/, 'Apenas número são permitidos'),
          contaMot: Yup.string()
                       .required('Banco é obrigatório')
                       .matches(/^[0-9]*$/, 'Apenas número são permitidos'),
    
    
        }),
        enableReinitialize: true,
    })

    const sairModal = () =>{
        setCreateEntity(false);
    }

    const { onClose } = useDisclosure()

    const initialRef = React.useRef(null)
    const finalRef = React.useRef(null)

  return (
    <form onSubmit={formik.handleSubmit}>
        <Modal
            size='lg' 
            initialFocusRef={initialRef}
            finalFocusRef={finalRef}
            isOpen={createEntity}
            onClose={onClose}
            >
            <ModalOverlay />
            <ModalContent>
            <ModalHeader textAlign={'center'}><Heading> Cadastrar Pessoa</Heading></ModalHeader>
            <ModalBody pb={6}>

                <FormControl isInvalid={!!formik.errors.cpfPessoa} textAlign={'center'}>
                <FormLabel 
                    paddingTop={'10px'}
                    display={'flex'} 
                    margin={'auto'}
                    justifyContent={'space-between'} 
                    gap={'10px'} w={'400px'} 
                    fontSize={'20px'} >CPF
                    <Input
                        name="cpfPessoa"
                        w={'200px'}
                        placeholder='CPF'
                        value={formik.values.cpfPessoa}
                        onChange={formik.handleChange}
                        maxLength={11} minLength={11}
                        />
                    </FormLabel>                
                </FormControl>

                <FormControl isInvalid={!!formik.errors.cnh} textAlign={'center'}>
                    <FormLabel 
                    paddingTop={'10px'}
                    display={'flex'} 
                    margin={'auto'}
                    justifyContent={'space-between'} 
                    gap={'10px'} w={'400px'} 
                    fontSize={'20px'} >CNH
                    <Input
                        name="cnh"
                        w={'200px'}
                        placeholder={'CNH'}
                        value={formik.values.cnh}
                        onChange={formik.handleChange}
                        maxLength={11} minLength={11}
                        borderColor={
                        formik.touched.cnh 
                        && formik.errors.cnh ? 'red' : undefined}
                        />
                    </FormLabel>
                    {formik.touched.cnh && !formik.errors.cnh ?
                            null: (
                            <FormErrorMessage>{formik.errors.cnh}</FormErrorMessage>
                        )}
                </FormControl>
                
                <FormControl isInvalid={!!formik.errors.bancoMot} textAlign={'center'}>
                    <FormLabel 
                    paddingTop={'10px'}
                    display={'flex'} 
                    margin={'auto'}
                    justifyContent={'space-between'} 
                    gap={'10px'} w={'400px'} 
                    fontSize={'20px'} >Banco
                    <Input
                        name="bancoMot"
                        w={'200px'}
                        placeholder={'Banco'}
                        value={formik.values.bancoMot}
                        onChange={formik.handleChange}
                        />
                    </FormLabel>
                    {formik.touched.bancoMot && !formik.errors.bancoMot ?
                                null: (
                                <FormErrorMessage>{formik.errors.bancoMot}</FormErrorMessage>
                            )}
                </FormControl>
                
                <FormControl isInvalid={!!formik.errors.agenciaMot} textAlign={'center'}>
                    <FormLabel 
                    paddingTop={'10px'}
                    display={'flex'} 
                    margin={'auto'}
                    justifyContent={'space-between'} 
                    gap={'10px'} w={'400px'} 
                    fontSize={'20px'} >Agência
                    <Input
                        name="agenciaMot"
                        w={'200px'}
                        placeholder={'Agência'}
                        value={formik.values.agenciaMot}
                        onChange={formik.handleChange}
                        />
                    </FormLabel>
                    {formik.touched.agenciaMot && !formik.errors.agenciaMot ?
                                null: (
                                <FormErrorMessage>{formik.errors.agenciaMot}</FormErrorMessage>
                            )}
                </FormControl>

                <FormControl isInvalid={!!formik.errors.contaMot} textAlign={'center'}>
                    <FormLabel 
                    paddingTop={'10px'}
                    display={'flex'} 
                    margin={'auto'}
                    justifyContent={'space-between'} 
                    gap={'10px'} w={'400px'} 
                    fontSize={'20px'} >Conta
                        <Input
                        name="contaMot"
                        w={'200px'}
                        placeholder={'Conta'}
                        value={formik.values.contaMot}
                        onChange={formik.handleChange}
                        />
                    </FormLabel>
                    {formik.touched.contaMot && !formik.errors.contaMot ?
                                null: (
                                <FormErrorMessage>{formik.errors.contaMot}</FormErrorMessage>
                            )}
                </FormControl>
                
            </ModalBody>
            <ModalFooter>
                <Button colorScheme='red' mr={3}
                        onClick={sairModal}>
                    Cancelar
                </Button>

                <Button
                onClick={formik.submitForm}
                colorScheme='blue' mr={3} type='submit'>
                    Salvar
                </Button>
                </ModalFooter>
            </ModalContent>
       </Modal>
    </form>
  )
}

export default ModalDeCriacao