package ejb;

import PoliTweetsCL.Core.BD.MongoDBController;
import PoliTweetsCL.Core.Model.Tweet;
import PoliTweetsCL.TextAPI.TextIndex;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class CronServiceEJB {

    private MongoDBController mongo = new MongoDBController("admin","DigitalOceanServer");

    @Inject
    private TextIndex textIndex;

    //@Schedule(persistent=false) // Todos los dias a las 00:00
    public void doCRON(){
        doIndexation();
        doMetricasPoliticos();
        doMetricasPartidos();
        doMetricasConglomerados();
    }

    private void doIndexation(){
        // obtener tweets no indexados
        Tweet[] tweets = mongo.getTextUnindexedTweets(true);

        // indexar en lucene
        int tweetsIndexados = textIndex.crearIndice(tweets);
    }

    private void doMetricasPoliticos(){
        // para cada politico
            // seleccionar las keyword de politico,
            String[] keywords = new String[0];

            // hacer busqueda
            int hits = textIndex.buscarKeywords(keywords);

            // obtener resultados de la busqueda anterior
            int positiveCount = textIndex.getPositiveCount();
            int negativeCount = textIndex.getNegativeCount();
            int neutralCount = textIndex.getNeutralCount();

            // guardar metrica en BD
        // fin foreach

    }

    private void doMetricasPartidos(){
        // para cada politico
            // seleccionar las keyword de partido,
            String[] keywords = new String[0];

            // hacer busqueda
            int hits = textIndex.buscarKeywords(keywords);

            // obtener resultados de la busqueda anterior
            int positiveCount = textIndex.getPositiveCount();
            int negativeCount = textIndex.getNegativeCount();
            int neutralCount = textIndex.getNeutralCount();

            // Sumar metricas de politicos
            positiveCount = positiveCount + (int)(0.5*positiveCount); // Asi las menciones directas al partido valen el doble que las heredadas de politicos

            // guardar metrica en BD
            // fin foreach
    }

    private void doMetricasConglomerados(){
        // para cada Conglomerado
            // seleccionar las keyword de conglomerado,
            String[] keywords = new String[0];

            // hacer busqueda
            int hits = textIndex.buscarKeywords(keywords);

            // obtener resultados de la busqueda anterior
            int positiveCount = textIndex.getPositiveCount();
            int negativeCount = textIndex.getNegativeCount();
            int neutralCount = textIndex.getNeutralCount();

            // Sumar metricas de partidos
            positiveCount = positiveCount + (int)(0.5*positiveCount); // Asi las menciones directas al partido valen el doble que las heredadas de partidos
            negativeCount = negativeCount + (int)(0.5*negativeCount);

            // guardar metrica en BD
        // fin foreach
    }



}

