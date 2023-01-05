package blocks;

public class Partition extends MemoryBlock {
    public static int id = 0;
    public Process allocatedProcess;

    public Partition(int size) {
        super("Partition" + id++, size);
    }
    public Partition(String name, int size) {
        super(name, size);
        id++;
    }
    public Partition(String name, int size, boolean copy) {
        super(name, size);
    }

    //copy constructor just like c++
    @Override
    public Partition copy() {
        return new Partition(name, size, true);
    }

    @Override
    public String toString() {
        if(allocatedProcess != null)
            return name + " (" + size + ") ===> " + allocatedProcess.name + '\n';
        else
            return name + " (" + size + ") ===> " + "External Fragment" + '\n';
    }
}
