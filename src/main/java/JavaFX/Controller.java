package JavaFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Controller {
    private PaymentsManager paymentsManager = new PaymentsManager();
    private FuturePaymentRepository futurePaymentRepository = paymentsManager.getFuturePaymentRepository();
    private PastPaymentRepository pastPaymentRepository = paymentsManager.getPastPaymentRepository();
    ObservableList<String> typeOptions = FXCollections.observableArrayList(Categories.Ubrania_Obuwie.toString(),
            Categories.Rachunki.toString(),Categories.Zywnosc.toString(),Categories.Uzywki.toString(),Categories.Chemia_SrodkiCzystosci.toString(),
            Categories.Transport.toString(),Categories.Rozrywka.toString(),Categories.Sprzet.toString(),Categories.Kosmetyki.toString());
    ObservableList<String> currencyOptions = FXCollections.observableArrayList("USD","EUR","CHF","JPY","MXN","RUB");
    private DrawingClass drawingClass = new DrawingClass(pastPaymentRepository.getPastPayments(),futurePaymentRepository.getFuturePayments());
    private ObservableList<PieChart.Data> pieChartData;
    private ObservableList<FuturePayment> futurePaymentsList;
    private ObservableList<PastPayment> pastPaymentList;

    @FXML
    private TableView<FuturePayment> mainPaymentsTable;
    @FXML
    private TableColumn<FuturePayment, Integer> idCol;
    @FXML
    private TableColumn<FuturePayment, String> nameCol;
    @FXML
    private TableColumn<FuturePayment, Float> priceCol;
    @FXML
    private TableColumn<FuturePayment, Categories> typeCol;
    @FXML
    private TableColumn<FuturePayment, String> descCol;
    @FXML
    private TableColumn<FuturePayment, String> foreignCurrencyCol;

    @FXML
    private TableView<PastPayment> pastPaymentsTable;
    @FXML
    private TableColumn<PastPayment, Integer> idColumn;
    @FXML
    private TableColumn<PastPayment, String> nameColumn;
    @FXML
    private TableColumn<PastPayment, Float> priceColumn;
    @FXML
    private TableColumn<PastPayment, Categories> typeColumn;
    @FXML
    private TableColumn<PastPayment, String> descColumn;
    @FXML
    private TableColumn<PastPayment, Date> dateColumn;
    @FXML
    private TableColumn<FuturePayment, String> foreignCurrencyCol2;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private ChoiceBox choiceBox,choiceBox1, choiceBox2;
    @FXML
    private TextField descTextField;
    @FXML
    private TextField finalDescTextField;
    @FXML
    private Tab mainTab;
    @FXML
    private Tab tableTab;
    @FXML
    private Tab chartsTab;
    @FXML
    private DatePicker datePicker;
    @FXML
    private CheckBox percentCheckBox;
    @FXML
    private CheckBox numberCheckBox;
    @FXML
    private CheckBox percentCheckBox2;
    @FXML
    private CheckBox numberCheckBox2;
    @FXML
    private DatePicker from;
    @FXML
    private DatePicker to;
    @FXML
    private ToggleButton toggleChartsMode;
    @FXML
    private Label Od,Do;

    @FXML
    private PieChart pieChart;

    @FXML CheckBox cyclicCheckBox;

    @FXML
    void deslectCheckBoxes(){
        if(!chartsTab.isSelected()) {
            percentCheckBox.setSelected(false);
            percentCheckBox.setDisable(false);
            numberCheckBox.setSelected(false);
            numberCheckBox.setDisable(false);
            numberCheckBox2.setSelected(false);
            percentCheckBox2.setSelected(false);
            from.setDisable(true);to.setDisable(true);
            from.setValue(null);to.setValue(null);
            pieChartData.clear();

        }

    }

    @FXML
    void initializeFuturePaymentsChartWindow(){
        if(toggleChartsMode.isSelected()){
            toggleChartsMode.setStyle("-fx-background-color: #84ff96");

            percentCheckBox.setDisable(true);
            percentCheckBox.setSelected(false);
            percentCheckBox2.setDisable(false);

            numberCheckBox.setDisable(true);
            numberCheckBox.setSelected(false);
            numberCheckBox2.setDisable(false);

            from.setDisable(true);
            to.setDisable(true);
            from.setOpacity(0);
            to.setOpacity(0);
            Od.setOpacity(0);
            Do.setOpacity(0);

            if(pieChartData != null){
                pieChartData.clear();
            }
        }
        else{

            toggleChartsMode.setStyle("");

            percentCheckBox2.setSelected(false);
            percentCheckBox2.setDisable(true);
            percentCheckBox.setDisable(false);

            numberCheckBox2.setSelected(false);
            numberCheckBox2.setDisable(true);
            numberCheckBox.setDisable(false);

            from.setDisable(false);
            to.setDisable(false);
            from.setOpacity(1);
            to.setOpacity(1);
            Od.setOpacity(1);
            Do.setOpacity(1);

            if(pieChartData != null)
            pieChartData.clear();
        }
    }

    @FXML
    void drawFuturePaymentsChart(){
        if(percentCheckBox2.isSelected()){
            numberCheckBox2.setSelected(false);
            DecimalFormat df = new DecimalFormat("##.##");
            df.setRoundingMode(RoundingMode.DOWN);
                float sum = drawingClass.returnTypeFuturePayments(Categories.Ubrania_Obuwie)+
                        drawingClass.returnTypeFuturePayments(Categories.Rachunki)+
                        drawingClass.returnTypeFuturePayments(Categories.Zywnosc)+
                        drawingClass.returnTypeFuturePayments(Categories.Uzywki)+
                        drawingClass.returnTypeFuturePayments(Categories.Chemia_SrodkiCzystosci)+
                        drawingClass.returnTypeFuturePayments(Categories.Transport)+
                        drawingClass.returnTypeFuturePayments(Categories.Rozrywka)+
                        drawingClass.returnTypeFuturePayments(Categories.Sprzet)+
                        drawingClass.returnTypeFuturePayments(Categories.Kosmetyki);
                float percentOne = (Float.parseFloat(Integer.toString(drawingClass.returnTypeFuturePayments(Categories.Ubrania_Obuwie)))/sum)*100;
                float percentTwo = (Float.parseFloat(Integer.toString(drawingClass.returnTypeFuturePayments(Categories.Rachunki)))/sum)*100;
                float percentThree = (Float.parseFloat(Integer.toString(drawingClass.returnTypeFuturePayments(Categories.Zywnosc)))/sum)*100;
                float percentFour = (Float.parseFloat(Integer.toString(drawingClass.returnTypeFuturePayments(Categories.Uzywki)))/sum)*100;
                float percentFive = (Float.parseFloat(Integer.toString(drawingClass.returnTypeFuturePayments(Categories.Chemia_SrodkiCzystosci)))/sum)*100;
                float percentSix = (Float.parseFloat(Integer.toString(drawingClass.returnTypeFuturePayments(Categories.Transport)))/sum)*100;
                float percentSeven = (Float.parseFloat(Integer.toString(drawingClass.returnTypeFuturePayments(Categories.Rozrywka)))/sum)*100;
                float percentEight = (Float.parseFloat(Integer.toString(drawingClass.returnTypeFuturePayments(Categories.Sprzet)))/sum)*100;
                float percentNine = (Float.parseFloat(Integer.toString(drawingClass.returnTypeFuturePayments(Categories.Kosmetyki)))/sum)*100;
                pieChartData = FXCollections.observableArrayList(
                        new PieChart.Data("Ubrania_Obuwie" + df.format(percentOne) + "%",percentOne),
                        new PieChart.Data("Rachunki " + df.format(percentTwo) + "%",percentTwo),
                        new PieChart.Data("Zywnosc " + df.format(percentThree) + "%",percentThree),
                        new PieChart.Data("Uzywki " + df.format(percentThree) + "%",percentFour),
                        new PieChart.Data("Chemia_SrodkiCzystosci " + df.format(percentThree) + "%",percentFive),
                        new PieChart.Data("Transport " + df.format(percentThree) + "%",percentSix),
                        new PieChart.Data("Rozrywka " + df.format(percentThree) + "%",percentSeven),
                        new PieChart.Data("Sprzet " + df.format(percentThree) + "%",percentEight),
                        new PieChart.Data("Kosmetyki " + df.format(percentThree) + "%",percentNine));
                pieChart.setData(pieChartData);
        }
        else {
            if(pieChartData != null)
                pieChartData.clear();
        }
    }

    @FXML
    void drawFuturePaymentsChart2(){
        if(numberCheckBox2.isSelected()){

            percentCheckBox2.setSelected(false);
            DecimalFormat df = new DecimalFormat("##.##");
            df.setRoundingMode(RoundingMode.DOWN);

                pieChartData = FXCollections.observableArrayList();
                pieChartData.add(new PieChart.Data("Ubrania_Obuwie " + df.format(drawingClass.returnSumOfFuturePayments(Categories.Ubrania_Obuwie)) +"PLN",drawingClass.returnSumOfFuturePayments(Categories.Ubrania_Obuwie)));
                pieChartData.add(new PieChart.Data("Rachunki " + df.format(drawingClass.returnSumOfFuturePayments(Categories.Rachunki)) +"PLN",drawingClass.returnSumOfFuturePayments(Categories.Rachunki)));
                pieChartData.add(new PieChart.Data("Zywnosc " + df.format(drawingClass.returnSumOfFuturePayments(Categories.Zywnosc)) +"PLN",drawingClass.returnSumOfFuturePayments(Categories.Zywnosc)));
                pieChartData.add(new PieChart.Data("Uzywki " + df.format(drawingClass.returnSumOfFuturePayments(Categories.Uzywki)) +"PLN",drawingClass.returnSumOfFuturePayments(Categories.Uzywki)));
                pieChartData.add(new PieChart.Data("Chemia_SrodkiCzystosci " + df.format(drawingClass.returnSumOfFuturePayments(Categories.Chemia_SrodkiCzystosci)) +"PLN",drawingClass.returnSumOfFuturePayments(Categories.Chemia_SrodkiCzystosci)));
                pieChartData.add(new PieChart.Data("Transport " + df.format(drawingClass.returnSumOfFuturePayments(Categories.Transport)) +"PLN",drawingClass.returnSumOfFuturePayments(Categories.Transport)));
                pieChartData.add(new PieChart.Data("Rozrywka " + df.format(drawingClass.returnSumOfFuturePayments(Categories.Rozrywka)) +"PLN",drawingClass.returnSumOfFuturePayments(Categories.Rozrywka)));
                pieChartData.add(new PieChart.Data("Sprzet " + df.format(drawingClass.returnSumOfFuturePayments(Categories.Sprzet)) +"PLN",drawingClass.returnSumOfFuturePayments(Categories.Sprzet)));
                pieChartData.add(new PieChart.Data("Kosmetyki " + df.format(drawingClass.returnSumOfFuturePayments(Categories.Kosmetyki)) +"PLN",drawingClass.returnSumOfFuturePayments(Categories.Kosmetyki)));
                pieChart.setData(pieChartData);
        }
        else {
            if(pieChartData != null)
                pieChartData.clear();
        }
    }


    @FXML
        //AMMOUNT
    void drawPastPaymentsChart(){
        if(numberCheckBox.isSelected()){
            from.setDisable(false);to.setDisable(false);
            percentCheckBox.setSelected(false);
            DecimalFormat df = new DecimalFormat("##.##");
            df.setRoundingMode(RoundingMode.DOWN);

            if((from.getEditor().getText().length() == 0 && to.getEditor().getText().length() == 0) || (from.getEditor().getText().length() != 0 && to.getEditor().getText().length() == 0)
                    || (from.getEditor().getText().length() == 0 && to.getEditor().getText().length() != 0)){
            pieChartData = FXCollections.observableArrayList();
            pieChartData.add(new PieChart.Data("Ubrania_Obuwie " + df.format(drawingClass.returnSumOfPayments(Categories.Ubrania_Obuwie)) +"PLN",drawingClass.returnSumOfPayments(Categories.Ubrania_Obuwie)));
            pieChartData.add(new PieChart.Data("Rachunki " + df.format(drawingClass.returnSumOfPayments(Categories.Rachunki)) +"PLN",drawingClass.returnSumOfPayments(Categories.Rachunki)));
            pieChartData.add(new PieChart.Data("Zywnosc " + df.format(drawingClass.returnSumOfPayments(Categories.Zywnosc)) +"PLN",drawingClass.returnSumOfPayments(Categories.Zywnosc)));
            pieChartData.add(new PieChart.Data("Uzywki " + df.format(drawingClass.returnSumOfPayments(Categories.Uzywki)) +"PLN",drawingClass.returnSumOfPayments(Categories.Uzywki)));
            pieChartData.add(new PieChart.Data("Chemia_SrodkiCzystosci " + df.format(drawingClass.returnSumOfPayments(Categories.Chemia_SrodkiCzystosci)) +"PLN",drawingClass.returnSumOfPayments(Categories.Chemia_SrodkiCzystosci)));
            pieChartData.add(new PieChart.Data("Transport " + df.format(drawingClass.returnSumOfPayments(Categories.Transport)) +"PLN",drawingClass.returnSumOfPayments(Categories.Transport)));
            pieChartData.add(new PieChart.Data("Rozrywka " + df.format(drawingClass.returnSumOfPayments(Categories.Rozrywka)) +"PLN",drawingClass.returnSumOfPayments(Categories.Rozrywka)));
            pieChartData.add(new PieChart.Data("Sprzet " + df.format(drawingClass.returnSumOfPayments(Categories.Sprzet)) +"PLN",drawingClass.returnSumOfPayments(Categories.Sprzet)));
            pieChartData.add(new PieChart.Data("Kosmetyki " + df.format(drawingClass.returnSumOfPayments(Categories.Kosmetyki)) +"PLN",drawingClass.returnSumOfPayments(Categories.Kosmetyki)));
            pieChart.setData(pieChartData);}

            if(from.getEditor().getText().length() != 0 && to.getEditor().getText().length() != 0){
                LocalDate date = from.getValue();
                int fromMonth = date.getMonthValue(); int fromDay = date.getDayOfMonth();
                LocalDate date2 = to.getValue();
                int toMonth = date2.getMonthValue(); int toDay = date2.getDayOfMonth();

                float sumOne = drawingClass.returnSumOfPaymentsBetweenDates(Categories.Ubrania_Obuwie,fromMonth,toMonth,fromDay,toDay);
                float sumTwo = drawingClass.returnSumOfPaymentsBetweenDates(Categories.Rachunki,fromMonth,toMonth,fromDay,toDay);
                float sumThree = drawingClass.returnSumOfPaymentsBetweenDates(Categories.Zywnosc,fromMonth,toMonth,fromDay,toDay);
                float sumFour = drawingClass.returnSumOfPaymentsBetweenDates(Categories.Uzywki,fromMonth,toMonth,fromDay,toDay);
                float sumFive = drawingClass.returnSumOfPaymentsBetweenDates(Categories.Chemia_SrodkiCzystosci,fromMonth,toMonth,fromDay,toDay);
                float sumSix = drawingClass.returnSumOfPaymentsBetweenDates(Categories.Transport,fromMonth,toMonth,fromDay,toDay);
                float sumSeven = drawingClass.returnSumOfPaymentsBetweenDates(Categories.Rozrywka,fromMonth,toMonth,fromDay,toDay);
                float sumEight = drawingClass.returnSumOfPaymentsBetweenDates(Categories.Sprzet,fromMonth,toMonth,fromDay,toDay);
                float sumNine = drawingClass.returnSumOfPaymentsBetweenDates(Categories.Kosmetyki,fromMonth,toMonth,fromDay,toDay);

                pieChartData = FXCollections.observableArrayList(
                        new PieChart.Data("Ubrania_Obuwie " + df.format(sumOne) + "PLN",sumOne),
                        new PieChart.Data("Rachunki " + df.format(sumTwo) + "PLN",sumTwo),
                        new PieChart.Data("Zywnosc " + df.format(sumThree) + "PLN",sumThree),
                        new PieChart.Data("Uzywki " + df.format(sumFour) + "PLN",sumFour),
                        new PieChart.Data("Chemia_SrodkiCzystosci " + df.format(sumFive) + "PLN",sumFive),
                        new PieChart.Data("Transport " + df.format(sumSix) + "PLN",sumSix),
                        new PieChart.Data("Rozrywka " + df.format(sumSeven) + "PLN",sumSeven),
                        new PieChart.Data("Sprzet " + df.format(sumEight) + "PLN",sumEight),
                        new PieChart.Data("Kosmetyki " + df.format(sumNine) + "PLN",sumNine));
                pieChart.setData(pieChartData);

            }

            }
        else {
            pieChartData.clear();
            from.setDisable(true);to.setDisable(true);
            from.setValue(null);to.setValue(null);
        }

   }
   @FXML
   void Update(){
        if(numberCheckBox.isSelected())
        drawPastPaymentsChart();
        else
        drawPastPaymentsChart2();
    }

   @FXML
       //PERCENT
   void drawPastPaymentsChart2(){
       if(percentCheckBox.isSelected()){
            from.setDisable(false);to.setDisable(false);
            numberCheckBox.setSelected(false);
           DecimalFormat df = new DecimalFormat("##.##");
           df.setRoundingMode(RoundingMode.DOWN);

           if((from.getEditor().getText().length() == 0 && to.getEditor().getText().length() == 0) || (from.getEditor().getText().length() != 0 && to.getEditor().getText().length() == 0)
           || (from.getEditor().getText().length() == 0 && to.getEditor().getText().length() != 0)){

               float sum = drawingClass.returnTypePastPayments(Categories.Ubrania_Obuwie)+
                       drawingClass.returnTypePastPayments(Categories.Rachunki)+drawingClass.returnTypePastPayments(Categories.Zywnosc)
                       +drawingClass.returnTypePastPayments(Categories.Uzywki)+drawingClass.returnTypePastPayments(Categories.Chemia_SrodkiCzystosci)+
                       drawingClass.returnTypePastPayments(Categories.Transport)+drawingClass.returnTypePastPayments(Categories.Rozrywka)+
                       drawingClass.returnTypePastPayments(Categories.Sprzet)+drawingClass.returnTypePastPayments(Categories.Kosmetyki);

               float percentOne = (Float.parseFloat(Integer.toString(drawingClass.returnTypePastPayments(Categories.Ubrania_Obuwie)))/sum)*100;
               float percentTwo = (Float.parseFloat(Integer.toString(drawingClass.returnTypePastPayments(Categories.Rachunki)))/sum)*100;
               float percentThree = (Float.parseFloat(Integer.toString(drawingClass.returnTypePastPayments(Categories.Zywnosc)))/sum)*100;
               float percentFour = (Float.parseFloat(Integer.toString(drawingClass.returnTypePastPayments(Categories.Uzywki)))/sum)*100;
               float percentFive = (Float.parseFloat(Integer.toString(drawingClass.returnTypePastPayments(Categories.Chemia_SrodkiCzystosci)))/sum)*100;
               float percentSix = (Float.parseFloat(Integer.toString(drawingClass.returnTypePastPayments(Categories.Transport)))/sum)*100;
               float percentSeven = (Float.parseFloat(Integer.toString(drawingClass.returnTypePastPayments(Categories.Rozrywka)))/sum)*100;
               float percentEight = (Float.parseFloat(Integer.toString(drawingClass.returnTypePastPayments(Categories.Sprzet)))/sum)*100;
               float percentNine= (Float.parseFloat(Integer.toString(drawingClass.returnTypePastPayments(Categories.Kosmetyki)))/sum)*100;

           pieChartData = FXCollections.observableArrayList(
                   new PieChart.Data("Ubrania_Obuwie " + df.format(percentOne) + "%",percentOne),
                   new PieChart.Data("Rachunki " + df.format(percentTwo) + "%",percentTwo),
                   new PieChart.Data("Zywnosc " + df.format(percentThree) + "%",percentThree),
                   new PieChart.Data("Uzywki " + df.format(percentFour) + "%",percentFour),
                   new PieChart.Data("Chemia_SrodkiCzystosci " + df.format(percentFive) + "%",percentFive),
                   new PieChart.Data("Transport " + df.format(percentSix) + "%",percentSix),
                   new PieChart.Data("Rozrywka " + df.format(percentSeven) + "%",percentSeven),
                   new PieChart.Data("Sprzet " + df.format(percentEight) + "%",percentEight),
                   new PieChart.Data("Kosmetyki " + df.format(percentNine) + "%",percentNine));
           pieChart.setData(pieChartData);
           }


           if(from.getEditor().getText().length() != 0 && to.getEditor().getText().length() != 0){
               LocalDate date = from.getValue();
               int fromMonth = date.getMonthValue(); int fromDay = date.getDayOfMonth();
               LocalDate date2 = to.getValue();
               int toMonth = date2.getMonthValue(); int toDay = date2.getDayOfMonth();

               float ones = drawingClass.countPaymentsBetweenDates(Categories.Ubrania_Obuwie,fromMonth,toMonth,fromDay,toDay);
               float twos = drawingClass.countPaymentsBetweenDates(Categories.Rachunki,fromMonth,toMonth,fromDay,toDay);
               float threes = drawingClass.countPaymentsBetweenDates(Categories.Zywnosc,fromMonth,toMonth,fromDay,toDay);
               float fours = drawingClass.countPaymentsBetweenDates(Categories.Uzywki,fromMonth,toMonth,fromDay,toDay);
               float fives = drawingClass.countPaymentsBetweenDates(Categories.Chemia_SrodkiCzystosci,fromMonth,toMonth,fromDay,toDay);
               float sixes = drawingClass.countPaymentsBetweenDates(Categories.Transport,fromMonth,toMonth,fromDay,toDay);
               float sevens = drawingClass.countPaymentsBetweenDates(Categories.Rozrywka,fromMonth,toMonth,fromDay,toDay);
               float eights = drawingClass.countPaymentsBetweenDates(Categories.Sprzet,fromMonth,toMonth,fromDay,toDay);
               float nines = drawingClass.countPaymentsBetweenDates(Categories.Kosmetyki,fromMonth,toMonth,fromDay,toDay);
               float sumOTT = ones+twos+threes+fours+fives+sixes+sevens+eights+nines;
               ones=(ones/sumOTT)*100;
               twos=(twos/sumOTT)*100;
               threes=(threes/sumOTT)*100;
               fours=(fours/sumOTT)*100;
               fives=(fives/sumOTT)*100;
               sixes=(sixes/sumOTT)*100;
               sevens=(sevens/sumOTT)*100;
               eights=(eights/sumOTT)*100;
               nines=(nines/sumOTT)*100;

               pieChartData = FXCollections.observableArrayList(
                       new PieChart.Data("Ubrania_Obuwie " + df.format(ones) + "%",ones),
                       new PieChart.Data("Rachunki " + df.format(twos) + "%",twos),
                       new PieChart.Data("Zywnosc " + df.format(threes) + "%",threes),
                       new PieChart.Data("Uzywki " + df.format(fours) + "%",fours),
                       new PieChart.Data("Chemia_SrodkiCzystosci " + df.format(fives) + "%",fives),
                       new PieChart.Data("Transport " + df.format(sixes) + "%",sixes),
                       new PieChart.Data("Rozrywka " + df.format(sevens) + "%",sevens),
                       new PieChart.Data("Sprzet " + df.format(eights) + "%",eights),
                       new PieChart.Data("Kosmetyki " + df.format(nines) + "%",nines));
               pieChart.setData(pieChartData);

           }
       }
       else {
           pieChartData.clear();
           from.setDisable(true);to.setDisable(true);
           from.setValue(null);to.setValue(null);
       }

   }

    @FXML
    void openTabEvent() {
        if (tableTab.isSelected()) {
            pastPaymentList = FXCollections.observableArrayList(pastPaymentRepository.getPastPayments());
            pastPaymentsTable.setItems(pastPaymentList);
            idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            foreignCurrencyCol2.setCellValueFactory(new PropertyValueFactory<>("priceInDifferentCurrency"));
        }
    }

    @FXML
    void openMainTabEvent() {
        if (mainTab.isSelected()) {
            futurePaymentsList = FXCollections.observableArrayList(futurePaymentRepository.getFuturePayments());
            mainPaymentsTable.setItems(futurePaymentsList);
            idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            foreignCurrencyCol.setCellValueFactory(new PropertyValueFactory<>("priceInDifferentCurrency"));
        }
    }

    @FXML
    void initialize(){
        choiceBox.setItems((typeOptions));
        choiceBox1.setItems(currencyOptions);
        choiceBox2.setItems(currencyOptions);
    }
    @FXML
    void changeCurrency(){
                if(choiceBox1.getValue().toString() == "USD") paymentsManager.convertEveryPrice("USD");
                if(choiceBox1.getValue().toString() == "EUR") paymentsManager.convertEveryPrice("EUR");
                if(choiceBox1.getValue().toString() == "CHF") paymentsManager.convertEveryPrice("CHF");
                if(choiceBox1.getValue().toString() == "JPY") paymentsManager.convertEveryPrice("JPY");
                if(choiceBox1.getValue().toString() == "MXN") paymentsManager.convertEveryPrice("MXN");
                if(choiceBox1.getValue().toString() == "RUB") paymentsManager.convertEveryPrice("RUB");
                if (futurePaymentsList != null)
                    futurePaymentsList.removeAll(futurePaymentsList);
                openMainTabEvent();
    }

    @FXML
    void changeCurrency2(){
                if(choiceBox2.getValue().toString() == "USD") paymentsManager.convertEveryPrice("USD");
                if(choiceBox2.getValue().toString() == "EUR") paymentsManager.convertEveryPrice("EUR");
                if(choiceBox2.getValue().toString() == "CHF") paymentsManager.convertEveryPrice("CHF");
                if(choiceBox2.getValue().toString() == "JPY") paymentsManager.convertEveryPrice("JPY");
                if(choiceBox2.getValue().toString() == "MXN") paymentsManager.convertEveryPrice("MXN");
                if(choiceBox2.getValue().toString() == "RUB") paymentsManager.convertEveryPrice("RUB");
                if (pastPaymentList != null)
                    pastPaymentList.removeAll(pastPaymentList);
                openTabEvent();
    }

    @FXML
    void addFuturePaymentClick(ActionEvent actionEvent){
        Categories choiceBoxResult = null;
        if(choiceBox.getValue().toString() == "Ubrania_Obuwie") choiceBoxResult = Categories.Ubrania_Obuwie;
        if(choiceBox.getValue().toString() == "Rachunki") choiceBoxResult = Categories.Rachunki;
        if(choiceBox.getValue().toString() == "Zywnosc") choiceBoxResult = Categories.Zywnosc;
        if(choiceBox.getValue().toString() == "Uzywki") choiceBoxResult = Categories.Uzywki;
        if(choiceBox.getValue().toString() == "Chemia_SrodkiCzystosci") choiceBoxResult = Categories.Chemia_SrodkiCzystosci;
        if(choiceBox.getValue().toString() == "Transport") choiceBoxResult = Categories.Transport;
        if(choiceBox.getValue().toString() == "Rozrywka") choiceBoxResult = Categories.Rozrywka;
        if(choiceBox.getValue().toString() == "Sprzet") choiceBoxResult = Categories.Sprzet;
        if(choiceBox.getValue().toString() == "Kosmetyki") choiceBoxResult = Categories.Kosmetyki;

        if(choiceBoxResult != null && nameTextField.getText() != null && priceTextField.getText() != null && descTextField.getText() != null){
        FuturePayment futurePayment = new FuturePayment(nameTextField.getText(), Float.parseFloat(priceTextField.getText()),
                choiceBoxResult,descTextField.getText());
        if(cyclicCheckBox.isSelected()){
            LocalDate localDate = LocalDate.now();
            futurePayment.setDescription(Integer.toString(localDate.getMonth().getValue()));
            futurePayment.setName(futurePayment.getPaymentName() + " (cykliczna)");
            futurePayment.setCyclic(true);
        }
        futurePaymentRepository.addToRepository(futurePayment);
        nameTextField.clear();priceTextField.clear();descTextField.clear();choiceBox.setValue(null);cyclicCheckBox.setSelected(false);
        chartsTab.setDisable(false);
        openMainTabEvent();
        }
    }

    @FXML
    void finishPaymentClick(ActionEvent actionEvent){
        if(mainPaymentsTable.getSelectionModel().getSelectedItem() != null && datePicker.getValue() != null){
        FuturePayment temp = mainPaymentsTable.getSelectionModel().getSelectedItem();
        Date date = Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        PastPayment pastPayment;
            if(finalDescTextField.getText() != null){
                pastPayment = new PastPayment(temp.getName(), temp.getPrice(),temp.getType(),finalDescTextField.getText(),date);
            }
            else{
                pastPayment = new PastPayment(temp.getName(), temp.getPrice(),temp.getType(),temp.getDescription(),date);
            }
            if(temp.getCyclic() == true){
                int desc = Integer.parseInt(temp.getDescription()) +1 ;
                if (desc == 13) desc = 1;
                pastPayment.setDescription(pastPayment.getPaymentDescription()+" opłacono dla :"+Integer.parseInt(temp.getDescription()) );

                temp.setDescription(Integer.toString(desc));
            }

            pastPaymentRepository.addToRepository(pastPayment);
            futurePaymentRepository.deletePayment(temp.getID());
            futurePaymentsList.removeAll(futurePaymentsList);
            openMainTabEvent();
            finalDescTextField.clear();
            datePicker.getEditor().clear();
            chartsTab.setDisable(false);
        }
    }

    @FXML void serialize(){
        paymentsManager.serializeAll();
    }
    @FXML void deserializeFuture(){
        paymentsManager.deserializeFuturePaymentRepository();
        futurePaymentRepository = paymentsManager.getFuturePaymentRepository();
        if (futurePaymentsList != null)
            futurePaymentsList.removeAll(futurePaymentsList);
        openMainTabEvent();
        chartsTab.setDisable(false);
        drawingClass = new DrawingClass(pastPaymentRepository.getPastPayments(),futurePaymentRepository.getFuturePayments());
    }

    @FXML void deserializePast(){
        paymentsManager.deserializerPastPaymentRepository();
        pastPaymentRepository = paymentsManager.getPastPaymentRepository();
        if (pastPaymentList != null)
            pastPaymentList.removeAll(pastPaymentList);
        openTabEvent();
        chartsTab.setDisable(false);
        drawingClass = new DrawingClass(pastPaymentRepository.getPastPayments(),futurePaymentRepository.getFuturePayments());
    }

     public Controller() { }
}
