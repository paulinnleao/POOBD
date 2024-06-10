import React from "react";
import { 
    Modal,
    ModalOverlay,
    ModalContent,
    ModalHeader,
    ModalCloseButton,
    ModalBody,
    FormControl,
    FormLabel,
    Input,
    ModalFooter,
    Button,
    useDisclosure
 } from "@chakra-ui/react";
import { EditModal } from "./Interfaces";

export const ModalComponent: React.FC<EditModal & { setEditModal: React.Dispatch<React.SetStateAction<EditModal | null>> }> = ({identificador, setEditModal}) => {
    
    const { isOpen, onOpen, onClose } = useDisclosure()

    const initialRef = React.useRef(null)
    const finalRef = React.useRef(null)

    const renderFormControl = (label, value, editavel) => (
        <FormControl key={label}>
          <FormLabel>{label}</FormLabel>
          <Input
            placeholder={label}
            value={value}
            disabled={!editavel}
          />
        </FormControl>
      );

    return <Modal 
    initialFocusRef={initialRef}
    finalFocusRef={finalRef}
    isOpen={!!identificador}
    onClose={onClose}
    >
    <ModalOverlay />
    <ModalContent>
      <ModalHeader>Editando entidade</ModalHeader>
      <ModalCloseButton />
      <ModalBody pb={6}>
      {identificador && Object.entries(identificador).map(([label, { value, editavel }]) => {
        return renderFormControl(label, value, editavel);
        })}
      </ModalBody>
      <ModalFooter>
        <Button colorScheme='blue' mr={3}>
          Salvar
        </Button>

      </ModalFooter>
    </ModalContent>
</Modal>
}
