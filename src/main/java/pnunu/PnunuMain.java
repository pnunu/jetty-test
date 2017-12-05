package pnunu;

public class PnunuMain {

	public static void main(String[] args) {
        PnunuExecutor executor = new PnunuExecutor();
        try {
            executor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

}
