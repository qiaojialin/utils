package btree;


public class Demo {

  public static void main(String args[]){
    // split when key # in a node hits {order}

    multiple();
//    one();
  }


  public static void multiple() {
    for(long total = 50000; total <= 1000000; total += 50000) {
      BPlusTree<Long, String> myTree = new BPlusTree<>(10);

      for (long i = 0; i < total; i++) {
        myTree.set(i, String.valueOf(i));
      }

      System.out.println((Constant.totalRead + Constant.totalWrite) / 2000);
      Constant.totalRead = 0;
      Constant.totalWrite = 0;
    }
  }

  public static void one() {
    BPlusTree<Long, String> myTree = new BPlusTree<>(10);

    for (long i = 0; i < 50000; i++) {
      myTree.set(i, String.valueOf(i));
    }

    System.out.println("total read write: " + (Constant.totalRead + Constant.totalWrite));
  }


}
