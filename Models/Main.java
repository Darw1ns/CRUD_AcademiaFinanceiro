package Models;
import java.sql.ResultSet;
import java.util.Scanner;

import Data.DbContext;

// Função Principal 
public class Main
{

	public static void main(String[] args) {
		inicio();
		
	}

// Função Principal
	public static void inicio(){
		while (true){

			System.out.println("\nSISTEMA DE ACADEMIA ");
			System.out.println("1 - Cadastrar");
			System.out.println("2 - Consultar");
			System.out.println("3 - Modificar");
			System.out.println("4 - Remover");

			System.out.print("Digite uma opção: ");

			Scanner input = new Scanner(System.in);

			if (input.hasNextInt()){
				int opcaoSelecionada = input.nextInt();

				if (opcaoSelecionada >= 1 && opcaoSelecionada <= 4){
					executaOpcao(opcaoSelecionada);
				}
				else{
					mensagemStatus("Opção Inválida.");
				}
			}
			input.nextLine();		
		}

	}

	public static void executaOpcao(int opcaoSelecionada){
		switch (opcaoSelecionada){
			case 1:
				cadastrarAluno();
				break;
			case 2:
				consultarAluno();
				break;
			case 3:
				modificarAluno();
				break;
			case 4:
				removerAluno();
				break;
		}
	}

	public static void cadastrarAluno(){
		
		Scanner input = new Scanner(System.in);
		System.out.println("Digite o cpf do Aluno: ");

		if (input.hasNext()){
			
			String cpfAluno = input.nextLine();

			System.out.println("Digite o nome do Aluno: ");
			String nomeAluno = input.nextLine();

			System.out.println("Digite o endereço do Aluno: ");
			String enderecoAluno = input.nextLine();
	
			System.out.println("Digite o telefone do Aluno: ");
			String telefoneAluno = input.nextLine();
	
			System.out.println("Digite o email do Aluno: ");
			String emailAluno = input.nextLine();
	
			System.out.println("Digite o valor da mensalidade do Aluno: ");
			float valor_mensalidade = input.nextFloat();

			input.nextLine();

			System.out.println("Digite a data de vencimento do pagamento do Aluno: ");
			String vencimento_pag = input.nextLine();
			
			DbContext database = new DbContext();
			try{
				database.conectarBanco();
				boolean statusQuery = database.executarUpdateSql("INSERT INTO public.alunos(cpf, nome, endereco, telefone, email, valor_mensalidade, vencimento_pag) VALUES ('" + cpfAluno + "', '" + nomeAluno + "', '" + enderecoAluno + "', '" + telefoneAluno + "', '" + emailAluno + "', '" + valor_mensalidade + "', '" + vencimento_pag + "')");
				
				if (statusQuery){
					mensagemStatus("Novo aluno cadastrado com sucesso !");
				}
				database.desconectarBanco();
			} catch (Exception e) {}
			inicio();
		}

		
	}

	public static void consultarAluno(){
		DbContext database = new DbContext();

		try{
			database.conectarBanco();
			ResultSet resultadoConsulta = database.executarQuerySql("SELECT * FROM public.alunos");

			while(resultadoConsulta.next()){
				System.out.println("\nID: " + resultadoConsulta.getInt("id") +
										 "|CPF: " + resultadoConsulta.getString("cpf") + 
										 "|Nome: " + resultadoConsulta.getString("nome") + 
										 "|Endereço: " + resultadoConsulta.getString("endereco") + 
										 "|Telefone: " + resultadoConsulta.getString("telefone") +
										 "|Email: " + resultadoConsulta.getString("email") +
										 "|Valor Mensalidade: " + resultadoConsulta.getFloat("valor_mensalidade") + 
										 "|Vencimento Pagamento: " + resultadoConsulta.getString("vencimento_pag"));
			}
			database.desconectarBanco();
		} catch (Exception e) {}
		inicio();
	}

	public static void modificarAluno(){
		System.out.println("Informe o id do Aluno a ser Modificado: ");
		Scanner input = new Scanner(System.in);

		if (input.hasNextInt()){

			int id = input.nextInt();
			input.nextLine();

			
			System.out.println("Informe o novo cpf do aluno: ");
			if (input.hasNext()){
				
				String cpfAluno = input.nextLine();

				System.out.println("Digite o novo nome do aluno: ");
				String novoNomeAluno = input.nextLine();

				System.out.println("Digite o novo endereço do aluno: ");
				String novoEnderecoAluno = input.nextLine();

				System.out.println("Digite o novo telefone do aluno: ");
				String novoTelefoneAluno = input.nextLine();
				
				System.out.println("Digite o novo email do aluno: ");
				String novoEmailAluno = input.nextLine();
				
				System.out.println("Digite a nova mensalidade do aluno: ");
				float novaMensalidadeAluno = input.nextFloat();

				input.nextLine();

				System.out.println("Digite o novo vencimento de pagamento do aluno: ");
				String novoVenciAluno = input.nextLine();

				DbContext database = new DbContext();
				try{
					database.conectarBanco();
					boolean statusQuery = database.executarUpdateSql("UPDATE public.alunos SET cpf = '" + cpfAluno + "', nome = '" + novoNomeAluno + "', endereco = '" + novoEnderecoAluno + "', telefone = '" + novoTelefoneAluno + "', email = '" + novoEmailAluno + "', valor_mensalidade = '" + novaMensalidadeAluno + "', vencimento_pag = '" + novoVenciAluno + "' WHERE id = '" + id + "'");
					
					if (statusQuery){
						mensagemStatus("Aluno atualizado com sucesso !");
					}
					database.desconectarBanco();
				} catch (Exception e) {}
			}

			inicio();

		}
	}

	public static void removerAluno(){

		System.out.println("\nInforme o ID do aluno a ser deletado: ");
		Scanner input = new Scanner(System.in);

		if (input.hasNextInt()){
			
			int id = input.nextInt();

			DbContext database = new DbContext();

			try{
				database.conectarBanco();
				boolean statusQuery = database.executarUpdateSql("DELETE FROM public.alunos WHERE id = " + id);
				if (statusQuery){
					mensagemStatus("Aluno deletado com sucesso !");
				}
				database.desconectarBanco();
			} catch (Exception e) {}
			inicio();
		}
	}
	
	public static void mensagemStatus(String mensagem){
		System.out.print("\n");
		System.out.print("---------------------");
		System.out.print("\n " + mensagem + " \n");
		System.out.print("---------------------");
		System.out.print("\n");
	}

	
}


