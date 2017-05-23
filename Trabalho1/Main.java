/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho1;

import Connect.EdgesOP;
import Connect.VertexOP;
import Model.MyEdges;
import Model.MyVertex;
import java.sql.SQLException;

/**
 *
 * @author jose
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        MyVertex v = new MyVertex();
        MyVertex v1;
        VertexOP vop= new VertexOP();
        EdgesOP eop = new EdgesOP();
        int i;
        /*
        for(i = 0; i < 2; i++){
            v.setName(i);
            v.setColor(i);
            v.setDescription("teste");
            v.setWeight(i);
            vop.InsertVertex(v);
        }*/
        
       /*
        System.out.println(v.getName());
        //
        System.out.println(vop.ExistenceVertex(0));
        v1 = vop.getVertex(0);
        System.out.println(v1.getDescription());
        vop.DeleteVertex(0);
        System.out.println(vop.ExistenceVertex(0));*/
       
       //Edges e = new Edges();
       /*e.setV1id(0);
       e.setV2id(1);
       e.setFlag(0);
       e.setWeight(0);
       eop.InsertEdge(e);*/
       
       //System.out.println(eop.ExistenceEdges(0, 1));
       //e = eop.getEdges(0, 1);
       //System.out.println(e.getV2id());
       //eo//p.DeleteEdges(0, 1);
       //System.out.println(eop.ExistenceEdges(0, 1));

    }    
}
