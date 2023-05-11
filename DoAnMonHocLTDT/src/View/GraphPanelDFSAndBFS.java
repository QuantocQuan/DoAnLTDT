package View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphPanelDFSAndBFS extends JPanel {
    private List<Point> points;
    private int[][] adjacencyMatrix;
    private String[] vertexLabels;
    private int[][] pos;
    private int sizeVertex = 13;
    private List<Integer> listReDFSOrBFS;
   

    public GraphPanelDFSAndBFS(List<Integer> listReDFSOrBFS, int[][] adjacencyMatrix, String[] vertexLabels) {
    	this.listReDFSOrBFS = listReDFSOrBFS;
        this.adjacencyMatrix = adjacencyMatrix;
        this.vertexLabels = vertexLabels;
        points = new ArrayList<>();
        setPos();
        drawGraph();
        
    }
    public void setPos() {
    	int[][] pos = {{150,83}, 
    				{124,129},
    				{70,145},
    				{26,111},
    				{23,52},
    				{62,23},
    				{124,32},
    				{183,33},
    				{213,79},
    				{197,137}
    				};
    	this.pos = pos;
    }
    public void addPoint(Point p) {
    	this.points.add(p);
    }
    public void drawGraph() {
    	for (int i = 0; i < adjacencyMatrix.length; i++) {
			this.addPoint(new Point(0, 0, vertexLabels[i]));
		}
    	int index=0;
    	for (Point p : points) {
			p.setX(pos[index][0]);
			p.setY(pos[index][1]);
			p.setLable(vertexLabels[index]);
			index++;
    	}
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set font and color for drawing labels
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.setColor(Color.BLACK);
        for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix.length; j++) {
				if(adjacencyMatrix[i][j]!=0) {
					g.drawLine(points.get(i).getX()+7, points.get(i).getY()+7, points.get(j).getX()+7, points.get(j).getY()+7);
					g.drawString(String.valueOf(adjacencyMatrix[i][j]),((points.get(i).getX()+7) +(points.get(j).getX()+7))/2 , ((points.get(i).getY()+7) +(points.get(j).getY()+7))/2);
					drawArrow(g,points.get(i).getX()+7, points.get(i).getY()+7, points.get(j).getX()+7, points.get(j).getY()+7, 15, 20);
				}
			}
		}
        for (int i = 0; i < listReDFSOrBFS.size()-1; i++) {
			int temp = i;
			if(adjacencyMatrix[listReDFSOrBFS.get(temp)][listReDFSOrBFS.get(i+1)] != 0) {
				g.setColor(Color.RED);
				g.drawLine(points.get(listReDFSOrBFS.get(temp)).getX()+7, points.get(listReDFSOrBFS.get(temp)).getY()+7, points.get(listReDFSOrBFS.get(i+1)).getX()+7, points.get(listReDFSOrBFS.get(i+1)).getY()+7);
			}else {
				while(true) {
					temp--;
					if(adjacencyMatrix[listReDFSOrBFS.get(temp)][listReDFSOrBFS.get(i+1)] != 0) {
						g.drawLine(points.get(listReDFSOrBFS.get(temp)).getX()+7, points.get(listReDFSOrBFS.get(temp)).getY()+7, points.get(listReDFSOrBFS.get(i+1)).getX()+7, points.get(listReDFSOrBFS.get(i+1)).getY()+7);
						break;
					}
				}
			}
		}
        for (Point p:points) {
        	g.setColor(Color.YELLOW);
			g.fillOval(p.getX(), p.getY(), sizeVertex,sizeVertex);
			g.setColor(Color.black);
			g.drawString(p.getLable(),p.getX()+3, p.getY()+11);
        }
       
        
        

    }
    private void drawArrow(Graphics g, int x1, int y1, int x2, int y2, int arrowSize, int arrowAngle) {
        // Compute arrow angle
        double angle = Math.atan2(y2 - y1, x2 - x1);
        double arrowAngleRadians = Math.toRadians(arrowAngle);

        // Compute coordinates of arrow points
        int arrowX1 = (int) (x2 - arrowSize * Math.cos(angle - arrowAngleRadians));
        int arrowY1 = (int) (y2 - arrowSize * Math.sin(angle - arrowAngleRadians));
        int arrowX2 = (int) (x2 - arrowSize * Math.cos(angle + arrowAngleRadians));
        int arrowY2 = (int) (y2 - arrowSize * Math.sin(angle + arrowAngleRadians));

        // Draw arrow
        g.drawLine(x2, y2, arrowX1, arrowY1);
        g.drawLine(x2, y2, arrowX2, arrowY2);
    }
//    public static void main(String[] args) {
//    	JFrame f=	new JFrame();
//    	f.setSize(500,400);
//    	int[][] matrix = {{1,0,1,1},
//    					{0,0,7,0},
//    			{0,0,1,1},
//    			{1,1,1,0}
//    	};
//    	String[] labels= {"0","1","2","3"};
//    	GraphPanelDFSAndBFS p = new GraphPanelDFSAndBFS(matrix, labels);
//    	f.setContentPane(p);
//    	f.setVisible(true);
//    }
}	
