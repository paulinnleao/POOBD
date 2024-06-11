// Espécies de tipos
type Dado = string | number;









// Util
export interface listaDeCardsProps {
    listaDeCards: [{
        nome: string,
        image: string,
        to: string,
    }]
}
export interface DadosTabelaProps {
    dadosTabela: {
        titulos:string[],
        dados:Dado[][]
    },
    
    setEditEntity: React.Dispatch<React.SetStateAction<EditEntity | null>>;
}
export interface EditEntity{
    editar: boolean,
    identificadores: number,
}
export interface ItemMapProps {
    value: string,
    id: number
}




































// Interface das entidades
//Motorista
export interface MotoristaDTO {
    cpfMotorista: number;
    cnh: string;
    bancoMot: number;
    agenciaMot: number;
    contaMot: number;
  }
export interface MotoristaModalProps {
    motorista: MotoristaDTO,
    atualizarPagina: boolean,
    setEditEntity: React.Dispatch<React.SetStateAction<EditEntity | null>>;
    setAtualizarPagina: React.Dispatch<React.SetStateAction<boolean>>;
}

// Motorista Veiculo
export interface MotoristaVeiculoDTO {
    cpfMotorista: number,
    placa: string,
}
  
export interface PassageiroDTO {
    cpfPassg: number,
    cartaoCred: string,
    bandeiraCartao: string,
    cidadeOrig: string,
}

export interface PessoaDTO {
    cpfPessoa: number;
    nome: string;
    endereco: string;
    telefone: number;
    sexo: string;
    eMail: string;
}

export interface TipoPgToDTO {
    codPagto: number,
    descPagto: string,
}

export interface VeiculosDTO{
    placa: string;
    marca: string;
    modelo: string;
    anoFabric: number;
    capacidadePass: number;
    cor: string;
    tipoCombust: string;
    potenciaMotor: number;
    cpfProp: number;
}
export interface ViagensDTO{
    cpfPassag : number;
    cpfMotorista : number;
    placa : string;
    localOrigViag : string;
    localDestViag : string;
    dtHoraInicio : string;
    dtHoraFim : string;
    qtdePass : number;
    formaPagto : string;
    valorPagto : number;
    cancelamMot : string;
    cancelamPass : string;
    codPagto : number;
}

export interface ProprietarioDTO{
    cpfProp : number ;
    cnhProp : string ;
    bancoProp : number ;
    agenciaProp : number ;
    contaProp : number ;
}