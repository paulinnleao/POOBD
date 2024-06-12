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
import { PassageiroDTO, PassageiroModalProps } from "../../../utils/Interfaces";
import { useFormik } from "formik";
import { toast } from "react-toastify";

export const ModalPassageiro: React.FC<PassageiroModalProps> = ({passageiro, atualizarPagina, editEntity, setEditEntity, setAtualizarPagina}) => {


  const formik = useFormik({
    initialValues:{
      cpfPassg: passageiro?.cpfPassg,
      cartaoCred: passageiro?.cartaoCred,
      bandeiraCartao: passageiro?.bandeiraCartao,
      cidadeOrig: passageiro?.cidadeOrig,
    },
    onSubmit: async (values) => {
      try{
        const response = await axios.put<PassageiroDTO>(`http://localhost:8080/passageiros`,values)
        
        toast.success(`Passageiro de cpf ${response?.data?.cpfPassg}!`,{
          position: 'bottom-center',
          theme: "colored"
        })
      }catch(err){
        toast.error('Erro ao atualizar passageiro!',{
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
      cartaoCred: Yup.string()
                     .required('Cartão de Crédito é obrigatório')
                     .matches(/^[0-9]*$/, 'Apenas números são permitidos'),
      bandeiraCartao: Yup.string()
                         .required('Bandeira do cartão é obrigatório'),
      cidadeOrig: Yup.string()
                     .required('Cidade de origem é obrigatório'),
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
          <ModalHeader textAlign={'center'}><Heading> Editar Passageiro</Heading></ModalHeader>
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
                    value={formik.values.cpfPassg}
                    onChange={formik.handleChange}
                    disabled/>
                </FormLabel>                
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
                fontSize={'20px'} >Cidade Origem
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
