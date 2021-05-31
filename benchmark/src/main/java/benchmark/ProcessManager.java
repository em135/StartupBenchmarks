package benchmark;

import org.openjdk.jmh.util.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProcessManager {

    private Process process;
    private List<String> arguments;
    private File home;

    public ProcessManager(String... args) {
        this.arguments = new ArrayList<>();
        this.arguments.add(System.getProperty("java.home") + "/bin/java");
        this.arguments.add("-Djava.security.egd=file:/dev/./urandom");
        this.arguments.add("-XX:TieredStopAtLevel=1");
        this.arguments.addAll(Arrays.asList(args));
        this.home = new File("target");
    }

    public void after() throws Exception {
        if (process != null && process.isAlive()) {
            process.destroyForcibly().waitFor();
        }
    }

    public void run() throws Exception {
        ProcessBuilder builder = new ProcessBuilder(arguments);
        builder.directory(home);
        builder.redirectErrorStream(true);
        process = builder.start();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null){
            if (line.contains("Started")){
                return;
            }
        }
    }
}
