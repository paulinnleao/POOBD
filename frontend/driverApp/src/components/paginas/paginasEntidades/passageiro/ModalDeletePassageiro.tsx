import React from 'react'
import { PassageiroModalDelete } from '../../../utils/Interfaces'
import { AlertDialog, AlertDialogBody, AlertDialogContent, AlertDialogHeader, AlertDialogOverlay, Button, Heading, useDisclosure } from '@chakra-ui/react'
import axios from 'axios';
import { toast } from 'react-toastify';

const ModalDeletePassageiro: React.FC<PassageiroModalDelete> = ({
    passageiro,
    atualizarPagina, 
    deleteEntity,
    setDeleteEntity, 
    setAtualizarPagina
}) => {

    const deleteEntityFunction = async () =>{
        try{
            const response = await axios.delete(`http://localhost:8080/passageiros/${passageiro?.cpfPassg}`);
            setAtualizarPagina(!atualizarPagina);
            toast.success(`Passageiro de CPF: ${passageiro} deletado com sucesso! `+response?.data,{
                position: 'top-center',
                theme: "colored"
              });
        }catch(err){
            toast.error('Erro ao tentar deletar o passageiro. Possivelmente este passageiro possui viagem.',{
                position: 'top-center',
                theme: "colored"
              });
              toast.error('Verifique ou contate o suporte.',{
                position: 'top-center',
                theme: "colored"
              });
        }finally{
            setDeleteEntity({
                deletar:false,
                identificador: -1
            })
            setAtualizarPagina(!atualizarPagina)
        }
    }

    const cancelRef = React.useRef(null)

    const limparDelete = () => {
        if(!!deleteEntity){
            setDeleteEntity?.({
                deletar: false,
                identificador: -1
            });
        }
    }
    const { onClose } = useDisclosure()
    return <AlertDialog 
            isOpen={deleteEntity.deletar}
            leastDestructiveRef={cancelRef}
            onClose={onClose}
        >
        <AlertDialogOverlay>
            <AlertDialogContent>
                <AlertDialogHeader fontSize='lg' fontWeight='bold' textAlign={'center'}>
                    <Heading>Deletar Passageiro</Heading>
                </AlertDialogHeader>

                <AlertDialogBody textAlign={'center'}>
                    Você tem certerteza que quer <br /> deletar o passageiro de <strong>CPF:</strong> {passageiro?.cpfPassg}?
                </AlertDialogBody>

                <AlertDialogBody textAlign={'center'}>
                    <Button ref={cancelRef} onClick={limparDelete}>
                        Cancel
                    </Button>
                    <Button  onClick={deleteEntityFunction} colorScheme='red' ml={3}>
                        Delete
                    </Button>
                </AlertDialogBody>
            </AlertDialogContent>
        </AlertDialogOverlay>
        </AlertDialog>
}

export default ModalDeletePassageiro