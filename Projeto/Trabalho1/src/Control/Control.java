/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import Connect.*;
import Model.MyEdges;
import Model.MyVertex;
import Thrift.*;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author jose
 */
public class Control {
    
    MyEdges me = new MyEdges();
    MyVertex mv = new MyVertex();
    EdgesOP eoe = new EdgesOP();
    VertexOP eov = new VertexOP();
    
    public void changeEdges(Edges e){
        me.setV1id((int) e.getV1id());
        me.setV2id((int) e.getV2id());
        me.setFlag(e.getFlag());
        me.setDescription(e.getDescription());
        me.setWeight((float) e.getWeight());
    }

    public void changeVertex(Vertex v){
        mv.setName((int) v.getName());
        mv.setColor((int) v.getColor());
        mv.setDescription(v.getDescription());
        mv.setWeight((float) v.getWeight());
    }
    
     public Edges reversechangeEdges(MyEdges me){
        Edges e = new Edges();
         
        e.setV1id((int) me.getV1id());
        e.setV2id((int) me.getV2id());
        e.setFlag(me.getFlag());
        e.setDescription(me.getDescription());
        e.setWeight((double) me.getWeight());
        
        return e;
    }
     

    public Vertex reversechangeVertex(MyVertex mv){
        Vertex v = new Vertex();
        
        v.setName((int) mv.getName());
        v.setColor((int) mv.getColor());
        v.setDescription(mv.getDescription());
        v.setWeight((double) mv.getWeight());
        
        return v;
    }

    public void addEdges() throws ClassNotFoundException, SQLException{
        
        if(eoe.ExistenceEdges(me.getV1id(),me.getV2id())){
            System.out.println("Aresta já exiente");
        }
        else{
            if(eov.ExistenceVertex(me.getV1id()) && eov.ExistenceVertex(me.getV2id())){
                eoe.InsertEdge(me);
            }
            else{
                System.out.println("Um ou os dois vértices não existem");
            }
        }
    }
    
    public void addVertex() throws ClassNotFoundException, SQLException{

        if(eov.ExistenceVertex(mv.getName())){
            System.out.println("O vértice já existe\n");
        }
        else{
            eov.InsertVertex(mv);
            System.out.println("Vértice inserido! \n");
        }
    }
    
    public MyVertex readVertex(int vertex) throws ClassNotFoundException, SQLException{
        mv = eov.getVertex(vertex);
        return mv;
    }
    
    public MyEdges readEdges(int vertex1, int vertex2) throws ClassNotFoundException, SQLException{
        me = eoe.getEdge(vertex1, vertex2);
        return me;
    }
    
    public void removeEdges(int vertex1, int vertex2) throws ClassNotFoundException, SQLException{
        if(eoe.ExistenceEdges(vertex1, vertex2)){
            eoe.DeleteEdges(vertex1, vertex2);
            System.out.println("Aresta removida!");
        }
        else{
            System.out.println("Aresta não existe ou já foi removida");
        }
    }
    
    public void removeVertex(int name) throws ClassNotFoundException, SQLException{
        
        if(eov.ExistenceVertex(name)){
            eoe.DeleteAdjacentEdges(name);
            eov.DeleteVertex(name);
            System.out.println("Vértice removido.");
        }
        else{
            System.out.println("O vértice não existe ou já foi removido.");
        }
    }
    
    public List<MyEdges> getEdges() throws ClassNotFoundException, SQLException{
        return eoe.getAllEdges();
    }
    
    public List<MyVertex> getVertex() throws ClassNotFoundException, SQLException{
       return eov.getAllVertex();
    }
    //arestas adjacentes ao vertice
    public List<MyEdges> listVertexsEdges(int name) throws ClassNotFoundException, SQLException{
        return eov.getEdgesVertex(name);
    }
    
    //vertices da aresta
    public List<MyVertex> listEdgesVertex(int vertex1, int vertex2) throws ClassNotFoundException, SQLException{
        return eoe.getVertexEdges(vertex1, vertex2);
    }
    
    public List<MyVertex> getAdjacent(int name) throws ClassNotFoundException, SQLException{
        return eoe.getAdjacenteVertex(name);
    }
    
    public void uvc(int color, int name) throws ClassNotFoundException, SQLException{
        eov.updateVetexColor(color, name);
    }
    public void uvd(String description, int name) throws ClassNotFoundException, SQLException{
        eov.updateVetexDescription(description, name);
    }
    public void uvw(double weight, int name) throws ClassNotFoundException, SQLException{
        eov.updateVetexWeight((float)weight, name); 
    }
   
    public void uef(int flag, int vertex1, int vertex2) throws ClassNotFoundException, SQLException{
        eoe.updateEdgesFlag(flag, vertex1, vertex2);
    }
    
    public void ued(String description, int vertex1, int vertex2) throws ClassNotFoundException, SQLException{
        eoe.updateEdgesDescription(description, vertex1, vertex2);
    }
    
    public void uew(double weight, int vertex1, int vertex2) throws ClassNotFoundException, SQLException{
        eoe.updateEdgesWeight(vertex2, vertex1, vertex2);
    }
   
    
}
