/*
 * Tyree King 
 * Serializable Account #2 to assignment 
 * 
 */

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Account implements Serializable {

	int num;
	String name;
	double balance;

	public Account(int num, String name, double balance) {
		this.num = num;
		this.name = name;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account num=" + num + ", name=" + name + ", balance=" + balance;
	}

	public static void main(String[] args) {
		try {
			// new file with ObjectOutputStream
			FileOutputStream out = new FileOutputStream("account.dat");
			ObjectOutputStream oout = new ObjectOutputStream(out);

			Account acc1 = new Account(1, "IronMan", 6000);
			Account acc2 = new Account(2, "SpiderMan", 300);
			Account acc3 = new Account(3, "BlackWidow", 6000);
			Account acc4 = new Account(4, "BlackPanther", 5000);
			Account acc5 = new Account(5, "Doctor", 200);

			oout.writeObject(acc1);
			oout.writeObject(acc2);
			oout.writeObject(acc3);
			oout.writeObject(acc4);
			oout.writeObject(acc5);
			oout.close();

			// ObjectInputStream for the account.dat
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("account.dat"));
			while (true) {
				Account account;
				try {
					account = (Account) ois.readObject();
					System.out.println(account);
				} catch (ClassNotFoundException e) {

				} catch (EOFException e) {
					break;
				}

			}
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}