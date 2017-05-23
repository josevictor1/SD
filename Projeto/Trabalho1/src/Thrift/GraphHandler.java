/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thrift;
import Connect.*;
import Control.*;
import Model.MyEdges;
import Model.MyVertex;
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
    Control c = new Control();
    Semaphore semaphore = new Semaphore(1);
    
    public void addVertex(Vertex vertex) throws org.apache.thrift.TException{
        c.changeVertex(vertex);
        try {
            c.addVertex();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void CreateEdges(Edges edges) throws org.apache.thrift.TException{
        c.changeEdges(edges);
        try {
            c.addEdges();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro CreateEdges");
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void CreateVertex(Vertex vertex) throws org.apache.thrift.TException{
        c.changeVertex(vertex);
        try {
            c.addVertex();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro CreateVertex");
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vertex ReadVertex(int vertex) throws org.apache.thrift.TException{
        Vertex v = new Vertex();
        MyVertex mv;
        
        try {
            semaphore.acquire();
            mv = c.readVertex(vertex);
            v = c.reversechangeVertex(mv);     
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ReadVertex");
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        semaphore.release();
        return v;
    }

    public Edges ReadEdges(int vertex1, int vertex2) throws org.apache.thrift.TException{
        Edges e = new Edges();
        MyEdges me;
        try {
            semaphore.acquire();
            me = c.readEdges(vertex1, vertex2);
            e = c.reversechangeEdges(me);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ReadEdges");
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        semaphore.release();
        return e;
    }

    public void DeleteEdges(int vertex1, int vertex2) throws org.apache.thrift.TException{
        try {
            semaphore.acquire();
            c.removeEdges(vertex1, vertex2);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro DeleteEdges");
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        semaphore.release();
    }

    public void DeleteVertex(int vertex1) throws org.apache.thrift.TException{
        try {
            semaphore.acquire();
            c.readVertex(vertex1);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro DeleteVertex");
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        semaphore.release();
    }
    
    public List<Edges> GetEdges() throws org.apache.thrift.TException{
        List<Edges> le = new ArrayList<>();
        List<MyEdges> lme;
        try {
            semaphore.acquire();
            lme = c.getEdges();
            for(MyEdges i: lme){
                le.add(c.reversechangeEdges(i));
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro GetEdges");
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        semaphore.release();
        return le;
    }

    public List<Vertex> GetVertex() throws org.apache.thrift.TException{
        List<Vertex> lv = new ArrayList<>();
        List<MyVertex> lmv;
        try {
            semaphore.acquire();
            lmv = c.getVertex();
            
            for(MyVertex mv: lmv){
                lv.add(c.reversechangeVertex(mv));
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro GetVertex");
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        semaphore.release();
        return lv;
    }

    public List<Vertex> GetVertexEdges(int vertex1, int vertex2) throws org.apache.thrift.TException{
        List<Vertex> lv = new ArrayList<Vertex>();
        List<MyVertex> lmv;
        try {
          semaphore.acquire();
          lmv = c.listEdgesVertex(vertex1, vertex2);
          
          for(MyVertex i: lmv){
              lv.add(c.reversechangeVertex(i));
          }
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro GetVertexEdges");
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        semaphore.release();
        return lv;
    }

    public List<Edges> GetEdgesVertex(int name) throws org.apache.thrift.TException{
        List<Edges> le = new ArrayList<>();
        List<MyEdges> lme;
        try {
            semaphore.acquire();
            lme = c.listVertexsEdges(name);
            for(MyEdges i: lme){
                le.add(c.reversechangeEdges(i));
            }
        } catch (SQLException ex) {
            System.out.println("Erro GetEdgesVertex");
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException | InterruptedException ex) {
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        semaphore.release();
        return le;
    }

    public List<Vertex> GetAdjacentVertex(int name) throws org.apache.thrift.TException{
        List<Vertex> lv = new ArrayList<>();
        List<MyVertex> lmv;
        try {
            semaphore.acquire();
            lmv = c.getAdjacent(name);
            for(MyVertex i: lmv){
                lv.add(c.reversechangeVertex(i));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro GetAdjacentVertex");
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        semaphore.release();
        return lv;
    }

    @Override
    public void UpdateEdgesWeight(double weight, int vertex1, int vertex2) throws TException {
        try {
            semaphore.acquire();
            c.uew(weight, vertex1, vertex2);
        } catch (ClassNotFoundException | SQLException | InterruptedException ex) {
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        semaphore.release();
    }

    @Override
    public void UpdateEdgesFlag(int flag, int vertex1, int vertex2) throws TException {
        try {
            semaphore.acquire();
            c.uef(flag, vertex1, vertex2);
        } catch (ClassNotFoundException | SQLException | InterruptedException ex) {
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        semaphore.release();
    }

    @Override
    public void UpdateEdgesDescription(String description, int vertex1, int vertex2) throws TException {
        try {
            semaphore.acquire();
            c.ued(description, vertex1, vertex2);
        } catch (ClassNotFoundException | SQLException | InterruptedException ex) {
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        semaphore.release();
    }

    @Override
    public void UpdateVertexColor(int color, int name) throws TException {
        try {
            semaphore.acquire();
            c.uvc(color, name);
        } catch (ClassNotFoundException | SQLException | InterruptedException ex) {
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        semaphore.release();
    }

    @Override
    public void UpdateVertexDescription(String description, int name) throws TException {
        try {
            semaphore.acquire();
            c.uvd(description, name);
        } catch (ClassNotFoundException | SQLException | InterruptedException ex) {
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        semaphore.release();
    }

    @Override
    public void UpdateVertexWeight(double weight, int name) throws TException {
        try {
            semaphore.acquire();
            c.uvw(weight, name);
        } catch (ClassNotFoundException | SQLException | InterruptedException ex) {
            Logger.getLogger(GraphHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        semaphore.release();
    }
    
}
