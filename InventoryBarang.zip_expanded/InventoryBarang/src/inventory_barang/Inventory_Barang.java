package inventory_barang;

import java.sql.*;
import javax.swing.*;



public class Inventory_Barang {

	public static Connection ConnectDB()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection
					("jdbc:sqlite:D:\\Kuliah\\Analog\\InventoryBarang\\inventory.db");
					JOptionPane.showMessageDialog(null, "Connected");
					return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Connection Error");
			return null;
		}
	}

}
