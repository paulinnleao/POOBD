import React from "react";
import axios from 'axios';
import * as Yup from 'yup';
import { 
    Modal,
    ModalOverlay,
    ModalContent,
    ModalHeader,
    ModalBody,
    FormControl,
    FormLabel,
    Input,
    ModalFooter,
    Button,
    useDisclosure,
    Heading,
    FormErrorMessage,
 } from "@chakra-ui/react";
import { MotoristaVeiculoDTO, MotoristaVeiculoModalProps } from "../../../utils/Interfaces";
import { useFormik } from "formik";
import { toast } from "react-toastify";

export const ModalMotoristaVeiculo: React.FC<MotoristaVeiculoModalProps> = ({motoristaVeiculo, atualizarPagina, editEntity, setEditEntity, setAtualizarPagina}) => {


  const formik = useFormik({
    initialValues:{
      cpfMotorista: motoristaVeiculo?.cpfMotorista,
      placa: motoristaVeiculo?.placa,
    },
    onSubmit: async (values) => {
      try{
        const response = await axios.put<MotoristaVeiculoDTO>(`http://localhost:8080/motoristas-veiculos`,values)
        
        toast.success(`Motorista de cpf ${response?.data?.cpfMotorista} atualizado para placa ${response?.data?.placa}!`,{
          position: 'bottom-center',
          theme: "colored"
        })
      }catch(err){
        toast.error('Erro ao atualizar motorista de veículo!',{
          position: 'bottom-center',
          theme: "colored"
        })
      } finally {
        sairModal();
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
  });

  const { onClose } = useDisclosure()

  const initialRef = React.useRef(null)
  const finalRef = React.useRef(null)

  const sairModal = () => {
    if(editEntity){
      setEditEntity?.({
        editar: false,
        identificadores: -1
      });
    }
  }
    return (
    <form onSubmit={formik.handleSubmit}>
      <Modal
        size='lg' 
        initialFocusRef={initialRef}
        finalFocusRef={finalRef}
        isOpen={editEntity?.editar}
        onClose={onClose}
        >
        <ModalOverlay />
        <ModalContent>
          <ModalHeader textAlign={'center'}><Heading> Editar Motorista</Heading></ModalHeader>
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
                    disabled/>
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
