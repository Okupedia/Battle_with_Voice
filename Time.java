public class Time {
    static double time() {


	long start = 0;
	long end = 0;

	start = start();
        // 時間計測
        int result = 0;
        for (int i = 0; i < 1000000; i++) {
            result += 1;
        }

	end = end();

  return end - start;

    }

    static long start(){

        // 処理前の時間を計測
        long start = System.currentTimeMillis();
	return start;
    }

    static long end(){
        // 処理後の時間
        long end = System.currentTimeMillis();
	return end;
    }
}
