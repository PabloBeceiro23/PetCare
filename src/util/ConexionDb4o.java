package util;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class ConexionDb4o {
    static final String DB_FILE = "petcare.db4o";

    public ObjectContainer abrirConexion(){

        return Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB_FILE);
    }

}
