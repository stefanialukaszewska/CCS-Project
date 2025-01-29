import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reports {
    private List<Integer> numbers;
    private List<Integer> numbers10;
    private int operationCount;
    private int operationCount10;
    private Map<String, Integer> operationsMap;
    private Map<String, Integer> operationsMap10;
    private int newClientsCount;
    private int errorOperationCount;
    private int errorOperationCount10;

    private int newClientsCount10;


    public Reports() {
        this.numbers = new ArrayList<>();
        this.numbers10 = new ArrayList<>();
        this.newClientsCount = 0;
        this.operationsMap = new HashMap<>();
        this.operationsMap.put("ADD", 0);
        this.operationsMap.put("SUB", 0);
        this.operationsMap.put("MUL", 0);
        this.operationsMap.put("DIV", 0);
        this.operationsMap10 = new HashMap<>();
        this.operationsMap10.put("ADD", 0);
        this.operationsMap10.put("SUB", 0);
        this.operationsMap10.put("MUL", 0);
        this.operationsMap10.put("DIV", 0);
    }

    public void getStatisticsNotstatic() throws InterruptedException {
        Log log = new Log("SERVER REPORTS");
        while (true) {

            log.log(
                    "\n          SERVER STATISTICS             \n" +
                            "========================================\n" +
                            "> General statistics since server start:\n" +
                            "   - New connected clients: " + newClientsCount + "\n" +
                            "   - Total calculated operations: " + operationCount + "\n" +
                            "   - Distinct operations: " + operationsMap + "\n" +
                            "   - Invalid operations: " + errorOperationCount + "\n" +
                            "   - Total sum of operations: " + getSum() + "\n\n" +
                            "> Statistics for the last 10 seconds:\n" +
                            "   - New connected clients: " + newClientsCount10 + "\n" +
                            "   - Calculated operations: " + operationCount10 + "\n" +
                            "   - Distinct operations: " + operationsMap10 + "\n" +
                            "   - Invalid operations: " + errorOperationCount10 + "\n" +
                            "   - Total sum: " + getSum10() + "\n" +
                            "========================================"
            );


            this.numbers10.clear();
            this.operationCount10 = 0;
            this.operationsMap10.replaceAll((o, v) -> 0);
            this.errorOperationCount10 = 0;
            this.newClientsCount10 = 0;

            Thread.sleep(10000);
        }
    }

    public void addNumbers(int a) {
        this.numbers.add(a);
        this.numbers10.add(a);
    }

    public void addOperationCount() {
        this.operationCount++;
        this.operationCount10++;
    }

    public int getSum() {
        int sum = 0;
        for (int a : this.numbers) {
            sum += a;
        }
        return sum;
    }

    public int getSum10() {
        int sum = 0;
        for (int a : this.numbers10) {
            sum += a;
        }
        return sum;
    }

    public void addMapOperation(String op) {
        int currenValue = this.operationsMap.get(op);
        int currenValue10 = this.operationsMap10.get(op);
        this.operationsMap.put(op, currenValue + 1);
        this.operationsMap10.put(op, currenValue10 + 1);
    }

    public void addErrorOperationCount() {
        this.errorOperationCount++;
        this.errorOperationCount10++;
    }

    public void addNewClientsCount() {
        this.newClientsCount++;
        this.newClientsCount10++;
    }


}
