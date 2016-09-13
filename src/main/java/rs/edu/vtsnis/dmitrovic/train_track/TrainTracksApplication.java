package rs.edu.vtsnis.dmitrovic.train_track;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.hibernate.SessionFactory;
import rs.edu.vtsnis.dmitrovic.train_track.exception_mappers.RelatedEntityMissingExceptionMapper;
import rs.edu.vtsnis.dmitrovic.train_track.models.Line;
import rs.edu.vtsnis.dmitrovic.train_track.models.Station;
import rs.edu.vtsnis.dmitrovic.train_track.models.dao.LineDAO;
import rs.edu.vtsnis.dmitrovic.train_track.models.dao.StationDAO;
import rs.edu.vtsnis.dmitrovic.train_track.resources.LineResource;
import rs.edu.vtsnis.dmitrovic.train_track.resources.StationResource;

/**
 * Created by danijel on 9/11/16.
 */
public class TrainTracksApplication extends Application<TrainTracksConfiguration> {

    private final HibernateBundle<TrainTracksConfiguration> hibernate = new HibernateBundle<TrainTracksConfiguration>(Station.class, Line.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(TrainTracksConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(final String[] args) throws Exception {
        new TrainTracksApplication().run(args);
    }

    @Override
    public void run(final TrainTracksConfiguration trainTracksConfiguration, final Environment environment) throws Exception {
        registerExceptionMappers(environment);
        registerHibernateResources(environment);
    }

    @Override
    public String getName() {
        return "TrainTracks";
    }

    @Override
    public void initialize(Bootstrap<TrainTracksConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
        super.initialize(bootstrap);
    }

    private void registerExceptionMappers(Environment environment) {
        environment.jersey().register(new RelatedEntityMissingExceptionMapper(environment.metrics()));
    }

    private void registerHibernateResources(Environment environment) {
        SessionFactory sessionFactory = hibernate.getSessionFactory();
        StationDAO stationDao = new StationDAO(sessionFactory);
        LineDAO lineDAO = new LineDAO(sessionFactory, stationDao);
        environment.jersey().register(new StationResource(stationDao));
        environment.jersey().register(new LineResource(lineDAO));
    }
}
