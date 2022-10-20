package proyectogitbiblioteca;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
        String[] tipos = {"entero[0-2147483647]", "octal[0-1777777777]", "hexadecimal[0-7FFFFFFFF]", "binario[0-1111111111]"};

        // System.out.print("Introduzca el nï¿½mero "+tipos[opcion-1]+ " a convertir: ");

        while (!salir) {
            System.out.println("********************************");
            System.out.println("1. Convertir un decimal");
            System.out.println("2. Convertir un octal");
            System.out.println("3. Convertir un hexadecimal");
            System.out.println("4. Convertir un binario");
            System.out.println("5. Salir");
            System.out.println("********************************");

            try {
                // Inicio control de errores en ejecucin.
                int number;
                String hexadecimal;
                String binario;

                //  String binario;
                System.out.println("Escriba una de las opciones de conversi\u00f3n");
                opcion = sn.nextInt();
                if (opcion == 5) {
                    System.out.println("Has salido del juego, chaoooo");
                    return;
                }

                // System.out.print("Introduzca el n\u00famero a convertir: ");
                Scanner entrada = new Scanner(System.in);
                System.out.print("Introduzca el n\u00famero " + tipos[opcion-1] + " a convertir: \n");
                switch (opcion) {
                    case 1 -> {
                        number = entrada.nextInt();
                        if (!validarDecimal(number)) {
                            System.out.println("N\u00famero decimal no v\u00e1lido");
                            return;
                        }

                        String binarioRes = decimalABinario(number);
                        System.out.println("El binario es " + binarioRes);
                        String hexaRes = decimalAHexadecimal(number);
                        System.out.println("El hexadecimal es " + hexaRes);
                        String octalRes = decimalAOctal(number);
                        System.out.println("El octal es " + octalRes);
                        System.out.println("                                 ");
                    }
                    case 2 -> {
                        number = entrada.nextInt();
                        if (!validarOctal(number)) {
                            System.out.println("N\u00famero octal no v\u00e1lido");
                            return;
                        }
                        int decimalRes = octalADecimal(number);
                        System.out.println("El decimal es " + decimalRes);
                        String binarioRes = octalABinario(number);
                        System.out.println("El binario es " + binarioRes);
                        String hexadecimalRes = octalAHexadecimal(number);
                        System.out.println("El hexadecimal es " + hexadecimalRes);
                        System.out.println("                                 ");
                    }
                    case 3 -> {
                        hexadecimal = entrada.nextLine().toUpperCase();
                        if (!validarHexadecimal(hexadecimal)) {
                            System.out.println("N\u00famero hexadecimal no v\u00e1lido");
                            return;
                        }
                        int decimalRes = hexadecimalADecimal(hexadecimal);
                        System.out.println("El decimal es " + decimalRes);
                        String binarioRes = hexadecimalABinario(hexadecimal);
                        System.out.println("El binario es " + binarioRes);
                        String octalRes = hexadecimalAOctal(hexadecimal);
                        System.out.println("El octal es " + octalRes);
                        System.out.println("                                 ");
                    }
                    case 4 -> {
                        number = entrada.nextInt();
                        if (!validarBinario(number)) {
                            System.out.println("N\u00famero binario no v\u00e1lido");
                            // Los cï¿½digos que estï¿½n en el lugar de las letras con tilde,
                            //son su cï¿½digo en java.
                            return;
                        }
                        int decimalRes = binarioADecimal(number);
                        System.out.println("El decimal es " + decimalRes);
                        String octalRes = binarioAOctal(number);
                        System.out.println("El octal es " + octalRes);
                        int hexadecimalRes = binarioADecimal(number);
                        System.out.println("El hexadecimal es " + hexadecimalRes);
                        System.out.println("                                 ");
                    }
                    case 5 ->
                            salir = true;
                    default ->
                            System.out.println("S\u00f3lo n\u00fameros entre 1 y 5");
                } // Final control de errores en ejecuciï¿½n.
            } catch (InputMismatchException e) {
                // "catch" se ejecuta al producirse errores de ejecuciï¿½n dentro de "try".
                System.out.println("Debes insertar un n\u00famero v\u00e1lido seg\u00fan la opci\u00f3n elegida");
                sn.next();
            }
        }

    }

    //De Decimal a otras bases
    public static String decimalABinario(int decimal) {
        // Esta funciï¿½n pasa de Decimal a Binario.
        String binario = "";
        while (decimal > 0) {
            binario = decimal % 2 + binario;
            decimal = decimal / 2;
        }
        return binario;
    }

    public static String decimalAOctal(int decimal) {
        // Esta funciï¿½n pasa de Decimal a Octal.
        int residuo;
        String octal = "";
        char[] caracteresOctales = {'0', '1', '2', '3', '4', '5', '6', '7'};
        while (decimal > 0) {
            residuo = decimal % 8;
            char caracter = caracteresOctales[residuo];
            octal = caracter + octal;
            decimal = decimal / 8;
        }
        return octal;
    }

    public static String decimalAHexadecimal(int decimal) {
        // Esta funciï¿½n pasa de Decimal a Hexadecimal.
        int residuo;
        String hexadecimal = "";
        char[] caracteresHexadecimales = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (decimal > 0) {
            residuo = decimal % 16;
            char caracterHexadecimal = caracteresHexadecimales[residuo];
            hexadecimal = caracterHexadecimal + hexadecimal;
            decimal = decimal / 16;
        }
        return hexadecimal;
    }

    // Conversiones de otras bases a decimal
    public static int binarioADecimal(int binario) {
        //Esta funcion pasa de Binario a Decimal.
        int decimal = 0;
        int potencia = 0;
        // Ciclo infinito hasta que binario sea 0
        while (true) {
            if (binario == 0) {
                break;
            } else {
                int temp = binario % 10;
                decimal += temp * Math.pow(2, potencia);
                binario = binario / 10;
                potencia++;
            }
        }
        return decimal;
    }
    public static String binarioAOctal(int binario) {
        //Esta funcion pasa primero de Binario a Decimal,
        //para despuï¿½s pasar de Decimal a Octal.
        int decimal = 0;
        int potencia = 0;
        // Ciclo infinito hasta que binario sea 0
        while (true) {
            if (binario == 0) {
                break;
            } else {
                int temp = binario % 10;
                decimal += temp * Math.pow(2, potencia);
                binario = binario / 10;
                potencia++;
            }
        }
        int residuo;
        String octal = "";
        char[] caracteresOctales = {'0', '1', '2', '3', '4', '5', '6', '7'};
        while (decimal > 0) {
            residuo = decimal % 8;
            char caracter = caracteresOctales[residuo];
            octal = caracter + octal;
            decimal = decimal / 8;
        }
        return octal;
    }
    public static String binarioAHexadecimal(int binario) {
        //Esta funcion pasa primero de Binario a Decimal,
        //para despuï¿½s pasar de Decimal a Hexadecimal.
        int decimal = 0;
        int potencia = 0;
        // Ciclo infinito hasta que binario sea 0
        while (true) {
            if (binario == 0) {
                break;
            } else {
                int temp = binario % 10;
                decimal += temp * Math.pow(2, potencia);
                binario = binario / 10;
                potencia++;
            }
        }
        int residuo;
        String hexadecimal = "";
        char[] caracteresHexadecimales = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (decimal > 0) {
            residuo = decimal % 16;
            char caracterHexadecimal = caracteresHexadecimales[residuo];
            hexadecimal = caracterHexadecimal + hexadecimal;
            decimal = decimal / 16;
        }
        return hexadecimal;
    }

    public static int octalADecimal(int octal) {
        // Esta funciï¿½n pasa de Octal a Decimal.
        int decimal = 0;
        int potencia = 0;
        // Ciclo infinito que se rompe cuando octal es 0
        while (true) {
            if (octal == 0) {
                break;
            } else {
                int temp = octal % 10;
                decimal += temp * Math.pow(8, potencia);
                octal = octal / 10;
                potencia++;
            }
        }
        return decimal;
    }
    public static String octalABinario(int octal) {
        // Esta funciï¿½n primero pasa de Octal a Decimal,
        //para despuï¿½s pasar de Decimal a Binario.

        int decimal = 0;
        int potencia = 0;
        // Ciclo infinito que se rompe cuando octal es 0
        while (true) {
            if (octal == 0) {
                break;
            } else {
                int temp = octal % 10;
                decimal += temp * Math.pow(8, potencia);
                octal = octal / 10;
                potencia++;
            }
        }
        String binario = "";
        while (decimal > 0) {
            binario = decimal % 2 + binario;
            decimal = decimal / 2;
        }
        return binario;
    }
    public static String octalAHexadecimal(int octal) {
        // Esta funciï¿½n primero pasa de Octal a Decimal,
        //para despuï¿½s pasar de Decimal a Hexadecimal.
        int decimal = 0;
        int potencia = 0;
        // Ciclo infinito que se rompe cuando octal es 0
        while (true) {
            if (octal == 0) {
                break;
            } else {
                int temp = octal % 10;
                decimal += temp * Math.pow(8, potencia);
                octal = octal / 10;
                potencia++;
            }
        }
        int residuo;
        String hexadecimal = "";
        char[] caracteresHexadecimales = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (decimal > 0) {
            residuo = decimal % 16;
            char caracterHexadecimal = caracteresHexadecimales[residuo];
            hexadecimal = caracterHexadecimal + hexadecimal;
            decimal = decimal / 16;
        }
        return hexadecimal;
    }

    public static int hexadecimalADecimal(String hexadecimal) {

        // Esta funcion convierte de nï¿½ Hexadecimal a Decimal.
        String caracteresHexadecimales = "0123456789ABCDEF";
        hexadecimal = hexadecimal.toUpperCase();
        int decimal = 0;
        for (int i = 0; i < hexadecimal.length(); i++) {
            char caracter = hexadecimal.charAt(i);
            int posicionEnCaracteres = caracteresHexadecimales.indexOf(caracter);
            decimal = 16 * decimal + posicionEnCaracteres;
        }
        return decimal;
    }
    public static String hexadecimalAOctal(String hexadecimal) {
        // Primero pasamos de Hexadecimal a Decimal, y luego de Decimal  a Octal.
        String caracteresHexadecimales = "0123456789ABCDEF";
        hexadecimal = hexadecimal.toUpperCase();
        int decimal = 0;
        for (int i = 0; i < hexadecimal.length(); i++) {
            char caracter = hexadecimal.charAt(i);
            int posicionEnCaracteres = caracteresHexadecimales.indexOf(caracter);
            decimal = 16 * decimal + posicionEnCaracteres;
        }
        int residuo;
        String octal = "";
        char[] caracteresOctales = {'0', '1', '2', '3', '4', '5', '6', '7'};
        while (decimal > 0) {
            residuo = decimal % 8;
            char caracter = caracteresOctales[residuo];
            octal = caracter + octal;
            decimal = decimal / 8;
        }
        return octal;
    }
    public static String hexadecimalABinario(String hexadecimal) {

        String caracteresHexadecimales = "0123456789ABCDEF";
        hexadecimal = hexadecimal.toUpperCase();
        int decimal = 0;
        for (int i = 0; i < hexadecimal.length(); i++) {
            char caracter = hexadecimal.charAt(i);
            int posicionEnCaracteres = caracteresHexadecimales.indexOf(caracter);
            decimal = 16 * decimal + posicionEnCaracteres;
        }
        String binario = "";
        while (decimal > 0) {
            binario = decimal % 2 + binario;
            decimal = decimal / 2;
        }
        return binario;
    }

    // Validadores
    public static boolean validarDecimal(int decimal) {
        // Decimal pasa la validaciï¿½n con el hecho de que sea entero
        return true;
    }

    public static boolean validarBinario(int binario) {
        // Comprueba si solo se compone de unos y ceros
        String binarioComoCadena = String.valueOf(binario);
        for (int i = 0; i < binarioComoCadena.length(); i++) {
            char caracter = binarioComoCadena.charAt(i);
            if (caracter != '0' && caracter != '1') {
                return false;
            }
        }
        return true;
    }

    public static boolean validarOctal(int octal) {
        // Comprueba si solo tiene nï¿½meros del 0 al 7
        String octalComoCadena = String.valueOf(octal);
        String caracteresOctales = "01234567";
        for (int i = 0; i < octalComoCadena.length(); i++) {
            char caracter = octalComoCadena.charAt(i);
            // Si no se encuentra dentro de los caracteres vï¿½lidos, regresamos false
            if (caracteresOctales.indexOf(caracter) == -1) {
                return false;
            }
        }
        return true;
    }

    // Nota: se debe enviar la cadena hexadecimal convertida a mayï¿½sculas
    public static boolean validarHexadecimal(String hexadecimal) {
        // Comprueba si solo tiene nï¿½meros del 0 al 9 y letras de la A a la F
        String caracteresHexadecimales = "0123456789ABCDEF";
        for (int i = 0; i < hexadecimal.length(); i++) {
            char caracter = hexadecimal.charAt(i);
            // Si no se encuentra dentro de los caracteres vï¿½lidos, regresamos false
            if (caracteresHexadecimales.indexOf(caracter) == -1) {
                return false;
            }
        }
        return true;
    }

}
