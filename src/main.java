public class main {
    public static void main(String[] args){
        Cell cellA = new Cell(0,0);
        Cell cellB = new Cell(1,0);

        System.out.println("Cell A south side: " + cellA.hasSouthSide());
        System.out.println("Cell B north side: " + cellB.hasNorthSide());

        cellA.removeSouthSide();
        cellB.removeNorthSide();

        System.out.println("After removing sides: ");
        System.out.println("Cell A south side: " + cellA.hasSouthSide());
        System.out.println("Cell B south side: " + cellB.hasNorthSide());
    }
}
