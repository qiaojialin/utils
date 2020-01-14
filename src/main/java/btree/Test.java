package btree;

public class Test {
  public static void main(String[] args) {
    BPlusTree<Integer, String> myTree = new BPlusTree<Integer, String>(2);

    int[] number = {10, 17, 3, 29, 4, 5, 18, 6, 22, 1, 33, 35};
//    int[] number = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

    int max = 1000000;
    long start = System.currentTimeMillis();
    for (int i = 0; i < number.length; i++) {
      myTree.set(number[i], String.valueOf(number[i]));
      System.out.println(myTree.toString());
    }
    System.out.println("time cost with BPlusTree: " + (System.currentTimeMillis() - start));
    System.out.println("Data has been inserted into tree");

    System.out.println("height: " + myTree.height());

    System.out.println("Success");
  }
}
