public class BoxMessage {
    private int number;
    private String message;
    //private boolean available;

    public void setNumber(int number){
        this.number = number;
    }
    /*public void setAvailable(boolean available){
        this.available = available;
    }*/
    public int getNumber(){
        return this.number;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
    /*public boolean getAvailable(){
        return available;
    }*/
/*
    public synchronized int get(int who){
        //while (available == false) {
            try {
                //wait for Producer to put value
                wait();
            } catch (InterruptedException e) { }
        //}
            ///available = false;
            //notify Producer that value has been retrieved

            notify();
            return 0;
    }

    public synchronized void put(){
        System.out.println(this.getNumber());
        //while (this.getNumber() < 100) {
            try {
                this.setNumber(this.getNumber() + 1);
                wait();
            } catch (InterruptedException e) { }
        //}

            //}
            //contents = value;
            //available = true;
            //notify Consumer that value has been set
            notifyAll();

    } */
}
