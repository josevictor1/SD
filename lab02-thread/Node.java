import java.util.*;

public class Node implements Runnable{
    private BoxMessage boxmessage;

    public Node(BoxMessage boxmessage){
        this.boxmessage = boxmessage;
    }

    private void setBoxmessage(BoxMessage boxmessage){
        this.boxmessage = boxmessage;
    }
    private BoxMessage getBoxmessage(){
        return this.boxmessage;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void upper(){

        int position = this.getBoxmessage().getNumber();
        char aux[] = this.getBoxmessage().getMessage().toCharArray();

        if(Character.isLowerCase(aux[position])){
            aux[position] = Character.toUpperCase(aux[position]);
            this.getBoxmessage().setNumber(position + 1);
            this.getBoxmessage().setMessage(String.valueOf(aux));
        }
        else{
            this.getBoxmessage().setNumber(position + 1);
        }
        clearScreen();
        System.out.println("\n" + this.getBoxmessage().getMessage());
        System.out.println(this.getBoxmessage().getNumber());
    }



    public void run() {
        //System.out.println("\n passou");
        //System.out.println(this.getBoxmessage().getNumber());
            try {
                upper();
                Thread.sleep(1000);
            }catch(InterruptedException e){

            }
            //this.getBoxmessage().setNumber(this.getBoxmessage().getNumber() + 1);
            //System.out.println(this.getBoxmessage().getNumber());
            //wait();
            //notify();
    }
}
