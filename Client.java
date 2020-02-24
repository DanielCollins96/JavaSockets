import java.net.*;
import java.io.*;

/**
 * Client
 */
public class Client {


    public static void main(String[] args) throws IOException{
        String sentence;
        String modifiedSentence;
        
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        // Socket clientSocket = new Socket("hostname", 6789);
        Socket clientSocket = new Socket("10.7.42.85", 6789);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        sentence = inFromUser.readLine();
        outToServer.writeBytes(sentence + '\n');

        modifiedSentence = inFromServer.readLine();

        System.out.println("FROM SERVER: "+ modifiedSentence);

        clientSocket.close();


    }
}