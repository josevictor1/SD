
class Main{
    public static void main(String args[]){
        BoxMessage b = new BoxMessage();
        b.setNumber(0);
        int i;
        Thread t[] = new Thread[30];
        //try{
                for(i=0; i<30; i++) {


                    t[i] = new Thread(new Node(b));
                    //t[i].start();
                    //i = (i+1)%100;
                }
                i = 0;
                while(b.getNumber() < 100){
                    t[i].run();
                    i = (i+1)%30;
                }

        //}catch(InterruptedException e) {}
    }
}
