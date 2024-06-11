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
    ModalCloseButton,
    FormErrorMessage
 } from "@chakra-ui/react";
import { MotoristaDTO, MotoristaModalProps } from "../../../utils/Interfaces";
import { useFormik } from "formik";
import { toast } from "react-toastify";

export const ModalMotorista: React.FC<MotoristaModalProps> = ({motorista, setEditEntity}) => {


  const formik = useFormik({
    initialValues:{
      cpfMotorista: motorista.cpfMotorista,
      cnh: motorista.cnh,
      bancoMot: motorista.bancoMot,
      agenciaMot: motorista.agenciaMot,
      contaMot: motorista.contaMot
    },
    onSubmit: values => {
      console.log(JSON.stringify(values, null, 2));
    },
    validationSchema: Yup.object().shape({
      cpfMotorista: Yup.string()
                       .required('O CPF é Obrigatório')
                       .matches(/^[0-9]*$/, 'Apenas números são permitidos')
                       .min(11, 'CPF deve ter pelo menos 11 dígitos!')
                       .max(11, 'CPF deve ter no máximo 11 dígitos!'),
      cnh: Yup.string()
              .matches(/^\d{11}$/, 'A CNH deve conter exatamente 11 dígitos numéricos.')
              .required('A CNH é obrigatória.'),
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
  });

  // const formik = useFormik({
  //   initialValues:{
  //     cpfMotorista: motorista.cpfMotorista,
  //     cnh: motorista.cnh,
  //     bancoMot: motorista.bancoMot,
  //     agenciaMot: motorista.agenciaMot,
  //     contaMot: motorista.contaMot
  //   },
  //   onSubmit: (values) => {
  //     console.log("asdfasdf")
  //   },
  //   validationSchema: Yup.object().shape({
  //     cpfMotorista: Yup.string()
  //                      .required('O CPF é Obrigatório')
  //                      .matches(/^[0-9]*$/, 'Apenas números são permitidos')
  //                      .min(11, 'CPF deve ter pelo menos 11 dígitos!')
  //                      .max(11, 'CPF deve ter no máximo 11 dígitos!'),
  //     cnh: Yup.string()
  //             .matches(/^\d{11}$/, 'A CNH deve conter exatamente 11 dígitos numéricos.')
  //             .required('A CNH é obrigatória.'),
  //     bancoMot: Yup.string()
  //                  .required('Banco é obrigatório')
  //                  .matches(/^[0-9]*$/, 'Apenas número são permitidos'),
  //     agenciaMot: Yup.string()
  //                    .required('Banco é obrigatório')
  //                    .matches(/^[0-9]*$/, 'Apenas número são permitidos'),
  //     contaMot: Yup.string()
  //                  .required('Banco é obrigatório')
  //                  .matches(/^[0-9]*$/, 'Apenas número são permitidos'),


  //   }),
  // });

  const { isOpen, onOpen, onClose } = useDisclosure()

  const initialRef = React.useRef(null)
  const finalRef = React.useRef(null)

  const sairModal = () => {
    setEditEntity(null);
  }
    return (
    <form onSubmit={formik.handleSubmit}>
      <Modal
        size='lg' 
        initialFocusRef={initialRef}
        finalFocusRef={finalRef}
        isOpen={!!motorista}
        onClose={onClose}
        >
        <ModalOverlay />
        <ModalContent>
          <ModalHeader textAlign={'center'}><Heading> Editando entidade</Heading></ModalHeader>
          <ModalBody pb={6}>
            <FormControl textAlign={'center'}>

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
                    />
                </FormLabel>

                {formik.touched.cnh && formik.errors.cnh ? (
                    <FormErrorMessage style={{ color: 'red' }}>{formik.errors.cnh}</FormErrorMessage>
                  ) : null}

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

                <FormLabel 
                  paddingTop={'10px'}
                  display={'flex'} 
                  margin={'auto'}
                  justifyContent={'space-between'} 
                  gap={'10px'} w={'400px'} 
                  fontSize={'20px'} >Conta
                    <Input
                      name="conta"
                      w={'200px'}
                      placeholder={'Conta'}
                      value={formik.values.contaMot}
                      onChange={formik.handleChange}
                      />
                </FormLabel>

                
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
