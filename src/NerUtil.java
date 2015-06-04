

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;



public class NerUtil {
	
	private static AbstractSequenceClassifier<CoreLabel> classifier=null;
	public NerUtil(){
		super();
		try {
			if  (classifier==null)
			classifier = CRFClassifier.getClassifier("spanish.ancora.distsim.s512.crf.ser.gz");
		} catch (ClassCastException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getKeywords(String name){
		
		StringBuilder r=new StringBuilder();
		try{
                    
                     
                    //System.out.println(classifier.classifyToString(name));   
                    System.out.println(classifier.classifyWithInlineXML(name));
                   
		for (List<CoreLabel> lcl : classifier.classify(name)) {
	          for (CoreLabel cl : lcl) {
	        	  //System.out.println(cl.value()+" "+getType(cl));
                      String type=getType(cl);
	            	 if (type.compareTo("O")!=0){  
	            		 String cad=cl.value().replaceAll("[^\\p{L}\\s]","");
	            		 if (!cad.isEmpty())
	            		 {r.append(cad);r.append(" "+type);r.append("|");}
	            	 }
	          }
	            
		}
		
		//get number
		StringTokenizer st = new StringTokenizer(name);
	     while (st.hasMoreTokens()) {
	         String token=st.nextToken();
	         if (token.matches(".*\\d.*") && !token.isEmpty())  
	        	 {r.append(token);r.append("|");}
	     }
		
		}catch(Exception e){
			
		}
		
		return r.toString();
	}
	
    public String getXmlClasiffier(String name){
		
		return classifier.classifyWithInlineXML(name);
	}
    
    private static String getType(CoreLabel cl){
		 Object[] key=cl.keySet().toArray();
	      Class c=(Class)key[key.length-1];
	  	  Object o=cl.get(c);
	  	  String result="";
	  	  if (o!=null) result=o.toString();
		  return  result;
	
	}
	
   
    public static void main(String[] args){
    
        NerUtil util=new NerUtil();
        
        String key=util.getKeywords("Internacionalización, integración, innovación e implicación. Son los cuatro i-motores que los expertos reunidos en el XVIII Encuentro de Alimentación y Bebidas, organizado por IESE y Deloitte, identificaron como palancas de crecimiento de la industria. Aspectos que conviene reforzar, siguiendo el ejemplo de las empresas más exitosas, para apuntalar el crecimiento de un sector que reivindica su potencial para reactivar la economía.\n" +
"\n" +
"En la celebración del Encuentro se concedió el Food & Beverage Global Award 2014 a José Antônio do Prado Fay, por su trayectoria al frente de empresas como BRF-Brasil Foods, S.A., Bunge Group and Perdigão, entre otras.\n" +
"\n" +
"\n" +
"Internacionalización… de verdad\n" +
"\n" +
"Durante el encuentro se presentó la nueva edición del Vademecum on Food and Beverage Markets 2014, guía práctica de exportación con el Índice de atractividad de países para alimentación y bebidas.\n" +
"\n" +
"Las empresas más internacionalizadas tienen, por definición, una visión global y hacen mucho más que vender productos a otros países. La mayoría coinciden en que la clave del éxito es la aproximación local. El gran reto es entender la cultura local y adecuar el modelo de negocio, no solo a los hábitos y preferencias del consumidor de cada país (adaptando sabores, formatos, etiquetajes, etc.), sino tratando de actuar como un insider en todo lo demás: en el diseño de las campañas de marketing y publicidad, contratando empleados autóctonos, apostando por la producción doméstica e integrando proveedores y socios locales.\n" +
"\n" +
"\"Para tener éxito, necesitas interactuar con partners locales\". Así explicaba Nuno Abrantes, miembro de la Dirección Ejecutiva y Chief Strategy & Innovation de Jerónimo Martins Group, el triunfo del primer distribuidor de alimentación portugués… en el mercado polaco. Desde 1997 apostó por ese país para realizar su primera incursión internacional, comprando las 243 tiendas que por aquel entonces tenía la cadena local Biedronka. Hoy, el 65% de su facturación corresponde a Polonia, donde lidera el sector de la distribución alimentaria con una cuota del 14%. Su estrategia ha conquistado a los consumidores polacos, que gustan de hacer la compra con frecuencia y en establecimientos cercanos: \"los formatos de proximidad y conveniencia son clave en la distribución de alimentación\".\n" +
"\n" +
"Haluk Dortluoğlu, miembro del comité ejecutivo y CFO de la cadena turca BIM Birlesik Mağazalar A.Ş., coincidió en la \"importancia de integrar partners locales y de adaptarse a la cultura local\". Con un modelo de negocio hard-discount creado a imagen y semejanza del de la alemana Aldi, inició sus actividades en 1995 con 21 establecimientos en Turquía. Hoy ya son más de 4.000, a los que hay que sumar las 164 tiendas que tiene en Marruecos y las 35 que abrió el año pasado en Egipto, el último país en el que ha desembarcado. Con una facturación anual que supera los 4.100 millones de euros, BIM lleva varios instalada en el ranking mundial de los retailers con un crecimiento más rápido que elabora la consultora Deloitte: en los últimos cinco ejercicios, sus ventas han crecido a un promedio anual del 27%.\n" +
"\n" +
"Otro elemento común a las empresas que han sabido internacionalizarse ha sido la correcta elección de la oportunidad de mercado. Todas las empresas tratan de elegir, como es lógico, destinos que combinen un buen potencial de crecimiento con un cierto nivel de estabilidad política y seguridad jurídica. Es más que probable que los grandes operadores mundiales del sector ya hayan tomado posiciones antes y eso también hay que tenerlo en cuenta, ya que en los mercados más atractivos, la competencia puede ser mayor.\n" +
"\n" +
"La internacionalización empresarial, en palabras de Julio López Castaño, director general de la división internacional de Osborne, requiere de una flexibilidad \"tremenda\": \"No hay una forma de hacer las cosas. Ni siquiera hay una forma de hacer las cosas en un mercado\". No hay dos consumidores iguales, y la distribución también es distinta en cada país.\" Lo importante es que sea una \"decisión estratégica de la empresa\".\n" +
"\n" +
"Además, Manuel Calvo, Consejero Delegado del Grupo Calvo recomienda iniciar el proceso de internacionalización partiendo de una situación de fortaleza en el mercado doméstico, porque \"si no tienes una compañía que funcione bien en tu propio mercado es difícil que puedas escalar en otros\".\n" +
"\n" +
"\n" +
"Las oportunidades de la integración\n" +
"\n" +
"Según los datos del Ministerio de Agricultura, en España operan 3.844 cooperativas agroalimentarias que en 2012 facturaron conjuntamente 25.732 millones de euros. En opinión de Eduardo Baamonde, Director General de Cooperativas Agro-alimentarias de España\"somos muchos a vender y pocos a comprar\". La excesiva atomización del sector primario, contrasta y dificulta la relación con un sector de la distribución que cada vez tiende más a la concentración. La solución pasa por la integración del sector cooperativo, que necesita ganar tamaño para mejorar la eficiencia y también para aumentar su poder de negociación. De ahí la importancia del Plan de Integración de Cooperativas puesto en marcha por el Ministerio de Agricultura.\n" +
"\n" +
"Francisco Borrás, subdirector general y director comercial de Anecoop, destacó el papel que juegan las cooperativas de segundo grado en esta labor de integración y subrayó los éxitos conseguidos por esta \"cooperativa de cooperativas\" que agrupa a 72 socios. En la última campaña (2012/2013), Anecoop aumentó su facturación en un 16,7%, hasta los 593,4 millones de euros, de los que casi el 90% fueron exportaciones.\n" +
"\n" +
"Josep Tejedo, Director General de Mercabarna, puso como ejemplo de integración para la obtención de beneficios comunes la colaboración público-privada que se está llevando a cabo entre el primer mercado mayorista de España y las empresas que operan en el recinto. Una cooperación que se ha vehiculado a través del Clúster Alimentari Barcelona y que ya está dando frutos en áreas cruciales como la internacionalización y el impulso a la innovación. Por ejemplo, la puesta en marcha de la primera ruta de transporte marítimo entre Argelia y Barcelona. Oportunidades y facilidades logísticas que ya están aprovechando empresas como Cultivar. Una empresa de distribución de fruta y verdura \"que ha pasado de ser 100% importadora a realizar un 20% de sus ventas en el exterior\", según explicó Pedro Sitjar, Director General de esta empresa ubicada en Mercabarna.\n" +
"\n" +
"\n" +
"Innovación: foco, alianzas e imaginación\n" +
"\n" +
"A las empresas se les reclama con insistencia que apuesten por la innovación. Pero lo cierto es que las estadísticas pueden frustrar a más de uno, dado el enorme porcentaje de innovaciones que fracasan en el mercado. Así se expresaba Pablo Aguilar, director de estrategia e innovación de calidad Pascual, que apostó por \"una nueva forma de innovar\" y por buscar vías que permitan proteger la innovación y hacerla más sostenible. El reciente desarrollo de DiaBalance, fruto de la alianza con la farmaceútica Esteve, ofrece productos y servicios creados única y exclusivamente para las necesidades de personas con diabetes y su entorno. \"Vimos una oportunidad de negocio a medio camino entre el sector del gran consumo y el farmacéutico.\"\n" +
"\n" +
"Jordi Gallés, presidente ejecutivo de Europastry remarcó la importancia de dotar a la innovación de los recursos necesarios: \"no hay innovación si no hay una inversión detrás\". Ellos llevan invertidos 115 millones de euros en innovaciones tecnológicas y tienen claro que \"la clave es la ejecución en el día a día\" añadió.\n" +
"\n" +
"Rosa Madrid, directora general de estrategia y desarrollo corporativo del Grupo Restalia, puso el acento en la necesidad de llevar a cabo una innovación enfocada que permita ser replicada en cualquier ciudad del planeta \"Nosotros innovamos desde la calle\". En el caso de este grupo de restauración (propietario de las cadenas 100 Montaditos, Cervecería La Sureña y TGB-The Good Burger), el foco se ha puesto en dos criterios básicos: rentabilidad y crecimiento.\n" +
"\n" +
"Tener una mentalidad abierta y ser capaz de cuestionar el statu quo es clave también para innovar. Fernando Valdés, CEO de Campofrío Food Group, explicó la importancia de desarrollar una cultura de la innovación en la empresa a base de romper falsos mitos como \"todo está inventado\", \"no existen necesidades sin cubrir\" o el \"el mercado cárnico es local\". Su apuesta por la comunicación emocional ha roto esquemas y ha desafiado los cánones del mercado publicitario.\n" +
"\n" +
"\n" +
"La hora de colaborar e implicarse\n" +
"\n" +
"El presidente de la FIAB, Pedro Astals, reivindicó la importancia tanto en cifras como a nivel estratégico, de la industria de alimentación y bebidas. No solo porque es el primer sector industrial de la economía española, con unas ventas netas de 91.900 millones de euros en 2013, cerca de medio millón de empleos y una balanza comercial positiva que roza los 3.500 millones de euros. Sino también como \"sector bisagra\" y nexo de conexión entre la agricultura y la distribución.\n" +
"\n" +
"Las líneas maestras del recientemente presentado Marco estratégico de la industria de alimentación y bebidas, con el que se pretende mantener un crecimiento sostenible en ventas de un 4% anual, alcanzando los 115.000 millones de euros en 2020, y crear 60.000 puestos de trabajo en los próximos seis años, son: eficiencia, creación de valor, internacionalización y dinamización para ganar tamaño.");
        System.out.println(key);
    }

}
