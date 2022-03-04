package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Game {
    private List<Player> players;
    private List<String> citiesPool;
    private List<String> usedCities;

    public Game(List<String> cities) {
        this.citiesPool = cities;
        this.players = new ArrayList<>();
        this.usedCities = new ArrayList<>();
    }

    public List<String> getUsedCities() {

        return usedCities;
    }

    public List<String> getPlayersNames() {
        List<String> lst = new ArrayList<>();
        for (Player pl: players)
            lst.add(pl.getName());
        return lst;
    }

    public void gettingReady(){
        Scanner in = new Scanner(System.in);
        System.out.println("Приветстуем всех на игре в города\nПожалуйста, введите количество игроков.\nМаксимальное количество - 9!");
        int count = Integer.parseInt(in.nextLine());
        for (int i = 0; i < count; i++){
            System.out.println("Введите имя " + (i+1) + " игрока");
            players.add(new Player(in.nextLine()));
        }
        System.out.print("Итак, сегодня с нами играют: ");
        for (int i = 0; i < players.size(); i++) {
            if (i == players.size() - 2) {
                System.out.print(players.get(i).getName() + " и ");
            }else if (i == players.size() - 1){
                System.out.println(players.get(i).getName());
            }else{
                System.out.print(players.get(i).getName() + ", ");
            }
        }
    }

    public char checkLetter(String city){
        char letter = city.toLowerCase(Locale.ROOT).charAt(city.length() - 1);
        for (int i = 2; i<city.length();i++){
            if (letter != 'ъ' && letter != 'ы' && letter != 'ь'){
                break;
            }
            letter = city.toLowerCase(Locale.ROOT).charAt(city.length() - i);
        }
        return letter;
    }

    private String firstStep(){
        Scanner in = new Scanner(System.in);
        while (true){
            System.out.println("На все ходы. кроме этого, у игрока будет 30 секунд.\nВведите любой город для начала игры ");
            String answer = in.nextLine().toLowerCase(Locale.ROOT).replace('ё', 'е');
            if (citiesPool.contains(answer)){
                usedCities.add(answer);
                return answer;
            }
            else{
                System.out.println("Нет такого города!");
            }
        }
    }

    public class Step extends Thread{
        private String city;

        public Step(String city){
            this.city = city;
        }

        private String getCity(){
            return city;
        }

        public void run() {
            Scanner in = new Scanner(System.in);
            char letter = checkLetter(city);
            while (true){
                System.out.println("Введите город на букву " + (char)(letter - 32));
                String answer = in.nextLine().toLowerCase(Locale.ROOT).replace('ё', 'е');
                if (answer.charAt(0) == letter){
                    if (!usedCities.contains(answer)){
                        if (citiesPool.contains(answer)){
                            usedCities.add(answer);
                            this.city = answer;
                            this.interrupt();
                            break;
                        }
                        else{
                            System.out.println("Нет такого города!");
                        }
                    }else{
                        System.out.println("Такой город уже назван!");
                    }
                }else{
                    System.out.println("Город начинается на другую букву!");
                }
            }
        }
    }

    public void start(){
        gettingReady();
        System.out.println("Первый ход делает " + players.get(0).getName());
        String city = firstStep();
        for (int i = 1; i < players.size();){
            int counter = 0;
            System.out.println("Ход игрока " + players.get(i).getName());
            Step step = new Step(city);
            step.start();
            try {
                while (true){
                    if (counter == 300){
                        step.interrupt();
                        System.out.println("К сожалению, время на ход истекло.\nПроиграл игрок " + players.get(i).getName());
                        System.exit(1);
                    }
                    if (!step.getCity().equals(city)){
                        city = step.getCity();
                        if (i == players.size() - 1){
                            i = 0;
                        }else {
                            i++;
                        }
                        break;
                    }
                    Thread.sleep(100);
                    counter++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
