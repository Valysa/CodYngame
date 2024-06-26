package M;
import launcher.Main;

import java.sql.*;


/**
 * The Bdd class provides methods to interact with the database.
 */

public class Bdd {

    static String port ;
    static String user;
    static String password;

    /**
     * Connects to the database.
     * @return Connection object
     */

    // Connect to the database

    public static Connection start (){

        Connection con = null;

        try{
            con =  DriverManager.getConnection("jdbc:mysql://localhost:" + port + "/",user,password);
            Statement stmt = con.createStatement();

            String sql = "CREATE DATABASE IF NOT EXISTS Exercise";
            stmt.executeUpdate(sql);

            stmt.close();
            end(con);

            con =  DriverManager.getConnection("jdbc:mysql://localhost:" + port + "/Exercise",user,password);

        } catch (SQLException e) {
            System.out.println("Error during the connexion to the database : " + e.getMessage());
        }

        return con ;
    }


    /**
     * Disconnects from the database.
     * @param con Connection object
     */

    // Disconnect to the database

    public static void end(Connection con){

        try {
            con.close();

        } catch (SQLException e) {
            System.out.println("Error during the disconnexion to the database : " + e.getMessage());
        }

    }

    /**
     * Sets the connection details for the database.
     * @param p Port
     * @param u User
     * @param pa Password
     */

    public static void idBdd(String p,String u,String pa){
        port = p;
        user = u;
        password = pa;
    }


    /**
     * Creates the Exercise table in the database.
     */

    // Create the table in the database

    public static void create() {


        try {

            Connection con = start();
            Statement stmt = con.createStatement();


            String sql =
                      "CREATE TABLE IF NOT EXISTS Question (\n"
                    + "Id INT AUTO_INCREMENT PRIMARY KEY,\n"
                    + "ExoType TINYINT,\n"
                    + "ExoName VARCHAR(255),\n"
                    + "Instruction TEXT,\n"
                    + "SolutionLang TINYINT,\n"
                    + "SolutionCode TEXT,\n"
                    + "GeneratorCode TEXT,\n"
                    + "MainCode TEXT,\n"
                    + "NbTry INT,\n"
                    + "NbSucess INT,\n"
                    + "NbSessionSucess INT,\n"
                    + "NbFirstTry INT\n"
                    + ")AUTO_INCREMENT = 1;\n";

            stmt.executeUpdate(sql);

            stmt.close();
            end(con);

        } catch (SQLException e) {
            System.out.println("Error during the creation of the table : " + e.getMessage());
        }

        basicExo();

    }

    /**
     * This method adds a basic exercise to the database.
     */

    public static void basicExo(){

        addEx(0,"[Stdin/Stdout] Exercise 1 : Intro Exercise","You will be given in entry a list of 10 integer and we ask you to give back their values multiplied by two",2,
                "import java.util.Scanner;\n" +
                "\n" +
                "public class soluceExo {\n" +
                "    public static void main(String[] args) {\n" +
                "        Scanner scanner = new Scanner(System.in);\n" +
                "        \n" +
                "        // Boucle pour lire 10 entiers et afficher leur double\n" +
                "        for (int i = 0; i < 10; i++) {\n" +
                "            int entry = scanner.nextInt();\n" +
                "            int exit = entry * 2;\n" +
                "            System.out.println(exit);\n" +
                "        }\n" +
                "        \n" +
                "        scanner.close();\n" +
                "    }\n" +
                "}\n",
                "import java.util.Random;\n" +
                        "\n" +
                        "public class genExo {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        if (args.length != 1) {\n" +
                        "            System.out.println(\"Please provide a mode (1: normal, 2: random, 3: error)\");\n" +
                        "            return;\n" +
                        "        }\n" +
                        "\n" +
                        "        int mode = Integer.parseInt(args[0]);\n" +
                        "        Random random = new Random();\n" +
                        "\n" +
                        "        switch (mode) {\n" +
                        "            case 1:\n" +
                        "                // Normal mode: predefined values\n" +
                        "                int[] normalValues = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};\n" +
                        "                for (int value : normalValues) {\n" +
                        "                    System.out.println(value);\n" +
                        "                }\n" +
                        "                break;\n" +
                        "\n" +
                        "            case 2:\n" +
                        "                // Random mode: generate random values\n" +
                        "                for (int i = 0; i < 10; i++) {\n" +
                        "                    int randomInt = random.nextInt(100); // Génère un entier aléatoire entre 0 et 99\n" +
                        "                    System.out.println(randomInt);\n" +
                        "                }\n" +
                        "                break;\n" +
                        "\n" +
                        "            case 3:\n" +
                        "                // Error mode: generate erroneous values\n" +
                        "                int[] errorValues = {-1, 101, 200, -50, 0, 99, 100, 150, -100, 300};\n" +
                        "                for (int value : errorValues) {\n" +
                        "                    System.out.println(value);\n" +
                        "                }\n" +
                        "                break;\n" +
                        "\n" +
                        "            default:\n" +
                        "                System.out.println(\"Invalid mode. Please provide a mode (1: normal, 2: random, 3: error)\");\n" +
                        "        }\n" +
                        "    }\n" +
                        "}\n","");
        addEx(0,"[Stdin/Stdout] Exercise 2 : Basic Exo","You will be given in entry a list of 10 integer and we ask you to give back their values multiplied by ten",0,"int main(){\n\tint entry;\n\tint exit;\n\tfor(int i=0; i<10; i++){\n\t\tscanf(\"%d\",&entry);\n\t\texit = entry*10;\n\t\tprintf(\"%d\\n\",exit);\n\t}\n\treturn 0;\n}","","");
        addEx(1,"[Include] Exercise 3 : Sum of an array","You will be creating a function that calculates the sum of an array.\nIt returns 0 if the array is empty.",0,"int array_sum1(int array[], int size){\n\tint sum = 0;\n\tfor(int i = 0; i < size; i++){\n\t\tsum += array[i];\n\t}\n\treturn sum;\n}","int array_sum(int array[], int size){\n\n}","#include <stdio.h>\n#include <stdlib.h>\n#include <time.h>\n#include \"soluceExo.c\"\n#include \"userExo.c\"\n\nint run_test(int array[], int size){\n\tint result1 = array_sum1(array, size);\n\tint result2 = array_sum(array, size);\n\tputs(\"\");\n\tif(result1 == result2){\n\t\tprintf(\"Test passed\");\n\t\treturn 0;\n\t}\n\telse{\n\t\tprintf(\"Test failed\");\n\t\treturn 1;\n\t}\n}\n\nint* generateRandomArray(int size, int minValue, int maxValue){\n\tint* array = malloc(size*sizeof(int));\n\tif(array == NULL){\n\t\texit(2);\n\t}\n\tfor(int i = 0; i < size; i++){\n\t\tarray[i] = rand()%(maxValue - minValue + 1) + minValue;\n\t}\n\treturn array; \n}\n\nint main(){\n\tsrand(time(NULL));\n\tint minSize = 5;\n\tint maxSize = 95;\n\tint minValue = -1000;\n\tint maxValue = 2001;\n\tint failed = 0;\n\tint size1 = rand()%(maxSize - minSize + 1) + minSize;\n\tint* array1 = generateRandomArray(size1, minValue, maxValue);\n\tfailed |= run_test(array1,size1);\n\tint size2 = rand()%(maxSize - minSize + 1) + minSize;\n\tint* array2 = generateRandomArray(size2, minValue, maxValue);\n\tfailed |= run_test(array2,size2);\n\tint size3 = rand()%(maxSize - minSize + 1) + minSize;\n\tint* array3 = generateRandomArray(size3, 0, 0);\n\tfailed |= run_test(array3,size3);\n\tint size4 = 0;\n\tint* array4 = generateRandomArray(size4, minValue, maxValue);\n\tfailed |= run_test(array4,size4);\n\treturn failed;\n}");
        addEx(1,"[Include] Exercise 4 : Sum of an array","You will be creating a function that calculates the sum of an array.\nIt returns 0 if the array is empty.",1,"def array_sum1(array, size):\n\tsum = 0\n\tfor i in range(size):\n\t\tsum += array[i]\n\treturn sum","def array_sum(array, size):\n\t","import sys\nimport random\nimport soluceExo\nimport userExo\n\ndef run_test(array, size):\n\tresult1 = soluceExo.array_sum1(array, size)\n\tresult2 = userExo.array_sum(array, size)\n\tif(result1 == result2):\n\t\tprint(\"Test passed\")\n\t\treturn 0\n\telse:\n\t\tprint(\"Test failed\")\n\t\treturn 1\n\ndef main():\n\tfailed = 0\n\tminSize = 5\n\tmaxSize = 100\n\tminValue = -1000\n\tmaxValue = 1000\n\tarraySize1 = random.randint(minSize, maxSize)\n\tarray1 = [random.randint(minValue, maxValue) for _ in range(arraySize1)]\n\tfailed |= run_test(array1,arraySize1)\n\tarraySize2 = random.randint(minSize, maxSize)\n\tarray2 = [random.randint(minValue, maxValue) for _ in range(arraySize2)]\n\tfailed |= run_test(array2,arraySize2)\n\tarraySize3 = random.randint(minSize, maxSize)\n\tarray3 = [0 for _ in range(arraySize3)]\n\tfailed |= run_test(array3,arraySize3)\n\tarray4 = []\n\tfailed |= run_test(array4,0)\n\treturn failed\n\nsys.exit(main())");
        addEx(1,"[Include] Exercise 5 : Sum of an array","You will be creating a function that calculates the sum of an array.\nIt returns 0 if the array is empty.",3, "<?php\n\tfunction arraySum1($array, $size){\n\t\t$sum = 0;\n\t\tfor($i = 0; $i < $size; $i++){\n\t\t\t$sum += $array[$i];\n\t\t}\n\t\treturn $sum;\n\t}\n?>", "function arraySum($array, $size){\n\n}", "<?php\n\tinclude 'soluceExo.php';\n\tinclude 'userExo.php';\n\n\tfunction generateRandomArray($minSize, $maxSize, $minValue, $maxValue){\n\t\t$size = rand($minSize, $maxSize);\n\t\tfor($i = 0; $i < $size; $i++){\n\t\t\t$array[$i] = rand($minValue, $maxValue);\n\t\t}\n\t\treturn $array;\n\t}\n\n\tfunction run_test($array, $size){\n\t\t$result1 = arraySum1($array, $size);\n\t\t$result2 = arraySum($array, $size);\n\t\tif($result1 == $result2){\n\t\t\techo \"Test passed\";\n\t\t\treturn 0;\n\t\t}\n\t\telse{\n\t\t\techo \"Test failed\";\n\t\t\treturn 1;\n\t\t}\n\t}\n\n\tfunction main(){\n\t\t$failed = 0;\n\t\t$minSize = 5;\n\t\t$maxSize = 100;\n\t\t$minValue = -1000;\n\t\t$maxValue = 1000;\n\t\t$array1 = generateRandomArray($minSize, $maxSize, $minValue, $maxValue);\n\t\t$failed |= run_test($array1,count($array1));\n\t\t$array2 = generateRandomArray($minSize, $maxSize, $minValue, $maxValue);\n\t\t$failed |= run_test($array2,count($array2));\n\t\t$array3 = generateRandomArray($minSize, $maxSize, 0, 0);\n\t\t$failed |= run_test($array3,count($array3));\n\t\t$array4 = [];\n\t\t$failed |= run_test($array4,count($array4));\n\t\treturn $failed;\n\t}\n\n\texit(main());\n?>");
        addEx(1,"[Include] Exercise 6 : Average of an array","You will be creating a function that calculates the average of an array.\nIt returns null if the array is empty.", 5, "export function array_moy1(array, size){\n\tif(size === 0){\n\t\treturn null;\n\t}\n\tlet sum = 0;\n\tfor(let i = 0; i < size; i++){\n\t\tsum = sum + array[i];\n\t}\n\treturn sum/size;\n}","function array_moy(array, size){\n\n}","import { array_moy } from \"./userExo.mjs\";\nimport { array_moy1 } from \"./soluceExo.mjs\";\n\nfunction generateRandomArray(minSize, maxSize, minValue, maxValue){\n\tconst size = Math.floor(Math.random() * (maxSize - minSize + 1)) + minSize;\n\tconst array = [];\n\tfor(let i = 0; i < size; i++){\n\t\tconst value = Math.floor(Math.random() * (maxValue - minValue + 1)) + minValue;\n\t\tarray.push(value);\n\t}\n\treturn array;\n}\n\nfunction run_test(array, size){\n\tlet result1 = array_moy1(array, size);\n\tlet result2 = array_moy(array, size);\n\tif(result1 === result2){\n\t\tconsole.log(\"Test passed\");\n\t\treturn 0;\n\t}\n\telse{\n\t\tconsole.log(\"Test failed\");\n\t\treturn 1;\n\t}\n}\n\nfunction main(){\n\tlet failed = 0;\n\tconst minSize = 5;\n\tconst maxSize = 100;\n\tconst minValue = -1000;\n\tconst maxValue = 1000;\n\tconst array1 = generateRandomArray(minSize,maxSize,minValue,maxValue);\n\tfailed |= run_test(array1,array1.length);\n\tconst array2 = generateRandomArray(minSize,maxSize,minValue,maxValue);\n\tfailed |= run_test(array2,array2.length);\n\tconst array3 = generateRandomArray(minSize,maxSize,0,0);\n\tfailed |= run_test(array3,array3.length);\n\tconst array4 = [];\n\tfailed |= run_test(array4,array4.length);\n\treturn failed;\n}\n\nprocess.exit(main());");
        addEx(1,"[Include] Exercise 7 : Average of an array","You will be creating a function that calculates the average of an array.\nIt returns 0 if the array is empty.",2, "package Exo7;\n\npublic class soluceExo{\n\tpublic float array_moy(int[] array, int size){\n\t\tif(size == 0){\n\t\t\treturn 0;\n\t\t}\n\t\tfloat sum = 0;\n\t\tfor (int i : array) {\n\t\t\tsum += i;\n\t\t}\n\t\treturn sum/size;\n\t}\n}\n","public float array_moy(int[] array, int size){\n","package Exo7;\n\nimport java.util.Random;\n\npublic class mainExo {\n\n\tpublic static int run_test(soluceExo array1, userExo array2, int[] array, int size){\n\t\tfloat result1 = array1.array_moy(array, size);\n\t\tfloat result2 = array2.array_moy(array, size);\n\t\tif(result1 == result2){\n\t\t\tSystem.out.println(\"Test passed\");\n\t\t\treturn 0;\n\t\t}\n\t\telse{\n\t\t\tSystem.out.println(\"Test failed\");\n\t\t\treturn 1;\n\t\t}\n\t}\n\n\tpublic static int[] generateRandomArray(int minSize, int maxSize, int minValue, int maxValue){\n\t\tRandom random = new Random();\n\t\tint size = random.nextInt((maxSize - minSize) + 1) + minSize;\n\t\tint[] array = new int[size];\n\t\tfor(int i = 0; i < size; i++){\n\t\t\tarray[i] = random.nextInt((maxValue - minValue) + 1) + minValue;\n\t\t}\n\t\treturn array;\n\t}\n\tpublic static void main(String[] args){\n\t\tint failed  = 0;\n\t\tint minSize = 5;\n\t\tint maxSize = 100;\n\t\tint minValue = -1000;\n\t\tint maxValue = 1000;\n\t\tint[] randomArray1 = generateRandomArray(minSize, maxSize, minValue, maxValue);\n\t\tsoluceExo arrays1 = new soluceExo();\n\t\tuserExo array1 = new userExo();\n\t\tfailed |= run_test(arrays1, array1, randomArray1, randomArray1.length);\n\t\tint[] randomArray2 = generateRandomArray(minSize, maxSize, minValue, maxValue);\n\t\tsoluceExo arrays2 = new soluceExo();\n\t\tuserExo array2 = new userExo();\n\t\tfailed |= run_test(arrays2, array2, randomArray2, randomArray2.length);\n\t\tint[] randomArray3 = generateRandomArray(minSize, maxSize, 0, 0);\n\t\tsoluceExo arrays3 = new soluceExo();\n\t\tuserExo array3 = new userExo();\n\t\tfailed |= run_test(arrays3, array3, randomArray3, randomArray3.length);\n\t\tint[] randomArray4 = new int[0];\n\t\tsoluceExo arrays4 = new soluceExo();\n\t\tuserExo array4 = new userExo();\n\t\tfailed |= run_test(arrays4, array4, randomArray4, randomArray4.length);\n\t\tSystem.exit(failed);\n\t}\n}");
        addEx(1,"[Include] Exercise 8 : Sorting by insertion","You will be creating a function that sorts the array by insertion.",0,"void insertionSort1(int array[], int size){\n\tint i,key,j;\n\tfor(int i = 1; i < size; i++){\n\t\tkey = array[i];\n\t\tj = i - 1;\n\t\twhile(j >= 0 && array[j] > key){\n\t\t\tarray[j + 1] = array[j];\n\t\t\tj = j - 1; \n\t\t}\n\t\tarray[j + 1] = key;\n\t}\n}","void insertionSort(int array[], int size){\n\t","#include <stdio.h>\n#include <stdlib.h>\n#include <time.h>\n#include \"soluceExo.c\"\n#include \"userExo.c\"\n\nint run_test(int array1[], int array2[], int size){\n\tinsertionSort1(array1, size);\n\tinsertionSort(array2, size);\n\tputs(\"\");\n\tfor(int i = 0; i < size; i++){\n\t\tif(array1[i] != array2[i]){\n\t\t\tprintf(\"Test failed\");\n\t\t\treturn 1;\n\t\t}\n\t}\n\tprintf(\"Test passed\");\n\treturn 0;\n}\n\nint* generateRandomArray(int size, int minValue, int maxValue){\n\tint* array = malloc(size*sizeof(int));\n\tif(array == NULL){\n\t\texit(2);\n\t}\n\tfor(int i = 0; i < size; i++){\n\t\tarray[i] = rand()%(maxValue - minValue + 1) + minValue;\n\t}\n\treturn array; \n}\n\nvoid arrayCopy(int array[], int copy[], int size){\n\tfor(int i = 0; i < size; i++){\n\t\tcopy[i] = array[i];\n\t}\n}\n\nint main(){\n\tsrand(time(NULL));\n\tint minSize = 5;\n\tint maxSize = 95;\n\tint minValue = -1000;\n\tint maxValue = 2001;\n\tint failed = 0;\n\tint size1 = rand()%(maxSize - minSize + 1) + minSize;\n\tint* array1 = generateRandomArray(size1, minValue, maxValue);\n\tint* arrayCopy1 = malloc(size1*sizeof(int));\n\tarrayCopy(array1,arrayCopy1,size1);\n\tfailed |= run_test(array1,arrayCopy1,size1);\n\tfree(array1);\n\tfree(arrayCopy1);\n\tint size2 = rand()%(maxSize - minSize + 1) + minSize;\n\tint* array2 = generateRandomArray(size2, minValue, maxValue);\n\tint* arrayCopy2 = malloc(size2*sizeof(int));\n\tarrayCopy(array2,arrayCopy2,size2);\n\tfailed |= run_test(array2,arrayCopy2,size2);\n\tfree(array2);\n\tfree(arrayCopy2);\n\tint size3 = rand()%(maxSize - minSize + 1) + minSize;\n\tint* array3 = generateRandomArray(size3, 0, 0);\n\tint* arrayCopy3 = malloc(size3*sizeof(int));\n\tarrayCopy(array3,arrayCopy3,size3);\n\tfailed |= run_test(array3,arrayCopy3,size3);\n\tfree(array3);\n\tfree(arrayCopy3);\n\tint size4 = 0;\n\tint* array4 = generateRandomArray(size4, minValue, maxValue);\n\tint* arrayCopy4 = malloc(size4*sizeof(int));\n\tarrayCopy(array4,arrayCopy4,size4);\n\tfailed |= run_test(array4,arrayCopy4,size4);\n\tfree(array4);\n\tfree(arrayCopy4);\n\treturn failed;\n}");
        addEx(1,"[Include] Exercise 9 : String and palindrome","You will be creating a function that checks whether a string is a palindrome.\nIt returns true if the string is a palindrome and false otherwise.",2,"package Exo9;\n\npublic class soluceExo {\n\n\tpublic boolean isPalindrome(String string){\n\t\tint left = 0;\n\t\tint right = string.length() - 1;\n\t\twhile(left < right){\n\t\t\twhile(left < right && !Character.isLetterOrDigit(string.charAt(left))) {\n\t\t\t\tleft++;\n\t\t\t}\n\t\t\twhile(left < right && !Character.isLetterOrDigit(string.charAt(right))) {\n\t\t\t\tright--;\n\t\t\t}\n\t\t\tif(Character.toLowerCase(string.charAt(left)) != Character.toLowerCase(string.charAt(right))){\n\t\t\t\treturn false;\n\t\t\t}\n\t\t\tleft++;\n\t\t\tright--;\n\t\t}\n\t\treturn true;\n\t}\n}","public boolean isPalindrome(String string){\n\t","package Exo9;\n\nimport java.util.Random;\n\npublic class mainExo {\n\n\tprivate static final String CHARACTERS = \"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789\";\n\n\tprivate static String generateRandomString(){\n\t\tRandom random = new Random();\n\t\tint minLength = 1;\n\t\tint maxLength = 100;\n\t\tint length = random.nextInt((maxLength - minLength) + 1) + minLength;\n\t\tStringBuilder sb = new StringBuilder(length);\n\t\tfor(int i = 0; i < length; i++){\n\t\t\tint index = random.nextInt(CHARACTERS.length());\n\t\t\tsb.append(CHARACTERS.charAt(index));\n\t\t}\n\t\treturn sb.toString();\n\t}\n\n\tpublic static int run_test(soluceExo string1, userExo string2, String str){\n\t\tboolean result1 = string1.isPalindrome(str);\n\t\tboolean result2 = string2.isPalindrome(str);\n\t\tif(result1 == result2){\n\t\t\tSystem.out.println(\"Test passed\");\n\t\t\treturn 0;\n\t\t}\n\t\telse{\n\t\t\tSystem.out.println(\"Test failed\");\n\t\t\treturn 1;\n\t\t}\n\t}\n\n\tpublic static void main(String[] args){\n\t\tint failed  = 0;\n\t\tString str1 = generateRandomString();\n\t\tsoluceExo stringS1 = new soluceExo();\n\t\tuserExo string1 = new userExo();\n\t\tfailed |= run_test(stringS1, string1, str1);\n\t\tString str2 = generateRandomString();\n\t\tsoluceExo stringS2 = new soluceExo();\n\t\tuserExo string2 = new userExo();\n\t\tfailed |= run_test(stringS2, string2, str2);\n\t\tString str3 = \"radar\";\n\t\tsoluceExo stringS3 = new soluceExo();\n\t\tuserExo string3 = new userExo();\n\t\tfailed |= run_test(stringS3, string3, str3);\n\t\tString str4 = \"\";\n\t\tsoluceExo stringS4 = new soluceExo();\n\t\tuserExo string4 = new userExo();\n\t\tfailed |= run_test(stringS4, string4, str4);\n\t\tSystem.exit(failed);\n\t}\n}\n");
        addEx(1,"[Include] Exercise 10 : String and palindrome","You will be creating a function that checks whether a string is a palindrome.\nIt returns True if the string is a palindrome and False otherwise.",1,"def isPalindrome1(string):\n\tstring = string.lower()\n\tstring = ''.join(c for c in string if c.isalnum())\n\treturn string == string[::-1]","def isPalindrome(string):\n\t","import sys\nimport random\nimport string\nimport soluceExo\nimport userExo\n\ndef run_test(string):\n\tresult1 = soluceExo.isPalindrome1(string)\n\tresult2 = userExo.isPalindrome(string)\n\tif(result1 == result2):\n\t\tprint(\"Test passed\")\n\t\treturn 0\n\telse:\n\t\tprint(\"Test failed\")\n\t\treturn 1\n\ndef generateRandomString(length):\n\tcharacters = string.ascii_letters + string.digits\n\treturn ''.join(random.choice(characters) for _ in range(length))\n\ndef main():\n\tfailed = 0\n\tminSize = 1\n\tmaxSize = 100\n\tstringLength1 = random.randint(minSize, maxSize)\n\tstring1 = generateRandomString(stringLength1)\n\tfailed |= run_test(string1)\n\tstringLength2 = random.randint(minSize, maxSize)\n\tstring2 = generateRandomString(stringLength2)\n\tfailed |= run_test(string2)\n\tstring3 = \"radar\"\n\tfailed |= run_test(string3)\n\tstring4 = \"\"\n\tfailed |= run_test(string4)\n\treturn failed\n\nsys.exit(main())");
        addEx(1,"[Include] Exercise 11 : Prime number","You will be creating a function that checks whether a number is prime.\nOnly positive or zero integers are considered.",3, "<?php\n\tfunction isPrime1($number){\n\t\tif($number <= 1){\n\t\t\treturn false;\n\t\t}\n\t\tfor($i = 2; $i <= sqrt($number); $i++){\n\t\t\tif($number % $i == 0){\n\t\t\t\treturn false;\n\t\t\t}\n\t\t}\n\t\treturn true;\n\t}\n?>","function isPrime($number){\n","<?php\n\tinclude 'soluceExo.php';\n\tinclude 'userExo.php';\n\n\tfunction run_test($number){\n\t\t$result1 = isPrime1($number);\n\t\t$result2 = isPrime($number);\n\t\tif($result1 == $result2){\n\t\t\techo \"Test passed\";\n\t\t\treturn 0;\n\t\t}\n\t\telse{\n\t\t\techo \"Test failed\";\n\t\t\treturn 1;\n\t\t}\n\t}\n\n\tfunction main(){\n\t\t$failed = 0;\n\t\t$minValue = 0;\n\t\t$maxValue = 1000;\n\t\t$number1 = rand($minValue, $maxValue);\n\t\t$failed |= run_test($number1);\n\t\t$number2 = rand($minValue, $maxValue);\n\t\t$failed |= run_test($number2);\n\t\t$number3 = 11;\n\t\t$failed |= run_test($number3);\n\t\t$number4 = 0;\n\t\t$failed |= run_test($number4);\n\t\treturn $failed;\n\t}\n\n\texit(main());\n?>");
        addEx(1,"[Include] Exercise 12 : Prime number","You will be creating a function that checks whether a number is prime.\nOnly positive or zero integers are considered.",5, "export function isPrime1(number){\n\tif(number <= 1){\n\t\treturn false;\n\t}\n\tfor(let i = 2; i <= Math.sqrt(number); i++){\n\tif(number % i === 0){\n\t\treturn false;\n\t}\n\t}\n\treturn true;\n}","function isPrime(number){\n\n}","import { isPrime } from \"./userExo.mjs\";\nimport { isPrime1 } from \"./soluceExo.mjs\";\n\nfunction run_test(number){\n\tlet result1 = isPrime1(number);\n\tlet result2 = isPrime(number);\n\tif(result1 === result2){\n\t\tconsole.log(\"Test passed\");\n\t\treturn 0;\n\t}\n\telse{\n\t\tconsole.log(\"Test failed\");\n\t\treturn 1;\n\t}\n}\n\n\tfunction main(){\n\t\tlet failed = 0;\n\t\tconst minValue = 0;\n\t\tconst maxValue = 1000;\n\t\tconst number1 = Math.floor(Math.random() * (maxValue - minValue + 1)) + minValue;\n\t\tfailed |= run_test(number1);\n\t\tconst number2 = Math.floor(Math.random() * (maxValue - minValue + 1)) + minValue;\n\t\tfailed |= run_test(number2);\n\t\tconst number3 = 11;\n\t\tfailed |= run_test(number3);\n\t\tconst number4 = 0;\n\t\tfailed |= run_test(number4);\n\t\treturn failed;\n\t}\n\n\tprocess.exit(main());");
        addEx(0,"[Stdin/Stdout] Exercise 13 : Average of ten numbers","You will be given 10 float and You have to give back the average",2,
                "import java.util.Scanner;\n" +
                        "\n" +
                        "public class soluceExo {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        Scanner scanner = new Scanner(System.in);\n" +
                        "        int sum = 0;\n" +
                        "        \n" +
                        "        System.out.println(\"Enter 10 integers:\");\n" +
                        "        for (int i = 0; i < 10; i++) {\n" +
                        "            int num = scanner.nextInt();\n" +
                        "            sum += num;\n" +
                        "        }\n" +
                        "        \n" +
                        "        double average = (double) sum / 10;\n" +
                        "        System.out.println(\"Average: \" + average);\n" +
                        "        \n" +
                        "        scanner.close();\n" +
                        "    }\n" +
                        "}\n",
                "import java.util.Random;\n" +
                        "\n" +
                        "public class genExo {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        if (args.length != 1) {\n" +
                        "            System.out.println(\"Please provide a mode (1: normal, 2: random, 3: error)\");\n" +
                        "            return;\n" +
                        "        }\n" +
                        "\n" +
                        "        int mode = Integer.parseInt(args[0]);\n" +
                        "        Random random = new Random();\n" +
                        "\n" +
                        "        switch (mode) {\n" +
                        "            case 1:\n" +
                        "                // Normal mode: predefined values\n" +
                        "                int[] normalValues = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};\n" +
                        "                for (int value : normalValues) {\n" +
                        "                    System.out.println(value);\n" +
                        "                }\n" +
                        "                break;\n" +
                        "\n" +
                        "            case 2:\n" +
                        "                // Random mode: generate random values\n" +
                        "                for (int i = 0; i < 10; i++) {\n" +
                        "                    int randomInt = random.nextInt(100); // Génère un entier aléatoire entre 0 et 99\n" +
                        "                    System.out.println(randomInt);\n" +
                        "                }\n" +
                        "                break;\n" +
                        "\n" +
                        "            case 3:\n" +
                        "                // Error mode: generate erroneous values\n" +
                        "                int[] errorValues = {-1, 101, 200, -50, 0, 99, 100, 150, -100, 300};\n" +
                        "                for (int value : errorValues) {\n" +
                        "                    System.out.println(value);\n" +
                        "                }\n" +
                        "                break;\n" +
                        "\n" +
                        "            default:\n" +
                        "                System.out.println(\"Invalid mode. Please provide a mode (1: normal, 2: random, 3: error)\");\n" +
                        "        }\n" +
                        "    }\n" +
                        "}",
                "");
        addEx(0,"[Stdin/Stdout] Exercise 14 : A bit odd isn't it ? ","You will receive an integer number as input and you have to determine whether it is even or odd.",2,
                "import java.util.Scanner;\n" +
                        "\n" +
                        "public class soluceExo {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        Scanner scanner = new Scanner(System.in);\n" +
                        "        \n" +
                        "        System.out.println(\"Enter a number:\");\n" +
                        "        int num = scanner.nextInt();\n" +
                        "        \n" +
                        "        if (num % 2 == 0) {\n" +
                        "            System.out.println(num + \" is even.\");\n" +
                        "        } else {\n" +
                        "            System.out.println(num + \" is odd.\");\n" +
                        "        }\n" +
                        "        \n" +
                        "        scanner.close();\n" +
                        "    }\n" +
                        "}\n",
                "import java.util.Random;\n" +
                        "\n" +
                        "public class genExo {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        if (args.length != 1) {\n" +
                        "            System.out.println(\"Please provide a mode (1: normal, 2: random, 3: error)\");\n" +
                        "            return;\n" +
                        "        }\n" +
                        "\n" +
                        "        int mode = Integer.parseInt(args[0]);\n" +
                        "        Random random = new Random();\n" +
                        "\n" +
                        "        switch (mode) {\n" +
                        "            case 1:\n" +
                        "                // Normal mode: predefined values\n" +
                        "                int[] normalValues = {2, 5, 8, 11, 14, 17, 20, 23, 26, 29};\n" +
                        "                for (int value : normalValues) {\n" +
                        "                    System.out.println(value);\n" +
                        "                }\n" +
                        "                break;\n" +
                        "\n" +
                        "            case 2:\n" +
                        "                // Random mode: generate random values\n" +
                        "                for (int i = 0; i < 10; i++) {\n" +
                        "                    int randomInt = random.nextInt(100); // Generates a random integer between 0 and 99\n" +
                        "                    System.out.println(randomInt);\n" +
                        "                }\n" +
                        "                break;\n" +
                        "\n" +
                        "            case 3:\n" +
                        "                // Error mode: generate erroneous values\n" +
                        "                int[] errorValues = {-2, 101, 200, -51, 0, 99, 100, 151, -101, 300};\n" +
                        "                for (int value : errorValues) {\n" +
                        "                    System.out.println(value);\n" +
                        "                }\n" +
                        "                break;\n" +
                        "\n" +
                        "            default:\n" +
                        "                System.out.println(\"Invalid mode. Please provide a mode (1: normal, 2: random, 3: error)\");\n" +
                        "        }\n" +
                        "    }\n" +
                        "}\n","");
        addEx(0,"[Stdin/Stdout] Exercise 15 : Binary Numbers Addition","Write a program that takes two binary numbers as input in the form of strings, adds them together, and outputs the result in binary form.\n" +
                        "\n" +
                        "The program should read two binary numbers from standard input.\n" +
                        "It should convert these binary numbers to decimal numbers.\n" +
                        "It should perform the addition of the two decimal numbers.\n" +
                        "It should convert the result of the addition back to a binary number.\n" +
                        "It should print the binary result.\n" +
                        "Input: Two binary numbers (strings).\n" +
                        "\n" +
                        "Output: The result of adding the two binary numbers, displayed in binary.\n" +
                        "\n" +
                        "Example : " +
                        "Input:" +
                        "1010\n" +
                        "1101\n" +
                        "Output:" +
                        "10111",2,
                "import java.util.Scanner;\n" +
                        "\n" +
                        "public class soluceExo {\n" +
                        "\n" +
                        "    public static void main(String[] args) {\n" +
                        "        Scanner scanner = new Scanner(System.in);\n" +
                        "\n" +
                        "        // Read the two binary numbers\n" +
                        "        String binary1 = scanner.nextLine();\n" +
                        "        String binary2 = scanner.nextLine();\n" +
                        "\n" +
                        "        // Convert binary numbers to decimal\n" +
                        "        int decimal1 = Integer.parseInt(binary1, 2);\n" +
                        "        int decimal2 = Integer.parseInt(binary2, 2);\n" +
                        "\n" +
                        "        // Perform addition in decimal\n" +
                        "        int sum = decimal1 + decimal2;\n" +
                        "\n" +
                        "        // Convert the result back to binary\n" +
                        "        String binarySum = Integer.toBinaryString(sum);\n" +
                        "\n" +
                        "        // Print the binary result\n" +
                        "        System.out.println(binarySum);\n" +
                        "\n" +
                        "        scanner.close();\n" +
                        "    }\n" +
                        "}\n",
                "import java.util.Random;\n" +
                        "\n" +
                        "public class genExo {\n" +
                        "\n" +
                        "    public static void main(String[] args) {\n" +
                        "        if (args.length != 1) {\n" +
                        "            System.out.println(\"Please provide a mode (1: normal, 2: random, 3: error)\");\n" +
                        "            return;\n" +
                        "        }\n" +
                        "\n" +
                        "        int mode = Integer.parseInt(args[0]);\n" +
                        "        Random random = new Random();\n" +
                        "\n" +
                        "        switch (mode) {\n" +
                        "            case 1:\n" +
                        "                // Normal mode: predefined values\n" +
                        "                String[] normalValues = {\"1010\", \"1101\"};\n" +
                        "                for (String value : normalValues) {\n" +
                        "                    System.out.println(value);\n" +
                        "                }\n" +
                        "                break;\n" +
                        "\n" +
                        "            case 2:\n" +
                        "                // Random mode: generate random binary values\n" +
                        "                for (int i = 0; i < 2; i++) {\n" +
                        "                    String binaryString = generateRandomBinary(random);\n" +
                        "                    System.out.println(binaryString);\n" +
                        "                }\n" +
                        "                break;\n" +
                        "\n" +
                        "            case 3:\n" +
                        "                // Error mode: generate erroneous values\n" +
                        "                String[] errorValues = {\"1010\", \"-1101\"}; // Second value is erroneous\n" +
                        "                for (String value : errorValues) {\n" +
                        "                    System.out.println(value);\n" +
                        "                }\n" +
                        "                break;\n" +
                        "\n" +
                        "            default:\n" +
                        "                System.out.println(\"Invalid mode. Please provide a mode (1: normal, 2: random, 3: error)\");\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    private static String generateRandomBinary(Random random) {\n" +
                        "        int length = random.nextInt(5) + 1; // Generate binary numbers of length between 1 and 5\n" +
                        "        StringBuilder binary = new StringBuilder(length);\n" +
                        "\n" +
                        "        for (int i = 0; i < length; i++) {\n" +
                        "            binary.append(random.nextInt(2));\n" +
                        "        }\n" +
                        "\n" +
                        "        return binary.toString();\n" +
                        "    }\n" +
                        "}\n","");
        addEx(0,"","",2,
                "",
                "","");
    }

    /**
     * Retrieves an exercise from the database by its ID.
     * @param Id The ID of the exercise to retrieve
     * @return The Exercise object retrieved from the database
     */

    // Take a Question (Id) from the database and stock it in the class Excercise
    
    public static Exercise take(int Id){

        Exercise exo = null;
        String sql = "SELECT * FROM Question WHERE Id = ?";

        Connection con = start();

        try {

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, Id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                if (rs.getInt("ExoType") == 0) {
                    exo = new ExerciseStdinStdout(
                            rs.getInt("Id"),
                            rs.getInt("ExoType"),
                            rs.getString("ExoName"),
                            rs.getString("Instruction"),
                            rs.getInt("SolutionLang"),
                            rs.getString("SolutionCode"),
                            rs.getString("GeneratorCode"),
                            rs.getInt("NbTry"),
                            rs.getInt("NbSucess"),
                            rs.getInt("NbSessionSucess"),
                            rs.getInt("NbFirstTry")
                    );
                }
                else {
                    exo = new ExerciseInclude(
                            rs.getInt("Id"),
                            rs.getInt("ExoType"),
                            rs.getString("ExoName"),
                            rs.getString("Instruction"),
                            rs.getInt("SolutionLang"),
                            rs.getString("SolutionCode"),
                            rs.getString("GeneratorCode"),
                            rs.getString("MainCode"),
                            rs.getInt("NbTry"),
                            rs.getInt("NbSucess"),
                            rs.getInt("NbSessionSucess"),
                            rs.getInt("NbFirstTry")
                    );
                }
            } else {
                System.out.println("No question find with this ID : " + Id);
            }

            end(con);
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Error during the recuperation of the table : " + e.getMessage());
        }

        return exo;
    }

    /**
     * Adds an exercise to the database (question).
     * @param ExoType Exercise type
     * @param ExoName Exercise name
     * @param Instruction Exercise instructions
     * @param SolutionLang Solution language
     * @param SolutionCode Solution code
     * @param GeneratorCode Generator code
     * @param MainCode Main code
     */

    // Add an exercise to the database (question)
    public static void addEx(int ExoType, String ExoName, String Instruction, int SolutionLang, String SolutionCode, String GeneratorCode, String MainCode) {

        int NbTry = 0;
        int NbSucess = 0;
        int NbSessionSucess = 0;
        int NbFirstTry = 0;

        String checkSql = "SELECT COUNT(*) FROM Question WHERE ExoName = ?";
        String insertSql = "INSERT INTO Question (ExoType, ExoName, Instruction, SolutionLang, SolutionCode, GeneratorCode, MainCode, NbTry, NbSucess, NbSessionSucess, NbFirstTry) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try {
            Connection con = start();

            PreparedStatement checkStmt = con.prepareStatement(checkSql);
            checkStmt.setString(1, ExoName);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) == 0) {
                PreparedStatement insertStmt = con.prepareStatement(insertSql);

                insertStmt.setInt(1, ExoType);
                insertStmt.setString(2, ExoName);
                insertStmt.setString(3, Instruction);
                insertStmt.setInt(4, SolutionLang);
                insertStmt.setString(5, SolutionCode);
                insertStmt.setString(6, GeneratorCode);
                insertStmt.setString(7, MainCode);
                insertStmt.setInt(8, NbTry);
                insertStmt.setInt(9, NbSucess);
                insertStmt.setInt(10, NbSessionSucess);
                insertStmt.setInt(11, NbFirstTry);
                insertStmt.executeUpdate();

                rs.close();
                checkStmt.close();
                insertStmt.close();
                end(con);
            }
        } catch (SQLException e) {
            System.out.println("Error during the addition of a new question: " + e.getMessage());
        }
    }

    /**
     * Updates the number of tries, successes, session successes, and first tries for a question in the database.
     * @param Id ID of the question to update
     * @param NbTry Number of tries to add
     * @param NbSucess Number of successes to add
     * @param NbSessionSucess Number of session successes to add
     * @param NbFirstTry Number of first tries to add
     */

    // Increment all the number of try/sucess of the wanted number in the database

    public static void update(int Id,int NbTry,int NbSucess,int NbSessionSucess,int NbFirstTry) {

        String sql =
                "UPDATE Question SET " +
                "NbTry = NbTry + ?, " +
                "NbSucess = NbSucess + ?, " +
                "NbSessionSucess = NbSessionSucess + ?, " +
                "NbFirstTry = NbFirstTry + ? " +
                " WHERE Id = ?";

        Connection con = start();

        try{
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, NbTry);
            pstmt.setInt(2, NbSucess);
            pstmt.setInt(3, NbSessionSucess);
            pstmt.setInt(4, NbFirstTry);
            pstmt.setInt(5, Id);

            pstmt.executeUpdate();

            end(con);
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Error during the update of the numbers : " + e.getMessage());
        }
    }

    /**
     * Counts the number of questions in the database.
     * @return The number of questions in the database
     */

    // Count the number of question in the database

    public static int count(){

        int count = 0;

        String sql = "SELECT COUNT(*) AS count FROM Question";

        Connection con = start();

        try{
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("count");
            }

            end(con);
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Error during the count : " + e.getMessage());
        }

        return count;
    }

    /**
     * Deletes the specified question from the database and adjusts the IDs to maintain correct order.
     * @param Id ID of the question to delete
     */

    // Delete the wanted question and remake the id to be correct
    public static void delete(int Id){

        String sqlDelete = "DELETE FROM Question WHERE Id = ?";
        String sqlUpdateIds = "UPDATE Question SET Id = Id - 1 WHERE Id > ?";

        Connection con = start();

        try{
            con.setAutoCommit(false);

            PreparedStatement pstmtDelete = con.prepareStatement(sqlDelete);
            pstmtDelete.setInt(1, Id);
            pstmtDelete.executeUpdate();

            PreparedStatement pstmtUpdateIds = con.prepareStatement(sqlUpdateIds);
            pstmtUpdateIds.setInt(1, Id);
            pstmtUpdateIds.executeUpdate();

            con.commit();

            end(con);
            pstmtDelete.close();
            pstmtUpdateIds.close();

        } catch (SQLException e) {
            System.out.println("Error during the delete of the question : " + e.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("Error during rollback : " + ex.getMessage());
            }
        }
    }
}