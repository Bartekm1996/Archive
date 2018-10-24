/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonResultParser;

import DatabaseController.ResultDB;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Colum
 */
public class ResultParser {

    public ResultParser() {
       
    }
    public void parse(String lineFromFile) {
         String[] archive;
        archive=lineFromFile.split(",");
        ResultDB update= new ResultDB();
        update.setState(archive[0]);
    }
}
