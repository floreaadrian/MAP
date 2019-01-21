package Controller;

import Model.IStmt;
import Model.ITuple;
import Model.PrgState;
import Model.Tuple;
import Repository.IRepository;
import Services.PrgStateService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Callback;

import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller implements Services.Observer<PrgState> {
    //////// FXML CRAP
    @FXML
    private Label prgStatesCnt;
    @FXML
    private TableView<Map.Entry<Integer, Integer>> heapTableView;
    @FXML
    private TableView<Map.Entry<Integer, Integer>> lockTableView;
    @FXML
    private ListView<String> outListView;
    @FXML
    private TableView<Tuple<Integer, String>> fileTableView;
    @FXML
    private ListView<PrgState> prgStateListView;
    @FXML
    private Label prgIdLabel;
    @FXML
    private TableView<Tuple<String, Integer>> symTableView;
    @FXML
    private ListView<IStmt> exeStackListView;
    @FXML
    private Button onestepBTN;

    ///// OUR DATA
    private IRepository repo;
    private boolean startThreads = true;
    private ExecutorService executor;
    private ObservableList<PrgState> prgStateModel;
    private ObservableList<String> outListModel;
    private ObservableList<Map.Entry<Integer, Integer>> heapTableModel;
    private ObservableList<Map.Entry<Integer, Integer>> lockTableModel;
    private ObservableList<Tuple<Integer, String>> fileTableModel;
    private ObservableList<IStmt> exeStackModel;
    private PrgStateService prgStateService;
    private ObservableList<Tuple<String, Integer>> symTableModel;

    @FXML
    private void initialize() {
    }

    public void setService(PrgStateService prgStateService) {
        this.prgStateService = prgStateService;
        this.repo = this.prgStateService.getRepo();
        this.prgStateModel = FXCollections.observableArrayList();
        this.prgStateListView.setItems(this.prgStateModel);

        this.prgStateListView.setCellFactory(new Callback<ListView<PrgState>, ListCell<PrgState>>() {
            @Override
            public ListCell<PrgState> call(ListView<PrgState> param) {
                return new ListCell<PrgState>() {
                    @Override
                    protected void updateItem(PrgState e, boolean empty) {
                        super.updateItem(e, empty);
                        if (e == null || empty)
                            setText("");
                        else
                            setText(String.valueOf(e.getId()));
                    }
                };
            }
        });
        heapServiceSetup();
        fileTableServiceSetup();
        symTableServiceSetup();
        lockTableServiceSetup();
        // outListView
        this.outListModel = FXCollections.observableArrayList();
        this.outListView.setItems(this.outListModel);

        this.prgStateListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        prgIdLabel.setText(String.valueOf(newValue.getId()));
                        List<PrgState> prgStates = prgStateService.getAll();
                        PrgState current = prgStates.stream()
                                .filter(e -> e.getId() == Integer.valueOf(prgIdLabel.getText())).findFirst()
                                .orElse(null);
                        if (current != null) {
                            List<IStmt> list = new ArrayList<>(current.getStk().toStack());

                            // Collections.reverse(list);
                            exeStackModel.setAll(list);
                            symTableModel.setAll(current.getSymTable().clone().toMap().entrySet().stream()
                                    .map(e -> new Tuple<>(e.getKey(), e.getValue())).collect(Collectors.toList()));
                        }
                    }
                });

        // exeStack
        this.exeStackModel = FXCollections.observableArrayList();
        this.exeStackListView.setItems(this.exeStackModel);

        this.update(this.prgStateService);
    }

    private void symTableServiceSetup() {
        TableColumn<Tuple<String, Integer>, String> symNameColumn = new TableColumn<>("Symbol name");
        TableColumn<Tuple<String, Integer>, String> symValueColumn = new TableColumn<>("Value");
        symNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFirst()));
        symValueColumn
                .setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getSecond())));
        this.symTableView.getColumns().setAll(symNameColumn, symValueColumn);
        this.symTableModel = FXCollections.observableArrayList();
        this.symTableView.setItems(this.symTableModel);
    }


    private void fileTableServiceSetup() {
        /// fileTableView
        this.fileTableModel = FXCollections.observableArrayList();
        TableColumn<Tuple<Integer, String>, String> fileDescColumn = new TableColumn<>("File descriptor");
        TableColumn<Tuple<Integer, String>, String> fileNameColumn = new TableColumn<>("File name");
        fileDescColumn
                .setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getFirst())));
        fileNameColumn
                .setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getSecond())));

        this.fileTableView.getColumns().setAll(fileDescColumn, fileNameColumn);
        this.fileTableView.setItems(this.fileTableModel);
    }

    private void heapServiceSetup() {
        // heapTableView
        this.heapTableModel = FXCollections.observableArrayList();
        TableColumn<Map.Entry<Integer, Integer>, String> heapAddrColumn = new TableColumn<>("Address");
        TableColumn<Map.Entry<Integer, Integer>, String> heapValueColumn = new TableColumn<>("Value");
        heapAddrColumn
                .setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getKey())));
        heapValueColumn
                .setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getValue())));
        this.heapTableView.getColumns().setAll(heapAddrColumn, heapValueColumn);
        this.heapTableView.setItems(this.heapTableModel);
    }

    private void lockTableServiceSetup() {
        // heapTableView
        this.lockTableModel = FXCollections.observableArrayList();
        TableColumn<Map.Entry<Integer, Integer>, String> lockAddrColumn = new TableColumn<>("Address");
        TableColumn<Map.Entry<Integer, Integer>, String> lockValueColumn = new TableColumn<>("Value");
        lockAddrColumn
                .setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getKey())));
        lockValueColumn
                .setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getValue())));
        this.lockTableView.getColumns().setAll(lockAddrColumn, lockValueColumn);
        this.lockTableView.setItems(this.lockTableModel);
    }

    /////////////////////////////////////////////

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
            Map<Integer, Integer> garbageCollected = conservativeGarbageCollector(allSymTableValues,
                    prgState.getHeap().getContent());
            prgState.getHeap().setContent(garbageCollected);
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
            // prgList = removeCompletedPrg(repo.getPrgList());
        }
        closeBuffer(repo.getPrgList().get(0).getFileTable().getContent().values());
        repo.setPrgList(prgList);
    }

    @FXML
    public void oneStepForGui() throws InterruptedException {
        if (this.startThreads) {
            this.startThreads = false;
            this.executor = Executors.newFixedThreadPool(2);
        }
        List<PrgState> prgList = removeCompletedPrg(this.repo.getPrgList());
        if (prgList.size() != 0) {
            oneStepForAllPrg(prgList);
            this.repo.setPrgList(prgList);
            prgList.forEach(prg -> this.repo.logPrgStateExec(prg));
            this.prgStateService.notifyObservers();
            this.repo.setPrgList(heapCleanup(prgList));
        }

    }

    private void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {

        this.repo.setPrgList(removeCompletedPrg(this.repo.getPrgList()));
        List<Callable<PrgState>> callList = prgList.stream().map((PrgState p) -> (Callable<PrgState>) (p::oneStep))
                .collect(Collectors.toList());
        List<PrgState> newPrgList = executor.invokeAll(callList).stream().map(future -> {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException ee) {
                // ee.printStackTrace();
                createAlertFromException(ee);
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        prgList.addAll(newPrgList);
        repo.setPrgList(prgList);

        // this.prgStateService.notifyObservers();
    }

    private void closeBuffer(Collection<ITuple<String, BufferedReader>> values) {
        values.forEach(e -> {
            try {
                e.getSecond().close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    public String getFilePath() {
        return this.repo.getFilePath();
    }

    ///////////////// JAVAFX FUN BEGINS HERE
    private void createAlertFromException(Exception ex) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Program State Exception Dialog");
        alert.setContentText("There was an exception:\n" + ex.getMessage());
        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();

    }

    @Override
    public void update(Services.Observable<PrgState> observable) {
        List<PrgState> prgStates = this.prgStateService.getAll();
        if (prgStates != null) {
            this.prgStatesCnt.setText(String.valueOf(prgStates.size()));
            // System.out.println(prgStates);
            this.prgStateModel.setAll(prgStates);
            this.outListModel.setAll(this.prgStateService.getOutList());
            this.heapTableModel.setAll(this.prgStateService.getHeapList());
            this.fileTableModel.setAll(this.prgStateService.getFileList());
            this.lockTableModel.setAll(this.prgStateService.getLockTableList());
            /// this we change
            PrgState current = prgStates.stream().filter(e -> e.getId() == Integer.valueOf(prgIdLabel.getText()))
                    .findFirst().orElse(null);
            if (current != null) {
                List<IStmt> list = new ArrayList<>(current.getStk().toStack());
                this.exeStackModel.setAll(list);
                this.symTableModel.setAll(current.getSymTable().clone().toMap().entrySet().stream()
                        .map(e -> new Tuple<>(e.getKey(), e.getValue())).collect(Collectors.toList()));
            }
        }
    }
}
