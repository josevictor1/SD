package Model;

public class MyEdges{
    private int V1id;
    private int V2id;
    private float Weight;
    private int flag;
    private String description;

    public void setV1id(int V1id){
            this.V1id = V1id;
    }

    public void setV2id(int V2id){
            this.V2id = V2id;
    }

    public void setWeight(float Weight){
            this.Weight = Weight;
    }

    public void setFlag(int flag){
            this.flag = flag;
    }

    public void setDescription(String Description){
            this.description = Description;
    }

    public int getV1id(){
            return this.V1id;
    }

    public int getV2id(){
            return this.V2id;
    }

    public float getWeight(){
            return this.Weight;
    }

    public int getFlag(){
            return this.flag;
    }

    public String getDescription() {
        return description;
    }
	
	
}
