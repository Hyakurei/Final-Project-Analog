package inventory_barang;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import java.awt.Color;


public class Inventory {

	private JFrame frame;
	private JTextField kode;
	private JTable table;
	private JTextField nama;
	private JTextField tglk;
	private JTextField tglm;
	private JTextField jumlahk;
	private JTextField jumlahm;

	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	
	DefaultTableModel model = new DefaultTableModel();
	
	/**
	 * Launch the application.
	 */
	
	public void updateTable()
	{
		conn = Inventory_Barang.ConnectDB();
		
		if (conn !=null)
		{
			String sql = "Select Kode Barang, Nama Barang, Tanggal Masuk, Tanggal Keluar, Banyak Barang Masuk, Banyak Barang Keluar";
		
		
		try 
		{
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			Object[] columnData = new Object[6];
			
			while (rs.next()) {
				columnData [0] = rs.getString("Kode Barang");
				columnData [1] = rs.getString("Nama Barang");
				columnData [2] = rs.getString("Tanggal Masuk");
				columnData [3] = rs.getString("Tanggal Keluar");
				columnData [4] = rs.getString("Banyak Barang Masuk");
				columnData [5] = rs.getString("Banyak Barang Keluar");
				
				model.addRow(columnData);
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		}
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory window = new Inventory();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Inventory() {
		initialize();
		
		conn = Inventory_Barang.ConnectDB();
		Object col[] = {"Kode Barang", "Nama Barang", "Tanggal Masuk", "Tanggal Keluar", "Banyak Barang Masuk", "Banyak Barang Keluar"};
		model.setColumnIdentifiers(col);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.CYAN);
		frame.setBounds(0, 0, 1450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblKodeBarang = new JLabel("Kode Barang");
		lblKodeBarang.setBounds(10, 103, 231, 25);
		lblKodeBarang.setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.getContentPane().add(lblKodeBarang);
		
		kode = new JTextField();
		kode.setBounds(251, 100, 406, 28);
		kode.setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.getContentPane().add(kode);
		kode.setColumns(10);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setBounds(39, 350, 147, 31);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql = "INSERT INTO Inventory Barang(Kode Barang, Nama Barang, Tanggal Masuk, Tanggal Keluar, Banyak Barang Masuk, Banyak Barang Keluar)VALUES(?,?,?,?,?,?)";
				
				try
				{
					pst = conn.prepareStatement(sql);
					pst.setString(1, kode.getText());
					pst.setString(2, nama.getText());
					pst.setString(3, tglm.getText());
					pst.setString(4, tglk.getText());
					pst.setString(5, jumlahm.getText());
					pst.setString(6, jumlahk.getText());
					
					pst.execute();
					
					rs.close();
					pst.close();
				}
				catch (Exception ev)
				{
					JOptionPane.showMessageDialog(null,"System Update Completed");
				}
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
					model .addRow(new Object[] {
							
							kode.getText(),
							nama.getText(),
							tglm.getText(),
							tglk.getText(),
							jumlahm.getText(),
							jumlahk.getText(),
							
					});
					if (table.getSelectedRow() == -1) {
						if (table.getRowCount() == 0) {
							JOptionPane.showMessageDialog(null, "Data Update confirmed", "Inventory Barang", JOptionPane.OK_OPTION);
						}
					}
					
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(667, 59, 757, 383);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Kode Barang", "Nama Barang", "Tanggal Masuk", "Tanggal Keluar", "Banyak Barang Masuk", "Banyak Barang Keluar"
			}
		));
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane.setViewportView(table);
		
		nama = new JTextField();
		nama.setBounds(251, 139, 406, 28);
		nama.setFont(new Font("Tahoma", Font.BOLD, 18));
		nama.setColumns(10);
		frame.getContentPane().add(nama);
		
		JLabel lblNamaBarang = new JLabel("Nama Barang");
		lblNamaBarang.setBounds(10, 142, 231, 25);
		lblNamaBarang.setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.getContentPane().add(lblNamaBarang);
		
		tglk = new JTextField();
		tglk.setBounds(251, 217, 406, 28);
		tglk.setFont(new Font("Tahoma", Font.BOLD, 18));
		tglk.setColumns(10);
		frame.getContentPane().add(tglk);
		
		JLabel lblTanggalKeluar = new JLabel("Tanggal Keluar");
		lblTanggalKeluar.setBounds(10, 220, 231, 25);
		lblTanggalKeluar.setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.getContentPane().add(lblTanggalKeluar);
		
		tglm = new JTextField();
		tglm.setBounds(251, 178, 406, 28);
		tglm.setFont(new Font("Tahoma", Font.BOLD, 18));
		tglm.setColumns(10);
		frame.getContentPane().add(tglm);
		
		JLabel lblTanggalMasuk = new JLabel("Tanggal Masuk");
		lblTanggalMasuk.setBounds(10, 181, 231, 25);
		lblTanggalMasuk.setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.getContentPane().add(lblTanggalMasuk);
		
		jumlahk = new JTextField();
		jumlahk.setBounds(251, 295, 406, 28);
		jumlahk.setFont(new Font("Tahoma", Font.BOLD, 18));
		jumlahk.setColumns(10);
		frame.getContentPane().add(jumlahk);
		
		JLabel lblJumlahKeluar = new JLabel("Banyak Barang Keluar");
		lblJumlahKeluar.setBounds(10, 298, 231, 25);
		lblJumlahKeluar.setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.getContentPane().add(lblJumlahKeluar);
		
		jumlahm = new JTextField();
		jumlahm.setBounds(251, 256, 406, 28);
		jumlahm.setFont(new Font("Tahoma", Font.BOLD, 18));
		jumlahm.setColumns(10);
		frame.getContentPane().add(jumlahm);
		
		JLabel lblJumlahMasuk = new JLabel("Banyak Barang Masuk");
		lblJumlahMasuk.setBounds(10, 259, 231, 25);
		lblJumlahMasuk.setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.getContentPane().add(lblJumlahMasuk);
		
		JButton btnPrint = new JButton("PRINT");
		btnPrint.setBounds(196, 350, 147, 31);
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				MessageFormat header = new MessageFormat("Printing in Progress");
				MessageFormat footer = new MessageFormat("Page {0, number, integer}");
				
				try
				{
					table.print();
					
				}
				catch(java.awt.print.PrinterException ev) {
					System.err.format("No Printer Found", ev.getMessage());
				}
				
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.getContentPane().add(btnPrint);
		
		JButton btnReset = new JButton("RESET");
		btnReset.setBounds(353, 350, 147, 31);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				kode.setText(null);
				nama.setText(null);
				tglm.setText(null);
				tglk.setText(null);
				jumlahm.setText(null);
				jumlahk.setText(null);
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.getContentPane().add(btnReset);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setBounds(510, 350, 147, 31);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame =new JFrame("EXIT");
				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "Inventory Barang",
						JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.getContentPane().add(btnExit);
		
		JLabel lblNewLabel_1 = new JLabel("Inventory Barang");
		lblNewLabel_1.setBounds(398, 11, 637, 37);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		frame.getContentPane().add(lblNewLabel_1);
	}
}
