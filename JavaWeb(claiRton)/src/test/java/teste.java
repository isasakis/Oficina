/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Model.Usuario;
import DAO.UsuarioDAO;
import java.util.Collection;

/**
 *
 * @author isabella
 */
public class teste {

    /**
     * @param args the command line arguments
     */
    
    static UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        testeInserir(new Usuario("Isabella", "123"));
        testeInserir(new Usuario("Claiton", "1234"));
        testeAutenticar(new Usuario("Isabella", "123"));
        testeListar();
        testeExcluir(1);
        testeInserir(new Usuario("Isa", "4321"));
        testeAtualizar(new Usuario("Isabella Sakis", "4321"), 3);
        testeListar();
        
    }
    
    public static void testeInserir(Usuario u){
        System.out.println("Inserindo usuário..");
        usuarioDAO.inserir(u);
    }
    
    public static void testeAutenticar(Usuario u){
        System.out.println("Testando login e senha do usuário..");
        if(usuarioDAO.autenticar(u))
            System.out.println("Usuário com login "+ u.getLogin() +" autenticado");
        else
            System.out.println("Usuário ou senha incorretos");
    }
    
    public static void testeListar(){
        System.out.println("Listando todos usuários..");
        Collection<Usuario> usuarios = usuarioDAO.listar();
        for(Usuario u: usuarios){
            System.out.println("Usuario " + u.getId() + " - Login: " + u.getLogin() + " - Senha: " +u.getSenha());
        }
    }
    
    public static void testeExcluir(int id){
        System.out.println("Buscando usuário com id " +id);
        Usuario u = usuarioDAO.getUsuario(id);
        if(u != null) {
            System.out.println("Usuário com id " +id + " encontrado.");
            if(usuarioDAO.deletar(u))
                System.out.println("Usuário com id " +id + " deletado.");
        }
    }
    
    public static void testeAtualizar(Usuario u, int id){
        System.out.println("Buscando usuário com id " +id);
        Usuario usu = usuarioDAO.getUsuario(id);
        if(usu != null) {
            System.out.println("Usuário com id " +id + " encontrado.");
            u.setId(usu.getId());
            if(usuarioDAO.atualizar(u))
                System.out.println("Usuário com id " +id + " atualizado.");
        }
    }
    
}
