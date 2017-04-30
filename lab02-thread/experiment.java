public class Message{
    boolean activated;

    public synchronized void Transmit(  ) {
        activated =
        if(flag){
            try {
    		   while (true) {

    			   wait();
    			   sleep(1);
    		   }
    	   } catch (InterruptedException e) {

	       }
        }

	}
}

public class Main {

    public static void main(String args){
        Thread t[] = new Thread[30];
        try{
                for(int i=0; i<30; i++) {
                    t[i] = new Thread(new HelloThread());
                    t[i].start();
                }

                for(int j=0; j<30; j++) {
                    t[j].join();
                }
        }catch(InterruptedException e) {
        }
    }

}
