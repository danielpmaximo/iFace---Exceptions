import java.util.InputMismatchException;
import java.util.Scanner;

public class ProjetoPOO {
  
  public static void main(String[] args) {

    ////////////////////////////////////
    
    Scanner in = new Scanner(System.in);

    ////////////////////////////////////
	  int opcao = 0;
    String nome, login, senha, mensagem=null, comunidade=null;
    int idUsuario, codigoPerfil=0;
    boolean resultadoB;

    ////////////////////////////////////

    while (true) { // Laço infinito

      System.out.print("\n1. Cadastrar");
      System.out.print("\n2. Login");
      System.out.print("\n3. Sair");
      System.out.print("\n>>> ");
      
      try{
        opcao = in.nextInt();
        in.nextLine(); // Limpa o buffer de memória
      }
      catch(InputMismatchException e){
        System.out.print("\nA entrada digitada não é válida. Digite novamente");
        in.nextLine();
      }

      if (opcao == 3) {
        in.close();
        break;
      }

      switch(opcao) {
        
        case 1:  System.out.print("\nNome: ");
                 nome = in.nextLine();
                 System.out.print("Login: ");
                 login = in.nextLine();
                 System.out.print("Senha: ");
                 senha = in.nextLine();

                 resultadoB = Rede.cadastrarUsuario(nome, login, senha);

                 if(resultadoB)
                   System.out.print("\nCadastrado com sucesso!\n");
                 else
                   System.out.print("\nJá cadastrado...\n");

                 break;

          case 2: System.out.print("\nLogin: ");
                 login = in.nextLine();
                 System.out.print("Senha: ");
                 senha = in.nextLine();

                 idUsuario = Rede.efetuarLogin(login, senha);

                 if(idUsuario != -1) {

                    // Login foi efetuado com sucesso
                    while(true) {

                      opcao = menuLogin(in, 0);

                      if (opcao == 6)  break;

                      switch (opcao) {

                        case 1 -> {
                            while(true) {
                                
                                System.out.print("\n" + Rede.visualizarPerfil(idUsuario));
                                
                                System.out.print("\nDigite o código ou 6 para cancelar: ");
                                do{
                                    System.out.print("Código: ");
                                    try{
                                      codigoPerfil = in.nextInt();
                                      in.nextLine(); // Limpa o buffer de memória
                                    }
                                    catch(InputMismatchException e){
                                      System.out.print("\nA entrada digitada não é válida. Digite novamente");
                                      in.nextLine();
                                    }
                                }while(codigoPerfil < 0 || codigoPerfil > 6);
                                
                                if (codigoPerfil == 6) break;
                                else editarPerfil(in, idUsuario, codigoPerfil);
                            }
                            }
                        case 2 -> { 
                            System.out.print("\nDigite o login do usuário para adicioná-lo como amigo: ");
                            try{
                              login = in.nextLine();
                            }
                            catch(InputMismatchException e){
                              System.out.print("\nA entrada digitada não é válida. Digite novamente");
                              in.nextLine();
                            }
                            Amigos.addAmigo(login);
                            }
                        case 3 -> {
                            System.out.print("\nDigite o login do usuário para enviar mensagem: ");
                            try{
                              login = in.nextLine();
                            }
                            catch(InputMismatchException e){
                              System.out.print("\nA entrada digitada não é válida. Digite novamente");
                              in.nextLine();
                            }
                            Amigos.addMsg(login);
                            try{
                              mensagem = in.nextLine();
                            }
                            catch(InputMismatchException e){
                              System.out.print("\nA entrada digitada não é válida. Digite novamente");
                              in.nextLine();
                            }
                            Amigos.Msg(mensagem);
                            System.out.print("\nMensagem enviada com sucesso!");
                            }
                        case 4 -> { 
                            System.out.print("\nDigite o nome da comunidade que você quer criar: ");
                            try{
                              comunidade = in.nextLine();
                            }
                            catch(InputMismatchException e){
                              System.out.print("\nA entrada digitada não é válida. Digite novamente");
                              in.nextLine();
                            }
                            Comunidade.CriarComunidade(comunidade);
                            }
                      }
                    }
                   
                 } else System.out.print("\nDados não conferem...\n");     
                 
                 break;
          
      }
      
    }

    ////////////////////////////////////
    
  }

  private static int menuLogin(Scanner in, int opcao) {

    System.out.print("\nSeja bem-vindo(a) ao iFace!");
    System.out.print("\n/////////////////////");
    System.out.print("\n1. Perfil");
    System.out.print("\n2. Amigos");
    System.out.print("\n3. Mensagem");
    System.out.print("\n4. Comunidade");
    System.out.print("\n5. Visualizar");
    System.out.print("\n6. Fechar");
    System.out.print("\n/////////////////////");
    
    System.out.print("\n>>> ");

    try{
      opcao = in.nextInt();
      in.nextLine(); // Limpa o buffer de memória
    }
    catch(InputMismatchException e){
      System.out.print("\nA entrada digitada não é válida. Digite novamente");
      in.nextLine();
    }
    
    return opcao;
  }

  private static void editarPerfil(Scanner in, int idUsuario, int codigoPerfil) {

    String dado = null;

    in.nextLine();

    if(codigoPerfil == 1) {
      System.out.print("\nNome: ");
      dado = Escanear(in);
    }else if(codigoPerfil == 2) {
      System.out.print("\nLogin: ");
      dado = Escanear(in);
    }else if(codigoPerfil == 3) {
      System.out.print("\nSenha: ");
      dado = Escanear(in);
    }else if(codigoPerfil == 4) {
      System.out.print("\nData de nascimento: ");
      dado = Escanear(in);
    }else if(codigoPerfil == 5) {
      System.out.print("\nCelular: ");
      dado = Escanear(in);
    }

    Rede.editarPerfil(idUsuario, codigoPerfil, dado);
    
  }

  private static String Escanear(Scanner in){
    String scan=null;
    try{
      scan = in.nextLine();
      //in.nextLine(); // Limpa o buffer de memória
    }
    catch(InputMismatchException e){
      System.out.print("\nA entrada digitada não é válida. Digite qualquer tecla para voltar a tela inicial\n");
      //in.nextLine();
    }
    return scan;
  }
}