package com.example.catuniverse.gameSupport.gameMathematics;

import java.util.ArrayList;

//Теория для метметических уровней - список с целыми формулами и список с вопросами
public class Theory {
    private final ArrayList<String> expressions; //Формулы
    private final ArrayList<String> questions; //Вопросы

    Theory() {
        expressions = new ArrayList<>();
        questions = new ArrayList<>();

        //Таблица квадратов
        expressions.add("11² = 121");
        expressions.add("12² = 144");
        expressions.add("13² = 169");
        expressions.add("14² = 196");
        expressions.add("15² = 225");
        expressions.add("16² = 256");
        expressions.add("17² = 289");
        expressions.add("18² = 324");
        expressions.add("19² = 361"); //8


        //тригонометрия
        expressions.add("cos²α + sin²α = 1"); //9
        expressions.add("tgα * ctgα = 1");
        expressions.add("cos2α = 2cos²α - 1");
        expressions.add("cos2α = 1 - 2sin²α");
        expressions.add("cos2α = cos²α - sin²α");
        expressions.add("sin2α = 2*sinα*cosα");//14

        //производные
        expressions.add("с' = 0"); //15
        expressions.add("x' = 1");
        expressions.add("x²' = 2x");
        expressions.add("√x' = 1/2√x");
        expressions.add("lnx' = 1/x");
        expressions.add("sinx' = cosx");
        expressions.add("cosx' = -sinx");
        expressions.add("e²' = e²"); //22


        questions.add("x² = 121");
        questions.add("x² = 144");
        questions.add("x² = 169");
        questions.add("x² = 196");
        questions.add("x² = 225");
        questions.add("x² = 256");
        questions.add("x² = 289");
        questions.add("x² = 324");
        questions.add("x² = 361");//8

        questions.add("11² = x");//9
        questions.add("12² = x");
        questions.add("13² = x");
        questions.add("14² = x");
        questions.add("15² = x");
        questions.add("15² = x");
        questions.add("17² = x");
        questions.add("18² = x");
        questions.add("19² = x");//17

        questions.add("x + sin²α = 1");//18
        questions.add("tgα * x = 1");
        questions.add("x + sin²α = 1");
        questions.add("cos2α = 1 - x");
        questions.add("cos2α = 2cos²α - x");//22
        questions.add("x = cos²α - sin²α");
        questions.add("x + sin²α = 1");
        questions.add("tgα * x = 1");
        questions.add("x + sin²α = 1");
        questions.add("cos²α + x = 1");
        questions.add("x = 2*sinα*cosα");
        questions.add("cos2α = x - sin²α");//29

        questions.add("с' = ?"); //30
        questions.add("x' = ?");
        questions.add("x²' = ?");
        questions.add("√x' = 1/?");
        questions.add("lnx' = ?");
        questions.add("sinx' = ?");
        questions.add("cosx' = ?");
        questions.add("e²' = ?");//37

        questions.add("?' = 0"); //38
        questions.add("?' = 1");
        questions.add("?' = 2x");
        questions.add("?' = 1/2√x");
        questions.add("?' = 1/x");
        questions.add("?' = cosx");
        questions.add("?' = -sinx");
        questions.add("?' = e²"); // 45

        //Производные
        //Функции и их графики
        //первообразные
        //ДОДЕЛАТЬ
    }

    //Проверка пойманного ответа
    boolean checkMathAnswer(String expr, String answer) {
        int indexQ = expr.indexOf("?"); //Если есть знак вопроса, то заменять его, иначе - х
        String result;
        if (indexQ == -1) //если смвол ? не был найден , то заменить x
            result = expr.replace("x", answer);
        else result = expr.replace("?", answer); //иначе - заменить вопрос

        //Если полученное выражение совпало с одним из теории
        for (int i = 0; i < expressions.size(); i++)
            if (result.equals(expressions.get(i))) {
                return true;
            }
        return false;
    }

    public ArrayList<String> getExpressions() {
        return expressions;
    }

    ArrayList<String> getQuestions() {
        return questions;
    }
}
