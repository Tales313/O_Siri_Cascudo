package aplicacao;

import java.util.ArrayList;

import fachada.Fachada;
import modelo.Conta;
import modelo.Garcom;
import modelo.Mesa;
import modelo.Pagamento;
import modelo.Produto;
 
public class Main {
    private static Produto feijoada;
    private static Produto bode;
    private static Produto galinhada;
    private static Produto cerveja;
    private static Produto refrigerante;
    private static Produto agua;
    private static Produto festa;
    private static double soma=0;
 
    public static void main (String[] args) {  
        listar();
        cadastrar();
        listar();
        atualizar();
        listar();
        erros();
        System.out.println("fim do teste");
    }
 
    public static void cadastrar(){
        try {   
            System.out.println("\n-------CADASTRO--------");
            feijoada    = Fachada.cadastrarProduto("feijoada", 25.0);
            bode        = Fachada.cadastrarProduto("bode guisado", 20.0);
            galinhada   = Fachada.cadastrarProduto("galinhada", 15.0);
            cerveja     = Fachada.cadastrarProduto("cerveja", 6.0);
            refrigerante = Fachada.cadastrarProduto("refrigerante", 5.0);
            agua        = Fachada.cadastrarProduto("agua", 2.0);
            festa       = Fachada.cadastrarProduto("festa", 400.0);
            Fachada.criarMesas(20);     // 20 mesas
            Garcom g;
            g = Fachada.cadastrarGarcom("baixinho", 1,5);
            g = Fachada.cadastrarGarcom("esperto", 6,10);
            g = Fachada.cadastrarGarcom("zezinho", 11,15);
        }catch (Exception e) {System.out.println("--->"+e.getMessage());}
 
    }
 
    public static void listar() {
        try {
            System.out.println("\n-------LISTAR--------");
            ArrayList<Produto> produtos = Fachada.listarProdutos("");
            System.out.println("\nprodutos cadastrados:");
            System.out.println(produtos);
            ArrayList<Mesa> mesas = Fachada.listarMesas();
            System.out.println("\nmesas criadas:");
            System.out.println(mesas);
            ArrayList<Garcom> garcons = Fachada.listarGarcons();
            System.out.println("\ngarcons cadastrados:");
            System.out.println(garcons);
            ArrayList<Conta> contas = Fachada.listarContas();
            System.out.println("\ncontas criadas:");
            System.out.println(contas);
 
        }catch (Exception e) {System.out.println("--->"+e.getMessage());}
 
    }
 
 
    public static void atualizar() {
        try {
            System.out.println("\n-------ATUALIZACAO--------");
            Fachada.criarConta(1);  //mesa 1
            Fachada.solicitarProduto(1, "galinhada");
            Fachada.solicitarProduto(1, "cerveja");
            Fachada.solicitarProduto(1, "refrigerante");
            System.out.println("conta da mesa 1: \n"+ Fachada.consultarConta(1)); 
            Fachada.solicitarProduto(1, "cerveja");
            Fachada.fecharConta(1);
            System.out.println("conta da mesa 1: \n"+ Fachada.consultarConta(1)); 
            soma = galinhada.getPreco()+2*cerveja.getPreco()+refrigerante.getPreco();
            if(Fachada.consultarConta(1).getTotal() == soma)
                System.out.println("soma da mesa 1 correta");
            else
                System.out.println("soma da mesa 1 errada ********");
 
             
 
 
 
            System.out.println("\ncriando contas 2 e 3...");
            Fachada.criarConta(2);  //mesa 2
            Fachada.criarConta(3);  //mesa 3
            Fachada.solicitarProduto(2, "agua");
            Fachada.solicitarProduto(2, "feijoada");
            Fachada.solicitarProduto(3, "cerveja");
            System.out.println("conta da mesa 2: \n"+ Fachada.consultarConta(2)); 
            System.out.println("conta da mesa 3: \n"+ Fachada.consultarConta(3)); 
            System.out.println("transferindo conta 2 para 3...");
            Fachada.transferirConta(2,3);
            Fachada.solicitarProduto(3, "refrigerante");
            System.out.println("conta da mesa 2: \n"+ Fachada.consultarConta(2)); 
            System.out.println("conta da mesa 3: \n"+ Fachada.consultarConta(3)); 
            Fachada.fecharConta(3);
            double somamesa3 = agua.getPreco()+cerveja.getPreco()+feijoada.getPreco()+refrigerante.getPreco();
            if(Fachada.consultarConta(3).getTotal() == somamesa3)
                System.out.println("soma da mesa 3 correta");
            else
                System.out.println("soma da mesa 3 errada ********"+somamesa3+ " - " +Fachada.consultarConta(3).getTotal() );
            soma = soma + somamesa3;
 
 
            System.out.println("\ncriando conta 4...");
            Fachada.criarConta(4);  //mesa 4
            Fachada.solicitarProduto(4, "cerveja");
            System.out.println("conta da mesa 4: \n"+ Fachada.consultarConta(4)); 
            System.out.println("cancelando a conta 4...");
            Fachada.cancelarConta(4);
            System.out.println("conta da mesa 4: \n"+ Fachada.consultarConta(4));           
            double gorjeta = Fachada.calcularGorjeta("baixinho");
            System.out.println("*********************"+gorjeta);
         
 
             
             
            //-----------PAGAMENTOS---------------------------
            Pagamento pag;
            Fachada.criarConta(6);  
            Fachada.solicitarProduto(6, "festa");
            Fachada.fecharConta(6);
            pag = Fachada.pagarConta(6,"dinheiro", 0, "", 0);
            System.out.println("\nconta da mesa 6: \n"+ Fachada.consultarConta(6));             
            Fachada.criarConta(7);  
            Fachada.solicitarProduto(7, "festa");
            Fachada.fecharConta(7);
            pag = Fachada.pagarConta(7,"dinheiro", 5, "", 0);
            System.out.println("\nconta da mesa 7: \n"+ Fachada.consultarConta(7));             
             
            System.out.println("\ngorjeta do esperto="+Fachada.calcularGorjeta("esperto"));
 
            Fachada.criarConta(8);
            Fachada.solicitarProduto(8, "festa");
            Fachada.fecharConta(8);
            pag = Fachada.pagarConta(8,"cartao", 0, "1111-1111-8888", 2);
            System.out.println("\nconta da mesa 8: \n"+ Fachada.consultarConta(8));             
            Fachada.criarConta(9);
            Fachada.solicitarProduto(9, "festa");
            Fachada.fecharConta(9);
            pag = Fachada.pagarConta(9,"cartao", 0, "1111-1111-9999", 4);
            System.out.println("\nconta da mesa 9: \n"+ Fachada.consultarConta(9));             
             
             
            System.out.println("\npercentual medio do garcom esperto:"+ Fachada.calcularPercentualMedio("esperto")); 
             
            System.out.println("\nexcluindo garcom esperto:"); 
            Fachada.excluirGarcom("esperto");
             
             
        }catch (Exception e) {System.out.println("--->"+e.getMessage());}
         
    }
 
 
    public static void erros() {
        System.out.println("\n-------ERROS--------");
        System.out.println("\ncriando conta 1...");
 
        try {
            Fachada.cadastrarProduto("agua", 2.0);
            System.out.println("*************FALHA1: produto agua ja cadastrado"); 
        }catch (Exception e) {System.out.println("1--->"+e.getMessage());}
        try {
            Fachada.cadastrarGarcom("zezinho", 16,20);
            System.out.println("*************FALHA2: garcon zezinho ja cadastrado"); 
        }catch (Exception e) {System.out.println("2--->"+e.getMessage());}
        try {
            Fachada.criarConta(99); 
            System.out.println("*************FALHA3: criar conta 99 - mesa inexistente"); 
        }catch (Exception e) {System.out.println("3--->"+e.getMessage());}
        try {
            Fachada.criarConta(1);  
            Fachada.solicitarProduto(1, "cerveja"); 
            Fachada.criarConta(1);  
            System.out.println("*************FALHA4: criar conta 1 - conta esta aberta"); 
        }catch (Exception e) {System.out.println("4--->"+e.getMessage());}
        try {
            Fachada.solicitarProduto(4, "cerveja");
            System.out.println("*************FALHA5: solicitacao 4 incorreta - conta nao aberta"); 
        }catch (Exception e) {System.out.println("5--->"+e.getMessage());}
        try {
            Fachada.solicitarProduto(1, "leite");
            System.out.println("*************FALHA6: solicitacao leite incorreta - produto inexistente"); 
        }catch (Exception e) {System.out.println("6--->"+e.getMessage());}
        try {           
            Fachada.transferirConta(1,1);
            System.out.println("*************FALHA7: transferencia 1 1 incorreta - mesma mesa"); 
        }catch (Exception e) {System.out.println("7--->"+e.getMessage());}
        try {
            Fachada.criarConta(6);
            Fachada.transferirConta(1,6);
            System.out.println("*************FALHA8: transferencia 1 10 incorreta - outro garcon"); 
        }catch (Exception e) {System.out.println("8--->"+e.getMessage());}       
        try {
            Fachada.fecharConta(1);
            Fachada.solicitarProduto(1, "cerveja"); 
            System.out.println("*************FALHA9: solicitacao 1 incorreta - conta ja fechada"); 
        }catch (Exception e) {System.out.println("9--->"+e.getMessage());}
 
        try {
            Fachada.fecharConta(1);
            System.out.println("*************FALHA10: fechamento 1 incorreto - conta ja fechada"); 
        }catch (Exception e) {System.out.println("10--->"+e.getMessage());}
        try {
            Fachada.criarConta(11);
            Fachada.solicitarProduto(11, "festa");
            Fachada.fecharConta(11);
            Pagamento pag = Fachada.pagarConta(11,"cartao", 0, "1111-1111-0000", 5);
            System.out.println("*************FALHA11: pagamento mesa 11 incorreto - parcela < 100"); 
        }catch (Exception e) {System.out.println("11--->"+e.getMessage());}
        try {
            Fachada.criarConta(8);
            System.out.println("*************FALHA12: criar conta incorreto - mesa sem garcom"); 
        }catch (Exception e) {System.out.println("12--->"+e.getMessage());}
 
    }
 
}