/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import Model.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Util.ConexaoMySQL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Claiton
 */
public class UsuarioDAO {

    public boolean autenticar(Usuario u) {
        String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?";
        PreparedStatement ps;
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            ps.setString(1, u.getLogin());
            ps.setString(2, u.getSenha());
            ResultSet rs = ps.executeQuery();
            return rs.next(); // true se existe usuario com esse login e senha
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Collection<Usuario> listar(){
        String sql = "SELECT * FROM usuario;";

        Collection<Usuario> usuarios = new ArrayList<>();
        try{
            PreparedStatement ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Usuario u = new Usuario();
                u.setId(rs.getInt("id_usuario"));
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                usuarios.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
        
    }

    public Boolean inserir(Usuario u) {
        String sql = "INSERT INTO usuario (login, senha) VALUES (?, ?);";
        PreparedStatement ps;
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            ps.setString(1, u.getLogin());
            ps.setString(2, u.getSenha());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true; // se entrar na excecao nao vai retornar true
    }

    public Boolean deletar(Usuario u) {
        String sql = "Delete FROM usuario WHERE id_usuario = ?;";
        PreparedStatement ps;
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            ps.setInt(1, u.getId());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public Usuario getUsuario(Integer id) {
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?;";
        PreparedStatement ps;
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Usuario usuario = new Usuario();
            if (rs.next()) {
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
            }
            return usuario;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Boolean atualizar(Usuario u) {
        String sql = "UPDATE usuario SET login = ?, senha = ? WHERE id_usuario = ?;";
        PreparedStatement ps;
        try {
            ps = ConexaoMySQL.getConexao().prepareStatement(sql);
            ps.setString(1, u.getLogin());
            ps.setString(2, u.getSenha());
            ps.setInt(3, u.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
