package Model;

public class MyVertex{
    private int name;
    private int color;
    private String description;
    private float weight;

    public void setName(int name){
            this.name = name;
    }
    public void setColor(int color){
            this.color = color;
    }
    public void setDescription(String description){
            this.description = description;
    }
    public void setWeight(float weight){
            this.weight = weight;
    }
    public int getName(){
            return this.name;
    }
    public int getColor(){
            return this.color;
    }
    public String getDescription(){
            return this.description;
    }
    public float getWeight(){
            return this.weight;
    }
}
