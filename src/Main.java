import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class Main {

    public static String playerName;
    public static int gold;
    public static int bank;
    public static long currentSteps = 0;

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

            public void run() {

                currentSteps += 1;

                System.out.print("\rCurrent Steps: " + currentSteps);

                if(currentSteps % 5 == 0) {
                    playerEXP += 3;

                }

                Random randomNum = new Random();

                int random = randomNum.nextInt(10) + 1;

                if (currentSteps % 2 == 1 && currentSteps > 5) {// even or above 5

//                    if (currentSteps == 1) {// quick tester
                    if (random % 4 == 0) {
//                    if (true) { //quick tester
                        steps.cancel();
                        Main attackChance = new Main();
                        attackChance.battleStart();
                    }
                } else if (currentSteps % 2 == 0 && currentSteps > 5) { //odd or above 5
                    if (random % 3 == 0 && currentSteps > 2) {
                      steps.cancel();
                      Main eventChance = new Main();
                        eventChance.eventStarter();
                    }
                }
            }
        }, 0, 1000);
    }


    public static void goHome() {
        Timer steps = new Timer();
        steps.schedule(new TimerTask() {

            public void run() {
                currentSteps -= 1;

                System.out.print("\rCurrent Steps: " + currentSteps);

                Random randomNum = new Random();

                int random = randomNum.nextInt(10) + 1;
//                System.out.println(random); // random number tester
//              return random;


                if (currentSteps == 0) {
                    steps.cancel();
                    System.out.println("\nYou have returned to Town.");
                    // new method call here to navigate village and talk to mayor
                    Main home = new Main();
                    home.mayorTalk();
                }
                else if (currentSteps % 2 == 1 && currentSteps > 5) {// even or above 5
//                    if (currentSteps == 1) {// quick tester
                    if (random % 4 == 0) {
//                    if (true) { //quick tester
                        steps.cancel();
                        Main attackChance = new Main();
                        attackChance.battleStart();
                    }
                } else if (currentSteps % 2 == 0 && currentSteps > 5) { //odd or above 5
                    if (random % 3 == 0 && currentSteps > 2) {
                        steps.cancel();
                        Main eventChance = new Main();
                        eventChance.eventStarter();
                    }
                }
            }
        }, 0, 1000);
    }
    int banditHP = 10;
    int banditLevel = 1;
    int banditOffLevel = 1;
    int banditDefLevel = 1;


    public static int playerEXP = 0;
    public void levelUp() {
        if (playerEXP >= 100) {
            playerLevel += 1;
            System.out.println("You Leveled Up.");
            System.out.println("You are Level " + playerLevel + "!");
            playerEXP = 0;
            playerHP = 20;
        }
    }
    public static int playerHP = 20;
    public static int playerLevel = 3;
    int playerOffLevel = 1;
    int playerDefLevel = 1;
    public void battleStart() {
        System.out.println("\nBandit: Lets Battle scumbag.");

        System.out.printf("\t\t\t\t\t\t%-15s\t\t\t\t\t\t%-15s", "Bandit Level: " + banditLevel, playerName + " Level: " + playerLevel);
        System.out.printf("\n\t\t\t\t\t\t%-15s\t\t\t\t\t%-15s", "Bandit Health: " + banditHP, playerName + " Health: " + playerHP);
        System.out.printf("\n\t\t\t\t\t\t%-15s\t\t\t\t%-15s", "Bandit Offensive LvL: " + banditOffLevel, playerName + " Offensive LvL: " + playerOffLevel);
        System.out.printf("\n\t\t\t\t\t\t%-15s\t\t\t\t%-15s", "Bandit Defensive LvL: " + banditDefLevel, playerName + " Defensive LvL: " + playerDefLevel);

        battleAttack();
        }



    public void battleAttack() {
        System.out.println("\nType \"Attack\" to Roll Your Dice for the Attack.\nType in \"run\" to try to run away.");
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
                    System.out.println("You Gained 100XP!");
                    playerEXP += 100;
                    banditHP = 20;
                    levelUp();
                    // xp gold and items gained here
                    remote();

                } else if (banditHP > 0) {
                    battleDefend();
                }

            } else {
                System.out.println("Bandit Blocked ALL damage.");
                System.out.println("Bandit: HA! What was that?.");
                battleDefend();
            }
        } else if (rollValidate.equalsIgnoreCase("run")) {
            Random randomRun = new Random();
            int runAway = randomRun.nextInt(4) + 1;// +5
            System.out.println(runAway);
            if (runAway < 2) {
                System.out.println("You ran away safely.");
                remote();
            } else {
                System.out.println("where do you think your going?");
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



        System.out.println("\nSpecial Event Found");
        // gold----items-----health/ health potions
        int eventRandom = (int)(Math.random() * 10) + 1;
        int goldRandom = (int)(Math.random() * 10) + 1;
            if (eventRandom <= 5) {
                System.out.println("You Found Gold!");
                    if (goldRandom >= 6) {
                        gold = gold + 50;
                        System.out.println("You found 50 gold.");
                        System.out.println("Gold Total: " + gold);


                        remote();

                    } else {
                        gold = gold + 100;
                        System.out.println("You found 100 gold.");
                        System.out.println("Gold Total: " + gold);

                        remote();
                    }

            } else {
                System.out.println("You Found Health!");
                if(playerHP < 20) {
                    playerHP += 20;
                    System.out.println("You are now max on health.");

                    remote();

                } else {
                    System.out.println("You are already max on health.");
                    remote();

                }
            }
    }



    public void remote() {
        System.out.println("Enter in \"c\" to continue your journey. \nEnter in \"home\" to return the the town.");

        Scanner remote = new Scanner(System.in);
        String playerResume = remote.nextLine();
        if (playerResume.equalsIgnoreCase("c")) {
            start();
        } else if (playerResume.equals("home")) {
            System.out.println("You are returning Home.");
            goHome();
        } else {
            remote();
        }
    }


    public void mayorTalk() {
        System.out.println("Mayor: Welcome Back " + playerName + ", I hope Your adventure was good.");
        System.out.println("Mayor: You came back with " + gold + "gold.");
        System.out.println("Mayor: Your Bank Total was: " + bank);
        bank += gold;
        gold = 0;
        System.out.println("Mayor: Your Bank Total is now: " + bank);

        System.out.println("Mayor: Now I gotta go handle mayor stuff, Enjoy your time in town and get back with me if you want to leave the gates again.");

        System.out.println("1. Mayor\n2. Blacksmith\n3. Armorer\n4. Wizard Shop\n5. Merchant\n6.Town Square");


        Scanner townScan = new Scanner(System.in);
        int townMove = townScan.nextInt();
        if(townMove == 1) {
            System.out.println("Mayor: Oh you scared me!");
            greetings();
        } else if (townMove == 2) {
            System.out.println("Blacksmith");
        } else if (townMove == 3) {
            System.out.println("Armorer");
        } else if (townMove == 4) {
            System.out.println("Wizard Shop");
        } else if (townMove == 5) {
            System.out.println("Merchant");
        } else if (townMove == 6) {
            System.out.println("Town Square");
        }

    }
}