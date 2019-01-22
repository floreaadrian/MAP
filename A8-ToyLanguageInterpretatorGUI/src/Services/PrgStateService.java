package Services;

import Model.PrgState;
import Model.Tuple;
import Repository.IRepository;

import java.util.*;

public class PrgStateService implements Observable<PrgState> {
    private List<Observer<PrgState>> observers = new ArrayList<>();
    private IRepository repo;

    public PrgStateService(IRepository repo) {
        this.repo = repo;
    }

    public IRepository getRepo() {
        return this.repo;
    }

    public List<PrgState> getAll() {
        return new ArrayList<>(this.repo.getPrgList());
    }

    public List<String> getOutList() {
        List<String> mList = new ArrayList<>();
        for (int i = 0; i < this.repo.getPrgList().get(0).getOut().size(); ++i)
            mList.add(String.valueOf(this.repo.getPrgList().get(0).getOut().fromIndex(i)));
        return mList;
    }

    public List<Tuple<Integer, String>> getFileList() {
        List<Tuple<Integer, String>> mList = new ArrayList<>();
        for (Integer x : this.repo.getPrgList().get(0).getFileTable().keys())
            mList.add(new Tuple<>(x, this.repo.getPrgList().get(0).getFileTable().lookup(x).getFirst()));
        return mList;
    }

    public List<Tuple<Integer, Tuple<Integer, List<Integer>>>> getBarrierList() {
        List<Tuple<Integer, Tuple<Integer, List<Integer>>>> tempList = new ArrayList<>();
        PrgState state = this.repo.getPrgList().get(0);
        for (Integer x : state.getBarrierTable().keys()) {
            tempList.add(new Tuple<>(x, new Tuple<>(state.getBarrierTable().lookup(x).getFirst(),
                    state.getBarrierTable().lookup(x).getSecond().toList())));
        }
        return tempList;
    }

    public List<Map.Entry<Integer, Integer>> getHeapList() {
        return new ArrayList<>(repo.getPrgList().get(0).getHeap().getContent().entrySet());
    }

    @Override
    public void addObserver(Observer<PrgState> o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer<PrgState> o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : this.observers)
            o.update(this);
    }

}