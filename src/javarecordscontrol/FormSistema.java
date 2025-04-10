
package javarecordscontrol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
// Classe da interface gráfica principal, estende JFrame (tela do sistema)
public class FormSistema extends javax.swing.JFrame {
    // Cria a pilha principal que armazenará objetos Recorde (com capacidade inicial de 10)
    Pilha<Recorde> minhaPilha = new Pilha<>(10);

    // Construtor do formulário: inicializa os componentes da interface (gerado pelo NetBeans geralmente)
    public FormSistema() {
        initComponents();
    }
    
    // Atualiza visualmente a lista de elementos da pilha em uma área de texto
    void mostrarPilha(Pilha<Recorde> p, JTextArea meuList) {
        meuList.setText(""); // limpa o texto anterior
        meuList.append(p.toString()); // mostra os elementos da pilha convertidos em texto
    }

    // Salva os dados da pilha em um arquivo de texto
    public void salvarPilha() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("recorde.txt"))) { 
            // Cria uma pilha temporária para restaurar os dados após salvar
            Pilha<Recorde> Pilha = new Pilha<>();
            // Enquanto a pilha principal não estiver vazia
            while (!minhaPilha.isEmpty()) {
                Recorde recorde = minhaPilha.pop(); // remove o topo
                // Escreve os dados do recorde no arquivo no formato CSV
                writer.write(recorde.getNome() + "," + recorde.getTempo() + "," + 
                    recorde.getDataRecorde().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                writer.newLine(); // pula linha
                Pilha.push(recorde); // guarda o elemento na pilha temporária
            }
            // Recoloca os elementos de volta na pilha original
            while (!Pilha.isEmpty()) {
                minhaPilha.push(Pilha.pop());
            }
            // Mensagem de sucesso
            JOptionPane.showMessageDialog(this, "Recordes salvos com sucesso!");
        } catch (IOException e) {
            // Mensagem de erro, caso aconteça algum problema ao salvar
            JOptionPane.showMessageDialog(this, "Erro ao salvar os Recordes: " + e.getMessage());
        }
    }
    // Carrega os dados do arquivo e recria a pilha com os registros
    void carregarPilha() {
        Pilha<Recorde> pilhaA = new Pilha<>(); // pilha auxiliar para manter a ordem
        try (BufferedReader reader = new BufferedReader(new FileReader("recorde.txt"))) {
            String linha;
            // Lê cada linha do arquivo
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(","); // separa os dados pelo ","
                String nome = partes[0];
                double tempo = Double.parseDouble(partes[1]);
                LocalDate data = LocalDate.parse(partes[2], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                // Cria um novo objeto Recorde com os dados lidos
                Recorde recorde = new Recorde();
                recorde.setNome(nome);
                recorde.setTempo(tempo);
                recorde.setDataRecorde(data);
                // Adiciona à pilha auxiliar
                pilhaA.push(recorde);
            }
            // Move os dados da pilha auxiliar para a pilha principal
            while (!pilhaA.isEmpty()) {
                minhaPilha.push(pilhaA.pop());
            }
            // Atualiza a visualização da pilha na interface
            mostrarPilha(minhaPilha, listPilha);
        } catch (IOException e) {
            // Mensagem de erro caso falhe ao ler o arquivo
            JOptionPane.showMessageDialog(this, "Erro ao carregar os Recordes: " + e.getMessage());
        }
    }
    // Remove o topo da pilha principal e mostra na área auxiliar
    void desempilhar(Pilha<Recorde> j, JTextArea listAux) {
        listAux.setText(""); // limpa a área de texto auxiliar
        if (!minhaPilha.isEmpty())
            listAux.append("\tTopo removido:\n" + minhaPilha.pop()); // mostra o elemento removido
        else
            listAux.append("\tPilha Vazia!"); // se a pilha estiver vazia
    }
    // Limpa os campos de entrada da interface (nome, tempo e data)
    void limpar(JTextField nome, JTextField tempo, JFormattedTextField data) {
        nome.setText("");
        tempo.setText("");
        data.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listAux = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        listPilha = new javax.swing.JTextArea();
        txtNome = new javax.swing.JTextField();
        lblTopo = new javax.swing.JLabel();
        btnRemove = new javax.swing.JButton();
        btnCarregar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtTempo = new javax.swing.JTextField();
        txtData = new javax.swing.JFormattedTextField();
        btnAdd1 = new javax.swing.JButton();
        btnSalvar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javarecordscontrol/corredor.png"))); // NOI18N
        jLabel2.setToolTipText("");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javarecordscontrol/cronometro.png"))); // NOI18N
        jLabel4.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addGap(45, 45, 45)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        listAux.setColumns(20);
        listAux.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        listAux.setRows(5);
        jScrollPane1.setViewportView(listAux);

        listPilha.setColumns(20);
        listPilha.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        listPilha.setRows(5);
        jScrollPane2.setViewportView(listPilha);

        txtNome.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        txtNome.setBorder(javax.swing.BorderFactory.createTitledBorder("Nome"));
        txtNome.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        lblTopo.setBackground(new java.awt.Color(255, 255, 255));
        lblTopo.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lblTopo.setForeground(new java.awt.Color(255, 255, 255));
        lblTopo.setText("Topo:");

        btnRemove.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRemove.setText("Desempilhar");
        btnRemove.setActionCommand("Remover");
        btnRemove.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnCarregar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCarregar.setText("Carregar");
        btnCarregar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarregarActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("App Records Control");

        txtTempo.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        txtTempo.setBorder(javax.swing.BorderFactory.createTitledBorder("Tempo"));
        txtTempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTempoActionPerformed(evt);
            }
        });

        txtData.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));
        try {
            txtData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataActionPerformed(evt);
            }
        });

        btnAdd1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAdd1.setText("Adicionar");
        btnAdd1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd1ActionPerformed(evt);
            }
        });

        btnSalvar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSalvar1.setText("Salvar");
        btnSalvar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSalvar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTopo)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45)
                                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSalvar1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCarregar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(117, 117, 117))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(47, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(294, 294, 294)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTopo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTempo, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(txtData, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemove, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(btnSalvar1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCarregar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Quando o botão "Carregar" for clicado
    private void btnCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarregarActionPerformed
        carregarPilha();// Chama o método que carrega os dados do arquivo para a pilha
    }//GEN-LAST:event_btnCarregarActionPerformed
    // Quando o botão "Remover" for clicado
    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        desempilhar(minhaPilha, listAux);// Remove o topo da pilha e mostra na área auxiliar
        mostrarPilha(minhaPilha, listPilha); // Atualiza a visualização da pilha principal
        txtNome.requestFocus(); // Move o foco para o campo de Nome
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        txtData.requestFocus();// Move o foco para o campo de data
        
    }//GEN-LAST:event_txtNomeActionPerformed

// Quando o botão "Adicionar" for clicado
    private void btnAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd1ActionPerformed
        Recorde novoRecorde = new Recorde();// Cria um novo objeto Recorde e define seus atributos com base nos campos da interface
        novoRecorde.setNome(txtNome.getText());
        novoRecorde.setTempo(Double.parseDouble(txtTempo.getText()));
        System.out.println("Digite a data do recorde (dd/MM/yyyy): ");// Converte o texto da data em LocalDate (formato dd/MM/yyyy)
        String dataStr = txtData.getText();
        LocalDate dataFormatada = LocalDate.parse(dataStr,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        novoRecorde.setDataRecorde(dataFormatada);
        limpar(txtNome, txtTempo, txtData);// Limpa os campos após a entrada
        if(minhaPilha.isEmpty()){// Lógica para adicionar o novo recorde na pilha, conforme as regras
            minhaPilha.push(novoRecorde);
            mostrarPilha(minhaPilha, listPilha);  // se vazia, apenas adiciona
        }
        else if(minhaPilha.isFull()){// se estiver cheia
            minhaPilha.resize(); // aumenta a capacidade
            minhaPilha.push(novoRecorde); // adiciona
            mostrarPilha(minhaPilha, listPilha);
        }else{
           Recorde topo = minhaPilha.peek();// Compara o tempo do novo recorde com o topo atual
                if(novoRecorde.getTempo() <= topo.getTempo()){
                    minhaPilha.push(novoRecorde);// adiciona se for menor ou igual
                    mostrarPilha(minhaPilha, listPilha);
                }else{
                    JOptionPane.showMessageDialog(this, "Tempo do Recorde é maior que o Anterior"); // Exibe erro se o tempo for maior (regra do problema)
                }
        }
        txtNome.requestFocus(); // Move o foco para o campo de Nome
    }//GEN-LAST:event_btnAdd1ActionPerformed
// Quando o botão "Salvar" for clicado
    private void btnSalvar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvar1ActionPerformed
        salvarPilha();// Salva os dados da pilha no arquivo
    }//GEN-LAST:event_btnSalvar1ActionPerformed

    private void txtDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataActionPerformed
        txtTempo.requestFocus(); // Move o foco para o campo de tempo
    }//GEN-LAST:event_txtDataActionPerformed

    private void txtTempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTempoActionPerformed
        btnAdd1.doClick(); // Simula o clique no botão "Adicionar"
    }//GEN-LAST:event_txtTempoActionPerformed

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
            java.util.logging.Logger.getLogger(FormSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormSistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormSistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd1;
    private javax.swing.JButton btnCarregar;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSalvar1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTopo;
    private javax.swing.JTextArea listAux;
    private javax.swing.JTextArea listPilha;
    private javax.swing.JFormattedTextField txtData;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtTempo;
    // End of variables declaration//GEN-END:variables
}
