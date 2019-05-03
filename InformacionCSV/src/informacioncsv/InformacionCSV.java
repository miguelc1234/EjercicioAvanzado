/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informacioncsv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Estudiantes
 */
public class InformacionCSV {

    public static String[] campos;
    public static String[] indicadores;
    public static String[] cantidadIndicadores;

    public static Integer cantidadArray = 0;
    public static Integer cantidadFilas = 0;
    public static Integer cantidadIndicador = 0;
    public static Integer indicador = 1;
    public static Integer banderaIndicador = 0;

    public static Integer valorMenorBase = 999999999;
    public static Double valorMayorBase = 0.0;
    public static Integer valorMenor = 0;

    public static String strIndicador = "";
    public static String strIndicadorMayor = "";

    public static Double sumaIndicadores = 0.0;
    public static Double sumaIndicadoresMayor = 0.0;
    public static Double[] sumaIndicadoresMayorArray;
    public static Double promedio = 0.0;
    public static Integer cont = 0;

    public static Double ValorMayor1 = 0.0;
    public static Double ValorMayor2 = 0.0;
    public static Double ValorMayor3 = 0.0;
    public static Double ValorMayor4 = 0.0;
    public static Double ValorMayor5 = 0.0;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        cantidadIndicadores = new String[25];
        sumaIndicadoresMayorArray = new Double[25];
        muestraContenido("ejercicio.csv");
    }

    public static void muestraContenido(String archivo) throws FileNotFoundException, IOException {
        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(archivo));
            String line = br.readLine();

            while (null != line) {

                if (cont == 0) {
                    cont = 1;
                    line = br.readLine();
                } else {
                    //sumar filas
                    cantidadFilas = cantidadFilas + 1;

                    campos = line.split(",");

                    String indi = campos[0].toString();
                    indicadores = indi.split("\\.");

                    banderaIndicador = Integer.parseInt(indicadores[0]);

                    if (indicador == banderaIndicador) {
                        cantidadIndicador = cantidadIndicador + 1;                        
                        sumaIndicadoresMayor = sumaIndicadoresMayor + Double.parseDouble(campos[1]);
                    } else {

                        //Guardar sumas
                        sumaIndicadoresMayorArray[cantidadArray] = sumaIndicadoresMayor;
                        
                        cantidadIndicadores[cantidadArray] = indicador + " = " + cantidadIndicador + " suma: " + sumaIndicadoresMayor;
                        cantidadArray = cantidadArray + 1;

                        //indicador de mayor precio
                        if (sumaIndicadoresMayor > valorMayorBase) {
                            valorMayorBase = sumaIndicadoresMayor;
                            strIndicadorMayor = String.valueOf(indicador);
                            sumaIndicadoresMayor = 0.0;
                        }
                        
                        indicador = Integer.parseInt(indicadores[0]);
                        cantidadIndicador = 0;
                        cantidadIndicador = cantidadIndicador + 1;
                    }

                    //indicador de menor precio
                    if (Integer.valueOf(campos[1]) < valorMenorBase) {
                        valorMenorBase = Integer.valueOf(campos[1]);
                        strIndicador = campos[0];
                    }

                    //Sumar precios indicadores
                    sumaIndicadores = sumaIndicadores + Double.parseDouble(campos[1]);

                    line = br.readLine();
                }
            }

            //ultima linea
            cantidadIndicadores[cantidadArray] = indicador + " = " + cantidadIndicador + " suma: " + sumaIndicadoresMayor;
            sumaIndicadoresMayorArray[cantidadArray] = sumaIndicadoresMayor;
            //Promedio
            promedio = sumaIndicadores / cantidadFilas;
            
            System.out.printf("\n1. Indicador: " + strIndicador);
            System.out.printf("\n2. Cantidad Filas: " + cantidadFilas);
            System.out.printf("\n3. Suma Indicadores: " + sumaIndicadores);
            System.out.printf("\n4. Promedio: " + promedio);
            System.out.printf("\n5. Cantidad por Indicador:");
            for (int i = 0; i < cantidadIndicadores.length; i++) {
                System.out.printf("\n" + cantidadIndicadores[i]);
            }           
            
            for (int i = 0; i < sumaIndicadoresMayorArray.length; i++) {
                if (sumaIndicadoresMayorArray[i] > ValorMayor1) {
                    ValorMayor1 = sumaIndicadoresMayorArray[i];
                }
            }

            for (int i = 0; i < sumaIndicadoresMayorArray.length; i++) {
                if (sumaIndicadoresMayorArray[i] != ValorMayor1) {
                    if (sumaIndicadoresMayorArray[i] > ValorMayor2) {
                        ValorMayor2 = sumaIndicadoresMayorArray[i];
                    }
                }
            }
            
            for (int i = 0; i < sumaIndicadoresMayorArray.length; i++) {
                if (sumaIndicadoresMayorArray[i] != ValorMayor1 
                        && sumaIndicadoresMayorArray[i] != ValorMayor2) {
                    if (sumaIndicadoresMayorArray[i] > ValorMayor3) {
                        ValorMayor3 = sumaIndicadoresMayorArray[i];
                    }
                }
            }
            
            for (int i = 0; i < sumaIndicadoresMayorArray.length; i++) {
                if (sumaIndicadoresMayorArray[i] != ValorMayor1 
                        && sumaIndicadoresMayorArray[i] != ValorMayor2
                        && sumaIndicadoresMayorArray[i] != ValorMayor3) {
                    if (sumaIndicadoresMayorArray[i] > ValorMayor4) {
                        ValorMayor4 = sumaIndicadoresMayorArray[i];
                    }
                }
            }
            
            for (int i = 0; i < sumaIndicadoresMayorArray.length; i++) {
                if (sumaIndicadoresMayorArray[i] != ValorMayor1 
                        && sumaIndicadoresMayorArray[i] != ValorMayor2
                        && sumaIndicadoresMayorArray[i] != ValorMayor3
                        && sumaIndicadoresMayorArray[i] != ValorMayor4) {
                    if (sumaIndicadoresMayorArray[i] > ValorMayor5) {
                        ValorMayor5 = sumaIndicadoresMayorArray[i];
                    }
                }
            }
            
            System.out.printf("\nAvanzado 1. Valor Mayor 1: " + ValorMayor1);
            System.out.printf("\nAvanzado 1. Valor Mayor 2: " + ValorMayor2);
            System.out.printf("\nAvanzado 1. Valor Mayor 3: " + ValorMayor3);
            System.out.printf("\nAvanzado 1. Valor Mayor 4: " + ValorMayor4);
            System.out.printf("\nAvanzado 1. Valor Mayor 5: " + ValorMayor5);
            
            System.out.printf("\nAvanzado 3. Indicador Mayor: " + strIndicadorMayor);
            
        } catch (Exception e) {

        } finally {
            if (null != br) {
                br.close();
            }
        }

    }

}
