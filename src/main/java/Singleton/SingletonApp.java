package Singleton;

//public class SingletonApp {
//    public static void main(String[] args) throws InterruptedException {
//        final int SIZE = 1000;
//        Thread t[] = new Thread[SIZE];
//        for (int i = 0; i < SIZE; i++){
//            t[i] = new Thread(new R());
//            t[i].start();
//        }
//        for (int i = 0; i<SIZE; i++){
//            t[i].join();
//        }
//        System.out.println(Singleton.counter);
//    }
//}
//class R implements Runnable{
//    @Override
//    public void run() {
//        Singleton.getInstance();
//    }
//}
////Singletonul dat nu va lucra corect cu mai multe fire de executie.
//class Singleton{
//    public static int counter = 0;
//    private static  Singleton instance = new Singleton();
//    private Singleton(){
//        counter++;
//    }
//    public static Singleton getInstance(){
//        return instance;
//    }
//
//}



//Testare ca prima solutie nu lucreaza cu multitreading
//public class SingletonApp {
//    public static void main(String[] args) throws InterruptedException {
//        final int SIZE = 1000;
//        Thread t[] = new Thread[SIZE];
//        for (int i = 0; i < SIZE; i++){
//            t[i] = new Thread(new R());
//            t[i].start();
//        }
//        for (int i =0; i<SIZE; i++){
//            t[i].join();
//        }
//        System.out.println(Singleton.counter);
//    }
//}
//class R implements Runnable{
//    @Override
//    public void run() {
//        Singleton.getInstance();
//    }
//}
////Singletonul dat nu va lucra corect cu mai multe fire de executie.
//class Singleton{
//    public static int counter = 0;
//    private static  Singleton instance;
//    private Singleton(){
//        counter++;
//    }
//
//    public static Singleton getInstance(){
//        if (instance == null){
//            instance = new Singleton();
//        }
//        return instance;
//    }
//}


public class SingletonApp {
    public static void main(String[] args) {
        Singleton[] s = new Singleton[1000];
        for (int i = 0; i < 1000; i++){
            s[i] = Singleton.getInstance();
        }
        System.out.println(Singleton.counter);
    }
}
//Singletonul dat nu va lucra corect cu mai multe fire de executie.
class Singleton{
    public static int counter = 0;
    private static  Singleton instance;
    private Singleton(){
        counter++;
    }

    public static Singleton getInstance(){
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}
