/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona_2019_1_exer_a;

import java.util.Scanner;

/**
 *
 * @author Tiago Silva
 */
public class Maratona_2019_1_Exer_A {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /**
         * Declarando as variáveis de entrada.
         */
        String entrada = null, latitude, longitude;
        
        
        /**
         * Declarando as variáveis que conterão so resultados
         * após filtragem da entrada.
         */
        Double lat, lat_minutos, lat_segundos;
        Double lon, lon_minutos, lon_segundos;
        
        
        /**
         * Lendo a entrada.
         */
        Scanner ler = new Scanner(System.in);        
        System.out.println("Digite a entrada");
        
        
        /**
         * Lendo múltiplas linhas. Neste formato o programa poderá
         * ler uma entrada com várias linhas conforme o exemplo
         * pede.
         */
        while(ler.hasNextLine()){
            
            entrada = ler.nextLine(); 
            
            
            /**
             * Caso a linha tenha apenas Zero, encerra o programa
             */
            if(entrada.equals("0")) {
                return;
            }
        
        
            /**
             * Para aceitar entradas onde as coordenadas possam ser separadas
             * por vírgula como 0o 0” 00.00’,-10o 0” 00.00’ vamos converter a
             * separação para ponto e vírcula.
             */
            entrada = entrada.replace(",", ";");


            /**
             * Dividindo as entradas com split
             * de um lado a latitude, de outro a longitude.
             */
            latitude = entrada.split(";")[0];
            longitude = entrada.split(";")[1];


            /**
             * Removendo as informações irrelevantes de cada
             * um dos itens: º e " da LATITUDE.
             */
            String lat_aux = latitude.split(" ")[0];
            String lat_min_aux = latitude.split(" ")[1];
            String lat_seg_aux = latitude.split(" ")[2];

            String lat_aux_limpo = remove_ultimo(lat_aux);
            String lat_min_aux_limpo = remove_ultimo(lat_min_aux);
            String lat_seg_aux_limpo = remove_ultimo(lat_seg_aux);

            lat = Double.parseDouble(lat_aux_limpo);
            lat_minutos = Double.parseDouble(lat_min_aux_limpo);
            lat_segundos = Double.parseDouble(lat_seg_aux_limpo);


            /**
             * Convertendo para graus da LATITUDE, conforme o exemplo:
             * -15o 46’ 47”
             * -15 + -46/60 + -47/3600 = -15 + -0,7666 + -0,0130 = -15,7796o
             */
            Double lat_minutos_convertidos = (lat_minutos * -1)/60;
            Double lat_segundos_convertidos = (lat_segundos * -1)/3600;

            lat = lat + lat_minutos_convertidos + lat_segundos_convertidos;

            //System.out.println("Graus latitude = " + lat.toString());


            /**
             ****************************************************************
             **             CONVERTENDO OS DADOS DA LONGITUDE
             ****************************************************************
            **/
            String lon_aux = longitude.split(" ")[0];
            String lon_min_aux = longitude.split(" ")[1];
            String lon_seg_aux = longitude.split(" ")[2];

            String lon_aux_limpo = remove_ultimo(lon_aux);
            String lon_min_aux_limpo = remove_ultimo(lon_min_aux);
            String lon_seg_aux_limpo = remove_ultimo(lon_seg_aux);

            lon = Double.parseDouble(lon_aux_limpo);
            lon_minutos = Double.parseDouble(lon_min_aux_limpo);
            lon_segundos = Double.parseDouble(lon_seg_aux_limpo);

            /**
             * Convertendo para graus da LONGITUDE, conforme o exemplo:
             * -15o 46’ 47”
             * -15 + -46/60 + -47/3600 = -15 + -0,7666 + -0,0130 = -15,7796o
             */
            Double lon_minutos_convertidos = (lat_minutos * -1)/60;
            Double lon_segundos_convertidos = (lat_segundos * -1)/3600;

            lon = lon + lon_minutos_convertidos + lon_segundos_convertidos;

            //System.out.println("Graus longitude = " + lon.toString());
            
            System.out.println( classifica_qual_quadrante(lat, lon) );
        }
    }
    
    
    
    /**
     * Função criada para remover a "sugeira" do valor, 
     * seja ela o simbolo de grau (º) ou as aspas dos minutos/segundos.
     * O método é estatico para eu poder chamar ele dentro do main.
     * @param str
     * @return str com apenas os números.
     */
    public static String remove_ultimo(String str) {
     
        // Verifica o tamanho da string.
        int str_size = str.length();
        
        // subSequence retura um pedaço da string conforme os valores.
        // zero é o inicio da string, até o valor final, que no caso
        // é o tamanho total, menos 1. Por exemplo:
        // -27º tem um size (tamanho) de 4, menos 1 (o último)
        // retorna -27 no formato CharSequence, que é um vetor de caracteres.
        CharSequence str_sem_ultimo = str.subSequence(0, str_size-1);
        
        
        // Para retornar, convertemos para String.
        return str_sem_ultimo.toString();        
    }
    
    
    public static String classifica_qual_quadrante(Double lat, Double lon) {
        
        String ponto_cardeal = "";
        
        if(lat == 0) {
            ponto_cardeal = "Equador ";
        } else if(lat > 0) {
            ponto_cardeal = "Norte ";
        } else if(lat < 0) {
            ponto_cardeal = "Sul ";
        }
        
        if(lon == 0) {
            ponto_cardeal += "Greenwich";
        } else if(lon > 0) {
             ponto_cardeal += "Leste";
        } else if(lon < 0) {
             ponto_cardeal += "Oeste";
        }
        
        return  ponto_cardeal;        
    }
    
}
