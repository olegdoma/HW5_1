public class Main {
    public static void main(String[] args) {
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - a + "мс");

        long b = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        Thread t1 = new MyThread("t1"){
            @Override
            public void run(){
                for (int i = 0; i < h; i++) {
                    a1[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        };
        Thread t2 = new MyThread("t2"){
            @Override
            public void run(){
                for (int i = h; i < size; i++) {
                    a2[i] = (float)(arr[i] * Math.sin(0.2f + (h + i) / 5) * Math.cos(0.2f + (h + i) / 5) * Math.cos(0.4f + (h + i) / 2));
                }
            }
        };
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - b + "мс");


    }
}


