import { useFormik } from 'formik';
import React from 'react';
import * as Yup from 'yup';
import axios from 'axios';
import { Modal, ModalOverlay, ModalContent, ModalHeader, Heading, ModalBody, FormControl, FormLabel, Input, FormErrorMessage, ModalFooter, Button, useDisclosure } from '@chakra-ui/react';
import { MotoristaVeiculoModalCreateProps } from '../../../utils/Interfaces';
import { toast } from 'react-toastify';

const ModalDeCriacaoMotoristaVeiculo: React.FC<MotoristaVeiculoModalCreateProps> = ({createEntity, atualizarPagina, setCreateEntity, setAtualizarPagina}) => {
    const formik = useFormik({
        initialValues:{
            cpfMotorista: '',
            placa: '',
        },
        onSubmit: async (values) => {
            try{
                const response = await axios.post('http://localhost:8080/motoristas-veiculos',values);
                toast.success('Motorista de veiculo cadastrado com sucesso!',{
                    position: 'top-center',
                    theme: "colored"
                })
                toast.success(`CPF: ${response?.data?.cpfMotorista}`,{
                    position: 'top-center',
                    theme: "colored"
                })
            }catch(err){
                toast.error('Erro ao cadastrar motorista de veiculo - '+err,{
                    position: 'top-center',
                    theme: "colored"
                })
                toast.error('Verifique se o motorista está cadastrado',{
                    position: 'top-center',
                    theme: "colored"
                })
            }finally{
                sairModal
                setAtualizarPagina(!atualizarPagina);
            }
        },
        validationSchema: Yup.object().shape({
          cpfMotorista: Yup.string()
                           .required('O CPF é Obrigatório')
                           .matches(/^[0-9]*$/, 'Apenas números são permitidos')
                           .min(11, 'CPF deve ter pelo menos 11 dígitos!')
                           .max(11, 'CPF deve ter no máximo 11 dígitos!'),
         placa: Yup.string()
                           .matches(/^[A-Z]{3}\d{1}[A-Z]{1}\d{2}$/, 'A placa deve seguir o formato AAA1A23')
                           .required('Placa é obrigatória')
    
    
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
            <ModalHeader textAlign={'center'}><Heading> Cadastrar Motorista</Heading></ModalHeader>
            <ModalBody pb={6}>

                <FormControl isInvalid={!!formik.errors.cpfMotorista} textAlign={'center'}>
                <FormLabel 
                    paddingTop={'10px'}
                    display={'flex'} 
                    margin={'auto'}
                    justifyContent={'space-between'} 
                    gap={'10px'} w={'400px'} 
                    fontSize={'20px'} >CPF
                    <Input
                        name="cpfMotorista"
                        w={'200px'}
                        placeholder='CPF'
                        value={formik.values.cpfMotorista}
                        onChange={formik.handleChange}
                        />
                    </FormLabel>                
                </FormControl>

                <FormControl isInvalid={!!formik.errors.placa} textAlign={'center'}>
                    <FormLabel 
                    paddingTop={'10px'}
                    display={'flex'} 
                    margin={'auto'}
                    justifyContent={'space-between'} 
                    gap={'10px'} w={'400px'} 
                    fontSize={'20px'} >CNH
                    <Input
                        name="placa"
                        w={'200px'}
                        placeholder={'PLACA'}
                        value={formik.values.placa}
                        onChange={formik.handleChange}
                        maxLength={7} minLength={7}
                        borderColor={
                        formik.touched.placa 
                        && formik.errors.placa ? 'red' : undefined}
                        />
                    </FormLabel>
                    {formik.touched.placa && !formik.errors.placa ?
                            null: (
                            <FormErrorMessage>{formik.errors.placa}</FormErrorMessage>
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

export default ModalDeCriacaoMotoristaVeiculo