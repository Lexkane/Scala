public class Work {

  public static void doWork(int a, int b, IMath m){
    double res= m.add(a,b);
    m.sub((int)res,b);
  }

}
