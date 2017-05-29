package ejb;


import PoliTweetsCL.Core.BD.MongoDBController;
import PoliTweetsCL.Core.Model.Tweet;
import facade.*;
import model.*;
import resourceClasses.ConfigHelper;
import service.PoliticoService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CronEJB {
    @EJB
    private ConglomeradoFacade conglomeradoEJB;
    @EJB
    private PartidoFacade partidoEJB;
    @EJB
    private PoliticoFacade politicoEJB;
    @EJB
    private MetricaFacade metricaEJB;
    @EJB
    private ConglomeradoMetricaFacade conglomeradoMetricaEJB;
    @EJB
    private PartidoMetricaFacade partidoMetricaEJB;
    @EJB
    private PoliticoMetricaFacade politicoMetricaEJB;
    @Inject
    ConfigHelper config;

    private Date now;

    private MongoDBController mongo;

    @EJB
    private TextIndexEJB textIndex;

    Logger logger = Logger.getLogger(getClass().getName());

    @PostConstruct
    public void init() {
        // connect to mongo
        mongo = new MongoDBController(config.getPropertiesObj());
    }

    public void doIndex(){
        logger.info("Doing CRON: Indexing");
        try {
            // obtener tweets no indexados
            Tweet[] tweets = mongo.getTextUnindexedTweets(true);

            // indexar en lucene
            int tweetsIndexados = textIndex.crearIndice(tweets);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void doMetricas(){
        // Guardar el tiempo de la metrica
        now = Date.from(Instant.now().truncatedTo(ChronoUnit.HOURS));

        doIndex();

        logger.info("Doing CRON: Metricas");

        try {
            doMetricasPoliticos();
            doMetricasPartidos();
            doMetricasConglomerados();
        }catch (Exception ex){
            logger.severe("Error al crear metrica: "+ Arrays.toString(ex.getStackTrace()));
            ex.printStackTrace();
        }
    }


    private void doMetricasPoliticos() throws Exception {
        List<Politico> politicos = politicoEJB.findAll();
        // para cada Politico
        for (Politico politico:politicos) {

            logger.info("Metrica de "+politico.getApellido());

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

            logger.info("Hits: "+String.valueOf(hits));


            // obtener resultados de la busqueda anterior
            int positiveCount = textIndex.getPositiveCount();
            int negativeCount = textIndex.getNegativeCount();
            int neutralCount = textIndex.getNeutralCount();
            float aprobacion = 50 + 50 * (positiveCount-negativeCount)/(float)hits; // 50% base + (%pos - %neg)/2

            // guardar metrica en BD

            PoliticoMetrica registro = new PoliticoMetrica();
            registro.setMetrica_politico(metricaEJB.findByName("aprobacion"));
            registro.setPolitico_metrica(politico);
            registro.setFecha(now);
            registro.setValor(aprobacion);
            politicoMetricaEJB.create(registro);

            logger.info(registro.toString());

            registro = new PoliticoMetrica();
            registro.setMetrica_politico(metricaEJB.findByName("sentimientoPositivo"));
            registro.setPolitico_metrica(politico);
            registro.setFecha(now);
            registro.setValor(positiveCount/(float)hits);
            politicoMetricaEJB.create(registro);

            registro = new PoliticoMetrica();
            registro.setMetrica_politico(metricaEJB.findByName("sentimientoNegativo"));
            registro.setPolitico_metrica(politico);
            registro.setFecha(now);
            registro.setValor(negativeCount/(float)hits);
            politicoMetricaEJB.create(registro);

            registro = new PoliticoMetrica();
            registro.setMetrica_politico(metricaEJB.findByName("sentimientoNeutro"));
            registro.setPolitico_metrica(politico);
            registro.setFecha(now);
            registro.setValor(neutralCount/(float)hits);
            politicoMetricaEJB.create(registro);
        }
    }


    private void doMetricasPartidos() throws Exception {
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
            registro.setMetrica_partido(metricaEJB.findByName("aprobacion"));
            registro.setPartido_metrica(partido);
            registro.setFecha(now);
            registro.setValor(aprobacion);
            partidoMetricaEJB.create(registro);

            registro = new PartidoMetrica();
            registro.setMetrica_partido(metricaEJB.findByName("sentimientoPositivo"));
            registro.setPartido_metrica(partido);
            registro.setFecha(now);
            registro.setValor(positiveCount/(float)hits);
            partidoMetricaEJB.create(registro);

            registro = new PartidoMetrica();
            registro.setMetrica_partido(metricaEJB.findByName("sentimientoNegativo"));
            registro.setPartido_metrica(partido);
            registro.setFecha(now);
            registro.setValor(negativeCount/(float)hits);
            partidoMetricaEJB.create(registro);

            registro = new PartidoMetrica();
            registro.setMetrica_partido(metricaEJB.findByName("sentimientoNeutro"));
            registro.setPartido_metrica(partido);
            registro.setFecha(now);
            registro.setValor(neutralCount/(float)hits);
            partidoMetricaEJB.create(registro);

        }
    }

    private void doMetricasConglomerados() throws Exception {
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
            registro.setMetrica(metricaEJB.findByName("aprobacion"));
            registro.setConglomerado(congl);
            registro.setFecha(now);
            registro.setValor(aprobacion);
            conglomeradoMetricaEJB.create(registro);

            registro = new ConglomeradoMetrica();
            registro.setMetrica(metricaEJB.findByName("sentimientoPositivo"));
            registro.setConglomerado(congl);
            registro.setFecha(now);
            registro.setValor(positiveCount/(float)hits);
            conglomeradoMetricaEJB.create(registro);

            registro = new ConglomeradoMetrica();
            registro.setMetrica(metricaEJB.findByName("sentimientoNegativo"));
            registro.setConglomerado(congl);
            registro.setFecha(now);
            registro.setValor(negativeCount/(float)hits);
            conglomeradoMetricaEJB.create(registro);

            registro = new ConglomeradoMetrica();
            registro.setMetrica(metricaEJB.findByName("sentimientoNeutro"));
            registro.setConglomerado(congl);
            registro.setFecha(now);
            registro.setValor(neutralCount/(float)hits);
            conglomeradoMetricaEJB.create(registro);
        }

    }
}

