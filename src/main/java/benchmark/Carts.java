package benchmark;

import org.openjdk.jmh.annotations.*;

@Measurement(iterations = 5)
@Warmup(iterations = 1)
@Fork(value = 10)
@BenchmarkMode({Mode.AverageTime})
public class Carts {

    private static String cartsControlPath = "C:\\Users\\Emil\\Desktop\\StartupBenchmarks\\control\\carts.jar";
    private static String cartsDI_Path = "C:\\Users\\Emil\\Desktop\\StartupBenchmarks\\di\\carts.jar";
    private static String otelAgent = "-javaagent:C:\\Users\\Emil\\Desktop\\StartupBenchmarks\\opentelemetry-javaagent-all.jar";
    private static String specialAgent = "-javaagent:C:\\Users\\Emil\\Desktop\\StartupBenchmarks\\opentracing-specialagent-1.7.5-SNAPSHOT.jar";

    @Benchmark
    public void controlCarts(NormalState state) throws Exception {
        state.run();
    }

    @Benchmark
    public void OTelCarts(OTelState state) throws Exception {
        state.run();
    }

    @Benchmark
    public void DI_Carts(DI_State state) throws Exception {
        state.run();
    }

    @Benchmark
    public void specialCarts(SpecialState state) throws Exception {
        state.run();
    }

    @State(Scope.Benchmark)
    public static class NormalState extends ProcessManager {
        public NormalState() {
            super("-jar", cartsControlPath, "--server.port=0");
        }
        @TearDown(Level.Iteration)
        public void stop() throws Exception {
            super.after();
        }
    }

    @State(Scope.Benchmark)
    public static class OTelState extends ProcessManager {
        public OTelState() {
            super(otelAgent, "-jar", cartsControlPath, "--server.port=0");
        }
        @TearDown(Level.Iteration)
        public void stop() throws Exception {
            super.after();
        }
    }

    @State(Scope.Benchmark)
    public static class DI_State extends ProcessManager {
        public DI_State() {
            super("-jar", cartsDI_Path, "--server.port=0");
        }
        @TearDown(Level.Iteration)
        public void stop() throws Exception {
            super.after();
        }
    }

    @State(Scope.Benchmark)
    public static class SpecialState extends ProcessManager {
        public SpecialState() {
            super(specialAgent, "-jar", cartsControlPath, "--server.port=0");
        }
        @TearDown(Level.Iteration)
        public void stop() throws Exception {
            super.after();
        }
    }

}
