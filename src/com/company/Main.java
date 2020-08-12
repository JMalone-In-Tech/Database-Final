package com.company;

import com.mysql.cj.result.SqlDateValueFactory;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Scanner kbd = new Scanner(System.in);
    public static void main(String[] args) {
    run();

    }
    //This method create both the logistics table and the worked_on table
    private static void createviews() throws SQLException{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root", "root", "Baller27.");
            Statement stmt = con.createStatement();

            Boolean ret = stmt.execute("USE companybudget");
            //update queries here
            String insert = "CREATE \n" +
                    "    ALGORITHM = UNDEFINED \n" +
                    "    DEFINER = `root`@`localhost` \n" +
                    "    SQL SECURITY DEFINER\n" +
                    "VIEW `companybudget`.`budget` AS\n" +
                    "    SELECT \n" +
                    "        `wo`.`GID` AS `GID`,\n" +
                    "        `g`.`location` AS `location`,\n" +
                    "        `g`.`month` AS `month`,\n" +
                    "        `g`.`sport` AS `sport`,\n" +
                    "        `g`.`distro` AS `distro`,\n" +
                    "        `g`.`fanCount` AS `fanCount`,\n" +
                    "        `pro`.`CID` AS `CID`,\n" +
                    "        `pro`.`company_name` AS `company_name`,\n" +
                    "        `pro`.`production_cost` AS `production_cost`,\n" +
                    "        `tal`.`talent_id` AS `talent_id`,\n" +
                    "        `tal`.`first_name` AS `first_name`,\n" +
                    "        `tal`.`last_name` AS `last_name`,\n" +
                    "        `tal`.`payrate` AS `payrate`,\n" +
                    "        `log`.`flight` AS `flight`,\n" +
                    "        `log`.`rental` AS `rental`,\n" +
                    "        `log`.`hotel` AS `hotel`\n" +
                    "    FROM\n" +
                    "        ((((`companybudget`.`worked_on` `wo`\n" +
                    "        JOIN `companybudget`.`game` `g` ON ((`g`.`GID` = `wo`.`GID`)))\n" +
                    "        JOIN `companybudget`.`productionco` `pro` ON ((`wo`.`CID` = `pro`.`CID`)))\n" +
                    "        JOIN `companybudget`.`talent` `tal` ON ((`wo`.`talent_id` = `tal`.`talent_id`)))\n" +
                    "        JOIN `companybudget`.`logistics` `log` ON (((`wo`.`GID` = `log`.`GID`)\n" +
                    "            AND (`tal`.`talent_id` = `log`.`talent_id`))))\n" +
                    "    ORDER BY `wo`.`GID`";
            System.out.println(insert);
            stmt.executeUpdate(insert);

            insert = "CREATE \n" +
                    "    ALGORITHM = UNDEFINED \n" +
                    "    DEFINER = `root`@`localhost` \n" +
                    "    SQL SECURITY DEFINER\n" +
                    "VIEW `companybudget`.`productioncost_by_game` AS\n" +
                    "    SELECT \n" +
                    "        `budget`.`GID` AS `GID`,\n" +
                    "        `budget`.`production_cost` AS `production_cost`\n" +
                    "    FROM\n" +
                    "        `companybudget`.`budget`\n" +
                    "    GROUP BY `budget`.`GID`\n" +
                    "    ORDER BY `budget`.`GID`";
            stmt.executeUpdate(insert);
            insert = "CREATE \n" +
                    "    ALGORITHM = UNDEFINED \n" +
                    "    DEFINER = `root`@`localhost` \n" +
                    "    SQL SECURITY DEFINER\n" +
                    "VIEW `companybudget`.`total_cost_per_game` AS\n" +
                    "    SELECT \n" +
                    "        `p`.`GID` AS `GID`,\n" +
                    "        SUM(`budget`.`payrate`) AS `total_pay`,\n" +
                    "        SUM(`budget`.`flight`) AS `total_flights`,\n" +
                    "        SUM(`budget`.`rental`) AS `total_rental`,\n" +
                    "        SUM(`budget`.`hotel`) AS `total_hotel`,\n" +
                    "        `p`.`production_cost` AS `production_cost`,\n" +
                    "        ((((SUM(`budget`.`payrate`) + SUM(`budget`.`flight`)) + SUM(`budget`.`rental`)) + SUM(`budget`.`hotel`)) + `p`.`production_cost`) AS `total_cost`\n" +
                    "    FROM\n" +
                    "        (`companybudget`.`budget`\n" +
                    "        JOIN `companybudget`.`productioncost_by_game` `p` ON ((`budget`.`GID` = `p`.`GID`)))\n" +
                    "    GROUP BY `p`.`GID`\n" +
                    "    ORDER BY `p`.`GID` ";
            stmt.executeUpdate(insert);
            System.out.println(insert);
    }
    private static void createLogistics() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root", "root", "Baller27.");
        Statement stmt = con.createStatement();

        Boolean ret = stmt.execute("USE companybudget");
        for(int i =1; i<=500; i++){
            int talent = (int) (Math.random()*11);
            int cid = (int) (Math.random()*6) +1;

            for(int j=0; j<3; j++){
                double flight = (int) (Math.random()*1500);
                double rental = (int) (Math.random() *500);
                double hotel = (int) (Math.random() *1000);
                talent=(talent+=3)%10;
                if(talent ==0)
                    talent+=1;

                    //update queries here
                    String insert = "Insert into logistics values(";
                    insert += i + "," + talent + "," + flight + "," + rental +"," + hotel +")";

                    stmt.executeUpdate(insert);

                    String insert2 = "Insert into worked_on values(";
                    insert2 += i + "," + talent + "," + cid +")";

                    stmt.executeUpdate(insert2);
                    System.out.println(insert + " - success");

            }
        }
    }
    private static void createtalentData() throws SQLException{
        Talent talent = null;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root", "root", "Baller27.");
        Statement stmt = con.createStatement();
        Boolean ret = stmt.execute("USE companybudget");
        for(int i = 1; i<=10; i++){

            switch (i){
                case 1:
                    talent = new Talent("Ari", "Wolfe", i, 1500.00);
                    break;
                case 2:
                    talent = new Talent("Doug", "Gottlieb", i, 1500.00);
                    break;
                case 3:
                    talent = new Talent("Tim", "Scarborough", i, 1250.00);
                    break;
                case 4:
                    talent = new Talent("Kristen", "Balboni", i, 1250.00);
                    break;
                case 5:
                    talent = new Talent("Max", "Starks", i, 1250.00);
                    break;
                case 6:
                    talent = new Talent("Doug", "Birdsong", i, 1000.00);
                    break;
                case 7 :
                    talent = new Talent("Dave", "Miller",i, 1000.00);
                    break;
                case 8:
                    talent = new Talent("Dave", "Feldman",i , 1250.00);
                    break;
                case 10:
                    talent = new Talent("Amina", "Smith", i, 1500.00);
                    break;
                case 9:
                    talent = new Talent("Dani", "Klupenger", i, 1250.00);
                    break;
            }

            if(talent !=null){

                //update queries here
                String insert = "Insert into talent values(";
                insert += talent.getId() + ",\""+ talent.getFirstName() + "\",\""+ talent.getLastName() + "\" , \"" +
                        + talent.getPayrate() +"\")";
                System.out.println(insert);
                stmt.executeUpdate(insert);
            }
        }
    }
    private static void createProductionData() throws SQLException{
        productionCo company = null;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root", "root", "Baller27.");
        Statement stmt = con.createStatement();
        Boolean ret = stmt.execute("USE companybudget");
        for(int i = 1; i<=6; i++){

            switch (i){
                case 1:
                    company = new productionCo(i, "Niles Media Group", 35000);
                    break;
                case 2:
                    company = new productionCo(i, "Live Mobile Group", 38000);
                    break;
                case 3:
                    company = new productionCo(i, "Ross Mobile Productions", 30000);
                    break;
                case 4:
                    company = new productionCo(i, "ProAngle", 25000);
                    break;
                case 5:
                    company = new productionCo(i, "Raycom", 28000);
                    break;
                case 6:
                    company = new productionCo(i, "Mobile Television Group", 40000);
                    break;
            }
            //Enter insert command here
            if(company !=null){

                    //update queries here
                    String insert = "Insert into productionCo values(";
                    insert += company.getCID() + ",\""+ company.getCompanyName() + "\"," + company.getProductionCost()+")";
                    System.out.println(insert);
                    stmt.executeUpdate(insert);
            }

        }
    }
    private static void createGames() throws SQLException{

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root", "root", "Baller27.");
        Statement stmt = con.createStatement();
        Boolean ret = stmt.execute("USE companybudget");

        String[] locations = {"Nashville, TN", "St. Louis, MO", "Orlando, FL", "Boca Raton, FL", "Salt Lake City, UT", "Los Angeles, LA",
        "Boston, MA", "Laramie, WY", "Murphysboro, TN", "Albuquerque, NM", "San Diego, CA", "Colrado Springs, CO", "Logan, UT", "Las Vegas, NV",
        "Ft Collins, CO", "Bowling Green, KY", "Birmingham, AL", "Denton, TX", "San Antonio, TX" };
        String[] distro = {"OTA", "Digital", "Facebook", "Twitter"};
        String [] sport = {"Basketball", "Football", "Soccer", "Softball"};
        String[] month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        for(int i = 1; i<=500; i++) {
            int sportChoice = (int) (Math.random()*4);
            int monthChoice = (int) (Math.random() *12);
            int fancount = (int) (Math.random()*50000) + 1000;
            int locChoice = (int) (Math.random()*19);
            int distroChoice = (int) (Math.random()*4);

                String insert = "Insert into game values(";
                insert += i + ",\"" + locations[locChoice] + "\",\"" + month[monthChoice] + "\",\"" + sport[sportChoice] +"\",\"" + distro[distroChoice] +"\"," + fancount + ")";
                stmt.executeUpdate(insert);
                System.out.println(insert);
        }
    }
    public static void run(){
        System.out.print("If you would you like to create a custom query, please enter C. \n" +
                "If you would like to run a predefined query, enter Q. \n" +
                "If you would like to create the data set, please enter X.\n" +
                "To quit, please enter Z\n" +
                "Choice: ");
        String opt = kbd.nextLine();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root", "root", "Baller27.")) {
            Statement stmt = con.createStatement();
            Boolean ret = stmt.execute("USE companybudget");

        if(opt.equalsIgnoreCase("q")) {

                System.out.println("Here's a list of predefined queries:\n" +
                        "1.\tList the talent by first name who broadcasted a given game and what was the average cost of flights?\n" +
                        "2.\tWhat company did a given game and what was the total cost of the production, including talent rates and production rates\n" +
                        "3.\tOf the talent, who cost the most for the season?\n" +
                        "4.\tWhich production company made the cost the most money this season\n" +
                        "5.\tWhich location did we visit the most and how much did the logistics cost for that location?\n" +
                        "6.\tWhich location did we visit the least and how much did logistics cost for that location\n" +
                        "7.\tWhich distribution platform hosted the most games and what was the average cost for all games?\n" +
                        "8.\tWhich distribution platform hosted the least games and what was the average cost?\n" +
                        "9.\tWhich broadcast had the most fans who were the talents and production company\n" +
                        "10.\tWhich location had the cheapest flight costs and which had the most?\n");
            System.out.println("\n" + "Please enter the number that corresponds with the query you would like to run");
            int selection = kbd.nextInt();
            kbd.nextLine();
            String queryChoice = "";
            switch (selection) {
                case 1:
                    System.out.println("List the talent by first name who broadcasted a given game and what was the average cost of flights?\n" +
                            "Please enter a GID to get a list of talent and the average flight cost");
                    int GID = kbd.nextInt();
                    kbd.nextLine();
                    queryChoice = "select first_name\n" +
                            "from budget\n" +
                            "where GID = " + GID;

                        System.out.println("Query----------: \n" + queryChoice + "\n");
                        System.out.println("Response-----------:");
                        try {
                            ResultSet rs = stmt.executeQuery(queryChoice);
                            ResultSetMetaData rsmd = rs.getMetaData();
                            int columnsNumber = rsmd.getColumnCount();
                            while (rs.next()) {
                                for (int i = 1; i <= columnsNumber; i++) {
                                    if (i > 1) System.out.print(",  ");
                                    String columnValue = rs.getString(i);
                                    System.out.print(rsmd.getColumnName(i) + " : " + columnValue);
                                }
                                System.out.println("");
                            }
                            System.out.println();
                        }catch(SQLException sqle){
                                System.out.println(sqle);
                        }
                        queryChoice = " select avg(flight) as 'Average flight cost'\n" +
                                " from budget\n" +
                                " where GID = " + GID;
                    break;

                case 2:
                    System.out.println("What company did a given game and what was the total cost of the production, including talent rates and production rates\n Please enter a game ID");
                    int game = kbd.nextInt();
                    kbd.nextLine();
                    queryChoice = "select b.GID, company_name, b.production_cost, tc.total_cost \n" +
                            "from budget as b\n" +
                            "join total_cost_per_game as tc\n" +
                            "\tusing (GID)\n" +
                            "where b.GID = " + game + "\n" +
                            "group by b.GID ";

                    System.out.println("Query ----------\n" + queryChoice + "\n");
                    System.out.println("Response ---------");
                    try {
                        ResultSet rs = stmt.executeQuery(queryChoice);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int columnsNumber = rsmd.getColumnCount();
                        while (rs.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                if (i > 1) System.out.print(",  ");
                                String columnValue = rs.getString(i);
                                System.out.print(rsmd.getColumnName(i) + " : " + columnValue);
                            }
                            System.out.println("");
                        }
                        System.out.println();
                    }catch(SQLException sqle){
                        System.out.println(sqle);
                    }

                    queryChoice = "select first_name, last_name, payrate\n" +
                            "from budget as b\n" +
                            "join total_cost_per_game as tc\n" +
                            "\tusing (GID)\n" +
                            "where b.GID = ";
                    queryChoice += game;
                    break;

                case 3:
                    System.out.println("Of the talent, who cost the most for the season?");
                    queryChoice = "select first_name, last_name, sum(payrate) + sum(flight) + sum(rental) + sum(hotel) as sum\n" +
                            "from budget\n" +
                            "group by talent_id\n" +
                            "order by sum desc\n" +
                            "limit 1";
                    break;
                case 4:
                    System.out.println("Which production company made the cost the most money this season");
                    queryChoice = "select p.CID, p.company_name, sum(tc.production_cost) as total_payment \n" +
                        "from total_cost_per_game tc\n" +
                        "join productionco p\n" +
                        "\tusing(CID)\n" +
                        "group by p.CID\n" +
                        "order by total_payment desc\n" +
                        "limit 1";
                    break;
//q5
                case 5:
                    System.out.println("Which location did we visit the most and how much did the logistics cost for that location?");
                    queryChoice = "select location, count(location) as countLoc, sum(production_cost+flight+payrate+rental+hotel) as cost\n" +
                            "from budget\n" +
                            "group by location\n" +
                            "order by countLoc desc\n" +
                            "limit 1";
                    break;
//q6
                case 6:
                    System.out.println("Which location did we visit the least and how much did logistics cost for that location");
                    queryChoice = "select location, count(location) as countLoc, sum(production_cost+flight+payrate+rental+hotel) as cost\n" +
                            "from budget\n" +
                            "group by location\n" +
                            "order by countLoc\n" +
                            "limit 1";
                    break;

//q7
                case 7:
                    System.out.println("Which distribution platform hosted the most games and what was the average cost for all games?");
                    queryChoice = "select \n" +
                        "\tdistro as 'Distribution outlet', \n" +
                        "    count(distro) Total, \n" +
                        "    sum(production_cost + payrate + flight+hotel+rental) as 'production totals', \n" +
                        "    avg(production_cost + payrate + flight+hotel+rental) as 'Average production totals'\n" +
                        "from budget\n" +
                        "group by distro\n" +
                        "order by total desc\n" +
                        "limit 1\n";
                    break;
//q8
                case 8:
                    System.out.println("Which distribution platform hosted the least games and what was the average cost?");
                    queryChoice = "select \n" +
                        "\tdistro as 'Distribution outlet', \n" +
                        "    count(distro) Total, \n" +
                        "    sum(production_cost + payrate + flight+hotel+rental) as 'production totals', \n" +
                        "    avg(production_cost + payrate + flight+hotel+rental) as 'Average production totals'\n" +
                        "from budget\n" +
                        "group by distro\n" +
                        "order by total \n" +
                        "limit 1\n";
                    break;
//q9
                case 9:
                    System.out.println("Which broadcast had the most fans who were the talents and production company");
                    queryChoice = "select GID, location, sport, fanCount, CID, company_name, talent_id, first_name, last_name\n" +
                            "from budget \n" +
                            "where fanCount =\n" +
                            "(select max(fanCount)\n" +
                            "\tfrom budget\n" +
                            ")";
                    break;
//q10
                case 10:
                    System.out.println("Which location had the cheapest flight costs and which had the most?");
                    queryChoice = "select GID, location, count(location) as times_visited, sum(flight) as total_cost, avg(flight) as average\n" +
                        "    from budget\n" +
                        "    group by location\n" +
                        "    order by average desc\n" +
                            "limit 1";
                    System.out.println("Query ----------\n" + queryChoice + "\n");
                    System.out.println("Response ---------");
                    try {
                        ResultSet rs = stmt.executeQuery(queryChoice);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int columnsNumber = rsmd.getColumnCount();
                        while (rs.next()) {
                            for (int i = 1; i <= columnsNumber; i++) {
                                if (i > 1) System.out.print(",  ");
                                String columnValue = rs.getString(i);
                                System.out.print(rsmd.getColumnName(i) + " : " + columnValue);
                            }
                            System.out.println("");
                        }
                        System.out.println();
                    }catch(SQLException sqle){
                        System.out.println(sqle);
                    }

                    queryChoice = "select GID, location, count(location) as times_visited, sum(flight) as total_cost, avg(flight) as average\n" +
                            "    from budget\n" +
                            "    group by location\n" +
                            "    order by average\n" +
                            "limit 1";
                    break;

                default:
                    System.out.println("Sorry, didn't understand that option");
                    run();

            }
            System.out.println("Query ----------: \n" + queryChoice + "\n");
            System.out.println("Response -----------:");
            ResultSet rs = stmt.executeQuery(queryChoice);
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                while (rs.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1) System.out.print(",  ");
                        String columnValue = rs.getString(i);
                        System.out.print(rsmd.getColumnName(i) + " : " + columnValue);
                    }
                    System.out.println("");

                }
                System.out.println();
                run();

        }
        else if(opt.equalsIgnoreCase("c")) {
            customQuery();
            run();
        }
        else if(opt.equalsIgnoreCase("x")){
            createtalentData();
            createProductionData();
            createGames();
            createLogistics();
            createviews();
            run();
        }
        else if(opt.equalsIgnoreCase("z")){
            System.out.println("Goodbye!");
            System.exit(1);
        }
        else{
            System.out.println("The value entered wasn't recognized. Would you like to try again(y/n)");
            String choice = kbd.nextLine();
            if(choice.equalsIgnoreCase("y")) {
                System.out.println();
                run();
            }
            else System.out.println("Goodbye");
        }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Press 1 to try again. Press any other key to quit");
            int option = 0;
            //kbd.nextLine();
            String in = kbd.nextLine();

            try {
                option = Integer.parseInt(in);
            } catch (NumberFormatException numForE) {
                System.out.println("Goodbye");
                System.exit(-1);
            } catch (Exception ex) {
                System.out.println("Goodbye");
                System.exit(-1);
            }
            if (option == 1)
                run();
            else System.out.println("Goodbye");

        }
    }
    private static void customQuery() throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root", "root", "Baller27.");
            Statement stmt = con.createStatement();

            Boolean ret = stmt.execute("USE companybudget");


            System.out.print("Please enter your query: ");
            String query = kbd.nextLine();

            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i) + " : " + columnValue);
                }
                System.out.println("");
            }

    }
}
