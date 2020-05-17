
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;     // ONLY STRUCTURE CHANGE - java.util.Scanner - java.io.DataOutputStream;

public class id12s_11thr_multisr_final_EXE { 
          
    public static Vector<HandlerReader> carr = new Vector<HandlerReader>();  // ONLY STRUCTURE CHANGE
    public static Map<Integer, String> clall = new HashMap<Integer, String>();
    static int cnum = 0;     
    
    public static void main(String[] args) throws IOException {

        ServerSocket server = null;
        server = new ServerSocket(32000); server.setReuseAddress(true);
             
        while (true) {
                
                Socket s = server.accept();      
                System.out.println("New client connected " + s.getInetAddress().getHostAddress());

                /*clienthandler h = new clienthandler("id"+cnum, s);
                threadparent th = new threadparent(h, s); th.start();
                //carr.add(clientsockth); clientsockth.start();  */

                threadsend send = new threadsend(s);send.start(); // empty thread at beginning

                //new
                HandlerReader h = new HandlerReader("id "+cnum, s);
                //Socket cs = server.accept(); 
                MyServerThSwitcher th = new MyServerThSwitcher(h, s); th.start();
                carr.add(h);
 
                /*Thread t = new Thread(th);
                t.start(); //cnum++; */

                cnum++;                  
        }  }}
    

/*
class clienthandler {

        private final Socket clientsocket;
        private String cname;  

        public clienthandler (String cname, Socket clientsocket) {
            this.cname = cname; this.clientsocket = clientsocket;  }
        
        synchronized public boolean readerclienthandler(Socket clientsocket) {
        
                        // run printers and writers
            
                        String received; String instr;String instr2;

                        PrintWriter out = null;   
                        BufferedReader in = null;
                        BufferedReader incl2 =null;
                        Scanner scn = new Scanner(System.in);

                try {
                    in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
                    out = new PrintWriter(clientsocket.getOutputStream(), true); 
                    //incl2 = // //new BufferedReader(new InputStreamReader(scanner)); //read interactive input with Java
                
                    //for loop can omit i line too   // array[i]
                    
                        //for(int i=0;i<2;i++) {  // for(String eans : ans)
                         //     Thread.sleep(300);
                         //     out.println(line+", " + ans[index]);} 

                    received = in.readLine();
                    //System.out.println(received); 
            
                             
                        if (received.equals("exit") || received.equals("logout") || received.equals(null)){
                        
                        System.out.println(this.cname +" disconnected");  
					    this.clientsocket.close(); in.close();  out.close();   //incl2.close();
                        return false;}  // close connection   // return false; // break;
                        
                          
                        StringTokenizer st = new StringTokenizer(received, "#");
                        String Recipient=null;
                        while (st.hasMoreTokens())
                              {Recipient= st.nextToken();}
                        System.out.println(Recipient); 
                        
                        
                        String[] ans = {"how are you","hi there ","greetings"};
                        Random random = new Random();
                        int index = random.nextInt(3);
                        instr2 = scn.next();//incl2.readLine();
                        instr = instr2+" "+ans[index];

                        StringTokenizer st2 = new StringTokenizer(instr, "#"); 
                        String MsgToSend=null;
                        while (st2.hasMoreTokens())
                        {MsgToSend = st2.nextToken();}

                        for (clienthandler mc : id12s_11thr_multisr_final_EXE.carr) { 
                            //if (mc.cname.equals(recipient) && mc.isloggedin==true) { 
                            out.println(mc.cname+" sent to sr: "+Recipient+" | sr rply: "+MsgToSend);  
                            break;  // }
                            }    
 
                        System.out.println(MsgToSend);  
                        return true;                             
                        
                }catch (IOException e) { e.printStackTrace(); }               
       
            try{ in.close(); out.close(); clientsocket.close();  }
            catch (IOException ux) {ux.printStackTrace(); return false;}
            catch (Exception e) {e.printStackTrace();return false;}
            
            catch (Throwable throwable) {System.out.println(false);}
            return true;
        }
}

class threadparent extends Thread {

    clienthandler h; Socket serverthscons; 

    public threadparent(clienthandler h, Socket serverthscons) {   // ONLY STRUCTURE CHANGE
        this.h = h; this.serverthscons = serverthscons;}

                    @Override
                    public void run() {
                    while (h.readerclienthandler(serverthscons)==true) // execute true boolean 1111111111
                          {
                            try { 
                            Thread.sleep(5000); System.out.println("thread true");
                            } catch (InterruptedException ex) {ex.printStackTrace();}  
                    }     
                    try {serverthscons.close();} catch (IOException ex) {ex.printStackTrace(); }
                    } 
}
*/




            
class HandlerReader {
 
        String instr;  String cid;
        BufferedReader insr = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream out; Socket csock; DataOutputStream out2;
        public HandlerReader(String cid, Socket csock){this.cid =cid;  this.csock =csock;}
        
        synchronized public boolean readerbundle(Socket cs) {  //remove passer
            try {   BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
                    out =new DataOutputStream(cs.getOutputStream());  out2=new DataOutputStream(cs.getOutputStream());
    
                    String cmsg = in.readLine();
              
                    if (cmsg == null || cmsg.equals("exit")) {
                        return false;}  // if client exits, close connection
                    
                    if (cmsg != null) {
                        id12s_11thr_multisr_final_EXE.clall.put(id12s_11thr_multisr_final_EXE.cnum, cmsg);}
                    
                    if (cmsg != null) {
                        System.out.println("client "+cid+": "+cmsg);}
                    
                    instr = insr.readLine() + "\n";
                    out.writeBytes(instr); 
                         
                    for (HandlerReader mc : id12s_11thr_multisr_final_EXE.carr) { 
                        //if (mc.cname.equals(recipient) && mc.isloggedin==true) { 
                        out2.writeBytes("client "+mc.cid+" history: "+cmsg+", sr rply: "+instr);  
                        break;  // }
                        }   
                    
                    return true;
    
                } catch (SocketException e) {System.out.println("Disconnected");return false;} 
                  catch (Exception e) {e.printStackTrace();return false;}
        }
       
        //public boolean testadd() {

            //int carrlg = id12s_11thr_multisr_final_EXE.carr.size(); // carr length

            //boolean x;    // add entries
            //for (x = false, 
                    //id12s_11thr_multisr_final_EXE.clall.put(id12s_11thr_multisr_final_EXE.cnum,"test message zero"),  
                    //id12s_11thr_multisr_final_EXE.clall.put(id12s_11thr_multisr_final_EXE.cnum, "test message one"),      
                    //id12s_11thr_multisr_final_EXE.clall.put(id12s_11thr_multisr_final_EXE.cnum, "test message two"); x;);       
            //return true;
        //}    
    } 
     
class MyServerThSwitcher extends Thread {  //thread is for multiple streamssets in case the handler has more streams
     
        HandlerReader h;Socket serverthscons; 
     
        public MyServerThSwitcher(HandlerReader h, Socket serverthscons) {
                this.h = h; this.serverthscons = serverthscons;}
     
                    @Override
                    public void run() {
                    while (h.readerbundle(serverthscons)==true) // execute true boolean 
                          {
                            try { 
                            Thread.sleep(5000); System.out.println("thread true");
                            //String maptest = h.testadd() ? "all good" : "empty map";
                            //System.out.println(maptest);
                            } catch (InterruptedException ex) {ex.printStackTrace();}  
                    }     
                    try {serverthscons.close();} catch (IOException ex) {ex.printStackTrace(); }
                    } 
}
           

class threadsend extends Thread{
        
        PrintWriter pwPrintWriter;
        Socket clientSock = null;
        
        public threadsend(Socket clientSock) {this.clientSock = clientSock;}
        
        public void run() {
            try{
            pwPrintWriter =new PrintWriter(new OutputStreamWriter(this.clientSock.getOutputStream()));
        
                String msgToClient = null;
                //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                //msgToClient = "no history yet" ;   //input.readLine();

                Integer key = id12s_11thr_multisr_final_EXE.clall.entrySet().stream().findFirst().get().getKey();
                String value = id12s_11thr_multisr_final_EXE.clall.entrySet().stream().findFirst().get().getValue();
                
                Thread.sleep(6000); 
                pwPrintWriter.println("history is "+key+" "+value+"\n"); pwPrintWriter.flush();         
            }
            catch(Exception ex){System.out.println(ex.getMessage());}	
 }}


    