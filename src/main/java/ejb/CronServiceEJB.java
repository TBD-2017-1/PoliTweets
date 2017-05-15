package ejb;

import PoliTweetsCL.Core.BD.MongoDBController;
import PoliTweetsCL.Core.Model.Tweet;
import PoliTweetsCL.TextAPI.TextIndex;
import model.*;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Startup
@Singleton
public class CronServiceEJB {

    private MongoDBController mongo = new MongoDBController("admin","DigitalOceanServer");

    @Inject
    private TextIndex textIndex;

    @EJB
    private ConglomeradoFacadeEJB conglomeradoEJB;
    @EJB
    private PartidoFacadeEJB partidoEJB;
    @EJB
    private PoliticoFacadeEJB politicoEJB;
    @EJB
    private MetricaFacadeEJB metricaEJB;
    @EJB
    private ConglomeradoMetricaFacadeEJB conglomeradoMetricaEJB;
    @EJB
    private PartidoMetricaFacadeEJB partidoMetricaEJB;
    @EJB
    private PoliticoMetricaFacadeEJB politicoMetricaEJB;

    private Date now;

    //@Schedule(persistent=false) // Todos los dias a las 00:00
    @Schedule(hour = "*", persistent = false) // cada hora
    public void doCRON(){
        // Guardar el tiempo de la metrica
        now = Date.from(Instant.now().truncatedTo(ChronoUnit.HOURS));

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
        List<Politico> politicos = politicoEJB.findAll();
        // para cada Politico
        for (Politico politico:politicos) {
            // seleccionar las keyword de politico
            List<Keyword> keywords = politico.getKeywords();
            List<String> kwArray = new ArrayList<>();
            for (Keyword word:keywords) {
                kwArray.add(word.getValue());
            }
            if(politico.getCuentaTwitter()!=null){
                kwArray.add(politico.getCuentaTwitter());
            }

            // hacer busqueda
            int hits = textIndex.buscarKeywords(kwArray.toArray(new String[0]));

            // obtener resultados de la busqueda anterior
            int positiveCount = textIndex.getPositiveCount();
            int negativeCount = textIndex.getNegativeCount();
            int neutralCount = textIndex.getNeutralCount();
            float aprobacion = 50 + 50 * (positiveCount-negativeCount)/(float)hits; // 50% base + (%pos - %neg)/2

            // guardar metrica en BD

            PoliticoMetrica registro = new PoliticoMetrica();
            registro.setMetrica_politico(metricaEJB.findByName("Aprobación"));
            registro.setPolitico_metrica(politico);
            registro.setFecha(now);
            registro.setValor(aprobacion);
            politicoMetricaEJB.create(registro);

            registro = new PoliticoMetrica();
            registro.setMetrica_politico(metricaEJB.findByName("Sentimiento Positivo"));
            registro.setPolitico_metrica(politico);
            registro.setFecha(now);
            registro.setValor(positiveCount/(float)hits);
            politicoMetricaEJB.create(registro);

            registro = new PoliticoMetrica();
            registro.setMetrica_politico(metricaEJB.findByName("Sentimiento Negativo"));
            registro.setPolitico_metrica(politico);
            registro.setFecha(now);
            registro.setValor(negativeCount/(float)hits);
            politicoMetricaEJB.create(registro);

            registro = new PoliticoMetrica();
            registro.setMetrica_politico(metricaEJB.findByName("Sentimiento Neutro"));
            registro.setPolitico_metrica(politico);
            registro.setFecha(now);
            registro.setValor(neutralCount/(float)hits);
            politicoMetricaEJB.create(registro);
        }
    }

    private void doMetricasPartidos(){
        List<Partido> partidos = partidoEJB.findAll();
        // para cada partido
        for (Partido partido:partidos) {
            // seleccionar las keyword de partido,
            List<Keyword> keywords = partido.getKeywords();
            List<String> kwArray = new ArrayList<>();
            for (Keyword word:keywords) {
                kwArray.add(word.getValue());
            }
            if(partido.getCuentaTwitter()!=null){
                kwArray.add(partido.getCuentaTwitter());
            }

            // hacer busqueda
            int hits = textIndex.buscarKeywords(kwArray.toArray(new String[0]));

            // obtener resultados de la busqueda anterior
            int positiveCount = textIndex.getPositiveCount();
            int negativeCount = textIndex.getNegativeCount();
            int neutralCount = textIndex.getNeutralCount();
            float aprobacion = 50 + 50 * (positiveCount-negativeCount)/(float)hits; // 50% base + (%pos - %neg)/2

            // guardar metrica en BD

            // guardar metrica en BD

            PartidoMetrica registro = new PartidoMetrica();
            registro.setMetrica_partido(metricaEJB.findByName("Aprobación"));
            registro.setPartido_metrica(partido);
            registro.setFecha(now);
            registro.setValor(aprobacion);
            partidoMetricaEJB.create(registro);

            registro = new PartidoMetrica();
            registro.setMetrica_partido(metricaEJB.findByName("Sentimiento Positivo"));
            registro.setPartido_metrica(partido);
            registro.setFecha(now);
            registro.setValor(positiveCount/(float)hits);
            partidoMetricaEJB.create(registro);

            registro = new PartidoMetrica();
            registro.setMetrica_partido(metricaEJB.findByName("Sentimiento Negativo"));
            registro.setPartido_metrica(partido);
            registro.setFecha(now);
            registro.setValor(negativeCount/(float)hits);
            partidoMetricaEJB.create(registro);

            registro = new PartidoMetrica();
            registro.setMetrica_partido(metricaEJB.findByName("Sentimiento Neutro"));
            registro.setPartido_metrica(partido);
            registro.setFecha(now);
            registro.setValor(neutralCount/(float)hits);
            partidoMetricaEJB.create(registro);

        }
    }

    private void doMetricasConglomerados(){
        List<Conglomerado> conglomerados = conglomeradoEJB.findAll();
        // para cada Conglomerado
        for (Conglomerado congl:conglomerados) {
            // seleccionar las keyword de conglomerado,
            List<Keyword> keywords = congl.getKeywords();
            List<String> kwArray = new ArrayList<>();
            for (Keyword word:keywords) {
                kwArray.add(word.getValue());
            }
            if(congl.getCuentaTwitter()!=null){
                kwArray.add(congl.getCuentaTwitter());
            }

            // hacer busqueda
            int hits = textIndex.buscarKeywords(kwArray.toArray(new String[0]));

            // obtener resultados de la busqueda anterior
            int positiveCount = textIndex.getPositiveCount();
            int negativeCount = textIndex.getNegativeCount();
            int neutralCount = textIndex.getNeutralCount();
            float aprobacion = 50 + 50 * (positiveCount-negativeCount)/(float)hits; // 50% base + (%pos - %neg)/2

            // guardar metrica en BD

            ConglomeradoMetrica registro = new ConglomeradoMetrica();
            registro.setMetrica(metricaEJB.findByName("Aprobación"));
            registro.setConglomerado(congl);
            registro.setFecha(now);
            registro.setValor(aprobacion);
            conglomeradoMetricaEJB.create(registro);

            registro = new ConglomeradoMetrica();
            registro.setMetrica(metricaEJB.findByName("Sentimiento Positivo"));
            registro.setConglomerado(congl);
            registro.setFecha(now);
            registro.setValor(positiveCount/(float)hits);
            conglomeradoMetricaEJB.create(registro);

            registro = new ConglomeradoMetrica();
            registro.setMetrica(metricaEJB.findByName("Sentimiento Negativo"));
            registro.setConglomerado(congl);
            registro.setFecha(now);
            registro.setValor(negativeCount/(float)hits);
            conglomeradoMetricaEJB.create(registro);

            registro = new ConglomeradoMetrica();
            registro.setMetrica(metricaEJB.findByName("Sentimiento Neutro"));
            registro.setConglomerado(congl);
            registro.setFecha(now);
            registro.setValor(neutralCount/(float)hits);
            conglomeradoMetricaEJB.create(registro);
        }

    }



}

