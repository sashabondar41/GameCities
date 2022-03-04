package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Game {
    private List<Player> players;
    private List<String> citiesPool;
    private List<String> usedCities;

    public Game() {
        players = new ArrayList<>();
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
}
