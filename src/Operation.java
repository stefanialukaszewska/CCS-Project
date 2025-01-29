import java.io.PrintWriter;

public class Operation {
    public static void getResult(String message, PrintWriter writer, Reports reports, Log log) throws Exception {


        String[] args = message.split(" ");
        if (args.length != 3) {
            log.log("ERROR: Incorrect number of arguments");
        } else {
            String op = args[0];
            try {
                int arg1 = Integer.valueOf(args[1]);
                int arg2 = Integer.valueOf(args[2]);
            } catch (Exception e) {
                log.log("ERROR: Invalid arguments");
                writer.println("ERROR: Invalid arguments");
                reports.addErrorOperationCount();
            }

            switch(args[0]){
                case "ADD":{
                    int sum = Integer.valueOf(args[1])+Integer.valueOf(args[2]);
                    log.log("Answer for message: "+message + " -> " + sum);
                    writer.println(sum);
                    reportsMethod(sum,reports,args[0]);

                } break;
                case "SUB":{
                    int minus = Integer.valueOf(args[1])-Integer.valueOf(args[2]);
                    log.log("Answer for message: "+message + " -> " + minus);
                    writer.println(minus);
                    reportsMethod(minus,reports,args[0]);
                } break;
                case "MUL":{
                    int mul = Integer.valueOf(args[1])*Integer.valueOf(args[2]);
                    log.log("Answer for message: "+message + " -> " + mul);
                    writer.println(mul);
                    reportsMethod(mul,reports,args[0]);

                } break;
                case "DIV":{
                    try {
                        int div = Integer.valueOf(args[1]) / Integer.valueOf(args[2]);
                        log.log("Answer for message: "+message + " -> " + div);
                        writer.println(div);
                        reportsMethod(div,reports,args[0]);
                    } catch (ArithmeticException e){
                        log.log("ERROR: Cannot divide by 0");
                        writer.println("ERROR: Cannot divide by 0");
                        reports.addErrorOperationCount();

                    }
                } break;
                default:{
                    log.log("ERROR: Wrong operation");
                    writer.println("ERROR: Wrong operation");
                    reports.addErrorOperationCount();

                }
            }
        }
    }

    public static void reportsMethod(int a, Reports reports, String op){
        reports.addNumbers(a);
        reports.addOperationCount();
        reports.addMapOperation(op);
    }
}
