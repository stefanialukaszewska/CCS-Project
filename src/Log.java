public class Log {
    public String PREFIX;

    public Log(String s){
        this.PREFIX = s;
    }
    public void log(String message) {
        System.out.println("["+PREFIX+"] " + message);
        System.out.flush();
    }
}
