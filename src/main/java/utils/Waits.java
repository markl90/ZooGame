package utils;

public class Waits {

    public static void wait(int seconds){
        try {
            Thread.sleep(seconds*1000);
            System.out.println(".....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void stopWatch(long startTime){
        for(int i =0; i<8;i++) {
            long end = System.currentTimeMillis();
            float sec = (end - startTime) / 1000F;
            System.out.println(sec + " seconds");
            wait(1);
            System.out.println("keep the Zoo open as long as you can!");
        }
    }

    public static float openingTime(long start){
        long end = System.currentTimeMillis();
        float sec = (end - start) / 1000F;
        System.out.println("Total opeing time is...");
        System.out.println(sec + " seconds!");
        System.out.println("It's a personal record!");
        return sec;
    }
}
