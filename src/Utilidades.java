
import static java.lang.Math.random;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import sun.reflect.generics.tree.VoidDescriptor;

public class Utilidades {

    private static String s1 = "$###,###.##";
    private static String s2 = "$###,###.##";
    private static DecimalFormat formato = new DecimalFormat(s1);
    private static DecimalFormat formato2 = new DecimalFormat(s2);
    private static Random random = new Random();

    public static String getFormatMoneda(double valor) {

        return formato.format(valor);
    }
    public static int getAleatoriosRango(int min, int max){
    
return ThreadLocalRandom.current().nextInt(min, max + 1);

}

    public static String getFormat(double valor) {

        return formato.format(valor);
    }

    public static int getAleatorios(int cota) {
        return random.nextInt(cota) + 1;
    }

    public static boolean igualQ(Object a, Object b) {

        if (a instanceof Integer && b instanceof Integer) {
            Integer x = (Integer) a;
            Integer y = (Integer) b;
            return x == y;
        }
        if (a instanceof String && b instanceof String) {
            String s1 = (String) a;
            String s2 = (String) b;
            return s1.equalsIgnoreCase(s2);
        }
                if (a instanceof Character && b instanceof Character) {
            char c1 = (char) a;
            char c2 = (char) b;
            return c1==c2;
           
        }
        return false;
    }
    
    public static int comparar(Object a, Object b) {

		int resultado = 0;

		if (a instanceof Integer) {

			if ((int) a < (int) b) {

				resultado = -1;

			}
			else if ((int) a > (int) b) {

				resultado = 1;

			}
			else {

				resultado = 0;

			}

		}
		else if (a instanceof String) {

			if (((String) a).compareToIgnoreCase((String) b) < 0) {

				resultado = -1;

			}
			else if (((String) a).compareToIgnoreCase((String) b) > 0) {

				resultado = 1;

			}
			else {

				resultado = 0;

			}

		}

		return resultado;

	}

	public static boolean mayorQue(Object e1, Object e2) {

		boolean menor = false;

		if (e1 instanceof Integer) {

			menor = (int) e1 > (int) e2;

		}

		if (e1 instanceof String) {

			menor = String.valueOf(e1).compareToIgnoreCase(String.valueOf(e2)) > 0;

		}

		return menor;
	}

	public static boolean menorQue(Object e1, Object e2) {

		boolean menor = false;

		if (e1 instanceof Integer) {

			menor = (int) e1 < (int) e2;

		}

		if (e1 instanceof String) {

			menor = String.valueOf(e1).compareToIgnoreCase(String.valueOf(e2)) < 0;

		}

		return menor;
	}

	public static Object minimo(Object obj1, Object obj2) {

		Object min = null;

		if (obj1 instanceof Integer) {

			if ((int) obj1 <= (int) obj2) {

				min = obj1;

			}
			else {

				min = obj2;

			}

		}

		if (obj1 instanceof String) {

			if (String.valueOf(obj1).compareToIgnoreCase(String.valueOf(obj2)) < 0) {

				min = obj1;

			}
			else {

				min = obj2;

			}

		}

		return min;
	}

	public static Object maximo(Object obj1, Object obj2) {

		Object max = null;

		if (obj1 instanceof Integer) {

			if ((int) obj1 > (int) obj2) {

				max = obj1;

			}
			else {

				max = obj2;

			}

		}

		if (obj1 instanceof String) {

			if (String.valueOf(obj1).compareToIgnoreCase(String.valueOf(obj2)) > 0) {

				max = obj1;

			}
			else {

				max = obj2;

			}

		}

		return max;
	}

	public static boolean igual(Object a, Object b) {

		boolean iguales = false;

		if (a instanceof String) {

			iguales = (String.valueOf(a).equalsIgnoreCase(String.valueOf(b)));

		}
		if (a instanceof Integer) {

			iguales = ((int) a) == ((int) b);

		}

		return iguales;

	}
   public static String formatoMonedaColon(double valor) {

        DecimalFormat formato = new DecimalFormat("₡#,###,###.##");

        return formato.format(valor);

    }

    public static String formatoMonedaDolar(double valor) {

        DecimalFormat formato = new DecimalFormat("$#,###,###.##");

        return formato.format(valor);

    }
 // muestra el Tn en formato hh:mm:ss
    public static String getTnHHMMSS(long tInicio, long tFin) {

        long milisegundos = tFin - tInicio;
        long hora = milisegundos / 3600000;
        long restohora = milisegundos % 3600000;
        long minuto = restohora / 60000;
        long restominuto = restohora % 60000;
        long segundo = restominuto / 1000;
        long restosegundo = restominuto % 1000;

        return hora + ":" + minuto + ":" + segundo + "." + restosegundo;
    }
    
    // Obtener hora y fecha con formato
    public static String getFechaHoraActual() {

        Date date = new Date();

        DateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return f.format(date);
    }

    // Obtener hora con formato
    public static String getHoraActual() {

        Date date = new Date();

        DateFormat f = new SimpleDateFormat("HH:mm:ss");

        return f.format(date);
    }

    // Obtener fecha con formato
    public static String getFechaActual() {

        Date date = new Date();

        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");

        return f.format(date);
    }

    // Da formato a un Date recibido por parámetro IMPRIME FECHA
    public static String formatoFecha(Date fecha) {

        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");

        return f.format(fecha);

    }

    // Da formato a un Date recibido por parámetro IMPRIME HORA Y FECHA
    public static String formatoHoraFecha(Date fecha) {

        DateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return f.format(fecha);

    }

    // Da formato a un Date recibido por parámetro IMPRIME HORA
    public static String formatoHora(Date fecha) {

        DateFormat f = new SimpleDateFormat("HH:mm:ss");

        return f.format(fecha);

    }

    // Calcula la diferecia de días entre 2 fechas tipo Date recibidas por
    // parámetro
    public static int diferenciaDias(Date inicio, Date fin) {

        // Calendar calendar = new GregorianCalendar();
        // Date trialTime = new Date();
        // calendar.setTime(trialTime);
        GregorianCalendar fechaInicio = new GregorianCalendar();

        fechaInicio.setTime(inicio);

        GregorianCalendar fechaFinal = new GregorianCalendar();

        fechaFinal.setTime(fin);

        long milisegundos = (fechaFinal.getTime()).getTime()
                - (fechaInicio.getTime()).getTime();

        long dias = milisegundos / (24 * 60 * 60 * 1000);

        return (int) dias;

    }

    public static char randomLetraMinuscula(){
        return  (char)Math.floor(Math.random()*(122 -97)+97);
    }
    
    public static char randomLetraMayuscula(){
        return (char)Math.floor(Math.random()*(90 -65)+65);
    }
     public static char randomCaracteres(){
        return (char)Math.floor(Math.random()*(48 -18)+18);
    }
          public static char randomMayusculasyMinusculas(){
        return (char)Math.floor(Math.random()*(90 -18)+18);
    }
    public static char randomLetras(){
        int al = (int)Math.floor(Math.random() * 2);
        if(al == 0){
            return randomLetraMinuscula();
        }else return randomLetraMayuscula();
    }
    
    public static int randomPrimos(int cota){
        int primo = getAleatorios(cota);
        if(primo<2){
          randomPrimos(cota);
        }
            int cont=0;
            for (int i = 1; i <= primo; i++) {
                if(primo%i==0){
                    cont++;
                }
            }
            if(cont==2){
                return primo;
            }else
                return randomPrimos(cota);
        
    }
    
    public static int randomNumerosDivisiblesEntre(int divisible, int cota){
        int num = getAleatorios(cota);
        if(num%divisible==0){
            return num;
        }else
            return randomNumerosDivisiblesEntre(divisible, cota);
    }
    
}
