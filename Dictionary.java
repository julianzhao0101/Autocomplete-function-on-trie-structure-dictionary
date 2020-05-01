import java.io.*;
import java.util.*;
public class Dictionary{
	public static void main(String[] args){
		
		int size = 10;
        String filename = "dict.txt";
		if (args.length == 1){
			
             
            try{    
                size = Integer.parseInt(args[0]);     // year     
        	} catch (Exception E){
                System.out.println("You can only put positive integer in the command ");
                System.exit(1);
        	}
		} 
		
		Autocomplete<List<String>> dict = new Autocomplete<>(filename, size);

		Scanner input = new Scanner(System.in);
        // our command line prompt                                                                                     
        System.out.print("Enter your prefix: ");

        while (input.hasNext()) {
            // read in next line as a string                                                                           
            String prefix = input.nextLine();

            if(prefix.equals("-q")){
                System.out.println("---Closing Dictionary---");
                System.exit(1);
            }

            List<String> list = dict.getCandidates(prefix);
			for(int i = 0; i < list.size(); i++){
				System.out.println(list.get(i));
			}
            
            System.out.print("Choose your candidates: ");                                                                   
            // our command line prompt  

            String candi = input.nextLine();

            if(candi.equals("-q")){
                System.out.println("---Closing Dictionary---");
                System.exit(1);
            }

            dict.pickCandidate(prefix,candi);

            System.out.print("Enter your prefix: ");
        }



	}

	
}
