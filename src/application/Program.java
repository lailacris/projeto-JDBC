package application;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import entities.Aluno;
import jdbc.AlunoJDBC;

public class Program {

	public static void main(String[] args) {
		
		try {
        	
            int opcao = 0;
            Scanner console = new Scanner(System.in);
            
            do {
            	System.out.println("####### Menu #######"
            						+ "\n1 - Cadastrar"
            						+ "\n2 - Listar"
            						+ "\n3 - Alterar"
            						+ "\n4 - Excluir"
            						+ "\n5 - Sair");
            	System.out.println("\n\tOpcao:");
            	opcao = Integer.parseInt(console.nextLine());
            	
            	if (opcao == 1) {
            	
            		Aluno a = new Aluno();
            		AlunoJDBC acao = new AlunoJDBC();
            		
            		System.out.println("\n ### Cadastrar Aluno ### \n\r");
            		
            		System.out.print("Nome: ");
            		a.setNome(console.nextLine());
            		
            		System.out.print("Sexo: ");
            		a.setSexo(console.nextLine());
            	
            		System.out.print("Data de Nascimento - Formato esperado: (dd/mm/aaaa): ");
            		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            		a.setDt_nasc( LocalDate.parse(console.nextLine(), formato) );
            		
            		acao.salvar(a);
            		console.nextLine();
            		System.out.println("\n\n\n\n");
            	}
            	
            	if(opcao == 2) {
            		 AlunoJDBC acao = new AlunoJDBC();
            		    List<Aluno> alunos = acao.listar();
            		    
            		    System.out.println("\n ### Lista de Alunos ### \n\r");
            		    
            		    for (Aluno aluno : alunos) {
            		        System.out.println("Matricula: " + aluno.getId());
            		        System.out.println("Nome: " + aluno.getNome());
            		        System.out.println("Sexo: " + aluno.getSexo());
            		        System.out.println("Data de Nascimento: " + aluno.getDt_nasc().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            		        System.out.println("-------------------------------------");
            		    }
            		    
            		    console.nextLine(); 
            	}
            	
            	if (opcao == 3) {
            	    AlunoJDBC acao = new AlunoJDBC();
            	    Aluno aluno = new Aluno();

            	    System.out.println("\n ### Alterar Aluno ### \n\r");

            	    System.out.print("Digite a matricula do Aluno que deseja alterar as informacoes: ");
            	    aluno.setId(Integer.parseInt(console.nextLine()));

            	    System.out.print("Digite o nome atualizado: ");
            	    aluno.setNome(console.nextLine());

            	    System.out.print("Digite o sexo atualizado: ");
            	    aluno.setSexo(console.nextLine());

            	    System.out.print("Digite a data de nascimento atualizada - Formato esperado: (dd/mm/aaaa): ");
            	    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            	    aluno.setDt_nasc(LocalDate.parse(console.nextLine(), formato));

            	    try {
            	        acao.alterar(aluno);
            	    } catch (SQLException e) {
            	        System.out.println("Erro ao atualizar aluno: " + e.getMessage());
            	    }
            	    console.nextLine();
            	}
            	
            	if (opcao == 4) {
            	    AlunoJDBC acao = new AlunoJDBC();
            	    
            	    System.out.println("\n ### Apagar Aluno ### \n\r");
            	    System.out.print("Digite a matricula do Aluno que deseja apagar: ");
            	    int id = Integer.parseInt(console.nextLine());

            	    try {
            	        acao.apagar(id);
            	    } catch (SQLException e) {
            	        System.out.println("Erro ao apagar aluno: " + e.getMessage());
            	    }
            	    console.nextLine();  
            	}
            	
            	if(opcao == 5) {
            		System.out.println("Sistema encerrado!!");
            	}
            	
            } while(opcao != 5);
            
        } catch (Exception e){
            System.out.println("Erro: " + e);
        }
	} 
}