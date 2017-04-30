
public class Main{
    public static void main(String args[]){
        int i;
        BoxMessage b = new BoxMessage();
        //b.setNumber(0);
        Thread t[] = new Thread[30];
        b.setMessage("asdfghjklpoiuytrewqzxcvbnmqazwsxedcrfvtgbyhnujmiklopqazwsxedcrfvtgbyhNMJUIKLOasAS");
        b.setNumber(0);
        for(i=0; i < 30; i++) {
            t[i] = new Thread(new Node(b));
        }
        i = 0;
        while(b.getNumber() < 80){
            t[i].run();
            i = (i+1)%30;
        }

    }
}
