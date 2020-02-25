import java.io.*;
import java.util.*;

public class Find_File {
    public static void main(String[] args) throws IOException{
        File file = new File("codebook.txt");

        System.out.println(file.getAbsolutePath());
    }
    }