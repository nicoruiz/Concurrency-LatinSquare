public class PoisonPill  implements Runnable {
    public PoisonPill(){}

    public void run() throws PoisonPillException {
        throw new PoisonPillException();
    }
}
