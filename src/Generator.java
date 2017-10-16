public interface Generator {
    public void attach(Observer<Generator> o);

    public int getValue();
}
