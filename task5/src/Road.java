public class Road extends Stage {
    boolean isLastStage;

    public Road(int length, boolean isLastStage) {
        this.length = length;
        this.isLastStage = isLastStage;
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            synchronized (Road.class) {
                System.out.println(c.getName() + " закончил этап: " + description);
                if (isLastStage) {
                    if (MainClass.winnerPrize == 1) {
                        MainClass.winnerPrize--;
                        System.out.println(c.getName() + " - WIN");
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
