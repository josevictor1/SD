/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thrift;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;
import java.util.concurrent.Semaphore;
/**
 *
 * @author jose
 */
public class GraphHandler implements Graph.Iface{
    
    /**
     *
     * @param edges
     * @throws org.apache.thrift.TException
     */
    List<Vertex> v = new ArrayList<Vertex>();
    List<Edges> e = new ArrayList<Edges>();
    Semaphore semaphore = new Semaphore(1);
    
    
    public boolean existenceVertex(int name){
        for(Vertex i: v){
            if(i.getName() == name){
                return true;
            }
        }
        return false;
    }
    
    public boolean existenceEdges(int vertex1, int vertex2){
        
        for(Edges  i: e){
            if((i.getV1id() == vertex1 && i.getV2id() == vertex2) || (i.getV1id() == vertex2 && i.getV2id() == vertex1)){
                return true;
            }
        }
        return false;
    }
    
    public void addVertex(Vertex vertex) throws org.apache.thrift.TException{
        if(!existenceVertex((int) vertex.getName())){
            try{
                semaphore.acquire();
                v.add(vertex);
            }
            catch(Exception e){
            }
            finally{
                semaphore.release();
            }
        }
        
    }
    
    public void CreateEdges(Edges edges) throws org.apache.thrift.TException{
        if(!existenceEdges((int)edges.getV1id(),(int)edges.getV2id())){
            if(existenceVertex((int)edges.getV1id()) && existenceVertex((int)edges.getV2id())){
                try{
                    e.add(edges);
                }
                catch(Exception e){
                }
                finally{
                    semaphore.release();
                }
            }

        }
    }

    public void CreateVertex(Vertex vertex) throws org.apache.thrift.TException{
        if(existenceVertex((int)vertex.getName())){
            try{
                v.add(vertex);
            }
            catch(Exception e){}
            finally{
                semaphore.release();
            }
        }
    }

    public Vertex ReadVertex(int vertex) throws org.apache.thrift.TException{
        Vertex result = null;
        
       for(Vertex i: v){
           if((int)i.getName() == vertex){
               result = i;
           }
       }
        
        return result;
    }

    public Edges ReadEdges(int vertex1, int vertex2) throws org.apache.thrift.TException{
        Edges result = null;
        
        for(Edges i: e){
            if((vertex1 == (int)i.getV1id() && vertex2 == (int)i.getV2id()) || (vertex2 == (int)i.getV1id() && vertex1 == (int)i.getV2id())){
                result = i;
            }
        }
        
        return result;
    }

    public void DeleteEdges(int vertex1, int vertex2) throws org.apache.thrift.TException{
        Edges result = ReadEdges(vertex1,vertex2);
        if(result!= null)
            e.remove(result);
    }

    public void DeleteVertex(int vertex1) throws org.apache.thrift.TException{
        Vertex result = ReadVertex(vertex1);
        if(result != null)
            v.remove(result);
    }
    
    public List<Edges> GetEdges() throws org.apache.thrift.TException{

        return e;
    }

    public List<Vertex> GetVertex() throws org.apache.thrift.TException{
        return v;
    }

    public List<Vertex> GetVertexEdges(int vertex1, int vertex2) throws org.apache.thrift.TException{
        List<Vertex> lv = new ArrayList<Vertex>();
        lv.add(ReadVertex(vertex1));
        lv.add(ReadVertex(vertex2));
        return lv;
    }

    public List<Edges> GetEdgesVertex(int name) throws org.apache.thrift.TException{
        List<Edges> le = new ArrayList<>();
        for(Edges i: e){
            if(i.getV1id() == name || i.getV2id() == name){
                le.add(i);
            }
        }
        return le;
    }

    public List<Vertex> GetAdjacentVertex(int name) throws org.apache.thrift.TException{
        List<Vertex> lv = new ArrayList<>();
        for(Edges i: e){
            if(i.getV1id() == name){
                lv.add(ReadVertex((int) i.getV2id()));
            }
            else if(i.getV2id() == name){
                lv.add(ReadVertex((int) i.getV1id()));
            }
        }
        return lv;
    }

    @Override
    public void UpdateEdgesWeight(double weight, int vertex1, int vertex2) throws TException {
        int index;
        
        for(Edges i: e){
            try{
                semaphore.acquire();
                if((i.getV1id() == vertex1 && i.getV2id() == vertex2)){
                    index = e.indexOf(i);
                    i.setWeight(weight);
                    e.add(index, i);
                }
                else if((i.getV1id() == vertex2 && i.getV2id() == vertex1)){
                    index = e.indexOf(i);
                    i.setWeight(weight);
                    e.add(index, i);
                }
            }
            catch(Exception excp){}
            finally{
                semaphore.release();
            }
        }
    }

    @Override
    public void UpdateEdgesFlag(int flag, int vertex1, int vertex2) throws TException {
        int index;
        
        for(Edges i: e){
            try{
                semaphore.acquire();
                if((i.getV1id() == vertex1 && i.getV2id() == vertex2)){
                    index = e.indexOf(i);
                    i.setFlag(flag);
                    e.add(index, i);
                }
                else if((i.getV1id() == vertex2 && i.getV2id() == vertex1)){
                    index = e.indexOf(i);
                    i.setFlag(flag);
                    e.add(index, i);
                }
            }
            catch(Exception excp){}
            finally{
                semaphore.release();
            }
        }
    }

    @Override
    public void UpdateEdgesDescription(String description, int vertex1, int vertex2) throws TException {
        int index;
        
        for(Edges i: e){
            try{
                semaphore.acquire();
                if((i.getV1id() == vertex1 && i.getV2id() == vertex2)){
                    index = e.indexOf(i);
                    i.setDescription(description);
                    e.add(index, i);
                }
                else if((i.getV1id() == vertex2 && i.getV2id() == vertex1)){
                    index = e.indexOf(i);
                    i.setDescription(description);
                    e.add(index, i);
                }
            }
            catch(Exception excp){}
            finally{
                semaphore.release();
            }
        }  
    }

    @Override
    public void UpdateVertexColor(int color, int name) throws TException {
        int index;
        
        for(Vertex i: v){
            try{
                semaphore.acquire();
                if(i.getName() == name){
                    index = v.indexOf(i);
                    i.setColor(color);
                    v.add(index, i);
                }
            }
            catch(Exception excp){}
            finally{
                semaphore.release();
            }
        }
    }

    @Override
    public void UpdateVertexDescription(String description, int name) throws TException {
        int index;
        
        for(Vertex i: v){
            try{
                semaphore.acquire();
                if(i.getName() == name){
                    index = v.indexOf(i);
                    i.setDescription(description);
                    v.add(index, i);
                }
            }
            catch(Exception excp){}
            finally{
                semaphore.release();
            }
        }
    }

    @Override
    public void UpdateVertexWeight(double weight, int name) throws TException {
        int index;
        
        for(Vertex i: v){
            try{
                semaphore.acquire();
                if(i.getWeight() == weight){
                    index = v.indexOf(i);
                    i.setWeight(weight);
                    v.add(index, i);
                }
            }
            catch(Exception excp){}
            finally{
                semaphore.release();
            }
        }
    }
    
}
