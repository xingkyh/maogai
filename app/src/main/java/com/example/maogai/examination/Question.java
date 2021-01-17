package com.example.maogai.examination;

//题目实体类
public class Question {
    private String question;//题目
    private String optionA;//选项A
    private String optionB;//选项B
    private String optionC;//选项C
    private String optionD;//选项D
    private int answer;//答案

    public Question(String question, String optionA, String optionB, String optionC, String optionD, int answer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public int getAnswer() {
        return answer;
    }


    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

}
