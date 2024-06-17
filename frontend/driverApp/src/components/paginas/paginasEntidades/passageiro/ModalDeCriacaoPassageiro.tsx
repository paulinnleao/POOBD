import { useFormik } from 'formik';
import React from 'react';
import * as Yup from 'yup';
import axios from 'axios';
import { Modal, ModalOverlay, ModalContent, ModalHeader, Heading, ModalBody, FormControl, FormLabel, Input, FormErrorMessage, ModalFooter, Button, useDisclosure } from '@chakra-ui/react';
import { PassageiroModalCreateProps } from '../../../utils/Interfaces';
import { toast } from 'react-toastify';

const ModalDeCriacaoPassageiro: React.FC<PassageiroModalCreateProps> = ({createEntity, atualizarPagina, setCreateEntity, setAtualizarPagina}) => {
    const formik = useFormik({
        initialValues:{
            cpfPassg: '',
            cartaoCred: '',
            bandeiraCartao: '',
            cidadeOrig: '',
        },
        onSubmit: async (values) => {
            try{
                const response = await axios.post('http://localhost:8080/passageiros',values);
                toast.success('Passageiro cadastrado com sucesso!',{
                    position: 'top-center',
                    theme: "colored"
                })
                toast.success(`CPF: ${response?.data?.cpfPassg}`,{
                    position: 'top-center',
                    theme: "colored"
                })
            }catch(err){
                toast.error('Erro ao cadastrar passageiro - '+err,{
                    position: 'top-center',
                    theme: "colored"
                })
                toast.error('Verifique se o passageiro está cadastrado',{
                    position: 'top-center',
                    theme: "colored"
                })
            }finally{
                sairModal
                setAtualizarPagina(!atualizarPagina);
            }
        },
        validationSchema: Yup.object().shape({
            cpfPassg: Yup.string()
                             .required('O CPF é Obrigatório')
                             .matches(/^[0-9]*$/, 'Apenas números são permitidos')
                             .min(11, 'CPF deve ter pelo menos 11 dígitos!')
                             .max(11, 'CPF deve ter no máximo 11 dígitos!'),
            cartaoCred: Yup.string()
                           .required('Cartão de Crédito é obrigatório')
                           .matches(/^[0-9]*$/, 'Apenas números são permitidos'),
            bandeiraCartao: Yup.string()
                               .required('Bandeira do cartão é obrigatório'),
            cidadeOrig: Yup.string()
                           .required('Cidade de origem é obrigatório'),
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
            <ModalHeader textAlign={'center'}><Heading> Cadastrar Passageiro</Heading></ModalHeader>
                <ModalBody pb={6}>

                <FormControl isInvalid={!!formik.errors.cpfPassg} textAlign={'center'}>
                <FormLabel 
                    paddingTop={'10px'}
                    display={'flex'} 
                    margin={'auto'}
                    justifyContent={'space-between'} 
                    gap={'10px'} w={'400px'} 
                    fontSize={'20px'} >CPF
                    <Input
                        name="cpfPassg"
                        w={'200px'}
                        placeholder='CPF'
                        minLength={11} maxLength={11}
                        value={formik.values.cpfPassg}
                        onChange={formik.handleChange}
                        />
                    </FormLabel>        
                    {formik.touched.cpfPassg && !formik.errors.cpfPassg ?
                            null: (
                            <FormErrorMessage>{formik.errors.cpfPassg}</FormErrorMessage>
                        )}        
                </FormControl>
                <FormControl isInvalid={!!formik.errors.cartaoCred} textAlign={'center'}>
                    <FormLabel 
                    paddingTop={'10px'}
                    display={'flex'} 
                    margin={'auto'}
                    justifyContent={'space-between'} 
                    gap={'10px'} w={'400px'} 
                    fontSize={'20px'} >Cartão de Crédito
                    <Input
                        name="cartaoCred"
                        w={'200px'}
                        placeholder={'Cartão de Crédito'}
                        value={formik.values.cartaoCred}
                        onChange={formik.handleChange}
                        borderColor={
                        formik.touched.cartaoCred 
                        && formik.errors.cartaoCred ? 'red' : undefined}
                        />
                    </FormLabel>
                    {formik.touched.cartaoCred && !formik.errors.cartaoCred ?
                            null: (
                            <FormErrorMessage>{formik.errors.cartaoCred}</FormErrorMessage>
                        )}
                </FormControl>

                <FormControl isInvalid={!!formik.errors.bandeiraCartao} textAlign={'center'}>
                    <FormLabel 
                    paddingTop={'10px'}
                    display={'flex'} 
                    margin={'auto'}
                    justifyContent={'space-between'} 
                    gap={'10px'} w={'400px'} 
                    fontSize={'20px'} >Bandeira do Cartão
                    <Input
                        name="bandeiraCartao"
                        w={'200px'}
                        placeholder={'Bandeira do Cartão'}
                        value={formik.values.bandeiraCartao}
                        onChange={formik.handleChange}
                        borderColor={
                        formik.touched.bandeiraCartao 
                        && formik.errors.bandeiraCartao ? 'red' : undefined}
                        />
                    </FormLabel>
                    {formik.touched.bandeiraCartao && !formik.errors.bandeiraCartao ?
                            null: (
                            <FormErrorMessage>{formik.errors.bandeiraCartao}</FormErrorMessage>
                        )}
                </FormControl>

                <FormControl isInvalid={!!formik.errors.cidadeOrig} textAlign={'center'}>
                    <FormLabel 
                    paddingTop={'10px'}
                    display={'flex'} 
                    margin={'auto'}
                    justifyContent={'space-between'} 
                    gap={'10px'} w={'400px'} 
                    fontSize={'20px'} >Cidade de Origem
                    <Input
                        name="cidadeOrig"
                        w={'200px'}
                        placeholder={'Cidade Origem'}
                        value={formik.values.cidadeOrig}
                        onChange={formik.handleChange}
                        maxLength={7} minLength={7}
                        borderColor={
                        formik.touched.cidadeOrig 
                        && formik.errors.cidadeOrig ? 'red' : undefined}
                        />
                    </FormLabel>
                    {formik.touched.cidadeOrig && !formik.errors.cidadeOrig ?
                            null: (
                            <FormErrorMessage>{formik.errors.cidadeOrig}</FormErrorMessage>
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

export default ModalDeCriacaoPassageiro