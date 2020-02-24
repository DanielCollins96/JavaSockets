import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Server
 */
public class Server {

    public static void main(String[] args) throws IOException{
        String clientSentence;
        String capitalizedSentence;

        File text = new File("H:\\3533\\JavaSockets\\codebook.txt");
        ArrayList<String[]> wordArray = new ArrayList<String[]>();

        BufferedReader fileReader = new BufferedReader(new FileReader(text));
        String line = null;
        while ((line = fileReader.readLine()) != null)
        {
            String[] splitLine = line.split("\t",2);
            wordArray.add(splitLine);
        }
        System.out.println(wordArray.size());

        // System.out.println(text.getAbsolutePath());        
        ServerSocket welcomeSocket = new ServerSocket(6789);
        while(true){
            Socket connectionSocket = welcomeSocket.accept();

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            clientSentence = inFromClient.readLine();
            capitalizedSentence = (clientSentence.toUpperCase() + '\n');
            System.out.println(capitalizedSentence.equals("hi" + '\n'));
            for (int i = 0; i < wordArray.size(); i++){
                    // Iterator<String[]> itr = wordArray.iterator();
                    for (String[] array: wordArray){
                        String wordCode = array[0];
                        if (capitalizedSentence.equals(wordCode))
                        {
                            System.out.println(array[1]);
                            capitalizedSentence = array[1];
                        }
                        for (String s : array){
                            // System.out.println(s);
                        }
                    }
                    // System.out.println(wordArray.get(i));
                
            }

            outToClient.writeBytes(capitalizedSentence);
        } 
    }
}