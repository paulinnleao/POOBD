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
    RadioGroup,
    Radio,
 } from "@chakra-ui/react";
import { PessoaDTO, PessoaModalProps } from "../../../utils/Interfaces";
import { useFormik } from "formik";
import { toast } from "react-toastify";

export const ModalPessoa: React.FC<PessoaModalProps> = ({pessoa, atualizarPagina, editEntity, setEditEntity, setAtualizarPagina}) => {


  const formik = useFormik({
    initialValues:{
      cpfPessoa: pessoa?.cpfPessoa,
      nome: pessoa?.nome,
      endereco: pessoa?.endereco,
      telefone: pessoa?.telefone,
      sexo: pessoa?.sexo,
      eMail: pessoa?.eMail
    },
    onSubmit: async (values) => {
      try{
        const response = await axios.put<PessoaDTO>(`http://localhost:8080/pessoas`,values)
        
        toast.success(`Pessoa de cpf ${response?.data?.cpfPessoa} atualizado com sucesso!`,{
          position: 'bottom-center',
          theme: "colored"
        })
      }catch(err){
        toast.error('Erro ao atualizar pessoa!',{
          position: 'bottom-center',
          theme: "colored"
        })
      } finally {
        sairModal();
        setAtualizarPagina(!atualizarPagina);
      }
    },
    validationSchema: Yup.object().shape({
      cpfPessoa: Yup.string()
                       .required('O CPF é Obrigatório')
                       .matches(/^[0-9]*$/, 'Apenas números são permitidos')
                       .min(11, 'CPF deve ter pelo menos 11 dígitos!')
                       .max(11, 'CPF deve ter no máximo 11 dígitos!'),
      nome: Yup.string()
               .required('Nome é obrigatório')
               .matches(
                /^([A-Za-z\u00C0-\u00D6\u00D8-\u00f6\u00f8-\u00ff\s]*)$/gi,
                    'Nome pode ter apenas letras.'
                )
               .matches(/^\s*[\S]+(\s[\S]+)+\s*$/gms,'Por favor, insira seu nome completo.'),
      endereco: Yup.string()
                   .required('Endereço é obrigatório'),
      telefone: Yup.string()
                   .required('Telefone é obrigatório')
                   .matches(/^[0-9]*$/, 'Apenas números são permitidos')
                   .min(11, 'Telefone deve ter pelo menos 11 dígitos!')
                   .max(11, 'Telefone deve ter no máximo 11 dígitos!'),
      eMail: Yup.string()
                .required('Email é obrigatório')
                .email('Email inválido'),
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
          <ModalHeader textAlign={'center'}><Heading> Editar Pessoa</Heading></ModalHeader>
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
                    disabled/>
                </FormLabel>                
            </FormControl>

            <FormControl isInvalid={!!formik.errors.nome} textAlign={'center'}>
                <FormLabel 
                paddingTop={'10px'}
                display={'flex'} 
                margin={'auto'}
                justifyContent={'space-between'} 
                gap={'10px'} w={'400px'} 
                fontSize={'20px'} >Nome
                  <Input
                    name="nome"
                    w={'200px'}
                    placeholder={'Nome'}
                    value={formik.values.nome}
                    onChange={formik.handleChange}
                    maxLength={11} minLength={11}
                    borderColor={
                      formik.touched.nome 
                      && formik.errors.nome ? 'red' : undefined}
                    />
                </FormLabel>
                {formik.touched.nome && !formik.errors.nome ?
                        null: (
                        <FormErrorMessage>{formik.errors.nome}</FormErrorMessage>
                      )}
            </FormControl>
            
            <FormControl isInvalid={!!formik.errors.endereco} textAlign={'center'}>
                <FormLabel 
                paddingTop={'10px'}
                display={'flex'} 
                margin={'auto'}
                justifyContent={'space-between'} 
                gap={'10px'} w={'400px'} 
                fontSize={'20px'} >Endereço
                  <Input
                    name="endereco"
                    w={'200px'}
                    placeholder={'Endereço'}
                    value={formik.values.endereco}
                    onChange={formik.handleChange}
                    />
                </FormLabel>
                {formik.touched.endereco && !formik.errors.endereco ?
                            null: (
                            <FormErrorMessage>{formik.errors.endereco}</FormErrorMessage>
                          )}
            </FormControl>
            
            <FormControl isInvalid={!!formik.errors.telefone} textAlign={'center'}>
                <FormLabel 
                paddingTop={'10px'}
                display={'flex'} 
                margin={'auto'}
                justifyContent={'space-between'} 
                gap={'10px'} w={'400px'} 
                fontSize={'20px'} >Telefone
                  <Input
                    name="telefone"
                    w={'200px'}
                    placeholder={'(99) 99999-9999'}
                    value={formik.values.telefone}
                    onChange={formik.handleChange}
                    />
                </FormLabel>
                {formik.touched.telefone && !formik.errors.telefone ?
                            null: (
                            <FormErrorMessage>{formik.errors.telefone}</FormErrorMessage>
                          )}
              </FormControl>
              <RadioGroup name="sexo" textAlign={'center'}>
                <Radio>Feminino</Radio>
                <Radio>Masculino</Radio>
                
                {formik.touched.sexo && !formik.errors.sexo ?
                            null: (
                            <FormErrorMessage>{formik.errors.sexo}</FormErrorMessage>
                          )}
              </RadioGroup>
              {/* <FormControl isInvalid={!!formik.errors.sexo} textAlign={'center'}>
                <FormLabel 
                  paddingTop={'10px'}
                  display={'flex'} 
                  margin={'auto'}
                  justifyContent={'space-between'} 
                  gap={'10px'} w={'400px'} 
                  fontSize={'20px'} >Sexo
                    <Input
                      name="sexo"
                      w={'200px'}
                      placeholder={'Sexo'}
                      value={formik.values.sexo}
                      onChange={formik.handleChange}
                      />
                </FormLabel>
                {formik.touched.sexo && !formik.errors.sexo ?
                            null: (
                            <FormErrorMessage>{formik.errors.sexo}</FormErrorMessage>
                          )}
              </FormControl> */}
              
              <FormControl isInvalid={!!formik.errors.eMail} textAlign={'center'}>
                <FormLabel 
                  paddingTop={'10px'}
                  display={'flex'} 
                  margin={'auto'}
                  justifyContent={'space-between'} 
                  gap={'10px'} w={'400px'} 
                  fontSize={'20px'} >E-mail
                    <Input
                      name="eMail"
                      w={'200px'}
                      placeholder={'email@exemplo.com'}
                      value={formik.values.eMail}
                      onChange={formik.handleChange}
                      />
                </FormLabel>
                {formik.touched.eMail && !formik.errors.eMail ?
                            null: (
                            <FormErrorMessage>{formik.errors.eMail}</FormErrorMessage>
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
