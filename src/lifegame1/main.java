package lifegame1;

public class main {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
        Cell cell;
        cell=new Cell(3,3);
        cell.randomCell();
        cell.printcell();
        System.out.println();
        cell.update();
        cell.printcell();
        System.out.println();
        cell.update();
        cell.printcell();
	}

}
