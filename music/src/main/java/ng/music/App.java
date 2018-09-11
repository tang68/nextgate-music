package ng.music;

import java.sql.Connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Bean;

import ng.music.dao.DataLoader;


@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App 
{
    public static void main( String[] args )
    {

    	DataLoader loader = new DataLoader();
    	Connection conn = loader.connect();

    	if (!loader.dataExist(conn, "ng_users"))
    		loader.importUserData(conn, "ng_users.txt");
    	
    	if(!loader.dataExist(conn, "ng_singers"))
    		loader.importSingersData(conn, "ng_singers.txt");
    	
    	if(!loader.dataExist(conn, "ng_albums"))
    		loader.importAlbumsData(conn, "ng_albums.txt");

    	SpringApplication.run(App.class, args);
    }


}


