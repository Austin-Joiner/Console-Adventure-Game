import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class Main {

    private static String playerName;
    private static int gold;
    private static long currentSteps = 0;
    public static void main(String[] args) {
        Scanner namePick = new Scanner(System.in);
        System.out.println("Mayor: Hello Adventurer! Im the Town Mayor! I am here to help you out.");
        System.out.println("Mayor: What is your name hero so I know what to call you?");
        playerName = namePick.nextLine();
        System.out.println("Mayor: Well, Hello " + playerName + ", That is a great name. Sounds like a legendary hero name.");
        greetings();
    }

    public static void greetings() {
        // Tutorial / description of game here
        System.out.println("Mayor: Okay " + playerName + ", are you ready to start your adventure? Y / N");

        Scanner starter = new Scanner(System.in);
        String playerStart = starter.nextLine();

            if(playerStart.equalsIgnoreCase("y")) {
                System.out.println("Mayor: Sounds Good! Good Luck " + playerName + "!");
                start();

            } else if (playerStart.equalsIgnoreCase("n")) {
                System.out.println("Mayor: OH! OKAY, Go walk around the town for a bit til you are ready i will be waiting here.");
            } else {
                System.out.println("Game Error: Not Valid Input");
                greetings();
            }
    }

    public static void start() {
        Timer steps = new Timer();
        steps.schedule(new TimerTask() {
            @Override
            public void run() {
                currentSteps += 1;
                System.out.print("\033[1A");
                // clear line
                System.out.print("\033[2K");
                // print new text
                System.out.println("Current Steps: " + currentSteps);


                Random randomNum = new Random();

                int random = randomNum.nextInt(10) + 1;
//                System.out.println(random); // random number tester
//              return random;
                if (currentSteps % 2 == 1 && currentSteps > 5) {// even or above 5
//                    if (currentSteps == 1) {// quick tester
                    if (random == 1 || random % 3 == 0) {
//                    if (true) { //quick tester
                        steps.cancel();
                        Main main = new Main();
                        main.battleStart();
                    }
                } else if (currentSteps % 2 == 0 && currentSteps > 5) { //odd or above 5
                    if (random % 4 == 0 && currentSteps > 5) {
                      steps.cancel();
                      Main main = new Main();
                      main.eventStarter();
                    }
                }
            }
        }, 0, 1000);
    }

    int banditHP = 20;
    int banditLevel = 1;
    int banditOffLevel = 1;
    int banditDefLevel = 1;



    private static int playerHP = 20;
    int playerLevel = 3;
    int playerOffLevel = 1;
    int playerDefLevel = 1;
    public void battleStart() {
        System.out.println("Bandit: Lets Battle scumbag.");

        System.out.printf("\t\t\t\t\t\t%-15s\t\t\t\t\t\t%-15s", "Bandit Level: " + banditLevel, playerName + " Level: " + playerLevel);
        System.out.printf("\n\t\t\t\t\t\t%-15s\t\t\t\t\t%-15s", "Bandit Health: " + banditHP, playerName + " Health: " + playerHP);
        System.out.printf("\n\t\t\t\t\t\t%-15s\t\t\t\t%-15s", "Bandit Offensive LvL: " + banditOffLevel, playerName + " Offensive LvL: " + playerOffLevel);
        System.out.printf("\n\t\t\t\t\t\t%-15s\t\t\t\t%-15s", "Bandit Defensive LvL: " + banditDefLevel, playerName + " Defensive LvL: " + playerDefLevel);

        battleAttack();
        }



    public void battleAttack() {
        System.out.println("\nType \"Attack\" to Roll Your Dice for the Attack.");
        Scanner roll = new Scanner(System.in);
        String rollValidate = roll.nextLine();

        if (rollValidate.equalsIgnoreCase("attack")) {


            Random randomRoll = new Random();

            int playerDice = randomRoll.nextInt((playerLevel + 5)) + 1;// +5
            int banditDice = randomRoll.nextInt((banditLevel + 5)) + 1;// +5


            System.out.println(playerName + " ATTACKED with a Roll of: " + playerDice);
            System.out.println("Bandit DEFENDED with a Roll of: " + banditDice);

            int damage = playerDice - banditDice;

            if (damage > 0) {
                System.out.println(playerName + " Dealt " + damage + " damage to Bandit.");
                banditHP -= damage;

                System.out.println("Bandit: Ouch!");



                System.out.printf("\t\t\t\t\t\t%-15s\t\t\t\t\t\t%-15s", "Bandit Level: " + banditLevel, playerName + " Level: " + playerLevel);
                System.out.printf("\n\t\t\t\t\t\t%-15s\t\t\t\t\t%-15s", "Bandit Health: " + banditHP, playerName + " Health: " + playerHP);
                System.out.printf("\n\t\t\t\t\t\t%-15s\t\t\t\t%-15s", "Bandit Offensive LvL: " + banditOffLevel, playerName + " Offensive LvL: " + playerOffLevel);
                System.out.printf("\n\t\t\t\t\t\t%-15s\t\t\t\t%-15s", "Bandit Defensive LvL: " + banditDefLevel, playerName + " Defensive LvL: " + playerDefLevel);



                if (banditHP <= 0) {
                    System.out.println("\nBandit:  NO! HOW COULD I DIE!?");
                    System.out.println("Bandit was defeated!");
                    banditHP = 20;


                    // xp gold and items gained here

                    start();

                } else if (banditHP > 0) {
                    battleDefend();
                }

            } else {
                System.out.println("Bandit Blocked ALL damage.");
                System.out.println("Bandit: HA! What was that?.");
                battleDefend();
            }
        } else {
            System.out.println("Game Error: Invalid Input Try Again");
            battleAttack();
        }
    }


    public void battleDefend() {

        System.out.println("\nType \"Defend\" to Roll Your Dice for the Attack.");
        System.out.println("Bandit: Prepare Yourself PUNK!");
        Scanner roll = new Scanner(System.in);
        String rollValidate = roll.nextLine();

        //

        if (rollValidate.equalsIgnoreCase("defend")) {


            Random randomRoll = new Random();

            int playerDice = randomRoll.nextInt((playerLevel + 5)) + 1;// +5
            int banditDice = randomRoll.nextInt((banditLevel + 5)) + 1;// +5


            System.out.println("Bandit ATTACKED with a Roll of: " + banditDice);
            System.out.println(playerName + " DEFENDED with a Roll of: " + playerDice);

            int damage = banditDice - playerDice;

            if (damage > 0) {
                System.out.println("Bandit Dealt " + damage + " damage to " + playerName + ".");
                playerHP -= damage;

                System.out.println("Bandit: Ha I know that Hurt!");



                System.out.printf("\t\t\t\t\t\t%-15s\t\t\t\t\t\t%-15s", "Bandit Level: " + banditLevel, playerName + " Level: " + playerLevel);
                System.out.printf("\n\t\t\t\t\t\t%-15s\t\t\t\t\t%-15s", "Bandit Health: " + banditHP, playerName + " Health: " + playerHP);
                System.out.printf("\n\t\t\t\t\t\t%-15s\t\t\t\t%-15s", "Bandit Offensive LvL: " + banditOffLevel, playerName + " Offensive LvL: " + playerOffLevel);
                System.out.printf("\n\t\t\t\t\t\t%-15s\t\t\t\t%-15s", "Bandit Defensive LvL: " + banditDefLevel, playerName + " Defensive LvL: " + playerDefLevel);



                if (playerHP <= 0) {
                    System.out.println("\nBandit:  HAHA IM THE BEST!");
                    System.out.println("You were was defeated!");
                    banditHP = 20;
                    playerHP = 20;

                    System.out.println("Game Over!");
                    // lose items, respawn etc



                } else if (playerHP > 0) {
                    battleAttack();
                }

            } else {
                System.out.println(playerName + " Blocked ALL damage.");
                System.out.println("Bandit: HOW DID I MISS!?");
                battleAttack();
            }
        } else {
            System.out.println("Game Error: Invalid Input Try Again");
            battleDefend();
        }
    }

    public void eventStarter() {
        System.out.println("Special Event Found");
        // gold----items-----health/ health potions
        int eventRandom = (int)(Math.random() * 10) + 1;
        int goldRandom = (int)(Math.random() * 10) + 1;
            if (eventRandom <= 5) {
                System.out.println("You Found Gold!");
                    if (goldRandom >= 6) {
                        gold = gold + 50;
                        System.out.println("You found 50 gold.");
                        System.out.println("Gold Total: " + gold);

                        start();
                    } else {
                        gold = gold + 100;
                        System.out.println("You found 100 gold.");
                        System.out.println("Gold Total: " + gold);

                        start();
                    }

            } else {
                System.out.println("You Found Health!");
                if(playerHP < 20) {
                    playerHP += 20;
                    System.out.println("You are now max on health.");
                    start();
                } else {
                    System.out.println("You are already max on health.");
                    start();
                }
            }
    }

}