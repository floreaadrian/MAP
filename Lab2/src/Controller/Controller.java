package Controller;

import Model.*;
import Repository.IRepository;

import java.util.Collection;
import java.util.Map;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.*;

public class Controller {
    private IRepository repo;
    private ExecutorService executor;

    public Controller(IRepository e) {
        this.repo = e;
    }

    private Map<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTableValues,
            Map<Integer, Integer> heap) {
        return heap.entrySet().stream().filter(e -> symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> collectSymTableValuesPrgList(List<PrgState> prgList) {
        List<Integer> tempList = new ArrayList<>();
        for (PrgState prgState : prgList) {
            tempList.addAll(prgState.getSymTable().values());
        }
        return tempList;
    }

    private List<PrgState> heapCleanup(List<PrgState> prgList) {
        Collection<Integer> allSymTableValues = collectSymTableValuesPrgList(prgList);
        return prgList.stream().peek(prgState -> {
            Map<Integer, Integer> jmk = conservativeGarbageCollector(allSymTableValues,
                    prgState.getHeap().getContent());
            prgState.getHeap().setContent(jmk);
        }).collect(Collectors.toList());
    }

    private List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream().filter(PrgState::isNotCompleted).collect(Collectors.toList());
    }

    public void reset() {
        this.repo.reset();
    }

    public void allStep() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
        prgList.forEach(prg -> repo.logPrgStateExec(prg));
        while (prgList.size() > 0) {
            oneStepForAllPrg(prgList);
            repo.setPrgList(heapCleanup(prgList));
            prgList.forEach(prg -> repo.logPrgStateExec(prg));
            prgList = removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        repo.setPrgList(prgList);
    }

    private void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        // prgList.forEach(prg -> repo.logPrgStateExec(prg));

        List<Callable<PrgState>> callList = prgList.stream().map((PrgState p) -> (Callable<PrgState>) (p::oneStep))
                .collect(Collectors.toList());

        List<PrgState> newPrgList = executor.invokeAll(callList).stream().map(future -> {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException ee) {
                ee.printStackTrace();
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        prgList.addAll(newPrgList);
        repo.setPrgList(prgList);
    }

//    private void closeBuffer(Collection<ITuple<String, BufferedReader>> values) {
//        values.forEach(e -> {
//            try {
//                e.getSecond().close();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        });
//    }

    public String getFilePath() {
        return this.repo.getFilePath();
    }
}
