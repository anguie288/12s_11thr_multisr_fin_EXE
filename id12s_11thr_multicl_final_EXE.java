import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
 
public class id12s_11thr_multicl_final_EXE {
 
    public static void main(final String[] args) throws IOException {
               
        final String host = "127.0.0.1";
        final int port = 32000;
        
        final Socket s = new Socket(host, port);

         //** printer lines three lines
        
        /*
        final Runnable sendMessage = new Runnable() {
            @Override
            public void run() {
                
                while (true) { 
                    		
                    try {
                        Scanner scn = new Scanner(System.in);
                        PrintWriter out = new PrintWriter(s.getOutputStream()); 
                        final String msg = scn.nextLine(); 
                        out.println(msg); }   
                    catch (final NullPointerException e){e.printStackTrace();} catch(final Exception e) {System.out.println("Unexcepted Exception");
                        e.printStackTrace();}
                    finally{}
                } 
            
            }};

        final Runnable readMessage = new Runnable() {
            @Override
            public void run() {
                

                while (true) { 
                    try {   BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                            String msg = in.readLine(); 
                          
                            if (msg.equals("exit")||msg.equals("logout")||msg.equals(null)) {
                                //out.close();in.close(); 
                                break;} 
                            System.out.println(msg);    } 
                    catch (IOException e) { e.printStackTrace(); } 
                } 
                 
            }};

        Thread threadsend = new Thread(sendMessage);
        Thread threadread = new Thread(readMessage);

        threadsend.start();
        threadread.start(); */
        
        
        Thread thrreader = new Thread() {
            public void run()  {
                //String instr;
                String answer;
                //BufferedReader incl =new BufferedReader(new InputStreamReader(System.in));       
                try{
                while (true) {  // fast reading and writing            
                    //DataOutputStream out =new DataOutputStream(s.getOutputStream());
                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
     
                    //instr = incl.readLine();
                    //out.writeBytes(instr + '\n');
                    //if (instr.equals("exit")) {break;}
     
                    answer = in.readLine();
                    System.out.println("FROM SERVER: " + answer);  
                }}catch (Exception e) {e.printStackTrace();}
                try{ s.close();}catch (IOException ux) {ux.printStackTrace(); }
                catch (Exception e) {e.printStackTrace();}         
            }    };
        thrreader.start();
        

        Thread thrwriter = new Thread() {
            public void run()  {
                String instr;
                //String answer;
                BufferedReader incl =new BufferedReader(new InputStreamReader(System.in));          
                try{
                while (true) {  // fast reading and writing               
                    DataOutputStream out =new DataOutputStream(s.getOutputStream());
                    //BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));    
                    instr = incl.readLine();
                    out.writeBytes(instr + '\n');
     
                    if (instr.equals("exit")) {break;}
                    //answer = in.readLine();
                    //System.out.println("FROM SERVER: " + answer);    
                }}catch (Exception e) {e.printStackTrace();}
                try{ s.close();}catch (IOException ux) {ux.printStackTrace(); }
                catch (Exception e) {e.printStackTrace();}  
            }    };
            thrwriter.start();  
        
        /*   
        if(!s.isConnected()){
            out.close();in.close();     s.close();  // ONLY STRUCTURE CHANGE - s.close()
        }  */
               
    }
}

