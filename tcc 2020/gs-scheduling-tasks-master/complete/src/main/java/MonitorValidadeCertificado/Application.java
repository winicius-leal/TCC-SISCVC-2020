package MonitorValidadeCertificado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication //importa as libs de configuração do spring boot
@EnableScheduling //garante que um executor de tarefa em segundo plano seja criado
public class Application { //classe de configuração

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class); //roda a classe de configuração "application"
    }
}
