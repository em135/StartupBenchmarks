package benchmark;

import org.openjdk.jmh.annotations.*;

@Measurement(iterations = 5)
@Warmup(iterations = 1)
@Fork(value = 10)
@BenchmarkMode({Mode.AverageTime})
public class TrainTicket {

    private static String ttControlPath = "C:\\Users\\Emil\\Desktop\\StartupBenchmarks\\control\\trainticket.jar";
    private static String ttDI_Path = "C:\\Users\\Emil\\Desktop\\StartupBenchmarks\\di\\trainticket.jar";
    private static String otelAgent = "-javaagent:C:\\Users\\Emil\\Desktop\\StartupBenchmarks\\opentelemetry-javaagent-all.jar";
    private static String specialAgent = "-javaagent:C:\\Users\\Emil\\Desktop\\StartupBenchmarks\\opentracing-specialagent-1.7.5-SNAPSHOT.jar";

    @Benchmark
    public void controlTrainTicket(NormalState state) throws Exception {
        state.run();
    }

    @Benchmark
    public void OTelTrainTicket(OTelState state) throws Exception {
        state.run();
    }

    @Benchmark
    public void DI_TrainTicker(DI_State state) throws Exception {
        state.run();
    }

    @Benchmark
    public void specialTrainTicket(SpecialState state) throws Exception {
        state.run();
    }

    @State(Scope.Benchmark)
    public static class NormalState extends ProcessManager {
        public NormalState() {
            super("-jar", ttControlPath, "--server.port=0");
        }
        @TearDown(Level.Iteration)
        public void stop() throws Exception {
            super.after();
        }
    }

    @State(Scope.Benchmark)
    public static class OTelState extends ProcessManager {
        public OTelState() {
            super(otelAgent, "-jar", ttControlPath, "--server.port=0");
        }
        @TearDown(Level.Iteration)
        public void stop() throws Exception {
            super.after();
        }
    }

    @State(Scope.Benchmark)
    public static class DI_State extends ProcessManager {
        public DI_State() {
            super("-jar", ttDI_Path, "--server.port=0");
        }
        @TearDown(Level.Iteration)
        public void stop() throws Exception {
            super.after();
        }
    }

    @State(Scope.Benchmark)
    public static class SpecialState extends ProcessManager {
        public SpecialState() {
            super(specialAgent, "-jar", ttControlPath, "--server.port=0");
        }
        @TearDown(Level.Iteration)
        public void stop() throws Exception {
            super.after();
        }
    }

}
