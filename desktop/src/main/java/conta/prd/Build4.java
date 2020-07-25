package conta.prd;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

// Responsável por configurar os serviços do spring
@Configuration
@EnableTransactionManagement
@ComponentScan({
// adptadores front-end javafx
        "conta.prd",
        "conta.tela",
// core do sistema
        "conta.sistema",
// adptadores real
        "conta.servicos.repositorio"})
public class Build4 {
// Build 3 - Adaptador JavaFX -> Sistema <- Adaptadores Real em Producao

    @Bean
    public DataSource dataSource() {
        var ds = new SimpleDriverDataSource();
        ds.setDriverClass(org.hsqldb.jdbcDriver.class);
        ds.setUrl("jdbc:hsqldb:file:E:/hsql/base");
        ds.setUsername("sa");
        ds.setPassword("1234");
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}