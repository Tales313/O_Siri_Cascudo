# O Siri Cascudo
Segundo projeto da disciplina de Programação Orientada a Objetos (Sistemas para Internet, IFPB) feito em 2018.1.

## Descrição Pt. 1
(Esse projeto foi dividido em duas partes) Todo o texto a seguir foi escrito pelo professor da disciplina, Fausto Ayres. 
Regras de negócio:
Um restaurante vende os produtos de seu cardápio. Quando a mesa é ocupada pelos clientes, o garçom efetua a abertura de uma conta para a mesa e à medida que os clientes fazem seus pedidos, o garçom os solicita no sistema. 
O restaurante possui 20 mesas identificadas por um identificador autoincrementado (1, 2, ...), onde cada garçom serve 5 mesas (num intervalo sequencial) e cada mesa é atendida por apenas um único garçom. Uma mesa é considerada ocupada se possuir uma conta aberta e considerada livre se não possuir conta aberta. Um garçom pode criar, movimentar, cancelar, consultar e fechar uma conta para qualquer uma de suas mesas. Ele também pode transferir os produtos de uma mesa para outra, cancelando automaticamente a conta de origem. 
No final do dia o garçom poderá consultar o valor da gorjeta que ele receberá do restaurante correspondente aos 10% de todas as contas fechadas que ele serviu naquele dia. Uma conta é identificada no sistema por um número autoincrementado (1, 2, ...). Uma conta só pode ser criada para uma determinada mesa e somente se a última conta criada para esta mesa tiver sido fechada. Uma conta só pode ser fechada se ela estiver aberta. Quando uma conta é fechada ela recebe a data de fechamento que é a data do computador.

![UML pt 1](/screenshots/uml_pt1.png)

Classe de Fachada
```
public static ArrayList<Produto> listarProdutos(nome) // retorna todos os produtos do restaurante (no caso de Enter) ou apenas os produtos que contém parte do nome fornecido
public static ArrayList<Garcom> listarGarcons() // retorna todos os garçons do restaurante
public static ArrayList<Mesa> listarMesas() // retorna todas as mesas do restaurante
public static ArrayList<Conta> listarContas() // retorna todas as contas do restaurante
public static void criarMesas(n) // cria todas as n mesas do restaurante
public static Produto cadastrarProduto(nome, preco) // cadastra o produto
public static Garcom cadastrarGarcom(apelido, senha, mesainicial, mesafinal) // cadastra o garçom
public static Conta criarConta(idmesa) // cria uma conta para a mesa
public static Conta consultarConta(idmesa) // retorna a conta da mesa
public static Produto solicitarProduto(idmesa, nomeproduto) // adiciona um produto na conta
public static void cancelarConta(idmesa) // excluir a conta da mesa e do restaurante
public static void transferirConta(idmesaorigem, idmesadestino) // transferir todos os produtos da conta de origem para a conta destino e cancelar a conta origem
public static void fecharConta(idmesa) // atualiza a data de fechamento da conta
public static double calcularGorjeta(apelido) // calcular a gorjeta do garçom
```
Esses métodos devem lançar exceção quando necessário.

Aplicação (MENU):
- Listar Produtos. Dados: qualquer parte do nome (um nome vazio ou inexistente significa todas as pessoas). Resultados: listagem de todos os produtos do restaurante.
- Listar Garçons. Resultados: listagem de todos os garçons do restaurante, contendo apelido, id das mesas.
- Listar Mesas. Resultados: listagem de todas as mesas do restaurante, contendo id da mesa e situação (aberta ou fechada).
- Listar Contas. Resultados: listagem de todas as contas do restaurante, contendo numero, data fechamento (se existir), total, lista de produtos, apelido e idmesa.
- Cadastrar Produto. Dados: nome e preco. Resultados: confirmação de cadastro.
- Cadastrar Garçom. Dados: apelido, idmesa inicial, idmesa final. Resultados: confirmação de cadastro.
- Criar Conta. Dados: id da mesa. Resultados: confirmação de abertura.
- Consultar Conta. Dados: id da mesa. Resultados: numero, data fechamento (se existir), total, lista de produtos, apelido e idmesa.
- Solicitar Produto. Dados: id da mesa e nome de produto. Resultados: confirmação
- Cancelar Conta. Dados: id da mesa. Resultados: confirmação.
- Transferir Conta. Dados: id da mesa origem, id da mesa destino. Resultados: confirmação.
- Fechar Conta. Dados: id da mesa. Resultados: confirmação.
- Calcular Gorjeta. Dados: apelido do garçom. Resultados: valor da gorjeta no dia de hoje para contas fechadas.

Considerações finais:
- A aplicação deverá pré-cadastrar 8 produtos e 4 garçons
- As datas devem ser visualizadas no formato dd/mm/aaaa
- Utilize somente coleções do tipo ArrayList

## Descrição Pt. 2
Objetivo: Adicionar novas funcionalidades ao Sistema de Vendas para Restaurante.
Regras de negócio:
O restaurante necessita registrar o pagamento das contas, o qual pode ser em dinheiro ou em cartão de credito.
O pagamento da conta só pode ser feito se a conta estiver fechada.
O valor do pagamento é calculado tomando como base o total da conta.
O valor do pagamento em dinheiro é calculado aplicando-se um desconto de 0% a 5% ao total da conta.
O valor do pagamento no cartão é calculado dividindo-se o total da conta em até 4 parcelas e aplicando-se um acréscimo de 10% e 20% sobre o total, para 3 e 4 parcelas, respectivamente, ou isentando-se de acréscimo para 1 e 2 parcelas.
A quantidade máxima permitida de parcelas é 4.
O valor mínimo permitido de cada parcela é R$100.
O valor da gorjeta do garçom de uma conta será calculado em cima do valor do pagamento.
Uma conta que foi fechada ou que foi paga pode ser consultada a qualquer momento.
Um garçom pode ser excluído do sistema, implicando na exclusão dos relacionamentos com as suas mesas.

Modelo de Negócio modificado:
O diagrama abaixo mostra as 3 novas classes:

![UML pt 2](/screenshots/uml_pt2.png)

A classe Pagamento e o seu método calcularPagamento(...) são abstratos.
A classe Garcom passa a ser indexada pelo nome do garçom na classe Restaurante, usando um TreeMap.

Classe de Fachada Modificada
```
public static Pagamento pagarConta(idmesa, tipo, percentual, cartão, quantidade) // criar pagamento para a conta da mesa, onde tipo pode ser “dinheiro” ou “cartao”.
public static void excluirGarcom(nome) // exclui o garçom do restaurante.
public static double calcularPercentualMedio(apelido) // retorna o percentual médio aplicado aos pagamentos em dinheiro das contas das mesas do garcom.
```
Alterações no MENU:
- Listar Produtos. Dados: qualquer parte do nome (um nome vazio ou inexistente significa todas as pessoas). Resultados: listagem de todos os produtos do restaurante em ordem alfabética.
- Listar Garçons. Resultados: listagem de todos os garçons do restaurante, contendo apelido, id das mesas em ordem alfabética.
- Listar Contas. Resultados: listagem de todas as contas do restaurante, contendo numero, data, fechamento (se existir), total, lista de produtos, apelido e idmesa, e dados do pagamento (valor pago, percentual ou quantidade e cartão) conforme o tipo.
- Consultar Conta. Dados: id da mesa. Resultados: numero, data fechamento (se existir), total, lista de produtos, apelido e idmesa e dados do pagamento (valor pago, percentual ou quantidade e cartão) conforme o tipo.
- Calcular Gorjeta. Dados: apelido do garçom. Resultados: valor da gorjeta no dia de hoje para contas pagas.
- Pagar Conta. Dados: id da mesa, tipo pagamento, percentual de desconto ou cartão e quantidade de parcelas (conforme o tipo). Resultados: valor pago.
- Excluir Garcom. Dados: nome do garçom. Resultados: confirmação.
- Percentual Médio. Dados: apelido do garcom. Resultados: o percentual médio praticado em todos os pagamentos em dinheiro das contas de um determinado garcom.

## Screenshots

![Screenshot 1](/screenshots/1.png)
![Screenshot 2](/screenshots/2.png)
![Screenshot 3](/screenshots/3.png)
