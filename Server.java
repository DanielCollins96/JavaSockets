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

        // File text = new File("H:\\3533\\JavaSockets\\codebook.txt");
        // File text = new File("H:\\3533-i\\JavaSockets\\codebook.txt");
        File text = new File("C:\\l\\Desktop\\Programmin\\School\\JavaSockets\\codebook.txt");

        ArrayList<String[]> wordArray = new ArrayList<String[]>();

        BufferedReader fileReader = new BufferedReader(new FileReader(text));
        String line = null;
        while ((line = fileReader.readLine()) != null)
        {
            String[] splitLine = line.split("\t",2);
            wordArray.add(splitLine);
        }


        // System.out.println(text.getAbsolutePath());        
        ServerSocket welcomeSocket = new ServerSocket(6789);
        while(true){
            String delimiters = "\\s+|,\\s*|\\.\\s*|\\!|\\?";
            Socket connectionSocket = welcomeSocket.accept();

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            clientSentence = inFromClient.readLine();
            capitalizedSentence = (clientSentence.toUpperCase() + '\n');
            String[] inputs;
            inputs = capitalizedSentence.split(delimiters);

            for (int i = 0; i < wordArray.size(); i++){

                    for (String[] array: wordArray){
                        String wordCode = array[0];
                        for (int j = 0; j < inputs.length; j++)
                        {
                            String inputIndexed = inputs[j];

                            // if (inputIndexed.equals(wordCode + '\n'))
                            if (inputIndexed.equals(wordCode))
                            {
                                String newValue = array[1];
                                capitalizedSentence = capitalizedSentence.replaceAll("\\b"+inputIndexed+ "\\b", newValue) + '\n';
                                // capitalizedSentence = newValue;
                            }
                        }
                    }
                
            }
            // System.out.println(capitalizedSentence);
            outToClient.writeBytes(capitalizedSentence);
        } 
    }
}