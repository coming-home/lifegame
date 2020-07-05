package lifegame1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {
    private static GUI frame;
    private Cell cell;
    private JButton[][] nGrid;
    private JButton jbNowGeneration, randomInit, clearGeneration; 
    private JButton clearCell, nextGeneration, start, stop, exit; 
    private boolean isRunning;
    private Thread thread;
    private boolean isDead;

    public static void main(String arg[]) 
    {
        frame = new GUI("������Ϸ");
    }
    public void initGUI() 
    {
         
        cell = new Cell(20, 20);

        JPanel backPanel, centerPanel, bottomPanel;
        JLabel jNowGeneration;
        backPanel = new JPanel(new BorderLayout());
        centerPanel = new JPanel(new GridLayout(20, 20));
        bottomPanel = new JPanel();
        this.setContentPane(backPanel);
        backPanel.add(centerPanel, "Center");
        backPanel.add(bottomPanel, "North");

        nGrid = new JButton[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) 
            {
                nGrid[i][j] = new JButton(""); 
                nGrid[i][j].setBackground(Color.BLACK); //��ʼʱ����ϸ����Ϊ��
                centerPanel.add(nGrid[i][j]);
            }
        }

        
        //jNowGeneration = new JLabel(" ��ǰ������");
        jbNowGeneration = new JButton(""+cell.getNowGeneration());
        jbNowGeneration.setEnabled(false);
        clearGeneration = new JButton("��������");
        randomInit = new JButton("�����ʼ��");
        clearCell = new JButton("ϸ������");
        start = new JButton("��ʼ����");
        nextGeneration = new JButton("��һ��");
        stop = new JButton("��ͣ");
        exit = new JButton("�˳�");

        bottomPanel.add(jbNowGeneration);
        bottomPanel.add(clearGeneration);
        bottomPanel.add(randomInit);
        bottomPanel.add(clearCell);
        bottomPanel.add(start);
        bottomPanel.add(nextGeneration);
        bottomPanel.add(stop);
        bottomPanel.add(exit);


        // ���ô���
        this.setSize(1000, 900);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() 
        {
            public void windowClosed(WindowEvent e) 
            {
                System.exit(0);
            }
        });
        //ok.addActionListener(this);
        clearGeneration.addActionListener(this);
        randomInit.addActionListener(this);
        clearCell.addActionListener(this);
        nextGeneration.addActionListener(this);
        start.addActionListener(this);
        stop.addActionListener(this);
        exit.addActionListener(this);
        for (int i = 0; i < 20; i++) 
        {
            for (int j = 0; j < 20; j++)
            {
                nGrid[i][j].addActionListener(this);
            }
        }
    }

    public GUI(String name) 
    {
        super(name);
        initGUI();
    }

    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == clearGeneration)//��������
        { 
            cell.setNowGeneration(0);
            jbNowGeneration.setText(""+cell.getNowGeneration());//ˢ�µ�ǰ����
            isRunning = false;
            thread = null;
        } 
        else if(e.getSource() == randomInit)//�����ʼ��
        { 
            cell.randomCell();
            showCell();
            isRunning = false;
            thread = null;
        } 
        else if(e.getSource() == clearCell)//ϸ������
        { 
            cell.deleteAllCell();
            showCell();
            isRunning = false;
            thread = null;
        } 
        else if (e.getSource() == start)//��ʼ 
        { 
            isRunning = true;
            thread = new Thread(new Runnable() {
                public void run() 
                {
                    while (isRunning) 
                    {
                        makeNextGeneration();
                        try 
                        {
                            Thread.sleep(500);
                        } 
                        catch (InterruptedException e1) 
                        {
                            e1.printStackTrace();
                        }
                        isDead = true;
                        for(int row = 0; row <20; row++) 
                        {
                            for (int col = 0; col <20; col++)
                            {
                                if (cell.getcells()[row][col] != 0) 
                                {
                                    isDead = false;
                                    break;
                                }
                            }
                            if (!isDead) 
                            {
                                break;
                            }
                        }
                        if (isDead)
                        {
                            JOptionPane.showMessageDialog(null, "����ϸ��������");
                            isRunning = false;
                            thread = null;
                        }
                    }
                }
            });
            thread.start();
        } 
        else if (e.getSource() == nextGeneration)//��һ��
        	{ 
        		makeNextGeneration();
            	isRunning = false;
            	thread = null;
        	} 
        else if (e.getSource() == stop) //��ͣ
        	{ 
            	isRunning = false;
            	thread = null;
        	} 
        else if (e.getSource() == exit) //�˳�
        	{ 
            	frame.dispose();
            	System.exit(0);
        	} 
    }

    private void makeNextGeneration() {
        cell.update();
        showCell();
        jbNowGeneration.setText(""+cell.getNowGeneration());//ˢ�µ�ǰ����
    }

    public void showCell(){
        int[][] grid = cell.getcells();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (grid[i][j] == 1) {
                    nGrid[i][j].setBackground(Color.YELLOW);
                } else {
                    nGrid[i][j].setBackground(Color.BLACK); 
                }
            }
        }
    }

}

