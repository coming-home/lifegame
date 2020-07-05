package lifegame1;
public class Cell {
    private int maxLength;
    private int maxWidth;
    private int nowGeneration;
    private int[][] cells;
    public Cell(int maxLength, int maxWidth)
    {
        this.maxLength = maxLength;
        this.maxWidth = maxWidth;
        nowGeneration = 0;
        cells = new int[maxLength][maxWidth];
        for (int i = 0; i < maxLength; i++)
        {
            for (int j = 0; j < maxWidth; j++)
                cells[i][j] = 0;
        }
    }

    public void setGrid(int[][] cells)
    {
        this.cells = cells;
    }

    public int[][] getcells()
    {
        return cells;
    }

    /* public int getneighborhood(int x,int y)
     {
         return neighborhood[x][y];
     }*/
    public void setNowGeneration(int nowGeneration)
    {
        this.nowGeneration = nowGeneration;
    }

    public int getNowGeneration()
    {
        return nowGeneration;
    }

    public void randomCell()
    {
        for (int i = 0; i < maxLength; i++)
            for (int j = 0; j < maxWidth; j++)
                cells[i][j] = Math.random()>0.5?1:0;
    }

    public void deleteAllCell()
    {
        for (int i = 0; i < maxLength; i++)
            for (int j = 0; j < maxWidth; j++)
                cells[i][j] = 0;
    }

    public void update()
    {
        int[][] newcells = new int[maxLength][maxWidth];
        int s;
        for (int i = 0; i < maxLength; i++)
        {
            for (int j = 0; j < maxWidth; j++)
            {
                s=getNeighbors(i,j);
                switch (s)
                {
                    case 2:
                        newcells[i][j] = cells[i][j];
                        break;
                    case 3:
                        newcells[i][j] = 1;
                        break;
                    default:
                        newcells[i][j] = 0;
                }
                cells[i][j] = newcells[i][j];
            }
        }
      /*  for (int i = 0; i <maxLength; i++)
            for (int j = 0; j < maxWidth; j++)
                cells[i][j] = newcells[i][j];*/
        nowGeneration++;
    }

    public int getNeighbors(int i,int j)
    {
        int count=0;
        if(i-1>=0 && j-1>=0 && cells[i-1][j-1]==1) count++;//左上
        if(i-1>=0 && cells[i-1][j]==1) count++;			//左
        if(i-1>=0 && j+1<maxWidth && cells[i-1][j+1]==1) count++;//左下
        if(j-1>=0 && cells[i][j-1]==1) count++;			//上
        if(j+1<maxLength&& cells[i][j+1]==1) count++;			//下
        if(i+1<maxLength && cells[i+1][j]==1) count++;         //右
        if(i+1<maxLength && j+1<maxWidth && cells[i+1][j+1]==1) count++;//右下
        if(i+1<maxLength && j-1>=0 && cells[i+1][j-1]==1) count++; //右上
        return count;
    }
    public void printcell() {
        for (int i = 0; i < maxLength; i++)
        {
            System.out.println();
            for (int j = 0; j < maxWidth; j++)
            {
                System.out.print(cells[i][j]+" ");
            }
        }
    }
}
