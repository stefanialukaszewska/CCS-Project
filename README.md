CCS - Centralized Computing System

Description:
App opens both TCP and UDP ports. UDP so clients can find working service, by broadcasting a message, that starts with "CCS DISCOVER". If service is found, response is sent - "CCS FOUND".
TCP port is listening for a command: <OPER> <ARG1> <ARG2>, where OPER - ADD, SUB, MUL, DIV and ARG* - int numbers. Handles exceptions like - dividing by 0, not enough arguments, incorrect OPER (operation).
After getting the command, it sends a response to the client.

App also keeps statistics - one since starting server, second one for last 10s.
Prints them every 10s on the screen.

RUNNGING PROGRAM: To run the program use one argument to act as port, the app should run on. Main class is src/Server/Main.java
