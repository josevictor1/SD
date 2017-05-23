package Connect;

import Model.MyEdges;
import Model.MyVertex;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class VertexOP{
    String sql;
    Connection con = null;
    PreparedStatement  pst = null;
    ResultSet rs = null;
    
    /**
     *
     * @param v
     * @throws ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public void InsertVertex(MyVertex v) throws ClassNotFoundException, SQLException{
        this.con = Connect.ConnectPostgress.conectabd();
        this.sql = "INSERT INTO Vertex"+"(NAMEVERTEX,COLOR,DESCRIPTION,WEIGHT)"+"VALUES(?,?,?,?)";
        //this.sql = "INSERT INTO Vertex (NAME,COLOR,DESCRIPTION,WEIGHT) VALUES(1,2,'teste',3)";
        this.pst = con.prepareStatement(sql);
        pst.setInt(1, v.getName());
        pst.setInt(2, v.getColor());
        pst.setString(3, v.getDescription());
        pst.setFloat(4, v.getWeight());
        pst.execute();
        pst.close();     
    }
    
    public MyVertex getVertex(int name) throws ClassNotFoundException, SQLException{
        this.con = Connect.ConnectPostgress.conectabd();
        this.sql = "SELECT * FROM Vertex WHERE Vertex.NAMEVERTEX =\'"+name+"\'";
        
        this.pst = con.prepareStatement(sql);
        this.rs = pst.executeQuery();
        
        while(rs.next()){
            MyVertex v = new MyVertex();
            v.setName(name);
            v.setColor(rs.getInt("COLOR"));
            v.setDescription(rs.getString("DESCRIPTION"));
            v.setWeight(rs.getFloat("WEIGHT"));
            pst.close();
            con.close();
            return v;
        }
        return null;
    }
    
    public List<MyVertex> getAllVertex() throws ClassNotFoundException, SQLException{
        this.con = Connect.ConnectPostgress.conectabd();
        this.sql = "SELECT * FROM Vertex";
        
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
    
    //Arestas adjacentes ao vertice
    public List<MyEdges> getEdgesVertex(int name) throws ClassNotFoundException, SQLException{
        this.con = Connect.ConnectPostgress.conectabd();
        this.sql = "SELECT * FROM Edges WHERE Edges.V1ID =\'"+name+"\' OR Edges.V2ID =\'"+name+"\'";
        
        this.pst = con.prepareStatement(sql);
        this.rs = pst.executeQuery();
        
        List<MyEdges> lme = new ArrayList<MyEdges>();
        while(rs.next()){
            MyEdges me = new MyEdges();
            me.setV1id(rs.getInt("V1ID"));
            me.setV2id(rs.getInt("V2ID"));
            me.setWeight(rs.getFloat("WEIGHT"));
            me.setFlag(rs.getInt("FLAG1"));
            me.setDescription(rs.getString("DESCRIPTION"));
            lme.add(me);
        }
        pst.close();
        con.close();
        
        return lme;
    }
       
    public boolean ExistenceVertex(int name) throws ClassNotFoundException, SQLException{
        this.con = Connect.ConnectPostgress.conectabd();
        this.sql = "SELECT * FROM Vertex WHERE Vertex.NAMEVERTEX =\'"+name+"\'";
        
        this.pst = con.prepareStatement(sql);
        this.rs = pst.executeQuery();
 
        return rs.next();
    }
    
    public void DeleteVertex(int name) throws ClassNotFoundException, SQLException{
        this.con = Connect.ConnectPostgress.conectabd();
        this.sql = "DELETE FROM Vertex WHERE Vertex.NAMEVERTEX =\'"+name+"\'";
        
        this.pst = con.prepareStatement(sql);
        pst.execute();
        pst.close();
    }
    
    public void updateVetexColor(int value, int name) throws ClassNotFoundException, SQLException{
        this.con = Connect.ConnectPostgress.conectabd();
        this.sql = "UPDATE Vertex SET COLOR =\'"+ value+"\' WHERE Vertex.NAMEVERTEX =\'"+name+"\'";
        this.pst = con.prepareStatement(sql);
        pst.execute();
        pst.close();     
    }
    
    public void updateVetexWeight(float weight, int name) throws ClassNotFoundException, SQLException{
        this.con = Connect.ConnectPostgress.conectabd();
        this.sql = "UPDATE Vertex SET WEIGHT =\'"+ weight+"\' WHERE Vertex.NAMEVERTEX =\'"+name+"\'";
        this.pst = con.prepareStatement(sql);
        pst.execute();
        pst.close();     
    }

    public void updateVetexDescription(String description, int name) throws ClassNotFoundException, SQLException{
        this.con = Connect.ConnectPostgress.conectabd();
        this.sql = "UPDATE Vertex SET DESCRIPTION =\'"+description+"\' WHERE Vertex.NAMEVERTEX =\'"+name+"\'";
        this.pst = con.prepareStatement(sql);
        pst.execute();
        pst.close();     
    }
    
}