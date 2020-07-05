package lifegame1;

import lifegame1.Cell;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

public class CellTest {

private static Cell cell=new Cell(20,20);

    @org.junit.Before
    public void setUp() throws Exception {
    }



    @Test
    public void getNowGeneration() {
       // cell.randomCell();
    	//cell.update();
        Assert.assertEquals(1, cell.getNowGeneration());
    }

    @org.junit.Test
    public void randomCell()
    {
        cell.randomCell();
        int[][] testgrid0=cell.getcells();
        int flag0=0;
        for (int i=0;i<20;i++)
            for (int j=0;j<20;j++)
            {
                if (testgrid0[i][j]==1)
                    flag0=1;
            }
        Assert.assertEquals(1,flag0);
    }

    @org.junit.Test
    public void deleteAllCell() {
        randomCell();
        cell.deleteAllCell();
        int[][] testgrid1=cell.getcells();
        int flag1=0;
        for (int i=0;i<20;i++)
            for (int j=0;j<20;j++)
            {
                if (testgrid1[i][j]==1)
                    flag1=1;
            }
        Assert.assertEquals(0,flag1);
    }

    @org.junit.Test
    public void update() {
        int[][] newGrid0=new int[22][22];
        for (int i=1;i<4;i++)
            for (int j=1;j<4;j++)
            {
                newGrid0[i][j]=1;
            }

        cell.setGrid(newGrid0);
            int num=0;
            cell.update();
       // Assert.assertEquals(1,newGrid0[1][2]);
        //int[][] newGrid1=cell.getGrid();

       for (int i=0;i<22;i++)
            for (int j=0;j<22;j++)
            {
                if (newGrid0[i][j]==1)
                    num++;
            }
            Assert.assertEquals(5,num);
    }

    @Test
    public void getNeighbors()
    {
        int[][] newGrid1=new int[3][3];
        newGrid1[0][0]=1;
        newGrid1[1][1]=1;
        newGrid1[2][2]=1;
        newGrid1[2][1]=1;
        cell.setGrid(newGrid1);
        assertEquals(3, cell.getNeighbors(1, 1));
        /*
        int newGird[][]=cell.getGrid();
        newGird[1][1]=1;
        newGird[2][2]=1;
        Assert.assertEquals(1,cell.getNeighborCount(1,1) );
        */
    }
}
