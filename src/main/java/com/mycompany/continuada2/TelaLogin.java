/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.continuada2;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author alexa
 */
public class TelaLogin extends javax.swing.JFrame {
    
    ConexaoBanco conexao = new ConexaoBanco();
    JdbcTemplate jdbcTemplate = conexao.conexao();
    Usuario usuario = new Usuario();
    int tentativas = 4;
    
        /**
     * Creates new form TelaLogin
     */
    public TelaLogin() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFldLogin = new javax.swing.JTextField();
        txtFldSenha = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        lblResposta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Login");

        jLabel2.setText("Senha");

        txtFldLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFldLoginActionPerformed(evt);
            }
        });

        btnLogin.setText("login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblResposta.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblResposta)
                    .addComponent(btnLogin)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(txtFldLogin)
                    .addComponent(txtFldSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                .addContainerGap(180, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtFldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtFldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnLogin)
                .addGap(38, 38, 38)
                .addComponent(lblResposta)
                .addContainerGap(108, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String emailUser = txtFldLogin.getText();
        String senhaUser = txtFldSenha.getText();

        // TODO add your handling code here:
        List<Usuario> listaUsuario = jdbcTemplate.query(
            "select * from usuario where email = ? and senha = ?",
            new BeanPropertyRowMapper(Usuario.class), emailUser, senhaUser);

        if (listaUsuario.isEmpty()) {
            for (int i = 0; i < tentativas; i++) {
                tentativas--;
                lblResposta.setText(String.format(
                        "email eou senha errados. voce ainda tem %d tentativas"
                ,tentativas));
            }
            if (tentativas == 0) {
                btnLogin.setVisible(false);
                lblResposta.setText("sistema bloqueado - procure o suporte");
            }
        } else {
            listaUsuario.forEach(
                user -> {
                    usuario.setId(user.getId());
                    usuario.setNome(user.getNome());
                    usuario.setEmail(user.getEmail());
                    usuario.setSenha(user.getSenha());
                    usuario.setTelefone(user.getTelefone());
                }
            );
            
            lblResposta.setText(String.format(
                    "Logado efetuado com sucesso. bem vindo %s",
                    usuario.getNome()));
            btnLogin.setVisible(false);
//            setVisible(false);
//            dispose();
            TelaInfo telainfo = new TelaInfo(usuario);
            telainfo.setVisible(true);
        }
//        for (Usuario usuario : listaUsuario) {
//            System.out.println("email: "+usuario.getEmail());
//            System.out.println("senha: "+usuario.getSenha());
//        }
        
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtFldLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldLoginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblResposta;
    private javax.swing.JTextField txtFldLogin;
    private javax.swing.JTextField txtFldSenha;
    // End of variables declaration//GEN-END:variables
}
