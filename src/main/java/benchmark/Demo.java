package benchmark;

import org.openjdk.jmh.annotations.*;

@Measurement(iterations = 5)
@Warmup(iterations = 1)
@Fork(value = 10)
@BenchmarkMode({Mode.AverageTime})
public class Demo {

    private static String demoControlPath = "C:\\Users\\Emil\\Desktop\\StartupBenchmarks\\control\\demo.jar";
    private static String demoDI_Path = "C:\\Users\\Emil\\Desktop\\StartupBenchmarks\\di\\demo.jar";
    private static String otelAgent = "-javaagent:C:\\Users\\Emil\\Desktop\\StartupBenchmarks\\opentelemetry-javaagent-all.jar";
    private static String specialAgent = "-javaagent:C:\\Users\\Emil\\Desktop\\StartupBenchmarks\\opentracing-specialagent-1.7.5-SNAPSHOT.jar";

    @Benchmark
    public void controldemo(NormalState state) throws Exception {
        state.run();
    }

    @Benchmark
    public void OTeldemo(OTelState state) throws Exception {
        state.run();
    }

    @Benchmark
    public void DI_demo(DI_State state) throws Exception {
        state.run();
    }

    @Benchmark
    public void specialdemo(SpecialState state) throws Exception {
        state.run();
    }

    @State(Scope.Benchmark)
    public static class NormalState extends ProcessManager {
        public NormalState() {
            super("-jar", demoControlPath, "--server.port=0");
        }
        @TearDown(Level.Iteration)
        public void stop() throws Exception {
            super.after();
        }
    }

    @State(Scope.Benchmark)
    public static class OTelState extends ProcessManager {
        public OTelState() {
            super(otelAgent, "-jar", demoControlPath, "--server.port=0");
        }
        @TearDown(Level.Iteration)
        public void stop() throws Exception {
            super.after();
        }
    }

    @State(Scope.Benchmark)
    public static class DI_State extends ProcessManager {
        public DI_State() {
            super("-jar", demoDI_Path, "--server.port=0");
        }
        @TearDown(Level.Iteration)
        public void stop() throws Exception {
            super.after();
        }
    }

    @State(Scope.Benchmark)
    public static class SpecialState extends ProcessManager {
        public SpecialState() {
            super(specialAgent, "-jar", demoControlPath, "--server.port=0");
        }
        @TearDown(Level.Iteration)
        public void stop() throws Exception {
            super.after();
        }
    }

}
