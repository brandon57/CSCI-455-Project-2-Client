# CSCI-455-Project-2-Client
The client application of project 2 for CSCI 455

If you are looking for the server application the github repository for it is here [Server application](https://github.com/brandon57/CSCI-455-Project-1-Server)

Note: In order to run this you need Java JDK version 17 or newer

# These are the requirements for the project
<p align="center">
  <img src="Documents/project1_2023_Fall.png" width="688" />
</p>

# How to start the client
In order to run the client application you need to open the command line or powershell and navigate to the directory of where the file is located.
Once you do that you can start the program by typing this command `java -jar Client.jar`

Once you do that should be greeted with a screen asking you what you want to do.
```text
What do you want to do?
1. Connect to a website
2. Exit
```

Once you get this you can type 1 or connect to connect to the server or you can type 2 or exit to close the program.

If you decide to connect you will be greated with this screen.
```text
Now type the IP address of where you want to connect.
Your input should look like this "0-255.0-255.0-255.0-255".
If your running the server off of the same computer you can type "localhost" instead.
Typing "exit" will close the program.
```
At this screen you are susposed to type in the IP address of the server you want to connect to. Or if you decide you don't want to connect anymore you can type exit to close the program.

Once you type a valid IP address you will then be asked to enter the port number of the server
```text
What is the port number of the server? Your input should be between 0-65535
Typing "exit" will close the program.
```
Once you type a valid port number you'll the application will try to connect and if all goes well you'll connect to the server and be greet with this screen

```text
You are connected!

---------------------------------------------------------------------------------
Here are on going funderaisers:
1. John's college fund | raised: $0 | goal: $1000 | deadline: 10/30/2023 |
---------------------------------------------------------------------------------
These are the options you have to chose from
1. See current funderaisers
2. See past funderaisers
3. Create a funderaiser
4. Donate to a funderaiser
5. Refresh
6. Exit
```
Once you get to this screen you are free to navigate the server.

# Navigating the menus
The way to navigate the server is by typing the number next to the option or typing the key word of the option.
An example of this is to either enter 2 or the word past in order to see past fundraisers.
```text
input: past
Result:
---------------------------------------------------------------------------------
Here are past funderaisers:
1. John's Wedding | raised: $0 | goal: $10000 | ended: 11/20/2022 |
---------------------------------------------------------------------------------
These are the options you have to chose from
1. See current funderaisers
2. See past funderaisers
3. Create a funderaiser
4. Donate to a funderaiser
5. Refresh
6. Exit
```

