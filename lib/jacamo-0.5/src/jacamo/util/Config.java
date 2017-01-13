package jacamo.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * JaCaMo configuration
 * 
 * @author jomi
 */
public class Config extends jason.jeditplugin.Config {

    private static final long  serialVersionUID = 1L;

    public static final String jacamoHomeProp = "JaCaMoHome";
    public static final String DOT_PATH       = "dotPath";

    private static Config      singleton     = null;

    public static Config get() {
        return get(true);
    }

    public static Config get(boolean tryToFixConfig) {
        jason.jeditplugin.Config.setClassFactory(Config.class.getName());
        if (singleton == null) {
            singleton = new Config();
            if (!singleton.load()) {
                if (tryToFixConfig) {
                    singleton.fix();
                    singleton.store();
                }
            }
        }
        return singleton;
    }
    

    public Config() {
        super();
    }

    @Override
    public InputStream getDetaultResource(String templateName) throws IOException {
        return Config.class.getResource("/templates/"+templateName).openStream();
    }

    @Override
    protected String getHome() {
        return getJaCaMoHome();
    }
    
    /** returns the file where the user preferences are stored */
    public File getUserConfFile() {
        return new File(System.getProperties().get("user.home") + File.separator + ".jacamo/user.properties");
    }
    
    public File getMasterConfFile() {
        return new File("jacamo.properties");
    }
    
    public String getFileConfComment() {
        return "JaCaMo user configuration";
    }

    public String getDotPath() {
        String r = super.getProperty(DOT_PATH);
        if (r == null)
            r = "/opt/local/bin/dot";
        File f = new File(r);
        if (f.exists())
            return r;
        else
            return null;
    }
    
    /** returns the jacamo home (based on jacamo.jar) */
    public String getJaCaMoHome() {
        try {
            return new File(getJasonJar()).getParentFile().getParent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /** Set most important parameters with default values */
    public void fix() {
        super.fix();
        put("version", getJaCaMoRunningVersion());
    }

    public String getJaCaMoRunningVersion() {
        return super.getJasonRunningVersion();
    }

    public static void main(String[] args) {
        Config.get().fix();
        Config.get().store();
    }
}
