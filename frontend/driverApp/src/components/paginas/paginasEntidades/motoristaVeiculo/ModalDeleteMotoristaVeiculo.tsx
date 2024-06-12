import React from 'react'
import { MotoristaVeiculoModalDelete } from '../../../utils/Interfaces'
import { AlertDialog, AlertDialogBody, AlertDialogContent, AlertDialogHeader, AlertDialogOverlay, Button, Heading, useDisclosure } from '@chakra-ui/react'
import axios from 'axios';
import { toast } from 'react-toastify';

const ModalDeleteMotoristaVeiculo: React.FC<MotoristaVeiculoModalDelete> = ({
    motoristaVeiculo,
    atualizarPagina, 
    deleteEntity,
    setDeleteEntity, 
    setAtualizarPagina
}) => {

    const deleteEntityFunction = async () =>{
        try{
            const response = await axios.delete(`http://localhost:8080/motoristas-veiculos/id?cpfMotorista=${motoristaVeiculo?.cpfMotorista}&placa=${motoristaVeiculo?.placa}`);
            setAtualizarPagina(!atualizarPagina);
            toast.success(`Motorista de CPF: ${motoristaVeiculo} e placa ${motoristaVeiculo?.placa} deletado com sucesso! `+response?.data,{
                position: 'top-center',
                theme: "colored"
              });
        }catch(err){
            toast.error('Erro ao tentar deletar o motorista de veiculo. Possivelmente este motorista possui cadastro ou viagem.',{
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
                    <Heading>Deletar Motorista de Veiculo</Heading>
                </AlertDialogHeader>

                <AlertDialogBody textAlign={'center'}>
                    Você tem certerteza que quer <br /> deletar o motorista de <strong>CPF:</strong> {motoristaVeiculo?.cpfMotorista} <br/> e <strong>PLACA:</strong> {motoristaVeiculo?.placa}?
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

export default ModalDeleteMotoristaVeiculo