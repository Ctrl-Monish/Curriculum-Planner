package com.iitd.Planner;

public class ContentToDisplay {
    int age,month, week, day;
    private String[][][][] content = new String[4][13][6][8];
    private String[] months = {"", "April", "May", "June", "July", "August", "September", "October", "November", "December", "January", "February", "March"};

    public ContentToDisplay(int age, int month, int week, int day) {
        this.age = age;
        this.month = month;
        this.week = week;
        this.day = day;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String monthContent(){
        switch (age){
            case 1:
                content[age][month][week][day] = months[month] + " overview for " + "1st year";
                break;
            case 2:
                content[age][month][week][day] = months[month] + " overview for " + "2nd year";
                break;
            case 3:
                content[age][month][week][day] = months[month] + " overview for " + "3rd year";
                break;
        }
        return content[age][month][week][day];
    }

    public String weekContent(){
        switch (age){
            case 1:
                content[age][month][week][day] = "Week " + week + " " +  months[month] + " overview for " + "1st year";
                break;
            case 2:
                content[age][month][week][day] = "Week " + week + " " + months[month] + " overview for " + "2nd year";
                break;
            case 3:
                content[age][month][week][day] = "Week " + week + " " + months[month] + " overview for " + "3rd year";
                break;
        }
        return content[age][month][week][day];
    }

}
