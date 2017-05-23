package Connect;

import Model.MyEdges;
import Model.MyVertex;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EdgesOP {
    String sql;
    Connection con = null;
    PreparedStatement  pst = null;
    ResultSet rs = null;

    
    public void InsertEdge(MyEdges e) throws ClassNotFoundException, SQLException{
        this.con = Connect.ConnectPostgress.conectabd();
        this.sql = "INSERT INTO Edges"+"(V1ID,V2ID,WEIGHT,FLAG1,DESCRIPTION)"+"VALUES(?,?,?,?,?)";

        this.pst = con.prepareStatement(sql);
        pst.setInt(1, e.getV1id());
        pst.setInt(2, e.getV2id());
        pst.setFloat(3, e.getWeight());
        pst.setInt(4, e.getFlag());
        pst.setString(5, e.getDescription());
        pst.execute();
        pst.close();     
    }
    
    public MyEdges getEdge(int vertex1, int vertex2) throws ClassNotFoundException, SQLException{
        this.con = Connect.ConnectPostgress.conectabd();
        this.sql = "SELECT * FROM Edges WHERE Edges.V1ID =\'"+vertex1+"\'"+"AND Edges.V2ID =\'"+vertex2+"\'";
        
        this.pst = con.prepareStatement(sql);
        this.rs = pst.executeQuery();
        
        while(rs.next()){
            MyEdges e = new MyEdges();
            e.setV1id(vertex1);
            e.setV2id(vertex2);
            e.setWeight(rs.getFloat("WEIGHT"));
            e.setFlag(rs.getInt("FLAG1"));
            e.setDescription(rs.getString("DESCRIPTION"));
            pst.close();
            con.close();
            return e;
        }
        return null;
    }
    
    public List<MyEdges> getAllEdges() throws ClassNotFoundException, SQLException{
        this.con = Connect.ConnectPostgress.conectabd();
        this.sql = "SELECT * FROM Edges";
        
        this.pst = con.prepareStatement(sql);
        this.rs = pst.executeQuery();
        List<MyEdges> lme;
        lme = new ArrayList<MyEdges>();
        
        while(rs.next()){
            MyEdges e = new MyEdges();
            e.setV1id(rs.getInt("V1ID"));
            e.setV2id(rs.getInt("V2ID"));
            e.setWeight(rs.getFloat("WEIGHT"));
            e.setFlag(rs.getInt("FLAG1"));
            e.setDescription(rs.getString("DESCRIPTION"));
            lme.add(e);
        }
        pst.close();
        con.close();
        
        return lme;
    }
    
    public List<MyVertex> getVertexEdges(int vertex1, int vertex2) throws ClassNotFoundException, SQLException{
        this.con = Connect.ConnectPostgress.conectabd();
        this.sql = "SELECT * FROM Vertex WHERE Vertex.NAMEVERTEX =\'"+vertex1+"\'"+"OR Vertex.NAMEVERTEX =\'"+vertex2+"\'";
        
        this.pst = con.prepareStatement(sql);
        this.rs = pst.executeQuery();
        
        List<MyVertex> lmv = new ArrayList<MyVertex>();
        System.out.println("Veritices da aresta ("+vertex1+","+vertex2+"):");
        while(rs.next()){
            MyVertex mv = new MyVertex();
            mv.setName(rs.getInt("NAMEVERTEX"));
            mv.setColor(rs.getInt("COLOR"));
            mv.setDescription(rs.getString("DESCRIPTION"));
            mv.setWeight(rs.getFloat("WEIGHT"));
            
            lmv.add(mv);
        }
        pst.close();
        con.close();
        return lmv;
    }
    
    public boolean ExistenceEdges(int vertex1, int vertex2) throws ClassNotFoundException, SQLException{
        this.con = Connect.ConnectPostgress.conectabd();
        this.sql = "SELECT * FROM Edges WHERE Edges.V1ID =\'"+vertex1+"\'"+"AND Edges.V2ID =\'"+vertex2+"\'";
        
        this.pst = con.prepareStatement(sql);
        this.rs = pst.executeQuery();
        
        return rs.next();
    }
     
    public void DeleteEdges(int vertex1, int vertex2) throws ClassNotFoundException, SQLException{
        this.con = Connect.ConnectPostgress.conectabd();
        this.sql = "DELETE FROM Edges WHERE Edges.V1ID =\'"+vertex1+"\' AND Edges.V2ID =\'"+vertex2+"\'";
        
        this.pst = con.prepareStatement(sql);
        pst.execute();
        pst.close();
    }
     
    public void DeleteAdjacentEdges(int vertex1) throws ClassNotFoundException, SQLException{
        this.con = Connect.ConnectPostgress.conectabd();
        this.sql = "DELETE FROM Edges WHERE Edges.V1ID =\'"+vertex1+"\'";
        
        this.pst = con.prepareStatement(sql);
        pst.execute();
        pst.close();
    }
    
    public List<MyVertex> getAdjacenteVertex(int vertex) throws ClassNotFoundException, SQLException{
        this.con = Connect.ConnectPostgress.conectabd();
        this.sql = "SELECT * FROM Vertex WHERE Vertex.NAMEVERTEX =\'"+vertex+"\'";
        
        this.pst = con.prepareStatement(sql);
        this.rs = pst.executeQuery();
        
        List<MyVertex> lmv = new ArrayList<MyVertex>();
   
        while(rs.next()){    
            MyVertex mv = new MyVertex();
            mv.setName(rs.getInt("NAMEVERTEX"));
            mv.setColor(rs.getInt("COLOR"));
            mv.setDescription(rs.getString("DESCRIPTION"));
            mv.setWeight(rs.getFloat("WEIGHT"));
            lmv.add(mv);
        }
        pst.close();
        con.close();
        
        return lmv;
    }
    
    public void updateEdgesWeight(float weight, int vertex1, int vertex2) throws ClassNotFoundException, SQLException{
        this.con = Connect.ConnectPostgress.conectabd();
        this.sql = "UPDATE Edges SET WEIGHT =\'"+ weight+"\' WHERE Edges.V1ID =\'"+vertex1+"\' AND Edges.V2ID =\'"+vertex2+"\'";
        this.pst = con.prepareStatement(sql);
        pst.execute();
        pst.close();     
    }
    
    public void updateEdgesFlag(int flag, int vertex1, int vertex2) throws ClassNotFoundException, SQLException{
        this.con = Connect.ConnectPostgress.conectabd();
        this.sql = "UPDATE Edges SET FLAG1 =\'"+ flag+"\' WHERE Edges.V1ID =\'"+vertex1+"\' AND Edges.V2ID =\'"+vertex2+"\'";
        this.pst = con.prepareStatement(sql);
        pst.execute();
        pst.close();     
    }
    
    public void updateEdgesDescription(String description, int vertex1, int vertex2) throws ClassNotFoundException, SQLException{
        this.con = Connect.ConnectPostgress.conectabd();
        this.sql = "UPDATE Edges SET DESCRIPTION =\'"+description+"\' WHERE Edges.V1ID =\'"+vertex1+"\' AND Edges.V2ID =\'"+vertex2+"\'";
        this.pst = con.prepareStatement(sql);
        pst.execute();
        pst.close();     
    }
}