package clients;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.regex.*;

public class ClientSide {

	private static DatagramSocket client = null;
	private static byte[] sendData = new byte[65535];
	private static byte[] recievedData = new byte[65535];
	private static Scanner input = new Scanner(System.in);
	private static String IP = null;
	private static String port = null;
	//private static DataOutputStream toServer = null;
	//private static BufferedReader fromServer = null;
	
	public static void main(String[] args) throws Exception {
		String textFromServer = "";
		//Socket client = null;
		String response = "";
		boolean valid_input = false;

		//Asks the user if they want to connect or not
		while(valid_input == false)
		{
			System.out.println("What do you want to do?\n1. Connect to a website\n2. Exit");
			response = input.nextLine().toLowerCase();
			switch(response)
			{
				case "connect":
				case "1":
					connect();
					valid_input = true;
					break;
				case "exit":
				case "2":
					close();
					break;
				default:
					System.out.println("Your input is not valid\nTry again");
					break;
			}
		}

		//fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
		//toServer = new DataOutputStream(client.getOutputStream());
		
		//The part where the User actually interacts with the Website
		System.out.println("");
		while(true)
		{		
			//This is how the User gets info from the Server
			try
			{
				while((textFromServer = recieveData()) != null)
				{
					if(textFromServer.equals("^^&^&^&"))
					{
						close();
					}
					else if(textFromServer.equalsIgnoreCase(" "))
					{
						break;
					}
					System.out.println(textFromServer);
				}
				sendData(false);
				//toServer.writeBytes(input.nextLine() + "\n");
			}
			catch(Exception e)
			{
				System.out.println("You and the server have disconnected");
				close();
			}
		}
	}
	
	private static String recieveData() throws Exception
	{
		DatagramPacket fromServer = new DatagramPacket(recievedData, recievedData.length);
		client.receive(fromServer);
		String data = fromServer.getData().toString();
		return data;
	}
	
	private static void sendData(boolean connect)
	{
		String data = null;
		if(connect == true)
		{
			data = "\n";
		}
		else
		{
			data = input.nextLine() + "\n";
		}
		
		try
		{
			DatagramPacket toServer = new DatagramPacket(data.getBytes(), data.length(), InetAddress.getByName(IP), Integer.valueOf(port));
			client.send(toServer);
		}
		catch(Exception e)
		{
			System.out.println("Couldn't send data");
		}
	}
	
	//This connects you to a website
	private static void connect() throws Exception
	{
		while(true)
		{
			//System.out.println("Great! Type the IP address of where you want to connect");
			System.out.println("Now type the IP address of where you want to connect.\n"
					+ "Your input should look like this \"0-255.0-255.0-255.0-255\".\n"
					+ "If your running the server off of the same computer you can type \"localhost\" instead.\n"
					+ "Typing \"exit\" will close the program.");
			IP = input.nextLine();
			if(validIP(IP) == true)
			{
				break;
			}
			else if(IP.equalsIgnoreCase("exit"))
			{
				close();
			}
			else
			{
				System.out.println("That IP address is not valid. Try again");
			}
		}
		while(true)
		{
			System.out.println("What is the port number of the server? Your input should be between 0-65535\n"
					+ "Typing \"exit\" will close the program.");
			port = input.nextLine();
			if(validPort(port) == true)
			{
				break;
			}
			else if(port.equalsIgnoreCase("exit"))
			{
				close();
			}
			else
			{
				System.out.println("That port number is not valid");
			}
		}
		//Tries connecting to IP multiple time before stoping
		for(int i = 0; i < 4; i++)
		{
			try
			{
				client = new DatagramSocket();
				sendData(true);
				return;
			}
			catch(Exception e)
			{
				if(i != 3)
				{
					System.out.println("Couldn't connect to the IP address: " + IP + " With port number: " + port + "\nTrying again");
				}
				else
				{
					System.out.println("Couldn't connect to the IP address: " + IP + " With port number: " + port);
					close();
				}
			}
		}
	}
	
	//Checks if the port number the user inputted is valid
	private static boolean validPort(String port)
	{
		Integer temp = 0;
		try
		{
			temp = Integer.valueOf(port);
			if(temp >= 0 && temp <= 65535)
			{
				return true;
			}
		}
		catch(Exception e)
		{
			return false;
		}
		return false;
	}
	
	private static boolean validIP(String IP)
	{
		if(IP.equalsIgnoreCase("localhost"))
		{
			IP = "127.0.01";
		}
		String pattern = "(\\d{1,2}|(0|1)\\d{2}|2[0-4]\\d|25[0-5])";
		String IP_pattern = pattern + "." + pattern + "." + pattern + "." + pattern;
		Pattern temp = Pattern.compile(IP_pattern);
		Matcher check = temp.matcher(IP);
		return check.matches();
	}
	
	//Stops the program
	private static void close() throws Exception
	{
		if(client != null)
		{
			client.close();
		}
		if(input != null)
		{
			input.close();
		}
		System.out.println("Closing...");
		System.exit(0);
	}

}
